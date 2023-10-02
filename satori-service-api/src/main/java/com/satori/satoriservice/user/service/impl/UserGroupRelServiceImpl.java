package com.satori.satoriservice.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.satori.satoriservice.model.UserGroupModel;
import com.satori.satoriservice.user.entity.UserGroupRel;
import com.satori.satoriservice.user.mapper.UserGroupMapper;
import com.satori.satoriservice.user.mapper.UserGroupRelMapper;
import com.satori.satoriservice.user.service.UserGroupRelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        return Optional.ofNullable(userGroupList).orElse(new ArrayList<>());
    }
}




