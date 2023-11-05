package com.satori.service.user.service;

import com.satori.service.model.UserExtModel;
import com.satori.service.user.entity.UserExt;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author yanfuyou
* @description 针对表【user_ext(用户扩展信息)】的数据库操作Service
* @createDate 2023-11-05 13:36:35
*/
public interface UserExtService extends IService<UserExt> {
    void saveUserExt(UserExtModel dto);


    UserExtModel userExtGet(Long id);
}
