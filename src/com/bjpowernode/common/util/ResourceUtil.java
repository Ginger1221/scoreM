package com.bjpowernode.common.util;

import com.bjpowernode.common.util.ContextHolderUtils;
import com.bjpowernode.system.entity.base.UserEntity;
import com.bjpowernode.system.manager.ClientManager;
import com.bjpowernode.system.vo.Client;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ResourceUtil {

   public static String getRequestPath(HttpServletRequest request) {
      String requestPath = request.getRequestURI() + "?" + request.getQueryString();
      if(requestPath.indexOf("&") > -1) {
         requestPath = requestPath.substring(0, requestPath.indexOf("&"));
      }

      requestPath = requestPath.substring(request.getContextPath().length() + 1);
      return requestPath;
   }

   public static String getIpAddr(HttpServletRequest request) {
      String ip = request.getHeader("x-forwarded-for");
      if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
         ip = request.getHeader("Proxy-Client-IP");
      }

      if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
         ip = request.getHeader("WL-Proxy-Client-IP");
      }

      if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
         ip = request.getRemoteAddr();
      }

      if(ip.equals("0:0:0:0:0:0:0:1")) {
         ip = "本地";
      }

      return ip;
   }

   public static final UserEntity getSessionUser() {
      HttpSession session = ContextHolderUtils.getSession();
      return ClientManager.getInstance().getClient(session.getId()) != null?ClientManager.getInstance().getClient(session.getId()).getUser():null;
   }

   public static final Client getClient() {
      HttpSession session = ContextHolderUtils.getSession();
      return ClientManager.getInstance().getClient(session.getId()) != null?ClientManager.getInstance().getClient(session.getId()):null;
   }

   public static Class getActualClass(Class clazz, int index) {
      Type type = clazz.getGenericSuperclass();
      if(!(type instanceof ParameterizedType)) {
         return getActualClass(clazz.getSuperclass(), index);
      } else {
         Type[] types = ((ParameterizedType)type).getActualTypeArguments();
         return index < types.length && index >= 0?(types[index] instanceof Class?(Class)types[index]:Object.class):Object.class;
      }
   }
}
