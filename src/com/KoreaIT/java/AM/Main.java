package com.KoreaIT.java.AM;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args)
  {
    System.out.println("== 프로그램 시작 ==");
    Scanner scan = new Scanner(System.in);
    int lastId = 0;

    List<Article> articles = new ArrayList<>(); // 배열로 생성

//  게시글 열람 및 삭제 관련
//  split()
//  startsWith()
//  "arraylist 요소" 삭제 검색
//  "자바 현재 날짜"


    while (true) {
      System.out.printf("명령어 ) ");
      String cmd = scan.nextLine().trim(); // trim == 양 끝의 공백 제거

      if (cmd.length() == 0) {
        System.out.println("명령어를 입력하세요.");
        continue;
      }

      if (cmd.equals("system exit"))
        break;
      else if (cmd.equals("help")) {
        System.out.println("article list - 게시글 목록");
        System.out.println("article write - 게시글 작성");
        System.out.println("article view (int) - 게시글 열람");
        System.out.println("article delete (int) - 게시글 삭제");
        System.out.println("system exit - 프로그램 종료");
      }

      else if (cmd.equals("article list")) {
        System.out.println("-- 게시글 목록 --");
        if (articles.size() == 0)
          System.out.println("게시글이 없습니다.");
        else {
          for (int i = articles.size() - 1; i >= 0; i--) {
            Article article = articles.get(i);
            System.out.printf("%d |  %s\n", article.Id, article.title);
          }
        }
      }

      else if (cmd.equals("article write")) {
        int id = lastId + 1;
        lastId = id;

        System.out.println("-- 게시글 작성 --");
        System.out.printf("제목 : ");
        String title = scan.nextLine();
        System.out.printf("내용 : ");
        String content = scan.nextLine();

        Article article = new Article(id, title, content);
        articles.add(article);

        System.out.printf("%d번 게시글이 생성되었습니다.\n", lastId);
      }

      else if (cmd.equals("article view")) {
        System.out.printf("게시글 번호 입력 : ");
        int id = scan.nextInt();
        System.out.println("-- 게시글 열람 --");
        if (id > articles.size()) {
          System.out.println("해당 번호의 게시글은 존재하지 않습니다.");
        }
        else {
          Article article = articles.get(id - 1);
          System.out.printf("제목 : %s\n내용 : %s\n", article.title, article.content);
        }
      }

      else if (cmd.equals("article delete")) {

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

  Article(int id, String title, String content) {
    this.Id = id;
    this.title = title;
    this.content = content;
  }


}