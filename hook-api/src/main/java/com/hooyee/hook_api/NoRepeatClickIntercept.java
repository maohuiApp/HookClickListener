package com.hooyee.hook_api;

import android.view.View;

/**
 * @author maohui
 * @date Created on 2019/4/9.
 * @description
 * @added
 */

public class NoRepeatClickIntercept extends BaseClickIntercept {
    private int mInterval = 500;

    public NoRepeatClickIntercept(int interval) {
        mInterval = interval;
    }

    @Override
    public boolean before(View v) {
        long time = System.currentTimeMillis();
        if(time - mLastClickTimeArray.get(v.getId(), 0l) > mInterval) {
            mLastClickTimeArray.put(v.getId(), time);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void after(View v) {
    }
}
