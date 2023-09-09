package com.satori.satoriservice.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.satori.model.enums.SystemCodeEnum;
import com.satori.model.model.BaseResponse;
import com.satori.satoriservice.entity.User;
import com.satori.satoriservice.enums.ErrorEnum;
import com.satori.satoriservice.model.request.UserSignRequest;
import com.satori.satoriservice.service.impl.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

        User user = new User();
        BeanUtil.copyProperties(request,user);
        try {
            userService.save(user);
        }catch (Exception e){
            return BaseResponse.fail(ErrorEnum.U_EXIST.getCode(),ErrorEnum.U_EXIST.getMsg());
        }

        return BaseResponse.success();
    }


    @GetMapping("/set")
    public String set(){
        try {

            redisson.getBucket("test").set("this is a test val!");
        }catch (Exception e){
            return "fail";
        }
        return "success";
    }

    @GetMapping("/get")
    public String get(){
        return (String) redisson.getBucket("test").get();
    }
}

