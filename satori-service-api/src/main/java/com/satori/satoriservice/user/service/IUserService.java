package com.satori.satoriservice.user.service;

import com.satori.satoriservice.model.UserModel;
import com.satori.satoriservice.model.request.user.UserSearchRequest;
import com.satori.satoriservice.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yanfuyou
 * @since 2023-09-02 12:06:30
 */
public interface IUserService extends IService<User> {

    /**
     * 用户查询
     * @param request 查询参数
     * @return 用户信息
     */
    List<UserModel> searchList(UserSearchRequest request);

}
