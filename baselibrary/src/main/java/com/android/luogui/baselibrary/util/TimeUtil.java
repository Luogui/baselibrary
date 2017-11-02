package com.android.luogui.baselibrary.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 描述：时间工具类
 * Created by LuoGui on 2017/8/28.
 */

public class TimeUtil {

    public static long getNowUnixTime() {
        Date date = new Date();
        long time = date.getTime() / 1000L;
        return time;
    }

    public static String getNowTime(String format) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    public static long DateTimeToUnix(String date, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date day = sdf.parse(date);
        long time = day.getTime() / 1000L;
        return time;
    }

    public static String unixToFormat(long unix, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = new Date(1000L * unix);
        return sdf.format(date);
    }

    public static String dateFormat(long unix, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = new Date(unix);
        return sdf.format(date);
    }

    public static String getChatTime(long unixTime) {
        String result = "";
        Date date = new Date();
        long l = 86400000L;
        long today = date.getTime() - date.getTime() % l - 28800000L;

        if (unixTime > today) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
            result = dateFormat.format(Long.valueOf(unixTime));
        } else if (unixTime + 86400000L > today) {
            result = "昨天";
        } else if (unixTime + 1471228928L > today) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd");
            result = dateFormat.format(Long.valueOf(unixTime));
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            result = dateFormat.format(Long.valueOf(unixTime));
        }

        return result;
    }

    public static int[] UnixParse(int unix) {
        int[] date = new int[5];
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(1000L * unix);
        date[0] = calendar.get(1);
        date[1] = calendar.get(2);
        date[2] = calendar.get(5);
        date[3] = calendar.get(11);
        date[4] = calendar.get(12);
        return date;
    }

}