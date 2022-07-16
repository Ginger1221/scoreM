package com.bjpowernode.buss.service;

import com.bjpowernode.buss.entity.base.StudentEntity;
import com.bjpowernode.common.util.Pagination;
import com.bjpowernode.system.service.SystemService;
import org.hibernate.criterion.DetachedCriteria;

public interface StudentService extends SystemService {

   Pagination findPageData(DetachedCriteria var1, StudentEntity var2, int var3, int var4);
}
