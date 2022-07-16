package com.bjpowernode.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ExcelImportUtil {

   private static Logger log;
   private static int rowsNum = 0;
   private static int columnNum = 0;


   public static List getExcelStringList(InputStream is) {
      try {
         new ArrayList();
         POIFSFileSystem fs = new POIFSFileSystem(is);
         HSSFWorkbook wb = new HSSFWorkbook(fs);
         HSSFSheet sheet = wb.getSheetAt(0);
         rowsNum = sheet.getLastRowNum();
         columnNum = getColumnNum(sheet, 4, 1);
         List e = readExcel(sheet, 0, 0, rowsNum, columnNum);
         List var6 = e;
         return var6;
      } catch (IOException var14) {
         log.error("read Excel Throws Exception!");
         var14.printStackTrace();
      } finally {
         try {
            is.close();
         } catch (IOException var13) {
            log.error("fileinputStream close Throws Exception!");
            var13.printStackTrace();
         }

      }

      return null;
   }

   private static List readExcel(HSSFSheet sheet, int startRow, int startColumn, int totalRows, int columnNum) {
      ArrayList results = new ArrayList();

      try {
         boolean e = true;

         for(int i = startRow; i <= totalRows; ++i) {
            StringBuffer sb = new StringBuffer();
            if(!e) {
               break;
            }

            HSSFRow row = sheet.getRow(i);
            if(row == null) {
               break;
            }

            for(int j = startColumn; j < columnNum; ++j) {
               HSSFCell cell = row.getCell(j);
               if(cell != null) {
                  sb.append("\t" + getCellValue(cell));
               }
            }

            results.add(sb.toString() + "\n");
         }
      } catch (Exception var12) {
         var12.printStackTrace();
      }

      return results;
   }

   private static int getColumnNum(HSSFSheet sheet, int startRow, int startColumn) {
      boolean columnNum = false;
      HSSFRow row = null;
      if(startRow == 0) {
         row = sheet.getRow(0);
      } else {
         row = sheet.getRow(startRow - 1);
      }

      int i = startColumn;

      int var7;
      while(true) {
         HSSFCell cell = row.getCell(i);
         if(cell == null) {
            var7 = i;
            break;
         }

         if("".equals(cell.toString())) {
            var7 = i;
            break;
         }

         ++i;
      }

      return var7;
   }

   private static String getCellValue(HSSFCell cell) {
      String ret;
      switch(cell.getCellType()) {
      case 0:
         if(!HSSFDateUtil.isCellDateFormatted(cell) && cell.getCellStyle().getDataFormat() != 179) {
            ret = String.valueOf(cell.getNumericCellValue());
         } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ret = sdf.format(cell.getDateCellValue());
         }
         break;
      case 1:
         ret = cell.getStringCellValue();
         break;
      case 2:
      default:
         ret = null;
         break;
      case 3:
         ret = "";
         break;
      case 4:
         ret = String.valueOf(cell.getBooleanCellValue());
         break;
      case 5:
         ret = null;
      }

      return ret;
   }
}
