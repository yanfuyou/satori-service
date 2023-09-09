package com.satori.satoriservice.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @auth YanFuYou
 * @date 10/09/23 上午 02:03
 */
public class ImageUtils {


    public static Map<String,Object> getImage() {
        Map<String,Object> map = new HashMap<>();
        int width = 150; // 图片宽度
        int height = 60; // 图片高度
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 获取图形上下文对象
        Graphics2D g2d = image.createGraphics();

        // 设置背景色
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);
        g2d.setClip(0, 0, width, height);

        // 设置字体
        g2d.setFont(new Font("Arial", Font.ITALIC, 30));

        // 生成随机验证码
        String captcha = generateRandomCaptcha();

        // 设置验证码颜色
        g2d.setColor(Color.BLACK);

        // 将验证码绘制到图像上
        g2d.drawString(captcha, 20, 40);

        // 添加干扰线
        drawRandomLines(g2d, 5);

        // 释放图形上下文对象
        g2d.dispose();
        map.put("img",image);
        map.put("code",captcha);
        return map;
    }

    // 生成随机验证码
    private static String generateRandomCaptcha() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder captcha = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            char randomChar = characters.charAt(random.nextInt(characters.length()));
            captcha.append(randomChar);
        }

        return captcha.toString();
    }

    // 添加随机干扰线
    private static void drawRandomLines(Graphics2D g2d, int numLines) {
        Random random = new Random();
        for (int i = 0; i < numLines; i++) {
            int x1 = random.nextInt(g2d.getClipBounds().width);
            int y1 = random.nextInt(g2d.getClipBounds().height);
            int x2 = random.nextInt(g2d.getClipBounds().width);
            int y2 = random.nextInt(g2d.getClipBounds().height);

            g2d.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
            g2d.drawLine(x1, y1, x2, y2);
        }
    }
}

