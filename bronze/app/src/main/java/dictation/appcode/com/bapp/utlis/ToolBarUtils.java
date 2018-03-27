package dictation.appcode.com.bapp.utlis;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * 修改系统状态栏颜色
 */
public class ToolBarUtils {

    public static final int COLOR_DEFAULT_RED = Color.parseColor("#4DB6AC");
    public static final int COLOR_DEFAULT_WHITE = Color.parseColor("#00000000");

    private static void setStatusBarColor(Activity activity, int statusColor) {
        Window window = activity.getWindow();
        ViewGroup mContentView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //First translucent status bar.
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //After LOLLIPOP not translucent status bar
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                //Then call setStatusBarColor.
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(statusColor);

                if (mContentView.getChildAt(0) != null) {
                    ViewCompat.setFitsSystemWindows(mContentView.getChildAt(0), true);
                }
            } else {
                int statusBarHeight = getStatusBarHeight(activity);

                //Before LOLLIPOP create a fake status bar View.
                View mTopView = mContentView.getChildAt(0);
                if (mTopView != null && mTopView.getLayoutParams() != null && mTopView.getLayoutParams().height == statusBarHeight) {
                    //if fake status bar view exist, we can setBackgroundColor and return.
                    mTopView.setBackgroundColor(statusColor);
                    return;
                }
                //now topView is layout content
                if (mTopView != null) {
                    ViewCompat.setFitsSystemWindows(mTopView, true);
                }

                mTopView = new View(activity);
                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
                mTopView.setBackgroundColor(statusColor);
                mContentView.addView(mTopView, 0, lp);
            }
        }
    }

    private static void translucentStatusBar(Activity activity) {
        Window window = activity.getWindow();
        ViewGroup mContentView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //First translucent status bar.
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //After LOLLIPOP just set LayoutParams.
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (mContentView.getChildAt(0) != null) {
                    ViewCompat.setFitsSystemWindows(mContentView.getChildAt(0), false);
                }
            } else {
                //如果添加了StatusBar, 需要移除这个View
                View statusBarView = mContentView.getChildAt(0);
                if (statusBarView != null && statusBarView.getLayoutParams() != null && statusBarView.getLayoutParams().height == getStatusBarHeight(activity)) {
                    mContentView.removeView(statusBarView);
                }
                if (mContentView.getChildAt(0) != null) {
                    ViewCompat.setFitsSystemWindows(mContentView.getChildAt(0), false);
                }
            }
        }
    }

    /**
     * 设置系统状态栏色值
     * @param activity
     */
    public static void setStatusBarColor(Activity activity) {

        setStatusBarColor(activity, COLOR_DEFAULT_RED);
    }

    /**
     * 获取状态栏的高度
     * @param context
     * @return
     */
    private static int getStatusBarHeight(Context context) {
        int result = 0;
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            result = context.getResources().getDimensionPixelOffset(resId);
        }
        return result;
    }

    /**
     * 是否修改系统状态栏
     * @param isTranslucent 是否为透明
     */
    public static void isChangeThemeStyle(Activity activity,boolean isTranslucent){

        if (isTranslucent) {
            translucentStatusBar(activity);
        } else {
            setStatusBarColor(activity, COLOR_DEFAULT_WHITE);
            setStatusBarColor(activity);
        }

    }




}
