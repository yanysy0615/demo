package net.imyan.demo.security.controller;

import net.imyan.demo.security.util.VerifyCodeUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * @author yanys
 */
@RestController
@RequestMapping("/security_demo")
public class LoginController {

    @RequestMapping(method = RequestMethod.GET, path = "/verify_code")
    public void generateVerifyCode(HttpSession httpSession, HttpServletResponse httpServletResponse) {
        String verifyCode = UUID.randomUUID().toString().replace("-", "").substring(0, 4);
        BufferedImage verifyCodeImage = VerifyCodeUtil.generateImageByText(verifyCode);
        httpSession.setAttribute("verifyCode", verifyCode);
        httpServletResponse.setContentType("image/jpeg");
        try (OutputStream os = httpServletResponse.getOutputStream()) {
            ImageIO.write(verifyCodeImage, "JPEG", os);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
