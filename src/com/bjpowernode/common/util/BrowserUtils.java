package com.bjpowernode.common.util;

import com.bjpowernode.common.util.BrowserType;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

public class BrowserUtils {

   private static final String IE11 = "rv:11.0";
   private static final String IE10 = "MSIE 10.0";
   private static final String IE9 = "MSIE 9.0";
   private static final String IE8 = "MSIE 8.0";
   private static final String IE7 = "MSIE 7.0";
   private static final String IE6 = "MSIE 6.0";
   private static final String MAXTHON = "Maxthon";
   private static final String QQ = "QQBrowser";
   private static final String GREEN = "GreenBrowser";
   private static final String SE360 = "360SE";
   private static final String FIREFOX = "Firefox";
   private static final String OPERA = "Opera";
   private static final String CHROME = "Chrome";
   private static final String SAFARI = "Safari";
   private static final String OTHER = "其它";


   public static boolean isIE(HttpServletRequest request) {
      return request.getHeader("USER-AGENT").toLowerCase().indexOf("msie") > 0 || request.getHeader("USER-AGENT").toLowerCase().indexOf("rv:11.0") > 0;
   }

   public static Double getIEversion(HttpServletRequest request) {
      Double version = Double.valueOf(0.0D);
      if(getBrowserType(request, "rv:11.0")) {
         version = Double.valueOf(11.0D);
      } else if(getBrowserType(request, "MSIE 10.0")) {
         version = Double.valueOf(10.0D);
      } else if(getBrowserType(request, "MSIE 9.0")) {
         version = Double.valueOf(9.0D);
      } else if(getBrowserType(request, "MSIE 8.0")) {
         version = Double.valueOf(8.0D);
      } else if(getBrowserType(request, "MSIE 7.0")) {
         version = Double.valueOf(7.0D);
      } else if(getBrowserType(request, "MSIE 6.0")) {
         version = Double.valueOf(6.0D);
      }

      return version;
   }

   public static BrowserType getBrowserType(HttpServletRequest request) {
      BrowserType browserType = null;
      if(getBrowserType(request, "rv:11.0")) {
         browserType = BrowserType.IE11;
      }

      if(getBrowserType(request, "MSIE 10.0")) {
         browserType = BrowserType.IE10;
      }

      if(getBrowserType(request, "MSIE 9.0")) {
         browserType = BrowserType.IE9;
      }

      if(getBrowserType(request, "MSIE 8.0")) {
         browserType = BrowserType.IE8;
      }

      if(getBrowserType(request, "MSIE 7.0")) {
         browserType = BrowserType.IE7;
      }

      if(getBrowserType(request, "MSIE 6.0")) {
         browserType = BrowserType.IE6;
      }

      if(getBrowserType(request, "Firefox")) {
         browserType = BrowserType.Firefox;
      }

      if(getBrowserType(request, "Safari")) {
         browserType = BrowserType.Safari;
      }

      if(getBrowserType(request, "Chrome")) {
         browserType = BrowserType.Chrome;
      }

      if(getBrowserType(request, "Opera")) {
         browserType = BrowserType.Opera;
      }

      if(getBrowserType(request, "Camino")) {
         browserType = BrowserType.Camino;
      }

      return browserType;
   }

   private static boolean getBrowserType(HttpServletRequest request, String brosertype) {
      return request.getHeader("USER-AGENT").toLowerCase().indexOf(brosertype) > 0;
   }

   public static String checkBrowse(HttpServletRequest request) {
      String userAgent = request.getHeader("USER-AGENT");
      return regex("Opera", userAgent)?"Opera":(regex("Chrome", userAgent)?"Chrome":(regex("Firefox", userAgent)?"Firefox":(regex("Safari", userAgent)?"Safari":(regex("360SE", userAgent)?"360SE":(regex("GreenBrowser", userAgent)?"GreenBrowser":(regex("QQBrowser", userAgent)?"QQBrowser":(regex("Maxthon", userAgent)?"Maxthon":(regex("rv:11.0", userAgent)?"rv:11.0":(regex("MSIE 10.0", userAgent)?"MSIE 10.0":(regex("MSIE 9.0", userAgent)?"MSIE 9.0":(regex("MSIE 8.0", userAgent)?"MSIE 8.0":(regex("MSIE 7.0", userAgent)?"MSIE 7.0":(regex("MSIE 6.0", userAgent)?"MSIE 6.0":"其它")))))))))))));
   }

   public static boolean regex(String regex, String str) {
      Pattern p = Pattern.compile(regex, 8);
      Matcher m = p.matcher(str);
      return m.find();
   }
}
