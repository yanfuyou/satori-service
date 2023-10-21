package com.satori.service.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.satori.service.model.UserModel;
import com.satori.service.user.entity.UserFriendRel;
import com.satori.service.user.service.UserFriendRelService;
import com.satori.service.user.mapper.UserFriendRelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author yanfuyou
* @description 针对表【user_friend_rel(好友列表)】的数据库操作Service实现
* @createDate 2023-09-23 22:36:58
*/
@Service
public class UserFriendRelServiceImpl extends ServiceImpl<UserFriendRelMapper, UserFriendRel>
    implements UserFriendRelService{
    @Override
    public List<UserModel> listFriend(Long userId) {
        return this.baseMapper.listFriend(userId);
    }
}



