package com.yuejianzhong.latte_core.delegate;

public abstract class LatteDelegate extends PermissionChecherDelegate{

   public <T extends LatteDelegate> T getParentDelegate() {
       return (T) getParentFragment();
   }
}
