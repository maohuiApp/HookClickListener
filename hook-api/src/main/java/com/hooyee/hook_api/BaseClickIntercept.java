package com.hooyee.hook_api;

import android.util.Log;
import android.util.SparseArray;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author maohui
 * @date Created on 2019/4/9.
 * @description
 * @added
 */

public abstract class BaseClickIntercept implements IOnClickIntercept {

    protected SparseArray<Long> mLastClickTimeArray = new SparseArray<>();
    protected SparseArray<View.OnClickListener> mOnClickListenerArray = new SparseArray<>();

    @Override
    public void onClick(View v) {
        if (before(v)) {
            mOnClickListenerArray.get(v.getId()).onClick(v);
        } else {
            Log.d(BaseClickIntercept.class.getSimpleName(), getClass().getSimpleName() + "#before()校验不通过");
        }
        after(v);
    }

    public void addClickListener(View v) {
        Method getListenerInfo;
        Object mListenerInfo;
        Field clickListener;

        try {
            getListenerInfo = View.class.getDeclaredMethod("getListenerInfo");
            getListenerInfo.setAccessible(true);
            mListenerInfo = getListenerInfo.invoke(v);

            clickListener = mListenerInfo.getClass().getDeclaredField("mOnClickListener");
            clickListener.setAccessible(true);
            if (clickListener.get(mListenerInfo) != null) {
                mOnClickListenerArray.put(v.getId(), (View.OnClickListener) clickListener.get(mListenerInfo));
                clickListener.set(mListenerInfo, this);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
