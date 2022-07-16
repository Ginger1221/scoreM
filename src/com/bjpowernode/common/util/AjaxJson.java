package com.bjpowernode.common.util;

import com.alibaba.fastjson.JSONObject;
import java.util.Map;

public class AjaxJson {

   private boolean success = true;
   private String msg = "操作成功";
   private Object obj = null;
   private Map attributes;


   public Map getAttributes() {
      return this.attributes;
   }

   public void setAttributes(Map attributes) {
      this.attributes = attributes;
   }

   public String getMsg() {
      return this.msg;
   }

   public void setMsg(String msg) {
      this.msg = msg;
   }

   public Object getObj() {
      return this.obj;
   }

   public void setObj(Object obj) {
      this.obj = obj;
   }

   public boolean isSuccess() {
      return this.success;
   }

   public void setSuccess(boolean success) {
      this.success = success;
   }

   public String getJsonStr() {
      JSONObject obj = new JSONObject();
      obj.put("success", Boolean.valueOf(this.isSuccess()));
      obj.put("msg", this.getMsg());
      obj.put("obj", this.obj);
      obj.put("attributes", this.attributes);
      return obj.toJSONString();
   }
}
