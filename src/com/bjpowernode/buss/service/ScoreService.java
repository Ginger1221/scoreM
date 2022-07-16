package com.bjpowernode.buss.service;

import com.bjpowernode.buss.entity.base.ScoreEntity;
import com.bjpowernode.common.util.Pagination;
import com.bjpowernode.system.service.SystemService;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;

public interface ScoreService extends SystemService {

   Pagination findPageData(DetachedCriteria var1, ScoreEntity var2, int var3, int var4, String var5, String var6, String var7, String var8);

   List findData(DetachedCriteria var1, String var2, String var3, String var4, String var5) throws Exception;
}
