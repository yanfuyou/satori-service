package com.satori.service.model.request.user;

import com.satori.model.model.BasePageRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author YanFuYou
 * @date 13/10/23 下午 09:26
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class PageFriendRequest extends BasePageRequest {

    @ApiModelProperty("关键词")
    private String keyword;
}
