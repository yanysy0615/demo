package net.imyan.demo.security.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class VerifyCodeUtil {
    private static final int IMAGE_WIDTH = 80;
    private static final int IMAGE_HEIGHT = 60;
    private static final Color BACKGROUND_COLOR = new Color(200, 200, 200);

    private static Random random = new Random();

    /**
     * 生成指定验证码的图片
     * @param text 验证码字符串
     * @return 验证码图片
     */
    public static BufferedImage generateImageByText(String text) {
        BufferedImage image = createImage();
        drawText(image, text);
        drawLine(image);
        return image;
    }

    /**
     * 创建背景图
     * @return 背景图
     */
    private static BufferedImage createImage() {
        BufferedImage image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        g2.setColor(BACKGROUND_COLOR);
        g2.fillRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
        return image;
    }

    /**
     * 在背景图上绘制验证码
     * @param image 背景图
     * @param text  验证码字符串
     */
    private static void drawText(BufferedImage image, String text) {
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        StringBuilder sb = new StringBuilder();
        g2.setColor(randomColor());
        g2.setFont(randomFont());
        g2.drawString(text, 12, IMAGE_HEIGHT - 20);
    }

    /**
     * 在图上绘制干扰线
     * @param image 验证码图片
     */
    private static void drawLine(BufferedImage image) {
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        int num = 5;
        for (int i = 0; i < num; i++) {
            int x1 = random.nextInt(IMAGE_WIDTH);
            int y1 = random.nextInt(IMAGE_HEIGHT);
            int x2 = random.nextInt(IMAGE_WIDTH);
            int y2 = random.nextInt(IMAGE_HEIGHT);
            g2.setColor(randomColor());
            g2.setStroke(new BasicStroke(1.5f));
            g2.drawLine(x1, y1, x2, y2);
        }
    }

    /**
     * 生成随机颜色
     * @return 随机颜色
     */
    private static Color randomColor() {
        int red = random.nextInt(150);
        int green = random.nextInt(150);
        int blue = random.nextInt(150);
        return new Color(red, green, blue);
    }

    /**
     * 生成随机字体
     * @return 随机字体
     */
    private static Font randomFont() {
        String name = "宋体";
        int style = (random.nextBoolean()? Font.PLAIN : 0) | (random.nextBoolean()? Font.PLAIN : 0)
                | (random.nextBoolean()? Font.PLAIN : 0);
        int size = random.nextInt(5) + 24;
        return new Font(name, style, size);
    }


}
