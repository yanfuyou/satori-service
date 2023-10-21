package com.satori.service.message.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 
 * @TableName user_message
 */
@Data
public class UserMessage implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 父消息id
     */
    private Long parentMessageId;

    /**
     * 发送者id
     */
    private Long senderId;

    /**
     * 接收者id,群或用户
     */
    private Long receiverId;

    /**
     * 接收者类型,1-用户,2-群
     */
    private Byte receiverType;

    /**
     * 
     */
    private Integer deleted;

    /**
     * 
     */
    private LocalDateTime createTime;

    /**
     * 
     */
    private LocalDateTime updateTime;

    /**
     * 消息内容
     */
    private String messageContent;

    private static final long serialVersionUID = 1L;
}