package com.satori.satoriservice.content.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.satori.model.enums.YesOrNoEnum;
import lombok.Data;

/**
 * 
 * @TableName content
 */
@Data
public class Content implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 类别id
     */
    private Long categoryId;

    /**
     * 详情
     */
    private String detail;

    /**
     * 创建者id
     */
    private Long createUserId;

    /**
     * 
     */
    private YesOrNoEnum deleted;

    /**
     * 
     */
    private LocalDateTime createTime;

    /**
     * 
     */
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;

}