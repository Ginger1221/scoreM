package com.bjpowernode.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.bjpowernode.common.controller.BaseController;
import com.bjpowernode.common.util.AjaxJson;
import com.bjpowernode.common.util.Pagination;
import com.bjpowernode.system.entity.base.RoleEntity;
import com.bjpowernode.system.entity.base.UserEntity;
import com.bjpowernode.system.service.SystemService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/userController"})
public class UserController extends BaseController {

   private static final Logger logger = Logger.getLogger(UserController.class);
   @Autowired
   private SystemService systemService;


   @RequestMapping(
      params = {"goUser"}
   )
   public ModelAndView goUser(HttpServletRequest request) {
      return new ModelAndView("system/user");
   }

   @RequestMapping(
      params = {"checkRemote"}
   )
   @ResponseBody
   public void checkRemote(HttpServletRequest request, HttpServletResponse response, String signcode) throws Exception {
      UserEntity user = (UserEntity)this.systemService.findUniqueByProperty(UserEntity.class, "username", signcode);
      String flag = "true";
      if(user != null) {
         flag = "false";
      }

      response.setCharacterEncoding("utf-8");
      response.getWriter().write(flag);
   }

   @RequestMapping(
      params = {"save"}
   )
   @ResponseBody
   public AjaxJson save(HttpServletRequest request, HttpServletResponse response, UserEntity user, String roleid) throws Exception {
      AjaxJson j = new AjaxJson();
      j.setMsg("保存成功！");
      j.setSuccess(true);

      try {
         Md5Hash e = new Md5Hash(user.getPassword());
         user.setPassword(e.toHex());
         user.setRoles(this.getRoleList(roleid));
         this.systemService.save(user);
      } catch (Exception var7) {
         j.setMsg("保存失败！");
         j.setSuccess(false);
      }

      return j;
   }

   @RequestMapping(
      params = {"update"}
   )
   @ResponseBody
   public AjaxJson update(HttpServletRequest request, HttpServletResponse response, UserEntity user, String roleid) throws Exception {
      AjaxJson j = new AjaxJson();
      j.setMsg("更新成功！");
      j.setSuccess(true);

      try {
         UserEntity e = (UserEntity)this.systemService.get(UserEntity.class, user.getId());
         user.setRoles(this.getRoleList(roleid));
         user.setPassword(e.getPassword());
         user.setUsername(e.getUsername());
         BeanUtils.copyProperties(e, user);
         this.systemService.update(e);
      } catch (Exception var7) {
         j.setMsg("更新失败！");
         j.setSuccess(false);
      }

      return j;
   }

   private List getRoleList(String roleid) {
      if(roleid == null) {
         return null;
      } else {
         String[] ids = roleid.split(",");
         ArrayList roleList = new ArrayList();
         String[] var7 = ids;
         int var6 = ids.length;

         for(int var5 = 0; var5 < var6; ++var5) {
            String id = var7[var5];
            RoleEntity re = (RoleEntity)this.systemService.get(RoleEntity.class, id);
            roleList.add(re);
         }

         return roleList;
      }
   }

   @RequestMapping(
      params = {"delete"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public AjaxJson delete(HttpServletRequest request, HttpServletResponse response, String ids) throws Exception {
      AjaxJson j = new AjaxJson();
      j.setMsg("删除成功！");
      j.setSuccess(true);

      try {
         String[] var8;
         int var7 = (var8 = ids.split(",")).length;

         for(int var6 = 0; var6 < var7; ++var6) {
            String e = var8[var6];
            UserEntity user = new UserEntity();
            user.setId(e);
            this.systemService.delete(user);
         }
      } catch (Exception var10) {
         j.setMsg("删除失败！");
         j.setSuccess(false);
      }

      return j;
   }

   @RequestMapping(
      params = {"queryRole"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public AjaxJson queryRole(HttpServletRequest request, HttpServletResponse response, String id) throws Exception {
      AjaxJson j = new AjaxJson();
      j.setMsg("成功！");
      j.setSuccess(true);

      try {
         UserEntity e = (UserEntity)this.systemService.get(UserEntity.class, id);
         String roleId = "";

         RoleEntity re;
         for(Iterator var8 = e.getRoles().iterator(); var8.hasNext(); roleId = roleId + re.getId() + ",") {
            re = (RoleEntity)var8.next();
         }

         if(roleId.length() > 0) {
            roleId = roleId.substring(0, roleId.length() - 1);
         }

         j.setObj(roleId);
      } catch (Exception var9) {
         j.setMsg("失败！");
         j.setSuccess(false);
      }

      return j;
   }

   @RequestMapping(
      params = {"datagrid"}
   )
   @ResponseBody
   public void datagrid(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String page = request.getParameter("page");
      String rows = request.getParameter("rows");
      if(page == null) {
         page = "0";
      }

      if(rows == null) {
         rows = "0";
      }

      DetachedCriteria condition = DetachedCriteria.forClass(UserEntity.class);
      Pagination pagination = this.systemService.getPageData(condition, Integer.parseInt(page), Integer.parseInt(rows));
      JSONObject jobj = new JSONObject();
      jobj.put("total", Integer.valueOf(pagination.getTotalCount()));
      jobj.put("rows", pagination.getDatas());
      response.setCharacterEncoding("utf-8");
      response.getWriter().write(jobj.toString());
   }
}
