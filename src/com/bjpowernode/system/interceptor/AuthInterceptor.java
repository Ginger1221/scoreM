package com.bjpowernode.system.interceptor;

import com.bjpowernode.common.util.ContextHolderUtils;
import com.bjpowernode.common.util.ResourceUtil;
import com.bjpowernode.system.manager.ClientManager;
import com.bjpowernode.system.service.SystemService;
import com.bjpowernode.system.vo.Client;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

public class AuthInterceptor implements HandlerInterceptor {

   private static final Logger logger = Logger.getLogger(AuthInterceptor.class);
   private SystemService systemService;
   private List excludeUrls;


   public List getExcludeUrls() {
      return this.excludeUrls;
   }

   public void setExcludeUrls(List excludeUrls) {
      this.excludeUrls = excludeUrls;
   }

   public SystemService getSystemService() {
      return this.systemService;
   }

   @Autowired
   public void setSystemService(SystemService systemService) {
      this.systemService = systemService;
   }

   public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) throws Exception {}

   public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {}

   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
      String requestPath = ResourceUtil.getRequestPath(request);
      HttpSession session = ContextHolderUtils.getSession();
      Client client = ClientManager.getInstance().getClient(session.getId());
      if(this.excludeUrls.contains(requestPath)) {
         return true;
      } else if(client != null && client.getUser() != null) {
         return true;
      } else {
         this.forward(request, response);
         return false;
      }
   }

   @RequestMapping(
      params = {"forword"}
   )
   public ModelAndView forword(HttpServletRequest request) {
      return new ModelAndView(new RedirectView("loginController.do?login"));
   }

   private void forward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.getRequestDispatcher("pages/common/timeout.jsp").forward(request, response);
   }
}
