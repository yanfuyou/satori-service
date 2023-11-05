package com.satori.service.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.satori.base.utils.Bean2Utils;
import com.satori.service.model.UserTodoModel;
import com.satori.service.user.entity.UserTodo;
import com.satori.service.user.service.UserTodoService;
import com.satori.service.user.mapper.UserTodoMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

/**
 * @author yanfuyou
 * @description 针对表【user_todo(用户代办列表)】的数据库操作Service实现
 * @createDate 2023-11-05 13:35:21
 */
@Service
public class UserTodoServiceImpl extends ServiceImpl<UserTodoMapper, UserTodo>
        implements UserTodoService {
    @Override
    public void changeState(Long id, Integer state) {
        this.baseMapper.update(null, Wrappers.lambdaUpdate(UserTodo.class)
                .set(UserTodo::getState, state)
                .eq(UserTodo::getId, id));
    }

    @Override
    public List<UserTodoModel> todoList(Long userId, LocalDateTime createTime) {
        List<UserTodo> userTodos = this.baseMapper.selectList(Wrappers.lambdaQuery(UserTodo.class)
                .eq(UserTodo::getCreateUserId, userId)
                .ne(UserTodo::getState, 2)
                .ge(UserTodo::getCreateTime, createTime.with(LocalTime.MIN))
                .le(UserTodo::getCreateTime, createTime.with(LocalTime.MAX)));
        return BeanUtil.copyToList(userTodos, UserTodoModel.class);
    }
}




