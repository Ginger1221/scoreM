package com.bjpowernode.buss.controller;

import com.bjpowernode.buss.entity.base.StudentEntity;
import com.bjpowernode.buss.service.StudentService;
import com.bjpowernode.common.controller.BaseController;
import com.bjpowernode.common.util.AjaxJson;
import com.bjpowernode.common.util.Pagination;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.exception.ConstraintViolationException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/studentController"})
public class StudentController extends BaseController {

   private static final Logger logger = Logger.getLogger(StudentController.class);
   @Autowired
   private StudentService studentService;


   @RequestMapping(
      params = {"goStudent"}
   )
   public ModelAndView goStudent(HttpServletRequest request) {
      return new ModelAndView("buss/student");
   }

   @RequestMapping(
      params = {"checkNum"}
   )
   @ResponseBody
   public void checkNum(HttpServletRequest request, HttpServletResponse response, String studentNum) throws Exception {
      StudentEntity student = (StudentEntity)this.studentService.findUniqueByProperty(StudentEntity.class, "studentnum", studentNum);
      String flag = "true";
      if(student != null) {
         flag = "false";
      }

      response.setCharacterEncoding("utf-8");
      response.getWriter().write(flag);
   }

   @RequestMapping(
      params = {"save"}
   )
   @ResponseBody
   public AjaxJson save(HttpServletRequest request, HttpServletResponse response, StudentEntity student) throws Exception {
      AjaxJson j = new AjaxJson();
      j.setMsg("保存成功！");
      j.setSuccess(true);

      try {
         this.studentService.save(student);
      } catch (Exception var6) {
         j.setMsg("保存失败！");
         j.setSuccess(false);
      }

      return j;
   }

   @RequestMapping(
      params = {"update"}
   )
   @ResponseBody
   public AjaxJson update(HttpServletRequest request, HttpServletResponse response, StudentEntity student) throws Exception {
      AjaxJson j = new AjaxJson();
      j.setMsg("更新成功！");
      j.setSuccess(true);

      try {
         this.studentService.update(student);
      } catch (Exception var6) {
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
            StudentEntity student = (StudentEntity)this.studentService.get(StudentEntity.class, e);
            this.studentService.delete(student);
         }
      } catch (ConstraintViolationException var10) {
         var10.printStackTrace();
         j.setMsg("删除失败,存在外键引用，请查看其它数据项中是否有与当前数据有关的信息!");
         j.setSuccess(false);
      } catch (Exception var11) {
         j.setMsg("删除失败！");
         j.setSuccess(false);
      }

      return j;
   }

   @RequestMapping(
      params = {"datagrid"}
   )
   @ResponseBody
   public void datagrid(HttpServletRequest request, HttpServletResponse response, StudentEntity student) throws Exception {
      String page = request.getParameter("page");
      String rows = request.getParameter("rows");
      if(page == null) {
         page = "0";
      }

      if(rows == null) {
         rows = "0";
      }

      DetachedCriteria condition = DetachedCriteria.forClass(StudentEntity.class);
      Pagination pagination = this.studentService.findPageData(condition, student, Integer.parseInt(page), Integer.parseInt(rows));
      JSONObject jobj = new JSONObject();
      jobj.put("total", pagination.getTotalCount());
      jobj.put("rows", pagination.getDatas());
      response.setCharacterEncoding("utf-8");
      response.getWriter().write(jobj.toString());
   }
}
