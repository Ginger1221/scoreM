package com.bjpowernode.common.service.impl;

import com.bjpowernode.common.dao.BaseDao;
import com.bjpowernode.common.service.BaseService;
import com.bjpowernode.common.util.Pagination;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("baseService")
public class BaseServiceImpl implements BaseService {

   @Autowired
   @Qualifier("baseDao")
   private BaseDao baseDao;
   @Autowired
   @Qualifier("sessionFactory")
   private SessionFactory sessionFactory;


   public Session getSession() {
      return this.sessionFactory.getCurrentSession();
   }

   public void saveOrUpdate(Object entity) {
      this.baseDao.saveOrUpdate(entity);
   }

   public Object get(Class entityClass, String id) {
      return this.baseDao.get(entityClass, id);
   }

   public Pagination getPageData(DetachedCriteria condition, int page, int rows) {
      Pagination pagination = new Pagination(page, rows);
      int total = this.baseDao.getRowCountByDetachedCriteria(condition);
      pagination.setTotalCount(total);
      condition.setProjection((Projection)null);
      if(total != 0) {
         List datas = this.baseDao.findByDetachedCriteria(condition, page, rows);
         pagination.setDatas(datas);
      }

      return pagination;
   }

   public List getQueryData(DetachedCriteria condition) {
      List resultList = this.baseDao.findByDetachedCriteriaNoPage(condition);
      return resultList;
   }

   public void save(Object entity) {
      this.baseDao.save(entity);
   }

   public void update(Object entity) {
      this.baseDao.update(entity);
   }

   public void delete(Object entity) {
      this.baseDao.delete(entity);
   }

   public void saveBatch(List entitys) {
      for(int i = 0; i < entitys.size(); ++i) {
         Object object = entitys.get(i);
         this.getSession().save(object);
         if(i % 20 == 0) {
            this.getSession().flush();
            this.getSession().clear();
         }
      }

      this.getSession().flush();
      this.getSession().clear();
   }
}
