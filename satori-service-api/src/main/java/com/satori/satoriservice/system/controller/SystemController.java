package com.satori.satoriservice.system.controller;

import com.satori.model.model.BaseResponse;
import com.satori.satoriservice.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
}
