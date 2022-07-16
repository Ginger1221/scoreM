package com.bjpowernode.system.entity.base;

import com.bjpowernode.common.entity.base.BaseEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(
   name = "T_S_DEPART"
)
public class DepartEntity extends BaseEntity {

   private static final long serialVersionUID = 3493122928615586987L;
   @Column(
      length = 45
   )
   private String departname;
   @Column(
      length = 100
   )
   private String description;
   @ManyToOne(
      fetch = FetchType.LAZY
   )
   @JoinColumn(
      name = "parentid"
   )
   private DepartEntity parentDepart;
   @OneToMany(
      cascade = {CascadeType.ALL},
      fetch = FetchType.LAZY,
      mappedBy = "parentDepart"
   )
   private List<DepartEntity> Departs = new ArrayList();


   public String getDepartname() {
      return this.departname;
   }

   public void setDepartname(String departname) {
      this.departname = departname;
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public List <DepartEntity>getDeparts() {
      return this.Departs;
   }

   public void setDeparts(List<DepartEntity> departs) {
      this.Departs = departs;
   }

   public DepartEntity getParentDepart() {
      return this.parentDepart;
   }

   public void setParentDepart(DepartEntity parentDepart) {
      this.parentDepart = parentDepart;
   }
}
