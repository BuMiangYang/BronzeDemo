package dictation.appcode.com.bapp.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * 描述：
 */

public class BaseFragmentPresenter<V> {

    public BaseFragmentActivity mContext;

    public BaseFragmentPresenter(BaseFragmentActivity context) {
        this.mContext = context;
    }

    protected Reference<V> mViewRef;

    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    public V getView() {
        return mViewRef != null ? mViewRef.get() : null;
    }
}
