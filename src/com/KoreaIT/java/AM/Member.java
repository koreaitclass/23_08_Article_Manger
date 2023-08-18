package com.KoreaIT.java.AM;

public class Member extends Dto {
  int userID;
  String id;
  String password;
  String nickname;
  String date;
  boolean online;
  Member(int userID, String id, String password, String nickname, String date) {
    this.userID = userID;
    this.id = id;
    this.password = password;
    this.nickname = nickname;
    this.date = date;
  }
}
