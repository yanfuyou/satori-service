package com.satori.satoriservice.user.controller;


import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.satori.model.enums.SystemCodeEnum;
import com.satori.model.enums.YesOrNoEnum;
import com.satori.model.model.BaseResponse;
import com.satori.satoriservice.enums.ErrorEnum;
import com.satori.satoriservice.model.request.user.UserInfoModel;
import com.satori.satoriservice.model.request.user.UserSignRequest;
import com.satori.satoriservice.user.entity.User;
import com.satori.satoriservice.user.service.IUserService;
import com.satori.satoriservice.utils.PasswordUtil;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.*;

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
        if (StrUtil.isBlank(request.getValidationCode())){
            return BaseResponse.fail(ErrorEnum.U_VALIDATION_BLANK.getCode(),ErrorEnum.U_VALIDATION_BLANK.getMsg());
        }
        //redis不直接提供过期判断，可以使用jedis判断
        String flag = (String) redisson.getBucket(request.getValidationCode()).get();
        if (StrUtil.isBlank(flag)){
            return BaseResponse.fail(ErrorEnum.U_VALIDATION_EXPIRED.getCode(),ErrorEnum.U_VALIDATION_EXPIRED.getMsg());
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
}

