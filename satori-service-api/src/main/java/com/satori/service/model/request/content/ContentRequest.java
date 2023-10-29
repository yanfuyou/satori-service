package com.satori.service.model.request.content;

import com.satori.model.enums.YesOrNoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * @author YanFuYou
 * @date 27/10/23 下午 07:40
 */

@Data
public class ContentRequest implements Serializable {
    private Long id;

    @NotNull(message = "类别不能为空")
    private Long categoryId;

    @NotBlank(message = "写个标题吧")
    private String title;

    @NotBlank(message = "写点内容吧")
    private String detail;

    @NotNull(message = "用户信息不能为空")
    private String createUserId;

    private YesOrNoEnum deleted;
}
