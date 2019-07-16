package com.yuejianzhong.latte_core.app;

import com.yuejianzhong.latte_core.util.storage.LattePreference;

public class AccountManager {
    private enum SignTag{
        SIGN_TAG
    }

    //保存用户登录状态
    public static void setSignState(boolean state) {
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    private static boolean isSignIn() {
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker) {
        if (isSignIn()) {
            checker.onSignIn();
        }else {
            checker.onNotSignIn();
        }
    }
}
