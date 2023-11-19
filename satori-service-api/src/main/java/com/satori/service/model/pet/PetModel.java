package com.satori.service.model.pet;

import com.satori.model.enums.YesOrNoEnum;
import com.satori.service.enums.PetStateEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author YanFuYou
 * @date 15/11/23 下午 10:24
 */
@Data
public class PetModel implements Serializable {

    private Long id;

    /**
     * 主人id
     */
    @NotNull(message = "主人id不能为空")
    private Long ownerId;

    /**
     * 宠物别名
     */
    @NotBlank(message = "宠物别名不能为空")
    private String alias;

    /**
     * 宠物描述
     */
    @NotBlank(message = "宠物简介不能为空")
    private String description;

    /**
     * 宠物头像
     */
    @NotBlank(message = "宠物头像不能为空")
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
}
