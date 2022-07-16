package com.bjpowernode.system.dao;

import com.bjpowernode.common.dao.BaseDao;
import com.bjpowernode.system.entity.base.UserEntity;
import java.util.List;

public interface SystemDao extends BaseDao {

   UserEntity getUserByNameAndPassword(UserEntity var1);

   List getTreeMenuResource(UserEntity var1);

   Object findUniqueByProperty(Class var1, String var2, Object var3);
}
