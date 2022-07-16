package com.bjpowernode.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.bjpowernode.common.controller.BaseController;
import com.bjpowernode.common.util.AjaxJson;
import com.bjpowernode.common.util.Pagination;
import com.bjpowernode.system.entity.base.ResourceEntity;
import com.bjpowernode.system.entity.base.RoleEntity;
import com.bjpowernode.system.service.SystemService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/roleController"})
public class RoleController extends BaseController {

   private static final Logger logger = Logger.getLogger(RoleController.class);
   @Autowired
   private SystemService systemService;


   @RequestMapping(
      params = {"goRole"}
   )
   public ModelAndView goRole(HttpServletRequest request) {
      return new ModelAndView("system/role");
   }

   private List getResources(String resourceids) {
      if(resourceids == null) {
         return null;
      } else {
         String[] resources = resourceids.split(",");
         ArrayList resList = new ArrayList();

         for(int i = 0; i < resources.length; ++i) {
            new ResourceEntity();
            ResourceEntity res = (ResourceEntity)this.systemService.get(ResourceEntity.class, resources[i]);
            resList.add(res);
         }

         return resList;
      }
   }

   @RequestMapping(
      params = {"save"}
   )
   @ResponseBody
   public AjaxJson save(HttpServletRequest request, HttpServletResponse response, RoleEntity role, String resourceids) throws Exception {
      AjaxJson j = new AjaxJson();
      role.setResource(this.getResources(resourceids));
      j.setMsg("保存成功！");
      j.setSuccess(true);

      try {
         this.systemService.save(role);
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
   public AjaxJson update(HttpServletRequest request, HttpServletResponse response, RoleEntity role, String resourceids) throws Exception {
      AjaxJson j = new AjaxJson();
      j.setMsg("更新成功！");
      j.setSuccess(true);

      try {
         role.setResource(this.getResources(resourceids));
         this.systemService.update(role);
      } catch (Exception var7) {
         j.setMsg("更新失败！");
         j.setSuccess(false);
      }

      return j;
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
            RoleEntity role = new RoleEntity();
            role.setId(e);
            this.systemService.delete(role);
         }
      } catch (Exception var10) {
         j.setMsg("删除失败！");
         j.setSuccess(false);
      }

      return j;
   }

   @RequestMapping(
      params = {"queryResource"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public AjaxJson queryResource(HttpServletRequest request, HttpServletResponse response, String id) throws Exception {
      AjaxJson j = new AjaxJson();
      j.setMsg("成功！");
      j.setSuccess(true);

      try {
         RoleEntity e = (RoleEntity)this.systemService.get(RoleEntity.class, id);
         String resourceId = "";
         Iterator var8 = e.getResource().iterator();

         while(var8.hasNext()) {
            ResourceEntity res = (ResourceEntity)var8.next();
            if(res.getResources() == null || res.getResources().size() == 0) {
               resourceId = resourceId + res.getId() + ",";
            }
         }

         if(resourceId.length() > 0) {
            resourceId = resourceId.substring(0, resourceId.length() - 1);
         }

         j.setObj(resourceId);
      } catch (Exception var9) {
         j.setMsg("失败！");
         j.setSuccess(false);
      }

      return j;
   }

   @RequestMapping(
      params = {"dropdown"}
   )
   @ResponseBody
   public void dropdown(HttpServletRequest request, HttpServletResponse response) throws Exception {
      DetachedCriteria condition = DetachedCriteria.forClass(RoleEntity.class);
      Pagination pagination = this.systemService.getPageData(condition, 0, 0);
      List list = pagination.getDatas();
      StringBuffer sb = new StringBuffer();
      sb.append("[");
      Iterator var8 = list.iterator();

      while(var8.hasNext()) {
         RoleEntity dropdown = (RoleEntity)var8.next();
         sb.append("{");
         sb.append("\"id\":");
         sb.append("\"");
         sb.append(dropdown.getId());
         sb.append("\"");
         sb.append(",");
         sb.append("\"text\":");
         sb.append("\"");
         sb.append(dropdown.getName());
         sb.append("\"");
         sb.append("},");
      }

      String dropdown1 = sb.substring(0, sb.length() - 1);
      dropdown1 = dropdown1 + "]";
      response.setCharacterEncoding("utf-8");
      response.getWriter().write(dropdown1);
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

      DetachedCriteria condition = DetachedCriteria.forClass(RoleEntity.class);
      Pagination pagination = this.systemService.getPageData(condition, Integer.parseInt(page), Integer.parseInt(rows));
      JSONObject jobj = new JSONObject();
      jobj.put("total", Integer.valueOf(pagination.getTotalCount()));
      jobj.put("rows", pagination.getDatas());
      response.setCharacterEncoding("utf-8");
      response.getWriter().write(jobj.toString());
   }
}
