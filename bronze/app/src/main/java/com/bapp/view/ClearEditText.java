package com.bapp.view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.bapp.R;


/**
 * @author bmy
 * @auto
 * @des :带删除按钮的EditText
 */
public class ClearEditText extends AppCompatEditText implements View.OnFocusChangeListener, TextWatcher {

    private boolean hasFocus = false;
    private Drawable mClearDrawable = null;

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs, android.R.attr.editTextStyle);
        this.init();
    }

    private void init() {

        mClearDrawable = this.getCompoundDrawables()[2];
        if (mClearDrawable == null) {
            mClearDrawable = this.getResources().getDrawable(R.mipmap.ic_action_clear);
        }

        mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());
        // 默认设置隐藏图标
        this.setClearIconVisible(false);
        // 设置焦点改变的监听
        this.setOnFocusChangeListener(this);
        // 设置输入框里面内容发生改变的监听
        this.addTextChangedListener(this);

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        if (charSequence != null && this.hasFocus) {
            setClearIconVisible(charSequence.length() > 0);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onFocusChange(View view, boolean hasfocus) {

        this.hasFocus = hasfocus;

        if (hasFocus) {
            setClearIconVisible(this.getText().length() > 0);
        } else {
            setClearIconVisible(false);
        }
    }

    private void setClearIconVisible(Boolean visible) {

        Drawable right = visible ? mClearDrawable : null;
        setCompoundDrawables(this.getCompoundDrawables()[0],
                this.getCompoundDrawables()[1], right, this.getCompoundDrawables()[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (this.getCompoundDrawables()[2] != null) {
                int x = (int) event.getX();
                int y = (int) event.getY();
                Rect rect = this.getCompoundDrawables()[2].getBounds();
                int height = rect.height();
                int distance = (getHeight() - height) / 2;
                boolean isInnerWidth = x > getWidth() - getTotalPaddingRight() && x < getWidth() - getPaddingRight();
                boolean isInnerHeight = y > distance && y < distance + height;
                if (isInnerWidth && isInnerHeight) {
                    this.setText("");
                }
            }
        }
        return super.onTouchEvent(event);
    }
}
