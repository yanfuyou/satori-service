package com.satori.service.pet.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 宠物
 * @TableName pet_info
 */
@Data
public class PetInfo implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 宠物别名
     */
    private String alias;

    /**
     * 宠物描述
     */
    private String description;

    /**
     * 宠物头像
     */
    private String avatar;

    /**
     * 宠物年龄
     */
    private Integer age;

    /**
     * 宠物生日
     */
    private LocalDate birthday;

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