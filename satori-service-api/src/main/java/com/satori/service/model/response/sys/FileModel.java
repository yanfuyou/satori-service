package com.satori.service.model.response.sys;

import com.satori.model.enums.YesOrNoEnum;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author YanFuYou
 * @date 15/10/23 下午 10:29
 */

@Data
public class FileModel {
    /**
     *
     */
    private Long id;

    /**
     * 原始名称
     */
    private String sourceName;

    /**
     * 访问路径
     */
    private String reqUrl;

    /**
     * 上传者id
     */
    private Long createUserId;

    /**
     * 是否私有
     */
    private YesOrNoEnum privated;

    /**
     *
     */
    private LocalDateTime createTime;

    /**
     *
     */
    private LocalDateTime updateTime;

    private YesOrNoEnum deleted;

}
