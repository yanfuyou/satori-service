package com.satori.satoriservice.user.controller;

import cn.hutool.core.bean.BeanUtil;
import com.satori.model.model.BaseResponse;
import com.satori.satoriservice.model.request.user.GroupMemberAddRequest;
import com.satori.satoriservice.model.request.user.UserGroupRequest;
import com.satori.satoriservice.user.entity.UserGroup;
import com.satori.satoriservice.user.entity.UserGroupRel;
import com.satori.satoriservice.user.service.UserGroupRelService;
import com.satori.satoriservice.user.service.UserGroupService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auth YanFuYou
 * @date 16/09/23 下午 10:53
 */
@RestController
@RequiredArgsConstructor
public class UserGroupController {

    private final UserGroupService userGroupService;

    private final UserGroupRelService userGroupRelService;

    @ApiOperation("新建群组")
    @PostMapping("/api/user/group/new")
    public BaseResponse<Object> newUserGroup(@RequestBody UserGroupRequest request){
        UserGroup userGroup = new UserGroup();
        BeanUtil.copyProperties(request,userGroup);
        userGroupService.save(userGroup);
        return BaseResponse.success();
    }

    @ApiOperation("加入群组")
    @PostMapping("/api/user/group/member/add")
    public BaseResponse groupMemberAdd(@RequestBody GroupMemberAddRequest request){
        //校验一下是不是当前用户
        UserGroupRel userGroupRel = new UserGroupRel();
        BeanUtil.copyProperties(request,userGroupRel);
        userGroupRelService.save(userGroupRel);
        return BaseResponse.success();
    }

}
