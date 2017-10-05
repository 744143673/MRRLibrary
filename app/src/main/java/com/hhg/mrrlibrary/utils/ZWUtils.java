package com.hhg.mrrlibrary.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * author  : hhg
 * e-mail  : hhg673@foxmail.com
 * date    : 2017/9/24.
 * desc    :
 * version : 1.0.0
 */

public class ZWUtils {

    private ZWUtils() {
    }

    public static String getOrderNumber(String str) {
        String regex = "[\u4e00-\u9fa5]";
//        String ordernumber1 = "第一个订单号为YLJN11," + "第二个订单号为YLJN2222222122," + "第二个订单号为YLJN2222222222";
        Pattern pat = Pattern.compile(regex);
        Matcher mat = pat.matcher(str);
        return mat.replaceAll("").replace(",", "").replace(" ", "").replace("，","").replace("。","").replace(".","");
    }
}
