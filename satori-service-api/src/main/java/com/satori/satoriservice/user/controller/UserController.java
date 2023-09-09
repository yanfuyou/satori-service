package com.satori.satoriservice.user.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.satori.model.enums.SystemCodeEnum;
import com.satori.model.model.BaseResponse;
import com.satori.satoriservice.user.entity.User;
import com.satori.satoriservice.enums.ErrorEnum;
import com.satori.satoriservice.model.request.UserSignRequest;
import com.satori.satoriservice.user.service.impl.UserServiceImpl;
import com.satori.satoriservice.utils.PasswordUtil;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Wrapper;
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

    private final UserServiceImpl userService;

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

}

