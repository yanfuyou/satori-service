package com.satori.service.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.satori.model.enums.YesOrNoEnum;
import com.satori.base.utils.Bean2Utils;
import com.satori.service.model.UserModel;
import com.satori.service.model.request.user.PageFriendRequest;
import com.satori.service.model.request.user.UserSearchRequest;
import com.satori.service.user.entity.User;
import com.satori.service.user.mapper.UserMapper;
import com.satori.service.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yanfuyou
 * @since 2023-09-02 12:06:30
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Override
    public List<UserModel> searchList(UserSearchRequest request) {
        List<User> users = baseMapper.selectList(Wrappers.lambdaQuery(User.class)
                .eq(User::getDeleted, YesOrNoEnum.NO.getValue())
                .like(User::getNikeName, request.getNikeName()));
        if (CollectionUtils.isEmpty(users)) {
            return Lists.newArrayList();
        }
        return users.stream().map(user -> {
            UserModel userModel = new UserModel();
            BeanUtil.copyProperties(user, userModel);
            return userModel;
        }).toList();
    }

    @Override
    public Page<UserModel> pageList(PageFriendRequest request) {
        Page<User> page = new Page<>(request.getPage(), request.getSize(), false);
        baseMapper.selectPage(page, Wrappers.lambdaQuery(User.class)
                .eq(User::getDeleted,YesOrNoEnum.NO.getValue())
                .like(Objects.nonNull(request.getKeyword()), User::getNikeName, request.getKeyword()));
        List<UserModel> models = Bean2Utils.copyProperties(page.getRecords(), UserModel::new);
        page.getRecords().clear();
        Page<UserModel> modelPage = Bean2Utils.clone(page, Page.class);
        modelPage.setRecords(models);
        return modelPage;
    }
}
