package com.bjpowernode.common.controller;

import com.bjpowernode.common.util.DateConvertEditor;
import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

@Controller
public class BaseController {

   @InitBinder
   public void initBinder(ServletRequestDataBinder binder) {
      binder.registerCustomEditor(Date.class, new DateConvertEditor());
   }
}
