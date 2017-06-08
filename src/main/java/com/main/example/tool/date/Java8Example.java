package com.main.example.tool.date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Administrator on 2017/2/21.
 */
public class Java8Example {
    public static void  main(String[]args){
        //获取本地时间
        LocalDate date =LocalDate.now();
        System.out.println(date);
        //根据年月日参数生成日期
        LocalDate date1=LocalDate.of(2018,12,11);
        System.out.println(date1);
        //获取年,月,日
        int year=date.getYear();
        int month=date.getMonthValue();
        int day=date.getDayOfMonth();
        System.out.println(year+"年"+month+"月"+day+"日");
        int dayOfYear=date.getDayOfYear();
        System.out.println("获取当前时间是本年的第几天:"+dayOfYear);
        DayOfWeek week=date.getDayOfWeek();
        System.out.println("今天星期几:"+week);
        //日期相加
        date=date.plusDays(1000);
        System.out.println(date);
        date=date.plusMonths(3);
        System.out.println(date);
        date=date.plusYears(5);
        System.out.println(date);
        date=date.plusWeeks(2);
        System.out.println(date);
        //日期相减
        date=date.minusDays(1000);
        System.out.println(date);
        //日期格式化
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy:MM:dd");
        String df=date.format(formatter);
        System.out.println(df);
        //获取年月日时分秒
        LocalDateTime dateTime=LocalDateTime.of(2014,12,11,18,30,12);
        System.out.println(dateTime);
        //只取年月日
        LocalDate ld=dateTime.toLocalDate();
        LocalTime tm=dateTime.toLocalTime();
        System.out.println("ld:"+ld+" tm:"+tm);
        //日期格式化
        DateTimeFormatter formatter1=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
       String df1= dateTime.format(formatter1);
        System.out.println(df1);
        //获取最大的时间值
        LocalTime time=LocalTime.MAX;
        System.out.println(time);
        //获取时分秒
        int hour=time.getHour();
        int mm=time.getMinute();
        int ss=time.getSecond();
        System.out.println(hour+"时"+mm+"分"+ss+"秒");
       int  nano=time.getNano();
        System.out.println(nano);
    }
}
