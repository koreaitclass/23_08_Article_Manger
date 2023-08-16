package com.KoreaIT.java.AM;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
  //현재 날짜 시간
  public static String getNowDateStr() {

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date(System.currentTimeMillis()); // Date(매개변수 < 없어도 됨)

    return formatter.format(date);
  }

}
