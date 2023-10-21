package com.satori.service.system.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.satori.model.enums.YesOrNoEnum;
import lombok.Data;

/**
 * 文件信息
 *
 * @TableName sys_file_info
 */
@Data
public class SysFileInfo implements Serializable {
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
     * 文件类型
     */
    private String type;

    /**
     * 上传者id
     */
    private Long createUserId;

    /**
     * 是否私有
     */
    private YesOrNoEnum privated;

    private String localPath;

    /**
     *
     */
    private LocalDateTime createTime;

    /**
     *
     */
    private LocalDateTime updateTime;

    private YesOrNoEnum deleted;

    private static final long serialVersionUID = 1L;
}