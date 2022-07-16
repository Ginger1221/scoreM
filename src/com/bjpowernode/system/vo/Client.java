package com.bjpowernode.system.vo;

import com.bjpowernode.system.entity.base.UserEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Client implements Serializable {

   private static final long serialVersionUID = 1L;
   private UserEntity user;
   private String ip;
   private Date logindatetime;
   private List menuList;


   public UserEntity getUser() {
      return this.user;
   }

   public void setUser(UserEntity user) {
      this.user = user;
   }

   public String getIp() {
      return this.ip;
   }

   public void setIp(String ip) {
      this.ip = ip;
   }

   public Date getLogindatetime() {
      return this.logindatetime;
   }

   public void setLogindatetime(Date logindatetime) {
      this.logindatetime = logindatetime;
   }

   public List getMenuList() {
      return this.menuList;
   }

   public void setMenuList(List menuList) {
      this.menuList = menuList;
   }
}
