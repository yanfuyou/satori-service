package com.satori.service.system.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.satori.model.enums.YesOrNoEnum;
import com.satori.model.ex.BaseException;
import com.satori.service.enums.ErrorEnum;
import com.satori.service.model.request.sys.FileUploadRequest;
import com.satori.service.model.response.sys.FileModel;
import com.satori.service.system.entity.CustomSystemConfig;
import com.satori.service.system.entity.SysFileInfo;
import com.satori.service.system.service.CustomSystemConfigService;
import com.satori.service.system.service.SysFileInfoService;
import com.satori.service.system.mapper.SysFileInfoMapper;
import com.satori.service.utils.SysUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yanfuyou
 * @description 针对表【sys_file_info(文件信息)】的数据库操作Service实现
 * @createDate 2023-10-15 22:24:11
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SysFileInfoServiceImpl extends ServiceImpl<SysFileInfoMapper, SysFileInfo>
        implements SysFileInfoService {

    @NonNull
    private final RestTemplate restTemplate;

    @NonNull
    private final CustomSystemConfigService customSystemConfigService;

    @NonNull
    private final RedissonClient redissonClient;

    @Value("${server.servlet.context-path}")
    private String contentPath;

    @Override
    public List<FileModel> fileAdd(FileUploadRequest dto) throws IOException {
        SysFileInfo sysFileInfo = saveFile(dto.getFiles().get(0),"img", 1   );
        return null;
    }


    @Override
    public String getDomain() {
        RBucket<Object> bucket = redissonClient.getBucket("domain");
        String domain = (String)bucket.get();
        if (!StrUtil.isBlank(domain)){
            return domain;
        }
        CustomSystemConfig sourceDomain = customSystemConfigService.getOne(Wrappers.lambdaQuery(CustomSystemConfig.class)
                .eq(CustomSystemConfig::getType, "domain"));
        bucket.set(sourceDomain.getParamValue());
        return sourceDomain.getParamValue();
    }


    private SysFileInfo saveFile(MultipartFile multipartFile, String type, Integer isPrivate){
        SysFileInfo sysFileInfo = new SysFileInfo();
        String domain = getDomain();
        String appPath = SysUtil.getAppPath();
        String sourceName = multipartFile.getOriginalFilename();
        sysFileInfo.setSourceName(sourceName);
        assert sourceName != null;
        String[] split = sourceName.split("\\.");
        String saveName = IdUtil.fastSimpleUUID() + "." + split[split.length - 1];
        String reqPath = domain + contentPath.replaceFirst("/","") +"/files/" + saveName;
        sysFileInfo.setReqUrl(reqPath);
        String saveDir = appPath + "/static/files";
        sysFileInfo.setLocalPath(saveDir + saveName);
        sysFileInfo.setPrivated(YesOrNoEnum.fromValue(isPrivate));

        File file = new File(saveDir, saveName);
        if (!file.getParentFile().exists()) {
            boolean mkdirs = file.getParentFile().mkdirs();
            assert mkdirs;
        }
        try {
            multipartFile.transferTo(file);
        }catch (IOException ioException){
            throw ErrorEnum.F_UPLOAD_FAIL.buildEx();
        }
        baseMapper.insert(sysFileInfo);
        return sysFileInfo;
    }
}




