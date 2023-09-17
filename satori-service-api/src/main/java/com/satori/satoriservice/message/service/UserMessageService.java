package com.satori.satoriservice.message.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.satori.satoriservice.message.entity.UserMessage;

/**
* @author yanfuyou
* @description 针对表【user_message】的数据库操作Service
* @createDate 2023-09-16 22:52:09
*/
public interface UserMessageService extends IService<UserMessage> {
    public void updateOnlineUsers(String userId);
}
