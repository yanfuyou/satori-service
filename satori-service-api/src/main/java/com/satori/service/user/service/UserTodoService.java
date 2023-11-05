package com.satori.service.user.service;

import com.satori.service.model.UserTodoModel;
import com.satori.service.user.entity.UserTodo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
* @author yanfuyou
* @description 针对表【user_todo(用户代办列表)】的数据库操作Service
* @createDate 2023-11-05 13:35:21
*/
public interface UserTodoService extends IService<UserTodo> {
    void changeState(Long id,Integer state );

    List<UserTodoModel> todoList(Long userId, LocalDateTime createTime);
}
