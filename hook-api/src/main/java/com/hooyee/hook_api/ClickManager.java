package com.hooyee.hook_api;

import android.view.View;

/**
 * @author maohui
 * @date Created on 2019/4/9.
 * @description
 * @added
 */

public class ClickManager {
    private static volatile ClickManager instance;

    private ClickManager() {
    }

    public static ClickManager getSingleton() {
        if (instance == null) {
            synchronized (ClickManager.class) {
                if (instance == null) {
                    instance = new ClickManager();
                }
            }
        }
        return instance;
    }

    public void wrap(BaseClickIntercept intercept, View... views) {
        if (intercept == null || views == null || views.length == 0) return;
        for (View v : views) {
            intercept.addClickListener(v);
        }
    }

    public void wrapClickInterval(View... views) {
        NoRepeatClickIntercept wrap = new NoRepeatClickIntercept(500);
        wrap(wrap, views);
    }

}
