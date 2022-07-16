package com.bjpowernode.common.util;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.springframework.util.StringUtils;

public class DateConvertEditor extends PropertyEditorSupport {

   private SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


   public void setAsText(String text) throws IllegalArgumentException {
      if(StringUtils.hasText(text)) {
         try {
            if(text.indexOf(":") == -1 && text.length() == 10) {
               this.setValue(this.dateFormat.parse(text));
            } else if(text.indexOf(":") > 0 && text.length() == 19) {
               this.setValue(this.datetimeFormat.parse(text));
            } else {
               if(text.indexOf(":") <= 0 || text.length() != 21) {
                  throw new IllegalArgumentException("Could not parse date, date format is error ");
               }

               text = text.replace(".0", "");
               this.setValue(this.datetimeFormat.parse(text));
            }
         } catch (ParseException var4) {
            IllegalArgumentException iae = new IllegalArgumentException("Could not parse date: " + var4.getMessage());
            iae.initCause(var4);
            throw iae;
         }
      } else {
         this.setValue((Object)null);
      }

   }
}
