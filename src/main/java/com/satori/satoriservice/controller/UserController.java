package com.satori.satoriservice.controller;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.satori.satoriservice.entity.User;
import com.satori.satoriservice.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yanfuyou
 * @since 2023-08-26 12:51:13
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @GetMapping("/get")
    public String getUser(){
        User one = userService.getOne(Wrappers.lambdaQuery(User.class).eq(User::getId, 1));
        return JSONObject.toJSONString(Optional.of(one).orElse(new User()));
    }
}
