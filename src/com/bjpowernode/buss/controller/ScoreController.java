package com.bjpowernode.buss.controller;

import com.bjpowernode.buss.entity.base.ScoreEntity;
import com.bjpowernode.buss.entity.base.StudentEntity;
import com.bjpowernode.buss.entity.base.TeacherEntity;
import com.bjpowernode.buss.service.ScoreService;
import com.bjpowernode.common.controller.BaseController;
import com.bjpowernode.common.util.AjaxJson;
import com.bjpowernode.common.util.Pagination;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.criterion.DetachedCriteria;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/scoreController"})
public class ScoreController extends BaseController {

   private static final Logger logger = Logger.getLogger(ScoreController.class);
   String[] excelHeader = new String[]{"学生学号", "课程名称", "分数", "学期", "班级", "教师工号", "教师姓名", "学生姓名"};
   @Autowired
   private ScoreService scoreService;


   @RequestMapping(
      params = {"goScore"}
   )
   public ModelAndView goScore(HttpServletRequest request) {
      return new ModelAndView("buss/score");
   }

   @RequestMapping(
      params = {"save"}
   )
   @ResponseBody
   public AjaxJson save(HttpServletRequest request, HttpServletResponse response, ScoreEntity scoreEntity, String studentid, String teacherid) throws Exception {
      AjaxJson j = new AjaxJson();
      j.setMsg("保存成功！");
      j.setSuccess(true);

      try {
         TeacherEntity e = (TeacherEntity)this.scoreService.get(TeacherEntity.class, teacherid);
         StudentEntity student = (StudentEntity)this.scoreService.get(StudentEntity.class, studentid);
         scoreEntity.setTeacherEntity(e);
         scoreEntity.setStudentEntity(student);
         this.scoreService.save(scoreEntity);
      } catch (Exception var9) {
         j.setMsg("保存失败！");
         j.setSuccess(false);
      }

      return j;
   }

   @RequestMapping(
      params = {"update"}
   )
   @ResponseBody
   public AjaxJson update(HttpServletRequest request, HttpServletResponse response, ScoreEntity scoreEntity, String studentid, String teacherid) throws Exception {
      AjaxJson j = new AjaxJson();
      j.setMsg("更新成功！");
      j.setSuccess(true);

      try {
         TeacherEntity e = (TeacherEntity)this.scoreService.get(TeacherEntity.class, teacherid);
         StudentEntity student = (StudentEntity)this.scoreService.get(StudentEntity.class, studentid);
         scoreEntity.setTeacherEntity(e);
         scoreEntity.setStudentEntity(student);
         this.scoreService.update(scoreEntity);
      } catch (Exception var9) {
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
            ScoreEntity scoreEntity = new ScoreEntity();
            scoreEntity.setId(e);
            this.scoreService.delete(scoreEntity);
         }
      } catch (Exception var10) {
         j.setMsg("删除失败！");
         j.setSuccess(false);
      }

      return j;
   }

   @RequestMapping(
      params = {"uploadScore"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public AjaxJson uploadScore(@RequestParam("scoreExcel") MultipartFile scoreExcel) {
      AjaxJson j = new AjaxJson();
      j.setMsg("导入成功！");
      j.setSuccess(true);

      try {
         if(!scoreExcel.isEmpty()) {
            InputStream e = scoreExcel.getInputStream();
            String[] fileName = scoreExcel.getOriginalFilename().split("\\.");
            ArrayList scoreList = new ArrayList();
            int numSheet;
            int rowNum;
            ScoreEntity se;
            TeacherEntity te;
            StudentEntity stu;
            int stuInt;
            int teacherInt;
            BigDecimal bd;
            if("xls".equals(fileName[1])) {
               HSSFWorkbook xssfWorkbook = new HSSFWorkbook(e);

               for(numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); ++numSheet) {
                  HSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
                  if(xssfSheet != null) {
                     for(rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); ++rowNum) {
                        HSSFRow xssfRow = xssfSheet.getRow(rowNum);
                        if(xssfRow != null) {
                           se = new ScoreEntity();
                           new TeacherEntity();
                           new StudentEntity();
                           HSSFCell stuNum = xssfRow.getCell(0);
                           HSSFCell courseName = xssfRow.getCell(1);
                           HSSFCell score = xssfRow.getCell(2);
                           HSSFCell term = xssfRow.getCell(3);
                           HSSFCell className = xssfRow.getCell(4);
                           HSSFCell teacherNum = xssfRow.getCell(5);
                           stuInt = (int)stuNum.getNumericCellValue();
                           teacherInt = (int)teacherNum.getNumericCellValue();
                           te = (TeacherEntity)this.scoreService.findUniqueByProperty(TeacherEntity.class, "teachernum", String.valueOf(teacherInt));
                           stu = (StudentEntity)this.scoreService.findUniqueByProperty(StudentEntity.class, "studentnum", String.valueOf(stuInt));
                           bd = new BigDecimal(score.getNumericCellValue());
                           se.setTeacherEntity(te);
                           se.setStudentEntity(stu);
                           se.setClassname(className.getStringCellValue());
                           se.setCoursename(courseName.getStringCellValue());
                           se.setScore(bd);
                           se.setTerm(term.getStringCellValue());
                           scoreList.add(se);
                        }
                     }
                  }
               }
            } else if("xlsx".equals(fileName[1])) {
               XSSFWorkbook var24 = new XSSFWorkbook(e);

               for(numSheet = 0; numSheet < var24.getNumberOfSheets(); ++numSheet) {
                  XSSFSheet var25 = var24.getSheetAt(numSheet);

                  for(rowNum = 1; rowNum <= var25.getLastRowNum(); ++rowNum) {
                     XSSFRow var26 = var25.getRow(rowNum);
                     if(var26 != null) {
                        se = new ScoreEntity();
                        new TeacherEntity();
                        new StudentEntity();
                        XSSFCell var27 = var26.getCell(0);
                        XSSFCell var28 = var26.getCell(1);
                        XSSFCell var29 = var26.getCell(2);
                        XSSFCell var30 = var26.getCell(3);
                        XSSFCell var31 = var26.getCell(4);
                        XSSFCell var32 = var26.getCell(5);
                        stuInt = (int)var27.getNumericCellValue();
                        teacherInt = (int)var32.getNumericCellValue();
                        te = (TeacherEntity)this.scoreService.findUniqueByProperty(TeacherEntity.class, "teachernum", String.valueOf(teacherInt));
                        stu = (StudentEntity)this.scoreService.findUniqueByProperty(StudentEntity.class, "studentnum", String.valueOf(stuInt));
                        bd = new BigDecimal(var29.getNumericCellValue());
                        se.setTeacherEntity(te);
                        se.setStudentEntity(stu);
                        se.setClassname(var31.getStringCellValue());
                        se.setCoursename(var28.getStringCellValue());
                        se.setScore(bd);
                        se.setTerm(var30.getStringCellValue());
                        scoreList.add(se);
                     }
                  }
               }
            } else {
               j.setMsg("请导入正确的excel文件！");
               j.setSuccess(false);
            }

            this.scoreService.saveBatch(scoreList);
         }
      } catch (Exception var23) {
         var23.printStackTrace();
         j.setMsg("导入失败！");
         j.setSuccess(false);
      }

      return j;
   }

   @RequestMapping(
      params = {"exportExcel"}
   )
   public void exportExcel(HttpServletRequest request, HttpServletResponse response, String teachername, String coursename, String term, String name) throws Exception {
      DetachedCriteria condition = DetachedCriteria.forClass(ScoreEntity.class);
      List scoreList = this.scoreService.findData(condition, name, teachername, coursename, term);
      HSSFWorkbook wb = new HSSFWorkbook();
      HSSFSheet sheet = wb.createSheet("sheet1");
      HSSFRow row = sheet.createRow(0);
      HSSFCellStyle style = wb.createCellStyle();
      style.setAlignment((short)2);

      int ouputStream;
      for(ouputStream = 0; ouputStream < this.excelHeader.length; ++ouputStream) {
         HSSFCell e = row.createCell(ouputStream);
         e.setCellValue(this.excelHeader[ouputStream]);
         e.setCellStyle(style);
         sheet.autoSizeColumn(ouputStream);
      }

      for(ouputStream = 0; ouputStream < scoreList.size(); ++ouputStream) {
         row = sheet.createRow(ouputStream + 1);
         ScoreEntity var21 = (ScoreEntity)scoreList.get(ouputStream);
         row.createCell(0).setCellValue(var21.getStudentEntity().getStudentnum());
         row.createCell(1).setCellValue(var21.getCoursename());
         row.createCell(2).setCellValue(var21.getScore().toString());
         row.createCell(3).setCellValue(var21.getTerm());
         row.createCell(4).setCellValue(var21.getClassname());
         row.createCell(5).setCellValue(var21.getTeacherEntity().getTeachernum());
         row.createCell(6).setCellValue(var21.getTeacherEntity().getTeachername());
         row.createCell(7).setCellValue(var21.getStudentEntity().getName());
      }

      response.setContentType("application/vnd.ms-excel");
      response.setHeader("Content-disposition", "attachment;filename=studentScore.xls");
      ServletOutputStream var20 = response.getOutputStream();

      try {
         wb.write(var20);
         var20.flush();
         var20.close();
      } catch (Exception var18) {
         var18.printStackTrace();
      } finally {
         var20.flush();
         var20.close();
      }

   }

   @RequestMapping(
      params = {"datagrid"}
   )
   @ResponseBody
   public void datagrid(HttpServletRequest request, HttpServletResponse response, ScoreEntity ve, String name, String teachername, String coursename, String term) throws Exception {
      String page = request.getParameter("page");
      String rows = request.getParameter("rows");
      if(page == null) {
         page = "0";
      }

      if(rows == null) {
         rows = "0";
      }

      DetachedCriteria condition = DetachedCriteria.forClass(ScoreEntity.class);
      Pagination pagination = this.scoreService.findPageData(condition, ve, Integer.parseInt(page), Integer.parseInt(rows), name, teachername, coursename, term);
      JSONObject jobj = new JSONObject();
      jobj.put("total", pagination.getTotalCount());
      jobj.put("rows", pagination.getDatas());
      response.setCharacterEncoding("utf-8");
      response.getWriter().write(jobj.toString());
   }
}
