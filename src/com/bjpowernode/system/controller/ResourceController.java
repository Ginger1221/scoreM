package com.bjpowernode.system.controller;

import com.bjpowernode.common.controller.BaseController;
import com.bjpowernode.common.util.AjaxJson;
import com.bjpowernode.common.util.Pagination;
import com.bjpowernode.system.entity.base.ResourceEntity;
import com.bjpowernode.system.service.SystemService;
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
@RequestMapping({"/resourceController"})
public class ResourceController extends BaseController {

   private static final Logger logger = Logger.getLogger(ResourceController.class);
   @Autowired
   private SystemService systemService;


   @RequestMapping(
      params = {"goResource"}
   )
   public ModelAndView goResource(HttpServletRequest request) {
      return new ModelAndView("system/resource");
   }

   @RequestMapping(
      params = {"save"}
   )
   @ResponseBody
   public AjaxJson save(HttpServletRequest request, HttpServletResponse response, ResourceEntity resource, String parentid) throws Exception {
      AjaxJson j = new AjaxJson();
      j.setMsg("保存成功！");
      j.setSuccess(true);

      try {
         ResourceEntity e = new ResourceEntity();
         e.setId(parentid);
         resource.setParentResource(e);
         this.systemService.save(resource);
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
   public AjaxJson update(HttpServletRequest request, HttpServletResponse response, ResourceEntity resource, String parentid) throws Exception {
      AjaxJson j = new AjaxJson();
      j.setMsg("更新成功！");
      j.setSuccess(true);

      try {
         ResourceEntity e = new ResourceEntity();
         e.setId(parentid);
         resource.setParentResource(e);
         this.systemService.update(resource);
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
            ResourceEntity resource = new ResourceEntity();
            resource.setId(e);
            this.systemService.delete(resource);
         }
      } catch (Exception var10) {
         j.setMsg("删除失败！");
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

      DetachedCriteria condition = DetachedCriteria.forClass(ResourceEntity.class);
      Pagination pagination = this.systemService.getPageData(condition, Integer.parseInt(page), Integer.parseInt(rows));
      List list = pagination.getDatas();
      String resourceJson = this.systemService.getTreeJson(list);
      response.setCharacterEncoding("utf-8");
      response.getWriter().write(resourceJson);
   }

   @RequestMapping(
      params = {"treeDropdown"}
   )
   @ResponseBody
   public void treeDropdown(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String page = request.getParameter("page");
      String rows = request.getParameter("rows");
      if(page == null) {
         page = "0";
      }

      if(rows == null) {
         rows = "0";
      }

      DetachedCriteria condition = DetachedCriteria.forClass(ResourceEntity.class);
      Pagination pagination = this.systemService.getPageData(condition, Integer.parseInt(page), Integer.parseInt(rows));
      List list = pagination.getDatas();
      String retJson = this.systemService.getTreeJson(list);
      String resourceJson = retJson.replaceAll("\"name\"", "\"text\"");
      response.setCharacterEncoding("utf-8");
      response.getWriter().write(resourceJson);
   }
}
