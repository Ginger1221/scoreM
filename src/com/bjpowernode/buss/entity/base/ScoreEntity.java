package com.bjpowernode.buss.entity.base;

import com.bjpowernode.buss.entity.base.StudentEntity;
import com.bjpowernode.buss.entity.base.TeacherEntity;
import com.bjpowernode.common.entity.base.BaseEntity;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(
   name = "T_B_SCORE"
)
public class ScoreEntity extends BaseEntity {

   private static final long serialVersionUID = -1756666143133716363L;
   @Column
   private BigDecimal score;
   @Column(
      length = 20
   )
   private String classname;
   @Column(
      length = 36
   )
   private String term;
   @Column(
      length = 20
   )
   private String coursename;
   @ManyToOne(
      fetch = FetchType.EAGER
   )
   @JoinColumn(
      name = "teacherid"
   )
   private TeacherEntity teacherEntity;
   @ManyToOne(
      fetch = FetchType.EAGER
   )
   @JoinColumn(
      name = "studentid"
   )
   private StudentEntity studentEntity;


   public BigDecimal getScore() {
      return this.score;
   }

   public void setScore(BigDecimal score) {
      this.score = score;
   }

   public String getClassname() {
      return this.classname;
   }

   public void setClassname(String classname) {
      this.classname = classname;
   }

   public String getTerm() {
      return this.term;
   }

   public void setTerm(String term) {
      this.term = term;
   }

   public String getCoursename() {
      return this.coursename;
   }

   public void setCoursename(String coursename) {
      this.coursename = coursename;
   }

   public TeacherEntity getTeacherEntity() {
      return this.teacherEntity;
   }

   public void setTeacherEntity(TeacherEntity teacherEntity) {
      this.teacherEntity = teacherEntity;
   }

   public StudentEntity getStudentEntity() {
      return this.studentEntity;
   }

   public void setStudentEntity(StudentEntity studentEntity) {
      this.studentEntity = studentEntity;
   }

   public static long getSerialversionuid() {
      return -1756666143133716363L;
   }
}
