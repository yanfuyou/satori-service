package com.satori.satoriservice.controller;


import com.alibaba.fastjson2.JSONObject;
import com.satori.satoriservice.entity.User;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yanfuyou
 * @since 2023-09-01 11:07:51
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final RedisTemplate redisTemplate;

    @ApiOperation("redis set测试")
    @GetMapping("/api/test/rds/set")
    public String testRedis(){
        User user = new User();
        user.setId(1L);
        user.setNikeName("富友");
        try {
            redisTemplate.opsForValue().set("user_info-" + user.getId(), JSONObject.toJSONString(user));
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }

    @ApiOperation("redis get测试")
    @GetMapping("/api/test/rds/get")
    public String testRedisGet(){

        try {
            String userInfo = (String)redisTemplate.opsForValue().get("user_info-1");
            return userInfo;
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }
    @ApiOperation("测试")
    @GetMapping("/api/test")
    public User test(){

        try {
            String userInfo = (String)redisTemplate.opsForValue().get("user_info-1");
            return JSONObject.parseObject(userInfo,User.class);
        }catch (Exception e){
            return null;
        }
    }
}

