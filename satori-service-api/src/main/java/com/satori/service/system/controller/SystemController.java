package com.satori.service.system.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.satori.model.enums.SystemCodeEnum;
import com.satori.model.model.BaseResponse;
import com.satori.service.model.request.sys.FileUploadRequest;
import com.satori.service.model.response.sys.FileModel;
import com.satori.service.system.entity.CustomSystemConfig;
import com.satori.service.system.service.CustomSystemConfigService;
import com.satori.service.system.service.SysFileInfoService;
import com.satori.service.utils.ImageUtils;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @auth YanFuYou
 * @date 10/09/23 上午 02:21
 */

@RestController
@RequiredArgsConstructor
public class SystemController {

    private final RedissonClient redisson;

    private final CustomSystemConfigService customSystemConfigService;

    private final SysFileInfoService sysFileInfoService;

    @GetMapping("/api/system/captcha")
    public BaseResponse<String> generateCaptcha() throws IOException {
        Map<String, Object> captchaMap = ImageUtils.getImage();

        String code = (String)captchaMap.get("code");
        BufferedImage image = (BufferedImage) captchaMap.get("img");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(image,"png",out);
        String imgBase64 = Base64.getEncoder().encodeToString(out.toByteArray());
        redisson.getBucket(code).set("1",30, TimeUnit.SECONDS);

        BaseResponse<String> response = new BaseResponse<>();
        response.setData(imgBase64);
        return response;
    }


    @ApiOperation("获取参数值")
    @GetMapping("/api/system/param/value/get")
    public BaseResponse<String> getParamValue(@RequestParam String paramName){
        BaseResponse<String> response = new BaseResponse<>();
        if (StrUtil.isBlank(paramName)){
            response.setCode(SystemCodeEnum.METHOD_ARGS_PARSING_ERR.getCode());
            response.setErrMsg(SystemCodeEnum.METHOD_ARGS_PARSING_ERR.getDesc());
            return response;
        }
        CustomSystemConfig param = customSystemConfigService.getOne(Wrappers.lambdaQuery(CustomSystemConfig.class)
                .eq(CustomSystemConfig::getParamName, paramName));
        if (ObjectUtil.isNull(param)){
            response.setCode(SystemCodeEnum.DATA_NOT_EXIST.getCode());
            response.setErrMsg(SystemCodeEnum.DATA_NOT_EXIST.getDesc());
            return response;
        }
        response.setData(param.getParamValue());
        return response;
    }

    @ApiOperation("文件上传")
    @PostMapping("/api/system/file/upload")
    public BaseResponse fileUpload(@RequestParam("files") List<MultipartFile> files,@RequestParam(name = "descs",required = false)List<String> descs,@RequestParam(name = "privated",required = false)List<Integer> privated, @RequestParam("type")String type){
        List<FileUploadRequest> request = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            FileUploadRequest req = new FileUploadRequest();
            req.setFile(files.get(i));
            req.setType(type);
            if (ObjectUtil.isNotNull(descs) && descs.size()-1 >= i) {
                req.setDescription(descs.get(i));
            }
            if (ObjectUtil.isNotNull(privated) && privated.size()-1 >= i){
                req.setPrivated(privated.get(i));
            }
            request.add(req);
        }
        List<FileModel> fileModels = sysFileInfoService.fileAdd(request);
        return BaseResponse.success(fileModels);
    }
}
