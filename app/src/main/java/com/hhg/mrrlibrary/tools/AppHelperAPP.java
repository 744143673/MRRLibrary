package com.hhg.mrrlibrary.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * author  : hhg
 * e-mail  : hhg673@foxmail.com
 * date    : 2017/9/7.
 * desc    : 因为银商提供sdk的AppHelper不是支持从Fragment跳转过去，所以AppHelperAPP 增添了这个需求
 * version : 1.0.0
 */

public final class AppHelperAPP {
    public static final String PAY_ACTION = "com.ums.transcontroller.call";
    public static final String PRINT_ACTION = "com.ums.transcontroller.print";
    private static final String a = "com.ums.tss.mastercontrol";
    private static final String b = "com.ums.anypay.AnyPay";
    private static final String c = "com.ums.anypay.ExternalCallPrintActivity";
    public static final String CASHBOX_ACTION = "com.ums.transcontroller.cashbox";
    public static final String TRANS_APP_NAME = "appName";
    public static final String TRANS_BIZ_ID = "transId";
    public static final String TRANS_DATA = "transData";
    public static final String RESULT_CODE = "resultCode";
    public static final String RESULT_MSG = "resultMsg";
    public static final String PRINT_FILE_PATH = "filePath";
    private static final String d = "result";
    public static final int TRANS_REQUEST_CODE = 1000;
    public static final int PRINT_REQUEST_CODE = 1001;
    public static final int CASHBOX_REQUEST_CODE = 1002;

    public AppHelperAPP() {
    }

    public static final void callTrans(Activity var0, String var1, String var2, JSONObject var3) {
        Intent var4 = new Intent();
        var4.setAction("com.ums.transcontroller.call");
        var4.setClassName("com.ums.tss.mastercontrol", "com.ums.anypay.AnyPay");
        var4.putExtra("appName", var1);
        var4.putExtra("transId", var2);
        var4.putExtra("transData", var3.toString());
        var0.startActivityForResult(var4, 1000);
    }

    public static final void callTrans(Fragment var0, String var1, String var2, JSONObject var3, int REQUEST_CODE) {
        Intent var4 = new Intent();
        var4.setAction("com.ums.transcontroller.call");
        var4.setClassName("com.ums.tss.mastercontrol", "com.ums.anypay.AnyPay");
        var4.putExtra("appName", var1);
        var4.putExtra("transId", var2);
        var4.putExtra("transData", var3.toString());
        var0.startActivityForResult(var4, REQUEST_CODE);
    }

    public static final Map<String, String> filterTransResult(Intent var0) {
        HashMap<String, String> var1 = new HashMap<>();
        String var2 = var0.getStringExtra("result");
        if (var2 != null && !var2.isEmpty()) {
            System.out.println(var2);

            try {
                JSONObject var3 = new JSONObject(var2);
                if (!var3.isNull("appName")) {
                    var1.put("appName", var3.getString("appName"));
                }

                if (!var3.isNull("transId")) {
                    var1.put("transId", var3.getString("transId"));
                }

                if (!var3.isNull("resultCode")) {
                    var1.put("resultCode", var3.getString("resultCode"));
                }

                if (!var3.isNull("resultMsg")) {
                    var1.put("resultMsg", var3.getString("resultMsg"));
                }

                if (!var3.isNull("transData")) {
                    var1.put("transData", var3.getString("transData"));
                }
            } catch (JSONException var4) {
                var4.printStackTrace();
            }

            return var1;
        } else {
            return var1;
        }
    }

    public static final void callPrint(Activity var0, String var1) {
        Intent var2 = new Intent();
        var2.setAction("com.ums.transcontroller.print");
        var2.setClassName("com.ums.tss.mastercontrol", "com.ums.anypay.ExternalCallPrintActivity");
        var2.putExtra("filePath", var1);
        var0.startActivityForResult(var2, 1001);
    }

    public static final void callCashBox(Activity var0) {
        Intent var1 = new Intent();
        var1.setAction("com.ums.transcontroller.cashbox");
        var0.startActivityForResult(var1, 1002);
    }

    public static final String callSyncPrint(Context var0, String var1) {
        Uri var2 = Uri.parse("content://com.ums.tss.mastercontrol.call/print");

        try {
            Cursor var3 = var0.getContentResolver().query(var2, (String[]) null, var1, (String[]) null, (String) null);
            if (var3 == null) {
                System.out.println("call print cursor null");
                return null;
            } else {
                var3.moveToFirst();
                String var4 = var3.getString(var3.getColumnIndexOrThrow("result"));
                System.out.println("call print result:" + var4);
                var3.close();
                return var4;
            }
        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }

    public static final String getBaseSysInfo(Context var0) {
        String var1 = null;
        Uri var2 = Uri.parse("content://com.ums.tss.mastercontrol.call/baseinfo");

        try {
            Cursor var3 = var0.getContentResolver().query(var2, (String[]) null, (String) null, (String[]) null, (String) null);
            if (var3 != null) {
                var3.moveToFirst();
                var1 = var3.getString(var3.getColumnIndexOrThrow("result"));
                var3.close();
            }
        } catch (Exception var4) {
            System.err.println("getBaseSysInfo exception:" + var4);
            var4.printStackTrace();
        }

        return var1;
    }

    public static final Bundle getSysLoginStatus(Context var0) {
        Bundle var1 = null;

        try {
            Cursor var2 = var0.getContentResolver().query(Uri.parse("content://com.ums.tss.mastercontrol.AppDataProvider/loginInfo"), (String[]) null, (String) null, (String[]) null, (String) null);
            if (var2 != null) {
                var1 = var2.getExtras();
            }
        } catch (Exception var3) {
            System.err.println("getSysLoginStatus exception:" + var3);
            var3.printStackTrace();
        }

        return var1;
    }

    public static final void doSysLogout(Context var0) {
        try {
            var0.getContentResolver().delete(Uri.parse("content://com.ums.tss.mastercontrol.AppDataProvider/loginInfo"), (String) null, (String[]) null);
        } catch (Exception var2) {
            System.err.println("doSysLogout exception:" + var2);
            var2.printStackTrace();
        }

    }

    public static final String getPayChannels(Context var0) {
        String var1 = null;

        try {
            Cursor var2 = var0.getContentResolver().query(Uri.parse("content://com.ums.tss.mastercontrol.getpaylist"), (String[]) null, (String) null, (String[]) null, (String) null);
            if (var2 != null && var2.moveToFirst()) {
                var1 = var2.getString(var2.getColumnIndex("result"));
                System.out.println("payChannels = " + var1);
                var2.close();
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return var1;
    }

    public static final String getAdPath(Context var0) {
        String var1 = null;

        try {
            Cursor var2 = var0.getContentResolver().query(Uri.parse("content://com.ums.tss.mastercontrol.getadpath"), (String[]) null, (String) null, (String[]) null, (String) null);
            if (var2 != null && var2.moveToFirst()) {
                var1 = var2.getString(var2.getColumnIndex("result"));
                System.out.println("getAdPath = " + var1);
                var2.close();
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return var1;
    }

    public static final String getFullAdPath(Context var0) {
        String var1 = null;

        try {
            Cursor var2 = var0.getContentResolver().query(Uri.parse("content://com.ums.tss.mastercontrol.getadpath/full"), (String[]) null, (String) null, (String[]) null, (String) null);
            if (var2 != null && var2.moveToFirst()) {
                var1 = var2.getString(var2.getColumnIndex("result"));
                System.out.println("getAdPath = " + var1);
                var2.close();
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return var1;
    }

    public static final String getBarAdPath(Context var0) {
        String var1 = null;

        try {
            Cursor var2 = var0.getContentResolver().query(Uri.parse("content://com.ums.tss.mastercontrol.getadpath/bar"), (String[]) null, (String) null, (String[]) null, (String) null);
            if (var2 != null && var2.moveToFirst()) {
                var1 = var2.getString(var2.getColumnIndex("result"));
                System.out.println("getAdPath = " + var1);
                var2.close();
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return var1;
    }
}

