package com.bjpowernode.buss.entity.base;

import com.bjpowernode.common.entity.base.BaseEntity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(
   name = "T_B_TEACHER"
)
public class TeacherEntity extends BaseEntity {

   private static final long serialVersionUID = 3752159363835368243L;
   @Column(
      length = 20
   )
   private String teachernum;
   @Column(
      length = 10
   )
   private String teachername;
   @Column(
      length = 20
   )
   private String phone;
   @Column(
      length = 10
   )
   private String tittle;
   @Column(
      length = 20
   )
   private Date starttime;


   public String getTeachername() {
      return this.teachername;
   }

   public void setTeachername(String teachername) {
      this.teachername = teachername;
   }

   public String getTittle() {
      return this.tittle;
   }

   public void setTittle(String tittle) {
      this.tittle = tittle;
   }

   public Date getStarttime() {
      return this.starttime;
   }

   public void setStarttime(Date starttime) {
      this.starttime = starttime;
   }

   public static long getSerialversionuid() {
      return 3752159363835368243L;
   }

   public String getTeachernum() {
      return this.teachernum;
   }

   public void setTeachernum(String teachernum) {
      this.teachernum = teachernum;
   }

   public String getPhone() {
      return this.phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }
}
