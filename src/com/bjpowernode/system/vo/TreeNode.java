package com.bjpowernode.system.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeNode {

   private String id;
   private String text;
   private boolean isLeaf;
   private String iconCls;
   private String state;
   private List children;
   private Map attributes;


   public String getId() {
      return this.id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getText() {
      return this.text;
   }

   public void setText(String text) {
      this.text = text;
   }

   public boolean isLeaf() {
      return this.isLeaf;
   }

   public void setLeaf(boolean isLeaf) {
      this.isLeaf = isLeaf;
   }

   public String getIconCls() {
      return this.iconCls;
   }

   public void setIconCls(String iconCls) {
      this.iconCls = iconCls;
   }

   public String getState() {
      return this.state;
   }

   public void setState(String state) {
      this.state = state;
   }

   public List getChildren() {
      return this.children;
   }

   public void setChildren(List children) {
      this.children = children;
   }

   public Map getAttributes() {
      if(this.attributes == null) {
         this.attributes = new HashMap();
      }

      this.attributes.put("isLeaf", Boolean.valueOf(this.isLeaf));
      return this.attributes;
   }

   public void setAttributes(Map attributes) {
      this.attributes = attributes;
   }
}
