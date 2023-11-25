package com.satori.service.model.pet;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author YanFuYou
 * @date 23/11/23 下午 09:56
 */

@ApiModel("宠物事迹")
@Data
public class PetDeedsModel {

    /**
     * 事迹id
     */
    private Long id;

    /**
     * 宠物id
     */
    private Long petId;

    /**
     * 事件标题
     */
    private String title;

    /**
     * 发生时间
     */
    private LocalDateTime occTime;

    /**
     * 图片
     */
    List<String> pictures;

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

}
