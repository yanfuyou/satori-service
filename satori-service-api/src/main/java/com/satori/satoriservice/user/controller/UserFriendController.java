package com.satori.satoriservice.user.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.satori.model.enums.SystemCodeEnum;
import com.satori.model.enums.YesOrNoEnum;
import com.satori.model.model.BaseResponse;
import com.satori.satoriservice.model.UserModel;
import com.satori.satoriservice.model.request.user.UserFriendAddRequest;
import com.satori.satoriservice.user.entity.UserFriendRel;
import com.satori.satoriservice.user.service.UserFriendRelService;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auth YanFuYou
 * @date 23/09/23 下午 10:37
 */

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserFriendController {

    private final UserFriendRelService userFriendRelService;

    @ApiOperation("添加好友")
    @PostMapping("/api/user/friend/add")
    public BaseResponse<Object> addFriend(@RequestBody @Validated UserFriendAddRequest request){
        try {
            UserFriendRel rel = new UserFriendRel();
            BeanUtil.copyProperties(request,rel);
            userFriendRelService.save(rel);
        }catch (Exception e){
            BaseResponse.fail(SystemCodeEnum.SYS_INTERNAL_ERR.getCode(),SystemCodeEnum.SYS_INTERNAL_ERR.getDesc());
        }
        return BaseResponse.success();
    }

    @ApiOperation("删除好友")
    @GetMapping("/api/user/friend/del")
    public BaseResponse<Object> deletedFriend(@RequestParam Long userId,@RequestParam Long userFriendId){
        userFriendRelService.update(Wrappers.lambdaUpdate(UserFriendRel.class)
                .eq(UserFriendRel::getUserId,userId)
                .eq(UserFriendRel::getUserFriendId,userFriendId)
                .set(UserFriendRel::getDeleted, YesOrNoEnum.YES.getValue()));
        return BaseResponse.success();
    }

    @ApiOperation("获取好友列表")
    @GetMapping("/api/user/friend/list")
    public BaseResponse<Object> listUserFriend(@RequestParam("userId") @NotNull(message = "用户id不能为空") Long userId){
        List<UserModel> userModels = userFriendRelService.listFriend(userId);
        return BaseResponse.success(userModels);
    }
}
