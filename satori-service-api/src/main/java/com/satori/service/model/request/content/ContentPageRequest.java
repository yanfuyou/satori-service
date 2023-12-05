package com.satori.service.model.request.content;

import com.satori.model.model.BasePageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author YanFuYou
 * @date 28/10/23 下午 10:31
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class ContentPageRequest extends BasePageRequest {

    private Long createUserId;

    private String keyWord;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Long categoryId;
}
