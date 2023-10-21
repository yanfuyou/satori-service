package com.satori.service.system.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName custom_system_config
 */
@Data
public class CustomSystemConfig implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 参数名
     */
    private String paramName;

    /**
     * 参数值
     */
    private String paramValue;

    private String type;

    private static final long serialVersionUID = 1L;
}