package com.yuejianzhong.latte_core.delegate;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public abstract class LatteDelegate extends PermissionCheckDelegate {

   public <T extends LatteDelegate> T getParentDelegate() {
       return (T) getParentFragment();
   }
    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
