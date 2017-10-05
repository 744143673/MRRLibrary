package com.hhg.mrrlibrary.widgets;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hhg.mrrlibrary.R;
import com.hhg.mrrlibrary.comm.Constant;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author  : hhg
 * e-mail  : hhg673@foxmail.com
 * date    : 2017/9/7.
 * desc    : 加载框
 * version : 1.0.0
 */

public class LoadingDialogFragment extends DialogFragment {


    public static final String TAG = "LoadingDialogFragment";

    private TextView tvLoading;
    Unbinder unbinder;

    private CountDownTimerAPP countDownTimerAPP; //倒计时控件

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme_Holo_Dialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getDialog().getWindow() != null)
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN;
            }
        });
        getDialog().setCanceledOnTouchOutside(false);

        View inflate = inflater.inflate(R.layout.layout_loading, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvLoading = view.findViewById(R.id.tvLoading);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(this, tag);
        ft.commitAllowingStateLoss();
    }

    @Override
    public void onResume() {
        super.onResume();
        countStart();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (countDownTimerAPP != null)
            countCancel();
        if (getDialog() != null)
            dismiss();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void countStart() {
        countDownTimerAPP = CountDownTimerAPP.instance(new WeakReference<>(tvLoading), Constant.CountDownTimerConstant.millisInFuture, Constant.CountDownTimerConstant.countDownInterval);
        countDownTimerAPP.start();
    }

    public void countCancel() {
        countDownTimerAPP.cancel();
    }
}
