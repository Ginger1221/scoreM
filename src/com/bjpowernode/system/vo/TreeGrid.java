package com.bjpowernode.system.vo;

import java.io.Serializable;
import java.util.Map;

public class TreeGrid implements Serializable {

   private static final long serialVersionUID = -776305556228690050L;
   private String id;
   private String text;
   private String parentId;
   private String parentText;
   private String code;
   private String src;
   private String note;
   private Map attributes;
   private String operations;
   private String state = "open";
   private String order;


   public String getOrder() {
      return this.order;
   }

   public void setOrder(String order) {
      this.order = order;
   }

   public String getOperations() {
      return this.operations;
   }

   public void setOperations(String operations) {
      this.operations = operations;
   }

   public Map getAttributes() {
      return this.attributes;
   }

   public void setAttributes(Map attributes) {
      this.attributes = attributes;
   }

   public String getParentText() {
      return this.parentText;
   }

   public void setParentText(String parentText) {
      this.parentText = parentText;
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public String getSrc() {
      return this.src;
   }

   public void setSrc(String src) {
      this.src = src;
   }

   public String getNote() {
      return this.note;
   }

   public void setNote(String note) {
      this.note = note;
   }

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

   public String getParentId() {
      return this.parentId;
   }

   public void setParentId(String parentId) {
      this.parentId = parentId;
   }

   public String getState() {
      return this.state;
   }

   public void setState(String state) {
      this.state = state;
   }
}
