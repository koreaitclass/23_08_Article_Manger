package com.KoreaIT.java.AM;


public class Article extends Dto {
  int lastId; // 미사용이네
  int id;
  String title;
  String content;
  String date;
  String modifyDate;
  int viewCnt = 0;

  Article(int id, String title, String content, String date) {
    this(id, title, content, date, 0);
  }
  Article(int id, String title, String content, String date, int viewCnt) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.date = date;
    this.viewCnt = viewCnt;
  }

  void modify(int id, String title, String content, String modifyDate) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.modifyDate = modifyDate;
  }

}
