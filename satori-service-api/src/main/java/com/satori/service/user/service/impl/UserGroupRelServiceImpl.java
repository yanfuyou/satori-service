package com.satori.service.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.satori.service.model.UserGroupModel;
import com.satori.service.model.UserModel;
import com.satori.service.user.entity.UserGroupRel;
import com.satori.service.user.mapper.UserGroupMapper;
import com.satori.service.user.mapper.UserGroupRelMapper;
import com.satori.service.user.service.UserGroupRelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
* @author yanfuyou
* @description 针对表【user_group_rel(用户组关系)】的数据库操作Service实现
* @createDate 2023-09-16 22:51:19
*/
@Service
@RequiredArgsConstructor
public class UserGroupRelServiceImpl extends ServiceImpl<UserGroupRelMapper, UserGroupRel>
    implements UserGroupRelService{

    private final UserGroupMapper userGroupMapper;

    private final UserGroupRelMapper userGroupRelMapper;
    @Override
    public List<UserGroupModel> getUserGroupList(Long userId) {
        List<UserGroupModel> userGroupList = userGroupRelMapper.getUserGroupList(userId);
        return Optional.ofNullable(userGroupList).orElse(Lists.newArrayList());
    }

    @Override
    public List<UserModel> getGroupUsers(Long groupId) {
        return Optional.ofNullable(userGroupRelMapper.getGroupUsers(groupId)).orElse(Lists.newArrayList());
    }
}




