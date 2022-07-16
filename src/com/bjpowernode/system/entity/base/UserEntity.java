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
   name = "T_S_USER"
)
public class UserEntity extends BaseEntity {

   private static final long serialVersionUID = 6455189720167859325L;
   public static int STATUS_NORMAL = 1;
   public static int STATUS_FORBIDDEN = 2;
   @Column(
      nullable = false,
      length = 50
   )
   private String username;
   @Column(
      nullable = false,
      length = 32
   )
   private String password;
   @Column(
      name = "real_name",
      length = 10
   )
   private String realName;
   @Column(
      length = 50
   )
   private String email;
   @Column(
      length = 20
   )
   private String phone;
   @Column(
      length = 20
   )
   private String position;
   @Column(
      name = "position_desc",
      length = 100
   )
   private String positonDesc;
   @ManyToMany(
      cascade = {CascadeType.ALL}
   )
   @JoinTable(
      name = "T_S_USER_ROLE",
      joinColumns = {         @JoinColumn(
            name = "user_id"
         )},
      inverseJoinColumns = {         @JoinColumn(
            name = "role_id"
         )}
   )
   private List<RoleEntity> roles;
   private Integer status;


   public String getUsername() {
      return this.username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword() {
      return this.password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getRealName() {
      return this.realName;
   }

   public void setRealName(String realName) {
      this.realName = realName;
   }

   public String getEmail() {
      return this.email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPhone() {
      return this.phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public String getPosition() {
      return this.position;
   }

   public void setPosition(String position) {
      this.position = position;
   }

   public String getPositonDesc() {
      return this.positonDesc;
   }

   public void setPositonDesc(String positonDesc) {
      this.positonDesc = positonDesc;
   }

   public List<RoleEntity> getRoles() {
      return this.roles;
   }

   public void setRoles(List<RoleEntity> roles) {
      this.roles = roles;
   }

   public Integer getStatus() {
      return this.status;
   }

   public void setStatus(Integer status) {
      this.status = status;
   }
}
