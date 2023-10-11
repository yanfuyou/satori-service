package com.satori.satoriservice.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.satori.satoriservice.user.entity.UserGroup;
import com.satori.satoriservice.user.mapper.UserGroupMapper;
import com.satori.satoriservice.user.service.UserGroupService;
import org.springframework.stereotype.Service;

/**
* @author yanfuyou
* @description 针对表【user_group(用户组)】的数据库操作Service实现
* @createDate 2023-09-16 22:49:06
*/
@Service
public class UserGroupServiceImpl extends ServiceImpl<UserGroupMapper, UserGroup>
    implements UserGroupService{


}




