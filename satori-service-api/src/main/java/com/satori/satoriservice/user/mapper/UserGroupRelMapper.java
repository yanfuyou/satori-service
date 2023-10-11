package com.satori.satoriservice.user.mapper;

import com.satori.satoriservice.model.UserGroupModel;
import com.satori.satoriservice.model.UserModel;
import com.satori.satoriservice.user.entity.UserGroupRel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author yanfuyou
* @description 针对表【user_group_rel(用户组关系)】的数据库操作Mapper
* @createDate 2023-09-16 22:51:19
* @Entity com.satori.satoriservice.user.entity.UserGroupRel
*/
public interface UserGroupRelMapper extends BaseMapper<UserGroupRel> {
    List<UserGroupModel> getUserGroupList(@Param("userId") Long userId);

    List<UserModel> getGroupUsers(@Param("groupId")Long groupId);
}




