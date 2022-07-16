package com.bjpowernode.system.service;

import com.bjpowernode.common.service.BaseService;
import com.bjpowernode.system.entity.base.UserEntity;
import java.util.List;

public interface SystemService extends BaseService {

   UserEntity getUserByNameAndPassword(UserEntity var1);

   List getTreeMenuResource(UserEntity var1);

   String getTreeJson(List var1);

   Object findUniqueByProperty(Class var1, String var2, Object var3);
}
