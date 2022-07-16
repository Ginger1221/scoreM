package com.bjpowernode.system.manager;

import com.bjpowernode.system.vo.Client;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ClientManager {

   private static ClientManager instance = new ClientManager();
   private Map map = new HashMap();


   public static ClientManager getInstance() {
      return instance;
   }

   public void addClinet(String sessionId, Client client) {
      this.map.put(sessionId, client);
   }

   public void removeClinet(String sessionId) {
      this.map.remove(sessionId);
   }

   public Client getClient(String sessionId) {
      return (Client)this.map.get(sessionId);
   }

   public Collection getAllClient() {
      return this.map.values();
   }
}
