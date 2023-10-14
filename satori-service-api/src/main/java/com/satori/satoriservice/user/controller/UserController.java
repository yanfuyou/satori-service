package com.satori.satoriservice.user.controller;


import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.satori.model.enums.SystemCodeEnum;
import com.satori.model.enums.YesOrNoEnum;
import com.satori.model.model.BaseResponse;
import com.satori.satoriservice.enums.ErrorEnum;
import com.satori.satoriservice.model.UserModel;
import com.satori.satoriservice.model.request.user.PageFriendRequest;
import com.satori.satoriservice.model.request.user.UserInfoModel;
import com.satori.satoriservice.model.request.user.UserSearchRequest;
import com.satori.satoriservice.model.request.user.UserSignRequest;
import com.satori.satoriservice.user.entity.User;
import com.satori.satoriservice.user.service.IUserService;
import com.satori.satoriservice.utils.PasswordUtil;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.context.ApplicationContext;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yanfuyou
 * @since 2023-09-01 11:07:51
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final IUserService userService;

    private final RedissonClient redisson;

    private final ApplicationContext applicationContext;

    @ApiOperation("注册")
    @PostMapping("/api/user/sign_up")
    public BaseResponse<Object> signUp(@RequestBody UserSignRequest request){
        if (StrUtil.isBlank(request.getUserName()) || StrUtil.isBlank(request.getPassword())){
            return BaseResponse.fail(SystemCodeEnum.METHOD_ARGS_PARSING_ERR.getCode(),SystemCodeEnum.METHOD_ARGS_PARSING_ERR.getDesc());
        }
        User one = userService.getOne(Wrappers.lambdaQuery(User.class).eq(User::getUserName, request.getUserName()));
        if (ObjectUtil.isNotNull(one)){
            return BaseResponse.fail(ErrorEnum.U_EXIST.getCode(),ErrorEnum.U_EXIST.getMsg());
        }
        User user = new User();
        BeanUtil.copyProperties(request,user);
        try {
            Map<String, String> pwdMap = PasswordUtil.encodePassword(request.getPassword(), null);
            user.setUserPassword(pwdMap.get("ciphertext"));
            user.setUserSalt(pwdMap.get("salt"));
        }catch (Exception e){
            return BaseResponse.fail(SystemCodeEnum.SYS_INTERNAL_ERR.getCode(),SystemCodeEnum.SYS_INTERNAL_ERR.getDesc());
        }
        try {
            userService.save(user);
        }catch (Exception e){
            e.printStackTrace();
            return BaseResponse.fail(ErrorEnum.U_SIGN_UP_FAIL.getCode(),ErrorEnum.U_SIGN_UP_FAIL.getMsg());
        }
        return BaseResponse.success();
    }


    @ApiOperation("登录")
    @PostMapping("/api/user/sign_in")
    public BaseResponse<Object> signIn(@RequestBody UserSignRequest request){
        String[] profiles = applicationContext.getEnvironment().getActiveProfiles();
        String currentProfile = profiles.length == 0 ? "dev" : profiles[0];
        if (!"dev".equals(currentProfile)) {
            if (StrUtil.isBlank(request.getValidationCode())) {
                return BaseResponse.fail(ErrorEnum.U_VALIDATION_BLANK.getCode(), ErrorEnum.U_VALIDATION_BLANK.getMsg());
            }
            //redis不直接提供过期判断，可以使用jedis判断
            String flag = (String) redisson.getBucket(request.getValidationCode()).get();
            if (StrUtil.isBlank(flag)) {
                return BaseResponse.fail(ErrorEnum.U_VALIDATION_EXPIRED.getCode(), ErrorEnum.U_VALIDATION_EXPIRED.getMsg());
            }
        }
        User one = userService.getOne(Wrappers.lambdaQuery(User.class)
                .eq(User::getUserName, request.getUserName()));
        if (ObjectUtil.isNull(one)){
            return BaseResponse.fail(ErrorEnum.U_NAME_OR_PWD_ERROR.getCode(),ErrorEnum.U_NAME_OR_PWD_ERROR.getMsg());
        }
        boolean pwdCheck = PasswordUtil.checkPassword(request.getPassword(), one.getUserPassword(), one.getUserSalt());
        if (!pwdCheck){
            return BaseResponse.fail(ErrorEnum.U_NAME_OR_PWD_ERROR.getCode(),ErrorEnum.U_NAME_OR_PWD_ERROR.getMsg());
        }
        StpUtil.login(one.getId());
        return BaseResponse.success(StpUtil.getTokenInfo());
    }


    @ApiOperation("退出")
    @GetMapping("/api/user/sign_out/{userId}")
    public BaseResponse<Object> signOut(@PathVariable("userId") Long userId){
        if (ObjectUtil.isNull(userId)){
            return BaseResponse.fail(ErrorEnum.U_NULL_ID.getCode(),ErrorEnum.U_NULL_ID.getMsg());
        }
        StpUtil.logout(userId);
        return BaseResponse.success();
    }

    @ApiOperation("更新用户信息")
    @PostMapping("/api/user/update")
    public BaseResponse<Object> update(@RequestBody UserInfoModel request){
        if (ObjectUtil.isNull(request.getId())){
            return BaseResponse.fail(ErrorEnum.U_NULL_ID.getCode(),ErrorEnum.U_NULL_ID.getMsg());
        }
        User user = new User();
        BeanUtil.copyProperties(request,user);
        //重新生成密文
        if (StrUtil.isNotBlank(user.getUserPassword())){
            Map<String, String> pwdMap = PasswordUtil.encodePassword(user.getUserPassword(), null);
            user.setUserPassword(pwdMap.get("ciphertext"));
            user.setUserSalt(pwdMap.get("salt"));
        }
        userService.updateById(user);
        return BaseResponse.success();
    }

    @ApiOperation("注销用户")
    @DeleteMapping("/api/user/del/{userId}")
    public BaseResponse<Object> del(@PathVariable("userId") Long userId){
        if (ObjectUtil.isNull(userId)){
            return BaseResponse.fail(ErrorEnum.U_NULL_ID.getCode(),ErrorEnum.U_NULL_ID.getMsg());
        }
        boolean b = userService.update(Wrappers.lambdaUpdate(User.class)
                .set(User::getDeleted, YesOrNoEnum.YES.getValue())
                .eq(User::getId, userId));
        if (!b){
            return BaseResponse.fail(ErrorEnum.U_DEL_FAIL.getCode(),ErrorEnum.U_DEL_FAIL.getMsg());
        }
        return BaseResponse.success();
    }

    @ApiOperation("搜索用户")
    @PostMapping("/api/user/search/list")
    public BaseResponse<List<UserModel>> searchUsers(@RequestBody UserSearchRequest request){
        BaseResponse<List<UserModel>> response = new BaseResponse<>();
        List<UserModel> userModels = userService.searchList(request);
        response.setData(userModels);
        return response;
    }


    @ApiOperation("获取用户信息")
    @GetMapping("/api/user/get/{id}")
    public BaseResponse<UserModel> getUserInfoById(@PathVariable("id") Long id){
        BaseResponse<UserModel> response = new BaseResponse<>();
        if (ObjectUtil.isNull(id)){
            response.setCode(ErrorEnum.U_NULL_ID.getCode());
            response.setErrMsg(ErrorEnum.U_NULL_ID.getMsg());
            return response;
        }
        User user = userService.getById(id);
        if (ObjectUtil.isNull(user)){
            response.setCode(SystemCodeEnum.DATA_NOT_EXIST.getCode());
            response.setErrMsg(SystemCodeEnum.DATA_NOT_EXIST.getDesc());
            return response;
        }
        UserModel model = new UserModel();
        BeanUtil.copyProperties(user,model);
        response.setData(model);
        return response;
    }

    @ApiOperation("分页搜索用户")
    @PostMapping("/api/user/search/page")
    public BaseResponse<Page<UserModel>> searchPage(@RequestBody @Validated PageFriendRequest request){
        BaseResponse<Page<UserModel>> response = new BaseResponse<>();
        Page<UserModel> modelPage = userService.pageList(request);
        response.setData(modelPage);
        return response;
    }
}

