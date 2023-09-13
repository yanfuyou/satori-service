package com.satori.satoriservice.content;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 评论信息表
 * @TableName comment
 */
@Data
public class Comment implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 内容id
     */
    private Long contentId;

    /**
     * 评论用户id
     */
    private Long createUserId;

    /**
     * 父级id，根为0
     */
    private Long parentId;

    /**
     * 详情
     */
    private String detail;

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

    private static final long serialVersionUID = 1L;
}