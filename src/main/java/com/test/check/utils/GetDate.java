package com.test.check.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class GetDate {
    public static String getDate(){
        SimpleDateFormat sf = new SimpleDateFormat("yyy-MM-dd ");
        String Time = sf.format(new Date());
        return Time;
    }

    public  static String getUid(){
        String replace = UUID.randomUUID().toString().replace("-", "");
        System.out.println(replace);
        return  replace;

    }


}
