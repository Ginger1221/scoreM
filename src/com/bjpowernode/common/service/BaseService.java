package com.bjpowernode.common.service;

import com.bjpowernode.common.util.Pagination;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;

public interface BaseService {

   void saveOrUpdate(Object var1);

   Object get(Class var1, String var2);

   Pagination getPageData(DetachedCriteria var1, int var2, int var3);

   List getQueryData(DetachedCriteria var1);

   void save(Object var1);

   void saveBatch(List var1);

   void update(Object var1);

   void delete(Object var1);
}
