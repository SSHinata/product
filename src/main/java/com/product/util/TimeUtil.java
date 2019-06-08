package com.product.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    public static Integer getTime(){
        int timeMillis = (int) (System.currentTimeMillis()/1000);
        return timeMillis;
    }

    public static String getStrByDate(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        return format;
    }

    public static String getDateByTime(Integer time){
        SimpleDateFormat simple=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simple.format(new Date(time * 1000L));
        return format;
    }

    public static String getTimeCode(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String format =simpleDateFormat.format(new Date());
        return format;
    }
}
