package com.briup.run.common.util;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
  public static String getDateStr(Date date){
    DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
    return dateFormat.format(date);
  }
  public static boolean isSameDate(Date date1,Date date2){
    return DateUtils.getDateStr(date1).equals(DateUtils.getDateStr(date2));
  }
}
