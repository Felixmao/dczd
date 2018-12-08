package com.dczd.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

/**
 * @program: com.dczd.common.util
 * @description: DateUtil
 * @author: hou yangkun
 * @create: 2018-11-29
 */
public class DateUtil {

    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public final static String yyyyMMdd = "yyyyMMdd";

    public final static String yyyy_MM_dd = "yyyy-MM-dd";

    public final static String ddMMyyHHmmss = "ddMMyyHHmmss";

    public final static String yyyyMMddHHmmss = "yyyyMMddHHmmss";

    public final static String yyyyMMddHHmm = "yyyyMMddHHmm";

    public final static String yyMMddHHmm = "yyMMddHHmm";

    public final static String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

    public final static String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";

    public final static String yyMMddHHmmss = "yyMMddHHmmss";

    public final static String yyyy_MM = "yyyy-MM";

    /**
     * 与当前时间相差多少秒
     *
     * @param date yyyy-mm-dd hh:mm:ss
     */
    public static Long diffNowDate(String date) {
        Date a = new Date();
        Date b = parseDate(date, yyyy_MM_dd_HH_mm_ss);
        if (b != null) {
            Long interval = (a.getTime() - b.getTime()) / 1000;
            return interval;
        }
        return Long.valueOf(0);
    }

    /**
     * 与当前时间相差多少秒
     *
     * @param date yyyy-mm-dd hh:mm:ss
     */
    public static Long diffNowDate(Integer date) {
        Date a = new Date();
        Long interval = (a.getTime() - (date * 1000)) / 1000;
        return interval;
    }

    /**
     * 获取规定模板时间
     *
     * @param datestr
     * @param datePattern
     * @return
     */
    public static Date parseDate(String datestr, String datePattern) {
        try {
            return new SimpleDateFormat(datePattern).parse(datestr);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }


    /**
     * 把日期字符串按格式字符串转换成Date类型
     *
     * @param fmtStr
     * @param dateStr
     * @return
     */
    public static Date paraseDate(String fmtStr, String dateStr)
    {
        DateFormat formatter = new SimpleDateFormat(fmtStr);
        try {
            return (Date) formatter.parse(dateStr);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 转换Date类型的格式
     * @param fmtStr
     * @param date
     * @return
     */
    public static Date paraseDate(String fmtStr, Date date) {
        Format formatter = new SimpleDateFormat(fmtStr);
        String dateStr = formatter.format(date);
        return paraseDate(fmtStr, dateStr);
    }

    /**
     * 获取指定格式的日期
     *
     * @param formatStr
     * @return
     */
    public static String getDate(Date date, String formatStr) {
        String strDateTime;
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        strDateTime = format.format(date);
        return strDateTime;
    }

    /**
     * 获取当月的1月1号，用于初始化页面时间控件
     * @return
     */
    public static String getCurMonthFirstDay(String format) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return getDate(cal.getTime(), format);
    }

    /**
     *
     * @param dbDate
     * @param format
     * @return
     */
    public static String getDBCurMonthFirstDay(Date dbDate, String format) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dbDate);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return getDate(cal.getTime(), format);
    }

    /**
     * 获取当前时间的字符串形式
     */
    public static String getCurDay(String format) {
        return getDate(new Date(), format);
    }

    /**
     * 两个日期相减，返回相差的天数
     */
    public static long subtract(String startDate, String endDate) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        long day = 0L;
        try {
            Date date = myFormatter.parse(startDate);
            Date mydate = myFormatter.parse(endDate);
            day = (date.getTime() - mydate.getTime()) / 86400000L;
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }
        return day;
    }

    /**
     * 求某个日期所在月的最后一天
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        if (null == date) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     *
     * 求某年某月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getLastDayOfMonth(int year, int month) {
        if (year < 1900) {
            return null;
        }
        if (month < 1 || month > 12) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     *
     * 获取某个时间n年后的时间
     *
     * @param dbDate
     * @param format
     * @param n，表示n年后
     * @return
     */
    public static String getNyearsLater(Date dbDate, String format, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dbDate);
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + n);
        return getDate(cal.getTime(), format);
    }

    /**
     * 返回给定日期的前一天
     * @param date
     * @return
     */
    public static Date getPreviousDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) - 1);
        return cal.getTime();
    }

    /**
     * 返回给定日期的前一天
     * @param date
     * @return
     */
    public static Date getLastNumDay(Date date,int num) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) - num);
        return cal.getTime();
    }

    /**
     * 返回给定日期的下一天
     * @param date
     * @return
     */
    public static Date getNextDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) + 1);
        return cal.getTime();
    }

    /**
     *
     * @param strDate
     * @return
     */
    public static String getNextDay(String strDate) {
        Date tmpDate = paraseDate(yyyy_MM_dd, strDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(tmpDate);
        cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) + 1);
        return getDate(cal.getTime(), yyyy_MM_dd);
    }

    /**
     * 返回给定日期的前一天
     * @param date
     * @return
     */
    public static String getPreviousDay(String strDate) {
        Date tmpDate = paraseDate(yyyy_MM_dd, strDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(tmpDate);
        cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) - 1);
        return getDate(cal.getTime(), yyyy_MM_dd);
    }

    /**
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static List<String> getGAPMonth(String beginDate, String endDate) {
        if(null == beginDate||null == endDate) {
            return null;
        }
        List<String> tableList = new ArrayList<String>();
        TreeSet tableSet = new TreeSet();
        Date begin = DateUtil.paraseDate(DateUtil.yyyy_MM_dd, beginDate);
        Date end = DateUtil.paraseDate(DateUtil.yyyy_MM_dd, endDate);
        Date lastEnd = DateUtil.getLastDayOfMonth(end);
        Date tmpDate = begin;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tmpDate);
        while(tmpDate.before(lastEnd)) {
            String strDate = DateUtil.getDate(calendar.getTime(), DateUtil.yyyy_MM_dd);
            strDate = strDate.substring(0,7).replaceAll("-", "");
            tableSet.add(strDate);
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+1);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            tmpDate = calendar.getTime();
        }
        tableList.addAll(tableSet);
        return tableList;
    }

    /**
     * 返回当前月的上一个月
     * @param date
     * @return
     */
    public static Date getPreviousMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    /**
     * 返回当前月的下一个月
     * @param date
     * @return
     */
    public static Date getNextMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    /**
     * 返回当前月的上一个月
     * @param date
     * @return
     */
    public static String getPreviousMonthStt(Date date) {
        return DateUtil.getDate(getPreviousMonth(date), yyyy_MM_dd);
    }

    /**
     * 返回当前月的上一个月
     * @param date
     * @return
     */
    public static String getPreviousMonthStr(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        return DateUtil.getDate(getPreviousMonth(cal.getTime()), yyyy_MM_dd);
    }

    /**
     *
     * @param date
     * @return
     */
    public static Date getNextHour(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) + 1);
        return cal.getTime();
    }

    /**
     *
     * @param date
     * @param minute
     * @return
     */
    public static Date getNextMinute(Date date,int minute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + minute);
        return cal.getTime();
    }

    /**
     *
     * @param date1
     * @param date2
     * @return
     * @throws ParseException
     */
    public static Boolean getMaxMinResultDate(String date1,String date2) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            long timeStart=sdf.parse(date1).getTime();
            long timeEnd=sdf.parse(date2).getTime();
            if(timeStart>=timeEnd) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return true;
    }

    /**
     *
     * @param d1
     * @param d2
     * @return
     */
    public static boolean isSameDay(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);
        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH) && c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH);
    }

}
