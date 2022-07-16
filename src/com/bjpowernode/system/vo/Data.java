package com.bjpowernode.system.vo;

import com.bjpowernode.common.util.Pagination;
import java.util.ArrayList;
import java.util.List;

public class Data {

   private Pagination pagination;
   private int total;
   private List rows = new ArrayList();


   public Data(Pagination pagination) {
      this.pagination = pagination;
   }

   public int getTotal() {
      return this.pagination.getTotalCount();
   }

   public void setTotal(int total) {
      this.total = total;
   }

   public List getRows() {
      return this.pagination.getDatas();
   }

   public void setRows(List rows) {
      this.rows = rows;
   }
}
