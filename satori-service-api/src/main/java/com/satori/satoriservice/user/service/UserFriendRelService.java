package com.satori.satoriservice.user.service;

import com.satori.satoriservice.model.UserModel;
import com.satori.satoriservice.user.entity.UserFriendRel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author yanfuyou
* @description 针对表【user_friend_rel(好友列表)】的数据库操作Service
* @createDate 2023-09-23 22:36:58
*/
public interface UserFriendRelService extends IService<UserFriendRel> {
    /**
     * 获取好友列表
     * @param userId
     * @return 用户id
     */
    List<UserModel> listFriend(Long userId);
}
