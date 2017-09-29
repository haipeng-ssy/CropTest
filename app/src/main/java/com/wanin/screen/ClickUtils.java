package com.wanin.screen;

import android.os.Handler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wanin on 2016/4/22.
 */
public class ClickUtils {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static Date lastClickTime;

    static {
        try {
            lastClickTime = sdf.parse("1970-1-1");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static boolean isFastDoubleClick() {
        Date time = new Date();
        if (time.getTime() - lastClickTime.getTime() < 1000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }


    private static long last = -1;

    public static boolean isFastClick(long interval) {
        long time = System.currentTimeMillis();
        if (time - last < interval) {
            last = time;
            return true;
        }
        last = time;
        return false;
    }


    public static boolean isDocubleClick() {
        Date time = new Date();
        if (time.getTime() - lastClickTime.getTime() < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 点击事件时调用
     * if (ClickUtils.getInstance().clickTime())
     * return;
     */
    private ClickUtils() {
    }

    static ClickUtils clickUtils;

    public static ClickUtils getInstance() {
        if (clickUtils == null) {
            return clickUtils = new ClickUtils();
        }
        return clickUtils;
    }

    private Handler myhander = new Handler();
    private boolean dbl_click = false;
    Runnable myrunable = new Runnable() {
        @Override
        public void run() {
            dbl_click = false;
        }
    };

    public boolean clickTime(int time) {
        if (dbl_click) {
            return true;
        }
        dbl_click = true;
        myhander.postDelayed(myrunable, time);
        return false;
    }

    public boolean clickTime() {
        if (dbl_click) {
            return true;
        }
        dbl_click = true;
        myhander.postDelayed(myrunable, 1000);
        return false;
    }

}
