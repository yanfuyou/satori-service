package com.satori.satoriservice.message.conteoller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.google.common.collect.Lists;
import com.satori.model.model.BaseResponse;
import com.satori.satoriservice.message.entity.UserMessage;
import com.satori.satoriservice.message.service.UserMessageService;
import com.satori.satoriservice.model.MessageModel;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * @auth YanFuYou
 * @date 16/09/23 下午 11:11
 */
@RestController
@RequiredArgsConstructor
public class MessageController {

    private final UserMessageService userMessageService;

    @ApiOperation("获取历史消息")
    @GetMapping("/api/message/history/get")
    @Validated
    public BaseResponse<List<MessageModel>> getMessageHistory(@RequestParam("userId") Long userId,@RequestParam("receiverId") @NotNull(message = "id不能为空") Long receiverId, @RequestParam("receiverType") @NotNull(message = "类型不能为空") Long receiverType) {
        BaseResponse<List<MessageModel>> response = new BaseResponse<>();
        LambdaQueryWrapper<UserMessage> wrapper = new LambdaQueryWrapper<>(UserMessage.class);
        if (1 == receiverType){
            wrapper.and(w-> w.eq(UserMessage::getReceiverId, receiverId)
                    .eq(UserMessage::getReceiverType, receiverType)
                    .le(UserMessage::getCreateTime, LocalDateTime.now())
                    .eq(UserMessage::getSenderId,userId));
            wrapper.or(w ->
                    w.eq(UserMessage::getReceiverId, userId)
                    .eq(UserMessage::getReceiverType, receiverType)
                    .le(UserMessage::getCreateTime, LocalDateTime.now())
                    .eq(UserMessage::getSenderId,receiverId));
        }else {
            wrapper.eq(UserMessage::getReceiverId, receiverId)
                    .eq(UserMessage::getReceiverType, receiverType)
                    .le(UserMessage::getCreateTime, LocalDateTime.now());
        }
        wrapper.orderByDesc(UserMessage::getId)
                .last(" limit 200");
        List<UserMessage> list = userMessageService.list(wrapper);
        List<MessageModel> models = Lists.newArrayList(list.stream().map(msg -> {
            MessageModel model = new MessageModel();
            BeanUtil.copyProperties(msg, model);
            return model;
        }).toList());
        Collections.reverse(models);
        response.setData(models);
        return response;
    }
}
