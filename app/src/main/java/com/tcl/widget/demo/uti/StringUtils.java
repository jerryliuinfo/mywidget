package com.tcl.widget.demo.uti;

/**
 * Created by lenovo on 2016/8/31.
 */

public class StringUtils {
    /**
     * 对象转整数
     *
     * @param 
     * @return 转换异常返回 0
     */
    public static int toInt(String str) {
        if (str == null) return 0;
        return toInt(str, 0);
    }
    /**
     * 字符串转整数
     *
     * @param str
     * @param defValue
     * @return
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defValue;
    }
    /**
     * 对象转整数
     *
     * @param str
     * @return 转换异常返回 0
     */
    public static long toLong(String str) {
        if (str == null) return 0;
       return toLong(str);
    }


    /**
     * 对象转整数
     *
     * @param str
     * @return 转换异常返回 0
     */
    public static long toLong(String str, long defValue) {
        try {
            return Long.parseLong(str);
        } catch (Exception e) {
        }
        return defValue;
    }


    public static double toDouble(String str) {
        if (str == null) return 0;
        return toDouble(str, 0);
    }

    /**
     * 字符串转整数
     *
     * @param str
     * @param defValue
     * @return
     */
    public static double toDouble(String str, float defValue) {
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
        }
        return defValue;
    }

    public static Boolean toBoolean(String str) {
        if (str == null) return false;
        return toBoolean(str, false);
    }
    public static Boolean toBoolean(String str,boolean defValue) {
        try {
            return Boolean.parseBoolean(str);
        } catch (Exception e) {
        }
        return defValue;
    }



}
