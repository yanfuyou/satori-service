package com.satori.service.model.content;

import com.satori.model.enums.YesOrNoEnum;
import lombok.Data;

/**
 * @author YanFuYou
 * @date 27/10/23 下午 07:45
 */

@Data
public class ContentModel {
    private Long id;

    private Long categoryId;

    private Long createUserId;

    private String title;

    private String detail;

    private YesOrNoEnum deleted;

    private String createTime;

    private String updateTime;
}
