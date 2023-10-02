package com.satori.satoriservice.user.service;

import com.satori.satoriservice.model.UserGroupModel;
import com.satori.satoriservice.user.entity.UserGroupRel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author yanfuyou
 * @description 针对表【user_group_rel(用户组关系)】的数据库操作Service
 * @createDate 2023-09-16 22:51:19
 */
public interface UserGroupRelService extends IService<UserGroupRel> {


    /**
     * 获取用户加入的群聊
     * @param userId 用户id
     * @return 群聊列表
     */
    List<UserGroupModel> getUserGroupList(Long userId);
}
