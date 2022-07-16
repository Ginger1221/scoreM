package com.bjpowernode.system;

import com.bjpowernode.common.util.CaptchaUtil;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class CaptchaServlet extends HttpServlet {

   private static final Logger LOGGER = Logger.getLogger(CaptchaServlet.class);
   private static final long serialVersionUID = -124247581620199710L;


   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setContentType("image/jpeg");
      resp.setHeader("Pragma", "No-cache");
      resp.setHeader("Cache-Control", "no-cache");
      resp.setDateHeader("Expire", 0L);

      try {
         HttpSession e = req.getSession();
         CaptchaUtil tool = new CaptchaUtil();
         StringBuffer code = new StringBuffer();
         BufferedImage image = tool.genRandomCodeImage(code);
         e.removeAttribute("SE_KEY_MM_CODE");
         e.setAttribute("SE_KEY_MM_CODE", code.toString());
         LOGGER.debug("验证码生成");
         ImageIO.write(image, "JPEG", resp.getOutputStream());
      } catch (Exception var7) {
         var7.printStackTrace();
      }

   }

   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      this.doGet(req, resp);
   }
}
