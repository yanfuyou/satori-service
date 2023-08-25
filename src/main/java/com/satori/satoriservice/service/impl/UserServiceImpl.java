package com.satori.satoriservice.service.impl;

import com.satori.satoriservice.entity.User;
import com.satori.satoriservice.mapper.UserMapper;
import com.satori.satoriservice.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yanfuyou
 * @since 2023-08-26 12:51:13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
