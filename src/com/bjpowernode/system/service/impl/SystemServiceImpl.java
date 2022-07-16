package com.bjpowernode.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bjpowernode.common.service.impl.BaseServiceImpl;
import com.bjpowernode.system.dao.SystemDao;
import com.bjpowernode.system.entity.base.ResourceEntity;
import com.bjpowernode.system.entity.base.UserEntity;
import com.bjpowernode.system.service.SystemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("systemService")
public class SystemServiceImpl extends BaseServiceImpl implements SystemService {

   @Autowired
   private SystemDao systemDao;


   public UserEntity getUserByNameAndPassword(UserEntity user) {
      return this.systemDao.getUserByNameAndPassword(user);
   }

   public List getTreeMenuResource(UserEntity user) {
      return this.systemDao.getTreeMenuResource(user);
   }

   public String getTreeJson(List list) {
      return this.createTreeJson(list);
   }

   private String createTreeJson(List list) {
      JSONArray rootArray = new JSONArray();

      for(int i = 0; i < list.size(); ++i) {
         ResourceEntity resource = (ResourceEntity)list.get(i);
         if(resource.getParentResource() == null) {
            JSONObject rootObj = this.createBranch(list, resource);
            rootArray.add(rootObj);
         }
      }

      return rootArray.toString();
   }

   private JSONObject createBranch(List list, ResourceEntity currentNode) {
      JSONObject currentObj = JSONObject.parseObject(JSON.toJSONString(currentNode));
      JSONArray childArray = new JSONArray();

      for(int i = 0; i < list.size(); ++i) {
         ResourceEntity newNode = (ResourceEntity)list.get(i);
         if(newNode.getParentResource() != null && currentNode.getId().equals(newNode.getParentResource().getId())) {
            JSONObject childObj = this.createBranch(list, newNode);
            childArray.add(childObj);
         }
      }

      if(!childArray.isEmpty()) {
         currentObj.put("children", childArray);
      }

      return currentObj;
   }

   public Object findUniqueByProperty(Class entityClass, String propertyName, Object value) {
      return this.systemDao.findUniqueByProperty(entityClass, propertyName, value);
   }
}
