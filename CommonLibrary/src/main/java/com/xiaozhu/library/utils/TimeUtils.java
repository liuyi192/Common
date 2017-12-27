package com.xiaozhu.library.utils;

import android.text.format.Time;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @说明 时间工具类
 * @作者 LY
 * @时间 2017/10/30 16:56
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public class TimeUtils {
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    public static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATETIME = "yyyy-MM-dd HH:mm";
    public static final String DEFAULT_TIME_PATTERN = "HH:mm:ss";
    public static final String DEFAULT_DATE_TIME = "MM-dd HH:mm";
    public static final String DEFAULT_TIME = "HH:mm";
    public static final String DEFAULT_DATE = "MM-dd";
    public static final String DEFAULT_YEAR = "yyyy";
    public static final String DEFAULT_MONTH = "MM";
    public static final String DEFAULT_DAY = "dd";
    public static final String DEFAULT_CN_DATE = "yyyy年MM月dd日";
    public static final String DEFAULT_CN_DATE_TIME = "yyyy年MM月dd日 HH:mm";
    public static final String DEFAULT_YEAR_MONTH = "yyyy年MM月";
    public static final String DEFAULT_DATE_SIMPLIFY = "yyyy-M-d";

    /**
     * 日期格式化
     *
     * @param time           需要格式的时间 例如：2017-01-09 09:00:00
     * @param toDateFormat   当前时间的格式 例如：yyyy-MM-dd HH:mm:ss
     * @param fromDateFormat 需要的时间格式 例如：yyyy-MM-dd
     * @return 格式化后的数据 例如：2017-01-09
     */
    public static String formatDate(String time, String toDateFormat, String fromDateFormat) {
        String timeDate = "";
        try {
            SimpleDateFormat fromDate = new SimpleDateFormat(fromDateFormat);
            SimpleDateFormat toDate = new SimpleDateFormat(toDateFormat);
            timeDate = fromDate.format(toDate.parse(time));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timeDate;
    }

    /**
     * 日期格式化
     *
     * @param time 需要格式的时间 例如：2017-01-09 09:00:00
     * @return 格式化后的数据 2017-01-09
     */
    public static String formatDate(String time) {
        return formatDate(time, DEFAULT_DATETIME_PATTERN, DEFAULT_DATE_PATTERN);
    }

    /**
     * 日期格式化
     *
     * @param time 需要格式的时间 例如：2017-1-9
     * @return 格式化后的数据 2017-01-09
     */
    public static String formatSimplifyDate(String time) {
        return formatDate(time, DEFAULT_DATE_SIMPLIFY, DEFAULT_DATE_PATTERN);
    }

    /**
     * 日期格式化
     *
     * @param time 需要格式的时间 例如：2017-01-09 09:00:00
     * @return 格式化后的数据 01-09 09:00
     */
    public static String formatDateAndTime(String time) {
        return formatDate(time, DEFAULT_DATETIME_PATTERN, DEFAULT_DATE_TIME);
    }

    /**
     * 日期格式化
     *
     * @param time 需要格式的时间 例如：2017-01-09 09:00:00
     * @return 格式化后的数据 01-09 09:00
     */
    public static String formatTime(String time) {
        return formatDate(time, DEFAULT_DATETIME_PATTERN, DEFAULT_TIME);
    }

    /**
     * 日期格式化
     *
     * @param time 需要格式的时间 例如：2017-01-09 09:00:00
     * @return 格式化后的数据 2017-01-09 09:00
     */
    public static String formatTime(String time, String format) {
        return formatDate(time, DEFAULT_DATETIME_PATTERN, format);
    }

    /**
     * 格式化日期字符串
     *
     * @param time    需要格式化的时间
     * @param pattern 格式化的样式 例如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date formatDate(String time, String pattern) {
        Date date = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            date = format.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取当前日期
     *
     * @return 例如：2017-01-09 09:00:00
     */
    public static String toDay() {
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATETIME_PATTERN);
        return format.format(new Date());
    }

    /**
     * 获取当前日期
     *
     * @return 例如：2017-01-09
     */
    public static String toDate() {
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        return format.format(new Date());
    }

    /**
     * 获取当前日期
     *
     * @return 例如：2017年01月09
     */
    public static String toCNDay() {
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_CN_DATE);
        return format.format(new Date());
    }

    /**
     * 获取当前月
     *
     * @return 例如：2017年01月
     */
    public static String toMonth() {
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_YEAR_MONTH);
        return format.format(new Date());
    }

    /**
     * 获取当前月
     *
     * @param date 2017-1-1
     * @return 例如：2017年01月
     */
    public static String toMonth(String date) {
        return formatDate(date, DEFAULT_DATE_SIMPLIFY, DEFAULT_YEAR_MONTH);
    }

    /**
     * 获取当前日期
     *
     * @param date 2017-1-1
     * @return 例如：2017年01月
     */
    public static String toDate(String date) {
        return formatDate(date, DEFAULT_DATE_SIMPLIFY, DEFAULT_CN_DATE);
    }

    /**
     * 根据时间获取周几
     *
     * @param date
     * @return
     */
    public static String getWeek(String date) {
        String time = formatDate(date, DEFAULT_DATE_SIMPLIFY, DEFAULT_DATE_PATTERN);
        return getWeek(formatDate(time, DEFAULT_DATE_PATTERN));
    }

    /**
     * 获取周几
     *
     * @return
     */
    public static String getWeek() {
        return getWeek(new Date());
    }

    /**
     * 获取周几
     *
     * @param date
     * @return
     */
    public static String getWeek(Date date) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 获取年份
     *
     * @return
     */
    public static int getYear() {
        return getYear(toDate());
    }

    /**
     * 获取年份
     *
     * @return
     */
    public static int getYear(String time) {
        String year = formatDate(time, DEFAULT_DATE_PATTERN, DEFAULT_YEAR);
        return Integer.valueOf(year);
    }

    /**
     * 获取月份
     *
     * @return
     */
    public static int getMonth() {
        return getMonth(toDate());
    }

    /**
     * 获取月份
     *
     * @return
     */
    public static int getMonth(String date) {
        String month = formatDate(date, DEFAULT_DATE_PATTERN, DEFAULT_MONTH);
        return Integer.valueOf(month);
    }

    /**
     * 获取年份
     *
     * @return
     */
    public static int getDay() {
        return getDay(toDate());
    }

    /**
     * 获取年份
     *
     * @return
     */
    public static int getDay(String time) {
        String day = formatDate(time, DEFAULT_DATE_PATTERN, DEFAULT_DAY);
        return Integer.valueOf(day);
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getDate() {
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        return format.format(new Date());
    }

    /**
     * 是否大于等于结束时间
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean isBeAndGtToDay(String startTime, String endTime) {
        boolean exceedToDay = false;
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        try {
            Date startDate = sdf.parse(startTime);
            Date endDate = sdf.parse(endTime);
            exceedToDay = startDate.getTime() >= endDate.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exceedToDay;
    }

    public static boolean isMoreToday(String date) {
        boolean moreToday = false;
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        try {
            Date startDate = sdf.parse(date);
            Date endDate = sdf.parse(toDate());
            moreToday = startDate.getTime() >= endDate.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return moreToday;
    }

    /**
     * 获取所有天数
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static int getAllDay(String startTime, String endTime) {
        Date begDate = formatDate(startTime, DEFAULT_DATE_PATTERN);
        Date endDate = formatDate(endTime, DEFAULT_DATE_PATTERN);
        return (int) ((endDate.getTime() - begDate.getTime()) / (24 * 60 * 60 * 1000)) + 1;
    }

    /**
     * 获取时间格式
     *
     * @param dateTime
     * @return
     */
    public static String getDateInfo(String dateTime) {
        Date date = formatDate(dateTime, DEFAULT_DATETIME_PATTERN);
        DateTime now = new DateTime();
        DateTime today_start = new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 0, 0, 0);
        DateTime today_end = today_start.plusDays(1);
        DateTime yesterday_start = today_start.minusDays(1);
        if (date.after(today_start.toDate()) && date.before(today_end.toDate())) {
            return String.format("今天 %s", new DateTime(date).toString(DEFAULT_TIME));
        } else if (date.after(yesterday_start.toDate()) && date.before(today_start.toDate())) {
            return String.format("昨天 %s", new DateTime(date).toString(DEFAULT_TIME));
        }
        return new DateTime(date).toString(DEFAULT_CN_DATE_TIME);
    }

    /**
     * 获取间隔时间
     *
     * @param date
     * @return
     */
    public static long getIntervalTime(String date) {
        long time = formatDate(date, DEFAULT_DATETIME_PATTERN).getTime();
        long intervalTime = time - (new Date()).getTime();
        return intervalTime;
    }

    public static String fromTime(long times) {
        SimpleDateFormat format = new SimpleDateFormat("mm:ss");
        return format.format(new Date(times));
    }

    /**
     * 判断当前系统时间是否在指定时间的范围内
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    public static boolean isCurrentInTimeScope(String startTime, String endTime) {
        int beginHour = Integer.valueOf(formatDate(startTime, DEFAULT_TIME, "HH"));
        int beginMin = Integer.valueOf(formatDate(startTime, DEFAULT_TIME, "mm"));
        int endHour = Integer.valueOf(formatDate(endTime, DEFAULT_TIME, "HH"));
        int endMin = Integer.valueOf(formatDate(endTime, DEFAULT_TIME, "mm"));
        return isCurrentInTimeScope(beginHour, beginMin, endHour, endMin);
    }

    /**
     * 判断当前系统时间是否在指定时间的范围内
     *
     * @param beginHour 开始小时，例如22
     * @param beginMin  开始小时的分钟数，例如30
     * @param endHour   结束小时，例如 8
     * @param endMin    结束小时的分钟数，例如0
     * @return true表示在范围内，否则false
     */
    public static boolean isCurrentInTimeScope(int beginHour, int beginMin, int endHour, int endMin) {
        boolean result = false;
        final long aDayInMillis = 1000 * 60 * 60 * 24;
        final long currentTimeMillis = System.currentTimeMillis();
        Time now = new Time();
        now.set(currentTimeMillis);
        Time startTime = new Time();
        startTime.set(currentTimeMillis);
        startTime.hour = beginHour;
        startTime.minute = beginMin;
        Time endTime = new Time();
        endTime.set(currentTimeMillis);
        endTime.hour = endHour;
        endTime.minute = endMin;
        if (!startTime.before(endTime)) {
            startTime.set(startTime.toMillis(true) - aDayInMillis);
            result = !now.before(startTime) && !now.after(endTime); // startTime
            Time startTimeInThisDay = new Time();
            startTimeInThisDay.set(startTime.toMillis(true) + aDayInMillis);
            if (!now.before(startTimeInThisDay)) {
                result = true;
            }
        } else {
            result = !now.before(startTime) && !now.after(endTime); // startTime
        }
        return result;
    }

    public static String getPmTime(String time) {
        String newTime = "";
        if (getTimeHour(time) > 12 && getTimeHour(time) < 18) {
            newTime = "下午  " + getSettingTime((getTimeHour(time) - 12) + ":" + getTimeMinute(time));
        } else if (getTimeHour(time) >= 18 && getTimeHour(time) <= 23) {
            newTime = "晚上  " + getSettingTime((getTimeHour(time) - 12) + ":" + getTimeMinute(time));
        } else {
            newTime = "早上  " + time;
        }
        return newTime;
    }

    public static int getTimeHour(String time) {
        return Integer.valueOf(formatDate(time, DEFAULT_TIME, "HH"));
    }

    public static int getTimeMinute(String time) {
        return Integer.valueOf(formatDate(time, DEFAULT_TIME, "mm"));
    }

    public static String getSettingTime(String time) {
        return formatDate(time, "H:m", DEFAULT_TIME);
    }
}
