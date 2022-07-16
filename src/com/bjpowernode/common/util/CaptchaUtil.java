package com.bjpowernode.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;
import javax.imageio.ImageIO;
import org.apache.log4j.Logger;

public class CaptchaUtil {

   private static final Logger LOGGER = Logger.getLogger(CaptchaUtil.class);
   private static final String RANDOM_STRS = "0123456789";
   private static final String FONT_NAME = "Fixedsys";
   private static final int FONT_SIZE = 18;
   private Random random = new Random();
   private int width = 80;
   private int height = 25;
   private int lineNum = 50;
   private int strNum = 4;


   public BufferedImage genRandomCodeImage(StringBuffer randomCode) {
      BufferedImage image = new BufferedImage(this.width, this.height, 4);
      Graphics g = image.getGraphics();
      g.setColor(this.getRandColor(200, 250));
      g.fillRect(0, 0, this.width, this.height);
      g.setColor(this.getRandColor(110, 120));

      int i;
      for(i = 0; i <= this.lineNum; ++i) {
         this.drowLine(g);
      }

      g.setFont(new Font("Fixedsys", 0, 18));

      for(i = 1; i <= this.strNum; ++i) {
         randomCode.append(this.drowString(g, i));
      }

      g.dispose();
      return image;
   }

   private Color getRandColor(int fc, int bc) {
      if(fc > 255) {
         fc = 255;
      }

      if(bc > 255) {
         bc = 255;
      }

      int r = fc + this.random.nextInt(bc - fc);
      int g = fc + this.random.nextInt(bc - fc);
      int b = fc + this.random.nextInt(bc - fc);
      return new Color(r, g, b);
   }

   private String drowString(Graphics g, int i) {
      g.setColor(new Color(this.random.nextInt(101), this.random.nextInt(111), this.random.nextInt(121)));
      String rand = String.valueOf(this.getRandomString(this.random.nextInt("0123456789".length())));
      g.translate(this.random.nextInt(3), this.random.nextInt(3));
      g.drawString(rand, 13 * i, 16);
      return rand;
   }

   private void drowLine(Graphics g) {
      int x = this.random.nextInt(this.width);
      int y = this.random.nextInt(this.height);
      int x0 = this.random.nextInt(16);
      int y0 = this.random.nextInt(16);
      g.drawLine(x, y, x + x0, y + y0);
   }

   private String getRandomString(int num) {
      return String.valueOf("0123456789".charAt(num));
   }

   public static void main(String[] args) {
      CaptchaUtil tool = new CaptchaUtil();
      StringBuffer code = new StringBuffer();
      BufferedImage image = tool.genRandomCodeImage(code);
      System.out.println(">>> random code =: " + code);

      try {
         ImageIO.write(image, "JPEG", new FileOutputStream(new File("random-code.jpg")));
      } catch (Exception var5) {
         LOGGER.info("context", var5);
      }

   }
}
