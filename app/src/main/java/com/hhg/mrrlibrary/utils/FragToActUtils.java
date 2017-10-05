package com.hhg.mrrlibrary.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by hhg on 2017/7/23.
 * description  :
 * version      :1.0.0
 */

public class FragToActUtils {

    public static void addFragmentToActivity(Fragment fragment, FragmentManager manager, int id){
        /*
         * java.lang.IllegalStateException: Fragment already added异常的处理。
         当快速双击调用FragmentTransaction.add()方法添加fragmentA，而fragmentA不是每次单独生成的，就会引起这个异常。
         */
        if (!fragment.isAdded()){
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(id, fragment);
            transaction.commitAllowingStateLoss();
        }
    }
}
