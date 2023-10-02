package com.satori.satoriservice.user.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.satori.model.enums.SystemCodeEnum;
import com.satori.model.enums.YesOrNoEnum;
import com.satori.model.model.BaseResponse;
import com.satori.satoriservice.enums.LockKeyEnum;
import com.satori.satoriservice.model.UserGroupModel;
import com.satori.satoriservice.model.request.user.GroupMemberAddRequest;
import com.satori.satoriservice.model.request.user.UserGroupRequest;
import com.satori.satoriservice.user.entity.UserGroup;
import com.satori.satoriservice.user.entity.UserGroupRel;
import com.satori.satoriservice.user.service.UserGroupRelService;
import com.satori.satoriservice.user.service.UserGroupService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author  YanFuYou
 * @date 16/09/23 下午 10:53
 */
@RestController
@RequiredArgsConstructor
public class UserGroupController {

    private final UserGroupService userGroupService;

    private final UserGroupRelService userGroupRelService;

    private final RedissonClient redissonClient;

    @ApiOperation("新建群组")
    @PostMapping("/api/user/group/create")
    public BaseResponse<Object> newUserGroup(@RequestBody @Validated UserGroupRequest request){
        UserGroup userGroup = new UserGroup();
        BeanUtil.copyProperties(request,userGroup);
        BaseResponse<Object> response = new BaseResponse<>();
        RLock lock = redissonClient.getLock(LockKeyEnum.CREATE_GROUP.getName());
        try {
            if (lock.tryLock()){
                userGroupService.save(userGroup);
            }else {
                response.setCode(SystemCodeEnum.GET_LOCK_FAIL.getCode());
                response.setErrMsg(SystemCodeEnum.GET_LOCK_FAIL.getDesc());
            }
        }finally {
            lock.unlock();
        }
        return BaseResponse.success();
    }

    @ApiOperation("加入群组")
    @PostMapping("/api/user/group/member/add")
    public BaseResponse<Object> groupMemberAdd(@RequestBody GroupMemberAddRequest request){
        //校验一下是不是当前用户
        UserGroupRel userGroupRel = new UserGroupRel();
        BeanUtil.copyProperties(request,userGroupRel);
        userGroupRelService.save(userGroupRel);
        return BaseResponse.success();
    }

    @ApiOperation("退出群聊")
    @GetMapping("/api/user/group/member/exit")
    public BaseResponse<Object> groupMemberExit(@RequestParam Long groupId,@RequestParam Long userId){
        userGroupRelService.update(Wrappers.lambdaUpdate(UserGroupRel.class)
                .eq(UserGroupRel::getGroupId,groupId)
                .eq(UserGroupRel::getUserId,userId)
                .set(UserGroupRel::getDeleted, YesOrNoEnum.YES.getValue()));
        return BaseResponse.success();
    }

    @ApiOperation("获取群聊列表")
    @GetMapping("/api/user/group/get/list")
    public BaseResponse<List<UserGroupModel>> getGroupList(@RequestParam Long userId){
        BaseResponse<List<UserGroupModel>> response = new BaseResponse<>();
        List<UserGroupModel> models = userGroupRelService.getUserGroupList(userId);
        response.setData(models);
        return response;
    }
}
