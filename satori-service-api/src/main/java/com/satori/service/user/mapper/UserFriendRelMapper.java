package com.satori.service.user.mapper;

import com.satori.service.model.UserModel;
import com.satori.service.user.entity.UserFriendRel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author yanfuyou
* @description 针对表【user_friend_rel(好友列表)】的数据库操作Mapper
* @createDate 2023-09-23 22:36:58
* @Entity com.satori.satoriservice.user.entity.UserFriendRel
*/
public interface UserFriendRelMapper extends BaseMapper<UserFriendRel> {
    List<UserModel> listFriend(@Param("userId") Long userId);
}




