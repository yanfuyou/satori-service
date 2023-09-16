package com.satori.satoriservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.satori.satoriservice.message.entity.UserMessage;
import com.satori.satoriservice.message.service.UserMessageService;
import com.satori.satoriservice.message.mapper.UserMessageMapper;
import org.springframework.stereotype.Service;

/**
* @author yanfuyou
* @description 针对表【user_message】的数据库操作Service实现
* @createDate 2023-09-16 22:52:09
*/
@Service
public class UserMessageServiceImpl extends ServiceImpl<UserMessageMapper, UserMessage>
    implements UserMessageService{

}




