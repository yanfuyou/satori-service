package com.satori.satoriservice.user.service.impl;

import com.satori.satoriservice.user.entity.User;
import com.satori.satoriservice.user.mapper.UserMapper;
import com.satori.satoriservice.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yanfuyou
 * @since 2023-09-02 12:06:30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
