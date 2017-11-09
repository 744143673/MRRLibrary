package com.hhg.mrrlibrary.comm;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.hhg.barlibrary.kernel.ImmersionBar;
import com.hhg.mrrlibrary.R;
import com.hhg.mrrlibrary.utils.FragToActUtils;
import com.hhg.mrrlibrary.utils.ResUtils;


/**
 * Created by hhg on 2017/7/23.
 * description  : Activity 基类
 * version      : 1.0.0
 */

public abstract class MRRActivity<P extends IBasePresenter, V extends Fragment> extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        V fragment = createFragment();
        FragToActUtils.addFragmentToActivity(fragment, getSupportFragmentManager(), R.id.contentFrame);
        createPresenter(fragment);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ImmersionBar
                .with(this)
                .barColor(barColor())
                .init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar
                .with(this)
                .destroy();
    }

    /**
     * 创建Presenter实例
     */
    protected abstract P createPresenter(V f);

    /**
     * 创建Fragment实例
     */
    protected abstract V createFragment();

    /**
     * 设置Bar颜色
     *
     * @return color
     */
    @ColorRes
    protected int barColor() {
        return R.color.colorPrimary;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (clickDouble(500L))
                return true;
            View v = getCurrentFocus();
            if (v != null) {
                if (isShouldHideInput(v, ev)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        return getWindow().superDispatchTouchEvent(ev) || onTouchEvent(ev);
    }


    /**
     * 键盘的处理事件
     */
    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    //添加两秒只能点击一次
    private long exitTime = 0;

    /**
     * 指定时间内只能响应一次点击事件；
     *
     * @param time 时间
     * @return boolean
     */
    protected boolean clickDouble(long time) {
        if ((System.currentTimeMillis() - exitTime) < time) {
            exitTime = System.currentTimeMillis();
            return true;
        }
        exitTime = System.currentTimeMillis();
        return false;
    }
}
