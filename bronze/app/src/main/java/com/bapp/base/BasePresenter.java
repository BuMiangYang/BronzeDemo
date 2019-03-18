package com.bapp.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @author BuMingYang
 * @des
 */
public class BasePresenter<V> {

    public BaseActivity mContext;

    public BasePresenter(BaseActivity context) {
        this.mContext = context;
    }

    protected Reference<V> mViewRef;

    /**
     * 绑定view
     *
     * @param view
     */
    public void attachView(V view) {

        mViewRef = new WeakReference<V>(view);
    }

    /**
     * 解绑View
     */
    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    /**
     * 获取view 层
     *
     * @return
     */
    public V getView() {
        return mViewRef != null ? mViewRef.get() : null;
    }
}
