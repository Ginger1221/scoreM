package com.bjpowernode.common.dao.impl;

import com.bjpowernode.common.dao.BaseDao;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("baseDao")
public class BaseDaoImpl implements BaseDao {

   private Logger logger = Logger.getLogger(this.getClass());
   @Autowired
   private SessionFactory sessionFactory;


   public Session getSession() {
      return this.sessionFactory.getCurrentSession();
   }

   public void saveOrUpdate(Object entity) {
      try {
         this.getSession().saveOrUpdate(entity);
         this.getSession().flush();
      } catch (RuntimeException var3) {
         this.logger.error("保存或更新实体异常", var3);
         throw var3;
      }
   }

   public Object get(Class entityClass, String id) {
      return this.getSession().get(entityClass, id);
   }

   public int getRowCountByDetachedCriteria(DetachedCriteria condition) {
      Criteria criteria = condition.getExecutableCriteria(this.getSession());
      Long totalCount = (Long)criteria.setProjection(Projections.rowCount()).uniqueResult();
      return totalCount == null?0:totalCount.intValue();
   }

   public List findByDetachedCriteria(DetachedCriteria condition, int page, int rows) {
      Criteria criteria = condition.getExecutableCriteria(this.getSession());
      criteria.setFirstResult((page - 1) * rows).setMaxResults(rows);
      criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
      return criteria.list();
   }

   public List findByDetachedCriteriaNoPage(DetachedCriteria condition) {
      Criteria criteria = condition.getExecutableCriteria(this.getSession());
      return criteria.list();
   }

   public void save(Object entity) {
      try {
         this.getSession().save(entity);
         this.getSession().flush();
      } catch (RuntimeException var3) {
         this.logger.error("保存实体异常", var3);
         throw var3;
      }
   }

   public void update(Object entity) {
      try {
         this.getSession().update(entity);
         this.getSession().flush();
      } catch (RuntimeException var3) {
         this.logger.error("更新实体异常", var3);
         throw var3;
      }
   }

   public void delete(Object entity) {
      try {
         this.getSession().delete(entity);
         this.getSession().flush();
      } catch (RuntimeException var3) {
         this.logger.error("删除实体异常", var3);
         throw var3;
      }
   }
}
