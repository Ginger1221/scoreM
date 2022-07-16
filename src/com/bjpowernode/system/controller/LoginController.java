package com.bjpowernode.system.controller;

import com.bjpowernode.common.util.AjaxJson;
import com.bjpowernode.common.util.ContextHolderUtils;
import com.bjpowernode.common.util.ResourceUtil;
import com.bjpowernode.system.entity.base.ResourceEntity;
import com.bjpowernode.system.entity.base.RoleEntity;
import com.bjpowernode.system.entity.base.UserEntity;
import com.bjpowernode.system.manager.ClientManager;
import com.bjpowernode.system.service.SystemService;
import com.bjpowernode.system.vo.Client;
import com.bjpowernode.system.vo.TreeNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping({"/loginController"})
public class LoginController {

   private static final Logger logger = Logger.getLogger(LoginController.class);
   @Autowired
   private SystemService systemService;


   @RequestMapping(
      params = {"login"}
   )
   public ModelAndView login(HttpServletRequest request) {
      return new ModelAndView("system/login");
   }

   @RequestMapping(
      params = {"home"}
   )
   public ModelAndView home(HttpServletRequest request) {
      return new ModelAndView("system/home");
   }

   @RequestMapping(
      params = {"logout"}
   )
   public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
      HttpSession session = ContextHolderUtils.getSession();
      ClientManager.getInstance().removeClinet(session.getId());
      session.invalidate();
      ModelAndView modelAndView = new ModelAndView(new RedirectView("loginController.do?login"));
      return modelAndView;
   }

   @RequestMapping(
      params = {"doLogin"}
   )
   public ModelAndView doLogin(HttpServletRequest req) {
      ModelAndView mav = new ModelAndView("system/main");
      HttpSession session = ContextHolderUtils.getSession();
      Client client = ClientManager.getInstance().getClient(session.getId());
      req.setAttribute("username", client.getUser().getUsername());
      return mav;
   }

   @RequestMapping(
      params = {"doCheck"}
   )
   @ResponseBody
   public AjaxJson doCheck(HttpServletRequest req, String username, String password, String captcha) {
      HttpSession session = ContextHolderUtils.getSession();
      AjaxJson j = new AjaxJson();
      if(!captcha.equalsIgnoreCase(String.valueOf(session.getAttribute("SE_KEY_MM_CODE")))) {
         j.setSuccess(false);
         j.setMsg("验证码错误!");
      } else {
         UserEntity user = new UserEntity();
         user.setUsername(username);
         user.setPassword(password);
         user = this.systemService.getUserByNameAndPassword(user);
         if(user == null) {
            j.setSuccess(false);
            j.setMsg("用户名或密码错误！");
            return j;
         }

         ArrayList resourceList = new ArrayList();
         List roleList = user.getRoles();
         Iterator var11 = roleList.iterator();

         while(var11.hasNext()) {
            RoleEntity client = (RoleEntity)var11.next();
            List tempRes = client.getResource();
            Iterator var14 = tempRes.iterator();

            while(var14.hasNext()) {
               ResourceEntity res = (ResourceEntity)var14.next();
               if(!resourceList.contains(res)) {
                  resourceList.add(res);
               }
            }
         }

         Client client1 = new Client();
         client1.setIp(ResourceUtil.getIpAddr(req));
         client1.setLogindatetime(new Date());
         client1.setUser(user);
         client1.setMenuList(resourceList);
         ClientManager.getInstance().addClinet(session.getId(), client1);
         if(user != null && user.getId() != null) {
            j.setSuccess(true);
            j.setMsg("登陆成功！");
         } else {
            j.setSuccess(false);
            j.setMsg("用户名或密码错误!");
         }
      }

      return j;
   }

   @RequestMapping(
      params = {"getTreeMenu"}
   )
   @ResponseBody
   public String getTreeMenu(HttpServletRequest request) {
      Client client = ResourceUtil.getClient();
      new ArrayList();
      if(client != null && client.getUser() != null) {
         List resourceList = client.getMenuList();
         ArrayList resource = new ArrayList();
         Iterator var6 = resourceList.iterator();

         while(var6.hasNext()) {
            ResourceEntity re = (ResourceEntity)var6.next();
            if(resourceList.size() <= 0) {
               break;
            }

            if("1".equals(re.getId())) {
               resource.add(re);
               break;
            }
         }

         return JSONObject.valueToString(this.resourceToTreeNode(resource, resourceList));
      } else {
         return "system/login";
      }
   }

   private List resourceToTreeNode(List resource, List userResource) {
      if(resource != null && !resource.isEmpty() && ((ResourceEntity)resource.get(0)).getResourceType().intValue() == ResourceEntity.TYPE_MENU) {
         ArrayList ch = new ArrayList();

         ResourceEntity rr;
         TreeNode node;
         for(Iterator var5 = resource.iterator(); var5.hasNext(); node.setChildren(this.resourceToTreeNode(rr.getResources(), userResource))) {
            rr = (ResourceEntity)var5.next();
            node = new TreeNode();
            if(userResource.contains(rr)) {
               if(rr.getHref() == null) {
                  node.setId(rr.getId());
               } else {
                  node.setId(rr.getId());
               }

               node.setId(rr.getId());
               node.setState("open");
               node.setText(rr.getName());
               HashMap _temp = new HashMap();
               _temp.put("href", rr.getHref());
               node.setAttributes(_temp);
               ch.add(node);
            }
         }

         return ch;
      } else {
         return Collections.emptyList();
      }
   }
}
