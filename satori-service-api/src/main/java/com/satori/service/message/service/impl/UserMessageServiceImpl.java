package com.satori.service.message.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.satori.service.message.entity.UserMessage;
import com.satori.service.message.service.UserMessageService;
import com.satori.service.message.mapper.UserMessageMapper;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

/**
* @author yanfuyou
* @description 针对表【user_message】的数据库操作Service实现
* @createDate 2023-09-16 22:52:09
*/

@RequiredArgsConstructor
//@Service(value = "userMessageService")
@Service
public class UserMessageServiceImpl extends ServiceImpl<UserMessageMapper, UserMessage>
    implements UserMessageService{

    private final RedissonClient redissonClient;

    public void updateOnlineUsers(String userId){
        RMap<Object, Object> onlineUsers = redissonClient.getMap("onlineUsers");
        onlineUsers.put(userId,"user-" + userId);
    }
}




