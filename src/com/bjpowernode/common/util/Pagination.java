package com.bjpowernode.common.util;

import java.util.ArrayList;
import java.util.List;

public class Pagination {

   private int start;
   private int totalCount;
   private List datas;
   private int pageSize = 10;


   public Pagination() {}

   public Pagination(int start, int pageSize) {
      this.start = start;
      this.pageSize = pageSize;
   }

   public int getPageSize() {
      return this.pageSize;
   }

   public void setPageSize(int pageSize) {
      this.pageSize = pageSize;
   }

   public int getStart() {
      return this.start;
   }

   public void setStart(int start) {
      this.start = start;
   }

   public int getTotalCount() {
      return this.totalCount;
   }

   public void setTotalCount(int totalCount) {
      this.totalCount = totalCount;
   }

   public List getDatas() {
      return (List)(this.datas == null?new ArrayList():this.datas);
   }

   public void setDatas(List datas) {
      this.datas = datas;
   }
}
