package com.bjpowernode.buss.service;

import com.bjpowernode.buss.entity.base.TeacherEntity;
import com.bjpowernode.common.util.Pagination;
import com.bjpowernode.system.service.SystemService;
import org.hibernate.criterion.DetachedCriteria;

public interface TeacherService extends SystemService {

   Pagination findPageData(DetachedCriteria var1, TeacherEntity var2, int var3, int var4);
}
