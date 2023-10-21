package com.satori.service.system.service;

import com.satori.service.model.request.sys.FileUploadRequest;
import com.satori.service.model.response.sys.FileModel;
import com.satori.service.system.entity.SysFileInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.IOException;
import java.util.List;

/**
* @author yanfuyou
* @description 针对表【sys_file_info(文件信息)】的数据库操作Service
* @createDate 2023-10-15 22:24:11
*/
public interface SysFileInfoService extends IService<SysFileInfo> {
    List<FileModel> fileAdd(FileUploadRequest dto) throws IOException;

    String getDomain();
}
