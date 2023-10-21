package com.satori.service.content.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
    @TableId(type = IdType.AUTO)
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