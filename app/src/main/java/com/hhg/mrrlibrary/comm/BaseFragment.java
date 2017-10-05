package com.hhg.mrrlibrary.comm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hhg.mrrlibrary.R;
import com.hhg.mrrlibrary.defaultimpl.DefaultCommViewImpl;
import com.hhg.mrrlibrary.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author  : hhg
 * e-mail  : hhg673@foxmail.com
 * date    : 2017/8/24.
 * desc    :  Fragment 基类
 * version : 1.0.0
 */

public abstract class BaseFragment extends Fragment {

    @BindView(R.id.frameLayoutContent)
    FrameLayout frameLayoutContent;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    Unbinder unbinder;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.imgBtnLeft)
    ImageButton imgBtnLeft;
    @BindView(R.id.imgBtnRight)
    ImageButton imgBtnRight;

    private ICommView iCommView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_base, container, false);
        frameLayoutContent = (FrameLayout) rootView.findViewById(R.id.frameLayoutContent);
        if (inflateViewLayoutId() != null)
            frameLayoutContent.addView(inflater.inflate(inflateViewLayoutId(), null));
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        tvTitle.setText(setToolbarTitle());
        setImgLeftAndImgRight(imgBtnLeft, imgBtnRight);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        //toolbar去除默认title显示
        ActionBar supportActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (supportActionBar != null)
            supportActionBar.setDisplayShowTitleEnabled(false);
        setHasOptionsMenu(true); // Fragment 接管Toolbar
    }


    /**
     * 返回LayoutId
     *
     * @return LayoutId
     */
    protected abstract Integer inflateViewLayoutId();

    /**
     * 设置Toolbar 中的标题
     *
     * @return 标题
     */
    protected abstract String setToolbarTitle();

    /**
     * 设置左侧返回键和右侧的设置键
     *
     * @param imgBtnLeft  Toolbar左边的返回
     * @param imgBtnRight Toolbar右边的设置
     */
    protected void setImgLeftAndImgRight(ImageButton imgBtnLeft, ImageButton imgBtnRight) {
        imgBtnLeft.setVisibility(View.VISIBLE);
        imgBtnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        imgBtnRight.setVisibility(View.VISIBLE);
        imgBtnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort("imgRight");
            }
        });
    }


    /**
     * @return 返回ICommView的默认实现实例 DefaultCommViewImpl
     */
    protected ICommView getICommView() {
        if (iCommView == null)
            iCommView = new DefaultCommViewImpl(this);
        return iCommView;
    }
}
