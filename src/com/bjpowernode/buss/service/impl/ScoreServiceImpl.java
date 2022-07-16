package com.bjpowernode.buss.service.impl;

import com.bjpowernode.buss.entity.base.ScoreEntity;
import com.bjpowernode.buss.service.ScoreService;
import com.bjpowernode.common.dao.BaseDao;
import com.bjpowernode.common.util.Pagination;
import com.bjpowernode.system.service.impl.SystemServiceImpl;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("scoreService")
public class ScoreServiceImpl extends SystemServiceImpl implements ScoreService {

   @Autowired
   private BaseDao baseDao;


   public Pagination findPageData(DetachedCriteria condition, ScoreEntity ce, int page, int rows, String name, String teachername, String coursename, String term) {
      Pagination pagination = new Pagination(page, rows);
      if(name != null && !"".equals(name)) {
         condition.createAlias("studentEntity", "s");
         condition.add(Restrictions.like("s.name", "%" + name + "%"));
      }

      if(teachername != null && !"".equals(teachername)) {
         condition.createAlias("teacherEntity", "t");
         condition.add(Restrictions.like("t.teachername", "%" + teachername + "%"));
      }

      if(coursename != null && !"".equals(coursename)) {
         condition.add(Restrictions.like("coursename", "%" + coursename + "%"));
      }

      if(term != null && !"".equals(term)) {
         condition.add(Restrictions.like("term", "%" + term + "%"));
      }

      condition.addOrder(Order.desc("createTime"));
      int total = this.baseDao.getRowCountByDetachedCriteria(condition);
      pagination.setTotalCount(total);
      condition.setProjection((Projection)null);
      if(total != 0) {
         List datas = this.baseDao.findByDetachedCriteria(condition, page, rows);
         pagination.setDatas(datas);
      }

      return pagination;
   }

   public List findData(DetachedCriteria condition, String name, String teachername, String coursename, String term) throws Exception {
      new ArrayList();
      if(name != null && !"".equals(name)) {
         name = URLDecoder.decode(name, "UTF-8");
         condition.createAlias("studentEntity", "s");
         condition.add(Restrictions.like("s.name", "%" + name + "%"));
      }

      if(teachername != null && !"".equals(teachername)) {
         teachername = URLDecoder.decode(teachername, "UTF-8");
         condition.createAlias("teacherEntity", "t");
         condition.add(Restrictions.like("t.teachername", "%" + teachername + "%"));
      }

      if(coursename != null && !"".equals(coursename)) {
         coursename = URLDecoder.decode(coursename, "UTF-8");
         condition.add(Restrictions.like("coursename", "%" + coursename + "%"));
      }

      if(term != null && !"".equals(term)) {
         term = URLDecoder.decode(term, "UTF-8");
         condition.add(Restrictions.like("term", "%" + term + "%"));
      }

      condition.addOrder(Order.desc("createTime"));
      List scoreList = this.baseDao.findByDetachedCriteriaNoPage(condition);
      return scoreList;
   }
}
