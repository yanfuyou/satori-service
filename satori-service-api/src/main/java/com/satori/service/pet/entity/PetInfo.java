package com.satori.service.pet.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.satori.model.enums.YesOrNoEnum;
import com.satori.service.enums.PetStateEnum;
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
    @TableId(type=IdType.AUTO)
    private Long id;

    private Long ownerId;

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
    private LocalDateTime birthday;

    /**
     * 状态
     */
    private PetStateEnum state;

    /**
     * 是否删除
     */
    private YesOrNoEnum deleted;

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