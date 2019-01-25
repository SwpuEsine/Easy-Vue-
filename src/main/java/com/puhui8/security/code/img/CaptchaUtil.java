
package com.puhui8.security.code.img;

import com.puhui8.security.ValidateCode;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CaptchaUtil {
    public static final String SESSION_KEY_IMAGE_CODE = "captcha";

    public CaptchaUtil() {
    }


    public static ImageCode out() throws IOException {
       return out(130,48,5);
    }


    public static ImageCode out(int width, int height, int len) throws IOException {
       return out(width, height, len, (Font)null,30);
    }

    public static ImageCode out(int width, int height, int len, Font font,int expireIn) throws IOException {
       return outCaptcha(width, height, len, font,expireIn);
    }



    private static ImageCode outCaptcha(int width, int height, int len, Font font,int expireIn) throws IOException {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();
        Random random = new Random();

        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        if (font==null){
            font=new Font("Times New Roman", Font.ITALIC, 38);
        }
        g.setFont(font);
        g.setColor(getRandColor(150, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(250);
            int yl = random.nextInt(250);
            g.drawLine(x, y, x + xl, y + yl);
        }

        StringBuilder sRand = new StringBuilder();
        for (int i = 0; i < len; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand.append(rand);
            g.setColor(new Color(10 + random.nextInt(110), 10 + random.nextInt(110), 10 + random.nextInt(110)));
            g.drawString(rand, 18 * i + 12, 32);
        }
        g.dispose();
        return new ImageCode(image, sRand.toString(), expireIn);

    }

    private static Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    public static void clear(HttpServletRequest request) {
        request.getSession().removeAttribute("captcha");
    }

    public static void setHeader(HttpServletResponse response) {
        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
    }
}
