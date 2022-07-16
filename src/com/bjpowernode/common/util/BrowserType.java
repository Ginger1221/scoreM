package com.bjpowernode.common.util;


public enum BrowserType {

   IE11("IE11", 0),
   IE10("IE10", 1),
   IE9("IE9", 2),
   IE8("IE8", 3),
   IE7("IE7", 4),
   IE6("IE6", 5),
   Firefox("Firefox", 6),
   Safari("Safari", 7),
   Chrome("Chrome", 8),
   Opera("Opera", 9),
   Camino("Camino", 10),
   Gecko("Gecko", 11);
   // $FF: synthetic field
   private static final BrowserType[] ENUM$VALUES = new BrowserType[]{IE11, IE10, IE9, IE8, IE7, IE6, Firefox, Safari, Chrome, Opera, Camino, Gecko};


   private BrowserType(String var1, int var2) {}
}
