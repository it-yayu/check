package com.test.check.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTest {


    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        String StringDate= timeStamp2Date(String.valueOf(1559618449+"000"),"yyyy-MM-dd HH:mm:ss SSS");
        System.out.println(StringDate);
    }


    /**
     * 时间戳转换成日期格式字符串
     * @param seconds 精确到秒的字符串
     * @param
     * @return
     */
    public static String timeStamp2Date(String seconds,String format) {
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
            return "";
        }
        if(format == null || format.isEmpty()) format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds)));
    }



}
