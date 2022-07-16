package com.bjpowernode.system.entity.base;

import com.bjpowernode.common.entity.base.BaseEntity;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(
   name = "T_S_ROLE"
)
public class RoleEntity extends BaseEntity {

   private static final long serialVersionUID = 87642784845062235L;
   @Column(
      nullable = false,
      length = 50
   )
   private String name;
   @Column(
      length = 200
   )
   private String description;
   @ManyToMany(
      cascade = {CascadeType.ALL}
   )
   @JoinTable(
      name = "T_S_ROLE_RESOURCE",
      joinColumns = {         @JoinColumn(
            name = "role_id"
         )},
      inverseJoinColumns = {         @JoinColumn(
            name = "resource_id"
         )}
   )
   private List<ResourceEntity> resource;


   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public List<ResourceEntity> getResource() {
      return this.resource;
   }

   public void setResource(List<ResourceEntity> resource) {
      this.resource = resource;
   }

   public String toString() {
      return super.getId();
   }
}
