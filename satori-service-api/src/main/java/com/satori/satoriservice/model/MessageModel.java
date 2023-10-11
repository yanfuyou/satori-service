package com.satori.satoriservice.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author YanFuYou
 * @date 02/10/23 下午 10:41
 */

@Data
public class MessageModel {
    private Long id;
    private Long parentMessageId;
    private Long senderId;
    private Long receiverId;
    private Integer receiverType;
    private String messageContent;
    private LocalDateTime createTime;

}
