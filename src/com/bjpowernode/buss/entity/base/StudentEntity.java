package com.bjpowernode.buss.entity.base;

import com.bjpowernode.common.entity.base.BaseEntity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(
   name = "T_B_STUDENT"
)
public class StudentEntity extends BaseEntity {

   private static final long serialVersionUID = -2280744824776533775L;
   @Column(
      length = 15
   )
   private String studentnum;
   @Column(
      length = 10
   )
   private String name;
   @Column(
      length = 1
   )
   private String sex;
   @Column(
      length = 20
   )
   private Date birthday;
   @Column(
      length = 15
   )
   private String qq;
   @Column(
      length = 15
   )
   private String mobile;


   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getSex() {
      return this.sex;
   }

   public void setSex(String sex) {
      this.sex = sex;
   }

   public Date getBirthday() {
      return this.birthday;
   }

   public void setBirthday(Date birthday) {
      this.birthday = birthday;
   }

   public String getQq() {
      return this.qq;
   }

   public void setQq(String qq) {
      this.qq = qq;
   }

   public String getMobile() {
      return this.mobile;
   }

   public void setMobile(String mobile) {
      this.mobile = mobile;
   }

   public String getStudentnum() {
      return this.studentnum;
   }

   public void setStudentnum(String studentnum) {
      this.studentnum = studentnum;
   }
}
