package com.hhg.mrrlibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.hhg.mrrlibrary.utils.LogUtils;
import com.hhg.mrrlibrary.utils.ToastUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtils.i("MRRLibrary");
        ToastUtils.showShort("MRRLibrary");
    }
}
