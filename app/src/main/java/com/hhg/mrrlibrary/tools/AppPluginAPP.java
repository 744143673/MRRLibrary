package com.hhg.mrrlibrary.tools;


import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * AppPluginUp
 * Created by Alex on 2017/5/8.
 */

public class AppPluginAPP {
    public static final String PAY_ACTION = "com.ums.transcontroller.call";
    public static final String PRINT_ACTION = "com.ums.transcontroller.print";
    private static final String CALL_PACKAGE_NAME = "com.ums.tss.mastercontrol";
    private static final String PAY_ACTIVITY = "com.ums.anypay.AnyPay";
    private static final String PRINT_ACTIVITY = "com.ums.anypay.ExternalCallPrintActivity";
    public static final String CASHBOX_ACTION = "com.ums.transcontroller.cashbox";
    public static final String TRANS_APP_NAME = "appName";
    public static final String TRANS_BIZ_ID = "transId";
    public static final String TRANS_DATA = "transData";
    public static final String RESULT_CODE = "resultCode";
    public static final String RESULT_MSG = "resultMsg";
    public static final String PRINT_FILE_PATH = "filePath";
    private static final String RESULT = "result";
    public static final int TRANS_REQUEST_CODE = 1000;
    public static final int PRINT_REQUEST_CODE = 1001;
    public static final int CASHBOX_REQUEST_CODE = 1002;

    public AppPluginAPP() {
    }

    public static final void callConsumerEngine(Activity activity, String appName, String bizName, JSONObject transData, int requestCode) {
        Intent intent = new Intent();
        intent.setAction("com.ums.transcontroller.call");
        intent.setClassName("com.ums.tss.mastercontrol", "com.ums.anypay.AnyPay");
        intent.putExtra("appName", appName);
        intent.putExtra("transId", bizName);
        intent.putExtra("transData", transData.toString());
        activity.startActivityForResult(intent, requestCode);
    }

    public static final void callConsumerEngine(Fragment fragment, String appName, String bizName, JSONObject transData, int requestCode) {
        Intent intent = new Intent();
        intent.setAction("com.ums.transcontroller.call");
        intent.setClassName("com.ums.tss.mastercontrol", "com.ums.anypay.AnyPay");
        intent.putExtra("appName", appName);
        intent.putExtra("transId", bizName);
        intent.putExtra("transData", transData.toString());
        fragment.startActivityForResult(intent, requestCode);
    }

    public static final Map<String, String> filterTransResult(Intent data) {
        HashMap map = new HashMap();
        String result = data.getStringExtra("result");
        if (result != null && !result.isEmpty()) {
            System.out.println(result);

            try {
                JSONObject e = new JSONObject(result);
                if (!e.isNull("appName")) {
                    map.put("appName", e.getString("appName"));
                }

                if (!e.isNull("transId")) {
                    map.put("transId", e.getString("transId"));
                }

                if (!e.isNull("resultCode")) {
                    map.put("resultCode", e.getString("resultCode"));
                }

                if (!e.isNull("resultMsg")) {
                    map.put("resultMsg", e.getString("resultMsg"));
                }

                if (!e.isNull("transData")) {
                    map.put("transData", e.getString("transData"));
                }
            } catch (JSONException var4) {
                var4.printStackTrace();
            }

            return map;
        } else {
            return map;
        }
    }

    public static final void callPrintEngine(Activity activity, String filePath, int requestCode) {
        Intent intent = new Intent();
        intent.setAction("com.ums.transcontroller.print");
        intent.setClassName("com.ums.tss.mastercontrol", "com.ums.anypay.ExternalCallPrintActivity");
        intent.putExtra("filePath", filePath);
        activity.startActivityForResult(intent, requestCode);
    }

    public static final void callPrintEngine(Fragment fragment, String filePath, int requestCode) {
        Intent intent = new Intent();
        intent.setAction("com.ums.transcontroller.print");
        intent.setClassName("com.ums.tss.mastercontrol", "com.ums.anypay.ExternalCallPrintActivity");
        intent.putExtra("filePath", filePath);
        fragment.startActivityForResult(intent, requestCode);
    }

    public static final void callCashBox(Activity activity) {
        Intent intent = new Intent();
        intent.setAction("com.ums.transcontroller.cashbox");
        activity.startActivityForResult(intent, 1002);
    }
}
