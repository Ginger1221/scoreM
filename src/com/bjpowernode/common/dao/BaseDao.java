package com.bjpowernode.common.dao;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;

public interface BaseDao {

   void saveOrUpdate(Object var1);

   Object get(Class var1, String var2);

   int getRowCountByDetachedCriteria(DetachedCriteria var1);

   List findByDetachedCriteria(DetachedCriteria var1, int var2, int var3);

   List findByDetachedCriteriaNoPage(DetachedCriteria var1);

   void save(Object var1);

   void update(Object var1);

   void delete(Object var1);
}
