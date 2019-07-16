package com.yuejianzhong.latte_core.util.timer;

import java.util.TimerTask;

public class BaseTimerTask  extends TimerTask {

    private ITimerListerer mITimerListerer = null;

    public BaseTimerTask(ITimerListerer listerer) {
        this.mITimerListerer = listerer;
    }

    @Override
    public void run() {
        if (mITimerListerer != null) {
            mITimerListerer.onTimer();
        }
    }
}
