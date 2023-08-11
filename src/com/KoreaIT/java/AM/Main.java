package com.KoreaIT.java.AM;
import java.util.Scanner;

public class Main {
  public static void main(String[] args)
  {
    System.out.println("== 프로그램 시작 ==");
    Scanner scan = new Scanner(System.in);


    System.out.printf("명령어 ) ");
    String cmd = scan.next();

    System.out.printf("입력된 명령어 : %s\n", cmd);
    scan.close();

    System.out.println("== 프로그램 종료 ==");
  }
}