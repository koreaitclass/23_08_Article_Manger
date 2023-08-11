package com.KoreaIT.java.AM;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args)
  {
    System.out.println("== 프로그램 시작 ==");
    Scanner scan = new Scanner(System.in);


    Article articles = new Article(); // 생성




    while (true) {
      System.out.printf("명령어 ) ");
      String cmd = scan.nextLine();

      if (cmd.length() == 0) {
        System.out.println("명령어를 입력하세요.");
        continue;
      }

      if (cmd.equals("system exit"))
        break;
      else if (cmd.equals("help")) {
        System.out.println("article list - 게시글 목록");
        System.out.println("article write - 게시글 작성");
        System.out.println("system exit - 프로그램 종료");
      }

      else if (cmd.equals("article list")) {
        System.out.println("-- 게시글 목록 --");
        if (articles.lastId == 0)
          System.out.println("게시글이 없습니다.");
        else {
          System.out.printf("%d) %s\n", articles.Id, articles.title);
        }
      }
      else if (cmd.equals("article write")) {
        System.out.println("-- 게시글 작성 --");
        System.out.printf("제목 : ");
        articles.title = scan.nextLine();
        System.out.printf("내용 : ");
        articles.content = scan.nextLine();

        articles.Id++;
        articles.lastId = articles.Id;
        System.out.printf("%d번 게시글이 생성되었습니다.\n", articles.lastId);
      }

      else if (cmd.equals("article view")) {
        System.out.printf("게시글 번호 입력 : ");
        int id = scan.nextInt();
        System.out.println("-- 게시글 열람 --");
        System.out.printf("제목 : %s \n내용 : %s\n", articles.title, articles.content);
        continue;
      }

      else
        System.out.println("존재하지 않는 명령어입니다.");
    }

    System.out.println("== 프로그램 종료 ==");
  }
}

class Article {
  int lastId; // 0
  int Id;
  String title;
  String content;

  void write() {
  }


}