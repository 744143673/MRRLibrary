package com.hhg.mrrlibrary.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

import com.hhg.mrrlibrary.R;


/**
 * Created by hhg on 2017/7/23.
 * description  : 自定义调度的ScrollView
 * version      : 1.0.0
 */


public class MaxHeightScrollView extends ScrollView {
    private float maxHeight;

    public MaxHeightScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ScrollViewMaxHeight);
        /*
         * getDimension()是基于当前DisplayMetrics进行转换，获取指定资源id对应的尺寸。文档里并没说这里返回的就是像素，要注意这个函数的返回值是float，像素肯定是int。
         getDimensionPixelSize()与getDimension()功能类似，不同的是将结果转换为int，并且小数部分四舍五入。
         getDimensionPixelOffset()与getDimension()功能类似，不同的是将结果转换为int，并且偏移转换（offset conversion，函数命名中的offset是这个意思）是直接截断小数位，即取整（其实就是把float强制转化为int，注意不是四舍五入哦）。
         */
        try {
            maxHeight = typedArray.getFloat(R.styleable.ScrollViewMaxHeight_maxHeightFloat, 0.5f);
        } catch (Exception e) {
            maxHeight = 0.5f;
        }
        maxHeight = maxHeight >= 0.5f ? 0.5f : maxHeight;
        typedArray.recycle();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        try {
            int height = getResources().getDisplayMetrics().heightPixels;
            heightMeasureSpec = MeasureSpec.makeMeasureSpec((int) (height * maxHeight), MeasureSpec.AT_MOST);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //重新计算控件高、宽
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
