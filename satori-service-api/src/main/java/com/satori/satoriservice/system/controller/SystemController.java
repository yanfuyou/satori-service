package com.satori.satoriservice.system.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.satori.model.enums.SystemCodeEnum;
import com.satori.model.model.BaseResponse;
import com.satori.satoriservice.system.entity.CustomSystemConfig;
import com.satori.satoriservice.system.service.CustomSystemConfigService;
import com.satori.satoriservice.utils.ImageUtils;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Wrapper;
import java.util.Base64;
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
}
