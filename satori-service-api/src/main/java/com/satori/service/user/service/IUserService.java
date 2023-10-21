package com.satori.service.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.satori.service.model.UserModel;
import com.satori.service.model.request.user.PageFriendRequest;
import com.satori.service.model.request.user.UserSearchRequest;
import com.satori.service.user.entity.User;

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

    /**
     * 分页查询
     * @param request 请求参数
     * @return 用户列表
     */
    Page<UserModel> pageList(PageFriendRequest request);

}
