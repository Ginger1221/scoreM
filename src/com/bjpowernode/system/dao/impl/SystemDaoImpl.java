package com.bjpowernode.system.dao.impl;

import com.bjpowernode.common.dao.impl.BaseDaoImpl;
import com.bjpowernode.system.dao.SystemDao;
import com.bjpowernode.system.entity.base.UserEntity;
import java.util.List;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class SystemDaoImpl extends BaseDaoImpl implements SystemDao {

   public UserEntity getUserByNameAndPassword(UserEntity user) {
      Md5Hash md5Hash = new Md5Hash(user.getPassword());
      String password = md5Hash.toHex();
      String query = "from UserEntity u where u.username = :username and u.password=:passowrd";
      Query queryObject = this.getSession().createQuery(query);
      queryObject.setParameter("username", user.getUsername());
      queryObject.setParameter("passowrd", password);
      List users = queryObject.list();
      return users != null && users.size() > 0?(UserEntity)users.get(0):null;
   }

   public List getTreeMenuResource(UserEntity user) {
      String hql = "select r.resource from UserEntity u inner join fecth u.roles r where u.id = :id";
      Query queryObject = this.getSession().createQuery(hql);
      queryObject.setParameter("id", user.getId());
      List resourceList = queryObject.list();
      return resourceList;
   }

   public Object findUniqueByProperty(Class entityClass, String propertyName, Object value) {
      return this.createCriteria(entityClass, new Criterion[]{Restrictions.eq(propertyName, value)}).uniqueResult();
   }

   private Criteria createCriteria(Class entityClass, Criterion ... criterions) {
      Criteria criteria = this.getSession().createCriteria(entityClass);
      Criterion[] var7 = criterions;
      int var6 = criterions.length;

      for(int var5 = 0; var5 < var6; ++var5) {
         Criterion c = var7[var5];
         criteria.add(c);
      }

      return criteria;
   }
}
