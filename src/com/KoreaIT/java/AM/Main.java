package com.KoreaIT.java.AM;
import java.text.SimpleDateFormat;
import java.util.Date;
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
      System.out.printf("\n명령어 ) ");
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

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());

        Article article = new Article(id, title, content, formatter.format(date));
        articles.add(article);

        System.out.printf("%d번 게시글이 생성되었습니다.\n", lastId);
      }

      else if (cmd.startsWith("article view")) {
        String index[] = cmd.split(" ");
        if (index.length < 3) {
          System.out.println("게시글의 번호를 입력해야 합니다.");
          continue;
        }
        int id = Integer.parseInt(index[2]);

        System.out.println("-- 게시글 열람 --");

        if (id > articles.size() || id <= 0) {
          System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
        }
        else {
          Article article = articles.get(id - 1);
          System.out.printf("번호 : %d \n작성일 : %s \n제목 : %s \n내용 : %s \n", id, article.date, article.title, article.content);
        }
      }

      else if (cmd.startsWith("article delete")) {
        String index[] = cmd.split(" ");
        if (index.length < 3) {
          System.out.println("게시글의 번호를 입력해야 합니다.");
          continue;
        }
        int id = Integer.parseInt(index[2]);
        if (id > articles.size() || id <= 0) {
          System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
          continue;
        }

        Article article = articles.get(id - 1);
        System.out.printf("%d번 게시글을 정말로 삭제하시겠습니까? \n제목 : %s \n[Y/N]\n", id, article.title);
        String answer = scan.nextLine().trim(); // trim == 양 끝의 공백 제거
        if(answer.equals("Y") || answer.equals("y")) {
          articles.remove(id - 1);
          for (int i = articles.size() - 1; i >= id - 1; i--) {
            Article article_data = articles.get(i);
            article_data.Id--; // 게시글 Id를 하나씩 감소시킴
          }
          System.out.println("게시글을 정상적으로 삭제했습니다.");
        }
        else if (answer.equals("N") || answer.equals("n"))
          System.out.println("삭제를 취소했습니다.");
        else
          System.out.println("잘못된 입력으로 삭제가 취소됐습니다.");
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
  String date;

  Article(int id, String title, String content, String date) {
    this.Id = id;
    this.title = title;
    this.content = content;
    this.date = date;
  }


}