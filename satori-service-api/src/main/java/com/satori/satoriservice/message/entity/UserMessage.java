package com.satori.satoriservice.message.entity;

import java.io.Serializable;
import java.util.Date;
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
    private Integer receiverType;

    /**
     * 
     */
    private Integer deleted;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 消息内容
     */
    private String userMessage;

    private static final long serialVersionUID = 1L;
}