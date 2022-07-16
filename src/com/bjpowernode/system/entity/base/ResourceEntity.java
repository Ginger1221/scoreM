package com.bjpowernode.system.entity.base;

import com.bjpowernode.common.entity.base.BaseEntity;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(
   name = "T_S_RESOURCE"
)
@JsonIgnoreProperties({"parent"})
public class ResourceEntity extends BaseEntity {

   private static final long serialVersionUID = -2134869782502357553L;
   public static int TYPE_MENU = 1;
   public static int TYPE_BTN = 2;
   private Integer resourceType;
   @Column(
      length = 200
   )
   private String name;
   @Column(
      length = 200
   )
   private String description;
   @Column(
      length = 3,
      name = "order_no"
   )
   private Integer orderNo;
   @ManyToOne
   @JoinColumn(
      name = "parentid"
   )
   private ResourceEntity parentResource;
   @OneToMany(
      mappedBy = "parentResource",
      fetch = FetchType.EAGER
   )
   @OrderBy("orderNo")
   private List<ResourceEntity> resources;
   @Column(
      length = 200
   )
   private String href;


   public Integer getResourceType() {
      return this.resourceType;
   }

   public void setResourceType(Integer resourceType) {
      this.resourceType = resourceType;
   }

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

   public Integer getOrderNo() {
      return this.orderNo;
   }

   public void setOrderNo(Integer orderNo) {
      this.orderNo = orderNo;
   }

   public String getHref() {
      return this.href;
   }

   public void setHref(String href) {
      this.href = href;
   }

   public ResourceEntity getParentResource() {
      return this.parentResource;
   }

   public void setParentResource(ResourceEntity parentResource) {
      this.parentResource = parentResource;
   }

   public List<ResourceEntity> getResources() {
      return this.resources;
   }

   public void setResources(List<ResourceEntity> resources) {
      this.resources = resources;
   }
}
