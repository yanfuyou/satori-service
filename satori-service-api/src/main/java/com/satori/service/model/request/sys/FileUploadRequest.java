package com.satori.service.model.request.sys;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @author YanFuYou
 * @date 15/10/23 下午 10:31
 */
@Data
public class FileUploadRequest implements Serializable {
    
    private MultipartFile file;
    
    private String description;

    private String type;

    private Integer privated;

}
