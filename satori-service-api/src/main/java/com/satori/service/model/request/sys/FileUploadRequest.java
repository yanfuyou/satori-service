package com.satori.service.model.request.sys;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

/**
 * @author YanFuYou
 * @date 15/10/23 下午 10:31
 */
@Data
public class FileUploadRequest implements Serializable {

    @NotEmpty(message = "选文件呀sir")
    private List<MultipartFile> files;

    private List<String> descriptions;

    private List<String> types;

    private List<Integer> privated;

}
