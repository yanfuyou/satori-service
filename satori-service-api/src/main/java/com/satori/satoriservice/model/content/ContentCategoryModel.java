package com.satori.satoriservice.model.content;

import lombok.Data;

/**
 * @auth YanFuYou
 * @date 10/09/23 下午 05:32
 */
@Data
public class ContentCategoryModel {

    private Long id;

    private String name;

    private Long createUserId;

    private Byte deleted;
}
