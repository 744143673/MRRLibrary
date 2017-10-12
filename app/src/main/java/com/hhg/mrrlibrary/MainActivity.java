package com.hhg.mrrlibrary;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.hhg.mrrlibrary.comm.IBasePresenter;
import com.hhg.mrrlibrary.comm.MRRActivity;

public class MainActivity extends MRRActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected IBasePresenter createPresenter(Fragment f) {
        return null;
    }

    @Override
    protected Fragment createFragment() {
        return null;
    }

}
