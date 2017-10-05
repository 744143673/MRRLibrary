package com.hhg.mrrlibrary.defaultimpl;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.hhg.mrrlibrary.comm.ICommView;
import com.hhg.mrrlibrary.utils.ToastUtils;
import com.hhg.mrrlibrary.widgets.CommDialogFragment;
import com.hhg.mrrlibrary.widgets.LoadingDialogFragment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * author  : hhg
 * e-mail  : hhg673@foxmail.com
 * date    : 2017/8/17.
 * desc    : CommView 默认实现
 * version : 1.0.0
 */

public class DefaultCommViewImpl implements ICommView {

    private Fragment fragment;
    private List<LoadingDialogFragment> list = new ArrayList<>();

    public DefaultCommViewImpl(@NonNull Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public boolean isActive() {
        return fragment.isAdded();
    }

    @Override
    public synchronized void showLoading() {
        if (isActive()) {
            LoadingDialogFragment loadingDialogFragment = new LoadingDialogFragment();
            loadingDialogFragment.show(fragment.getFragmentManager(), LoadingDialogFragment.TAG);
            list.add(loadingDialogFragment);
        }
    }

    @Override
    public synchronized void hideLoading() {
        if (isActive()) {
            Iterator<LoadingDialogFragment> iterator = list.iterator();
            if (iterator.hasNext()) {
                LoadingDialogFragment dialogFragment = iterator.next();
                dialogFragment.dismiss();
                iterator.remove();
            }
        }
    }

    @Override
    public void showToastShort(@NonNull String msg) {
        if (isActive())
            ToastUtils.showShort(msg);
    }

    @Override
    public void showPointDialog(String strContent, boolean isShowCancel, CommDialogFragment.PosOnClickListener pos0nClickListener) {
        if (isActive())
            CommDialogFragment.getInstance(strContent, isShowCancel, pos0nClickListener).show(fragment.getFragmentManager(), strContent);
    }
}
