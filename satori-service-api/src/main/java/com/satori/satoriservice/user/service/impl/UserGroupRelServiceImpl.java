package com.satori.satoriservice.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.satori.satoriservice.user.entity.UserGroupRel;
import com.satori.satoriservice.user.service.UserGroupRelService;
import com.satori.satoriservice.user.mapper.UserGroupRelMapper;
import org.springframework.stereotype.Service;

/**
* @author yanfuyou
* @description 针对表【user_group_rel(用户组关系)】的数据库操作Service实现
* @createDate 2023-09-16 22:51:19
*/
@Service
public class UserGroupRelServiceImpl extends ServiceImpl<UserGroupRelMapper, UserGroupRel>
    implements UserGroupRelService{

}




