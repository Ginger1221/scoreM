package com.bjpowernode.buss.service.impl;

import com.bjpowernode.buss.entity.base.TeacherEntity;
import com.bjpowernode.buss.service.TeacherService;
import com.bjpowernode.common.dao.BaseDao;
import com.bjpowernode.common.util.Pagination;
import com.bjpowernode.system.service.impl.SystemServiceImpl;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("teacherService")
public class TeacherServiceImpl extends SystemServiceImpl implements TeacherService {

   @Autowired
   private BaseDao baseDao;


   public Pagination findPageData(DetachedCriteria condition, TeacherEntity ce, int page, int rows) {
      Pagination pagination = new Pagination(page, rows);
      if(ce.getTeachernum() != null && !"".equals(ce.getTeachernum())) {
         condition.add(Restrictions.like("teachernum", "%" + ce.getTeachernum() + "%"));
      }

      if(ce.getTeachername() != null && !"".equals(ce.getTeachername())) {
         condition.add(Restrictions.like("teachername", "%" + ce.getTeachername() + "%"));
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
}
