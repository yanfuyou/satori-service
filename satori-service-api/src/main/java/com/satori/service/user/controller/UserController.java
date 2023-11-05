package com.satori.service.user.controller;


import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.satori.model.enums.SystemCodeEnum;
import com.satori.model.enums.YesOrNoEnum;
import com.satori.model.model.BaseResponse;
import com.satori.service.enums.ErrorEnum;
import com.satori.service.model.UserExtModel;
import com.satori.service.model.UserModel;
import com.satori.service.model.request.user.*;
import com.satori.service.user.entity.User;
import com.satori.service.user.service.IUserService;
import com.satori.service.user.service.UserExtService;
import com.satori.service.user.service.UserTodoService;
import com.satori.service.utils.PasswordUtil;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
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

    private final UserExtService userExtService;

    @ApiOperation("注册")
    @PostMapping("/api/user/sign_up")
    public BaseResponse<Object> signUp(@RequestBody @Validated UserSignRequest request) {
        String[] profiles = applicationContext.getEnvironment().getActiveProfiles();
        String currentProfile = profiles.length == 0 ? "dev" : profiles[0];
        if (!"dev".equals(currentProfile)) {
            String flag = (String) redisson.getBucket(request.getValidationCode()).get();
            if (StrUtil.isBlank(flag)) {
                return ErrorEnum.U_VALIDATION_EXPIRED.buildResp();
            }
        }
        userService.signUp(request);
        return BaseResponse.success();
    }


    @ApiOperation("登录")
    @PostMapping("/api/user/sign_in")
    public BaseResponse<Object> signIn(@RequestBody UserSignRequest request) {
        String[] profiles = applicationContext.getEnvironment().getActiveProfiles();
        String currentProfile = profiles.length == 0 ? "dev" : profiles[0];
        if (!"dev".equals(currentProfile)) {
            if (StrUtil.isBlank(request.getValidationCode())) {
                return ErrorEnum.U_VALIDATION_BLANK.buildResp();
            }
            //redisson 不直接提供过期判断，可以使用jedis判断
            String flag = (String) redisson.getBucket(request.getValidationCode()).get();
            if (StrUtil.isBlank(flag)) {
                return ErrorEnum.U_VALIDATION_EXPIRED.buildResp();
            }
        }
        userService.signIn(request);
        return BaseResponse.success(StpUtil.getTokenInfo());
    }


    @ApiOperation("退出")
    @GetMapping("/api/user/sign_out/{userId}")
    public BaseResponse<Object> signOut(@PathVariable("userId") Long userId) {
        if (ObjectUtil.isNull(userId)) {
            return BaseResponse.fail(ErrorEnum.U_NULL_ID.getCode(), ErrorEnum.U_NULL_ID.getMsg());
        }
        StpUtil.logout(userId);
        return BaseResponse.success();
    }

    @ApiOperation("更新用户信息")
    @PostMapping("/api/user/update")
    public BaseResponse<Object> update(@RequestBody UserInfoModel request) {
        if (ObjectUtil.isNull(request.getId())) {
            return BaseResponse.fail(ErrorEnum.U_NULL_ID.getCode(), ErrorEnum.U_NULL_ID.getMsg());
        }
        User user = new User();
        BeanUtil.copyProperties(request, user);
        //重新生成密文
        if (StrUtil.isNotBlank(user.getUserPassword())) {
            Map<String, String> pwdMap = PasswordUtil.encodePassword(user.getUserPassword(), null);
            user.setUserPassword(pwdMap.get("ciphertext"));
            user.setUserSalt(pwdMap.get("salt"));
        }
        userService.updateById(user);
        return BaseResponse.success();
    }

    @ApiOperation("注销用户")
    @DeleteMapping("/api/user/del/{userId}")
    public BaseResponse<Object> del(@PathVariable("userId") Long userId) {
        if (ObjectUtil.isNull(userId)) {
            return BaseResponse.fail(ErrorEnum.U_NULL_ID.getCode(), ErrorEnum.U_NULL_ID.getMsg());
        }
        boolean b = userService.update(Wrappers.lambdaUpdate(User.class)
                .set(User::getDeleted, YesOrNoEnum.YES.getValue())
                .eq(User::getId, userId));
        if (!b) {
            return BaseResponse.fail(ErrorEnum.U_DEL_FAIL.getCode(), ErrorEnum.U_DEL_FAIL.getMsg());
        }
        return BaseResponse.success();
    }

    @ApiOperation("搜索用户")
    @PostMapping("/api/user/search/list")
    public BaseResponse<List<UserModel>> searchUsers(@RequestBody UserSearchRequest request) {
        BaseResponse<List<UserModel>> response = new BaseResponse<>();
        List<UserModel> userModels = userService.searchList(request);
        response.setData(userModels);
        return response;
    }


    @ApiOperation("获取用户信息")
    @GetMapping("/api/user/get/{id}")
    public BaseResponse<UserModel> getUserInfoById(@PathVariable("id") Long id) {
        BaseResponse<UserModel> response = new BaseResponse<>();
        if (ObjectUtil.isNull(id)) {
            response.setCode(ErrorEnum.U_NULL_ID.getCode());
            response.setErrMsg(ErrorEnum.U_NULL_ID.getMsg());
            return response;
        }
        User user = userService.getById(id);
        if (ObjectUtil.isNull(user)) {
            response.setCode(SystemCodeEnum.DATA_NOT_EXIST.getCode());
            response.setErrMsg(SystemCodeEnum.DATA_NOT_EXIST.getDesc());
            return response;
        }
        UserModel model = new UserModel();
        BeanUtil.copyProperties(user, model);
        response.setData(model);
        return response;
    }

    @ApiOperation("分页搜索用户")
    @PostMapping("/api/user/search/page")
    public BaseResponse<Page<UserModel>> searchPage(@RequestBody @Validated PageFriendRequest request) {
        BaseResponse<Page<UserModel>> response = new BaseResponse<>();
        Page<UserModel> modelPage = userService.pageList(request);
        response.setData(modelPage);
        return response;
    }

    @ApiOperation("保存用户扩展信息")
    @PostMapping("/api/user/ext/save_update")
    public BaseResponse<Object> saveExt(@RequestBody @Validated UserExtModel request) {
        try {
            userExtService.saveUserExt(request);
        } catch (DuplicateKeyException e) {
            return SystemCodeEnum.GET_LOCK_FAIL.buildResp();
        }
        return BaseResponse.success();
    }

    @ApiOperation("获取用户扩展信息")
    @GetMapping("/api/user/ext/get/{id}")
    public BaseResponse<UserExtModel> userExtGet(@PathVariable("id") Long id){
        return BaseResponse.success(userExtService.userExtGet(id));
    }
}

