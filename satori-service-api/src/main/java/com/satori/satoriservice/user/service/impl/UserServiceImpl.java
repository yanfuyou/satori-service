package com.satori.satoriservice.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import com.satori.model.enums.YesOrNoEnum;
import com.satori.satoriservice.model.UserModel;
import com.satori.satoriservice.model.request.user.UserSearchRequest;
import com.satori.satoriservice.user.entity.User;
import com.satori.satoriservice.user.mapper.UserMapper;
import com.satori.satoriservice.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yanfuyou
 * @since 2023-09-02 12:06:30
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final UserMapper userMapper;

    @Override
    public List<UserModel> searchList(UserSearchRequest request) {
        List<User> users = userMapper.selectList(Wrappers.lambdaQuery(User.class)
                .eq(User::getDeleted, YesOrNoEnum.NO.getValue())
                .like(User::getNikeName, request.getNikeName()));
        if (CollectionUtils.isEmpty(users)){
            return Lists.newArrayList();
        }
        return users.stream().map(user -> {
            UserModel userModel = new UserModel();
            BeanUtil.copyProperties(user, userModel);
            return userModel;
        }).toList();
    }
}
