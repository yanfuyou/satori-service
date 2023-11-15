package com.satori.service.pet.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 宠物
 * @TableName pet_deeds
 */
@Data
public class PetDeeds implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 宠物id
     */
    private Long petId;

    /**
     * 发生时间
     */
    private LocalDateTime occTime;

    /**
     * 图片,多张[,]分割
     */
    private String pics;

    /**
     * 事件描述
     */
    private String description;

    /**
     * 查看次数
     */
    private Integer readCount;

    /**
     * 状态 0-初始化,1-已解决
     */
    private Integer state;

    /**
     * 是否删除
     */
    private Integer deleted;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}