package com.satori.satoriservice.content.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.satori.model.enums.YesOrNoEnum;
import lombok.Data;

/**
 * 
 * @TableName content_category
 */
@Data
public class ContentCategory implements Serializable {
    /**
     * 
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 类别名称
     */
    private String name;

    /**
     * 创建者id
     */
    private Long createUserId;

    /**
     * 删除标记
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