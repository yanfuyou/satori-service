package com.satori.service.model.request.content;

import com.satori.model.enums.YesOrNoEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author YanFuYou
 * @date 27/10/23 下午 07:40
 */

@Data
public class ContentRequest implements Serializable {
    private Long id;

    private Long categoryId;

    private String title;

    private String detail;

    private String createUserId;

    private YesOrNoEnum deleted;
}
