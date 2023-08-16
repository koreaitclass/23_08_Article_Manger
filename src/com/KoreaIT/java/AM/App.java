package com.KoreaIT.java.AM;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class App {
  public static int lastId = 0;
  private static List<Article> articles = articles = new ArrayList<>(); // 배열로 생성, private(접근지정자) static
  public static void start() {
    System.out.println("== 프로그램 시작 ==");
    Scanner scan = new Scanner(System.in);

    makeTestData();


    while (true) {
      System.out.printf("\n명령어 ) ");
      String cmd = scan.nextLine().trim(); // trim == 양 끝의 공백 제거

      if (cmd.length() == 0) {
        System.out.println("명령어를 입력하세요.");
        continue;
      }

      if (cmd.equals("system exit"))
        break;

      else if (cmd.equals("help") || cmd.equals("article help")) {
        System.out.println("article list - 게시글 목록");
        System.out.println("article write - 게시글 작성");
        System.out.println("article view (int) - 게시글 열람");
        System.out.println("article delete (int) - 게시글 삭제");
        System.out.println("system exit - 프로그램 종료");
      } else if (cmd.equals("article"))
        System.out.println("자세한 명령어는 help를 입력해주세요.");

      else if (cmd.equals("article list")) {
        System.out.println("-- 게시글 목록 --");
        if (articles.size() == 0)
          System.out.println("게시글이 없습니다.");
        else {
          System.out.printf("%-4s|%5s  %-3s|%5s\n", "번호", "제목", "", "조회수");
          for (int i = articles.size() - 1; i >= 0; i--) {
            Article article = articles.get(i);
            System.out.printf("%-4s|  %6s%-3s|%5s\n", article.id, article.title, "", article.viewCnt);
          }
        }
      } else if (cmd.equals("article write")) {
        int id = lastId + 1;
        lastId = id;

        System.out.println("-- 게시글 작성 --");
        System.out.printf("제목 : ");
        String title = scan.nextLine();
        System.out.printf("내용 : ");
        String content = scan.nextLine();

        String regData = Util.getNowDateStr();

        Article article = new Article(id, title, content, regData);
        articles.add(article);

        System.out.printf("%d번 게시글이 생성되었습니다.\n", lastId);
      } else if (cmd.startsWith("article view")) {
        String index[] = cmd.split(" ");
        if (index.length < 3) {
          System.out.println("게시글의 번호를 입력해야 합니다.");
          continue;
        }
        int id = Integer.parseInt(index[2]);

        System.out.println("-- 게시글 열람 --");
        boolean is_exist = false;
        int i;
        for (i = 0; i < articles.size(); i++) {
          Article article = articles.get(i);
          if (article.id == id) {
            is_exist = true;
            break;
          }
        }

        if (is_exist) {
          Article article = articles.get(i);
          article.viewCnt++; // 조회수 상승

          System.out.printf("번호 : %d\n", id);
          System.out.printf("작성일 : %s\n", article.date);
          if (article.modifyDate != null) {
            System.out.printf("수정일 : %s\n", article.modifyDate);
          }
          System.out.printf("제목 : %s\n", article.title);
          System.out.printf("내용 : %s\n", article.content);
          System.out.printf("조회수 : %d\n", article.viewCnt);
        }

        else
          System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
      }

      else if (cmd.startsWith("article delete")) {
        String index[] = cmd.split(" ");
        if (index.length < 3) {
          System.out.println("게시글의 번호를 입력해야 합니다.");
          continue;
        }
        int id = Integer.parseInt(index[2]);
        boolean is_exist = false;
        int i;

        for (i = 0; i < articles.size(); i++) {
          Article article = articles.get(i);
          if (article.id == id) {
            is_exist = true;
            break;
          }
        }

        if (is_exist) {
          Article article = articles.get(i);
          System.out.printf("%d번 게시글을 정말로 삭제하시겠습니까? \n제목 : %s \n[Y/N]\n", id, article.title);
          String answer = scan.nextLine().trim(); // trim == 양 끝의 공백 제거
          if (answer.equals("Y") || answer.equals("y")) {
            articles.remove(i);
            System.out.println("게시글을 정상적으로 삭제했습니다.");
          } else if (answer.equals("N") || answer.equals("n"))
            System.out.println("삭제를 취소했습니다.");
          else
            System.out.println("잘못된 입력으로 삭제가 취소됐습니다.");
        } else
          System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
      } else if (cmd.startsWith("article modify")) {
        String index[] = cmd.split(" ");
        if (index.length < 3) {
          System.out.println("게시글의 번호를 입력해야 합니다.");
          continue;
        }
        int id = Integer.parseInt(index[2]);
        boolean is_exist = false;
        int i;
        for (i = 0; i < articles.size(); i++) {
          Article article = articles.get(i);
          if (article.id == id) {
            is_exist = true;
            break;
          }
        }

        System.out.println("-- 게시글 수정 --");
        if (is_exist) {
          Article article = articles.get(i);
          System.out.printf("제목 : ");
          String title = scan.nextLine();
          System.out.printf("내용 : ");
          String content = scan.nextLine();
          String regData = Util.getNowDateStr(); // 수정 날짜

          article.modify(id, title, content, regData);
          System.out.printf("%d번 게시글이 수정되었습니다.\n", id);
        } else
          System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
      } else
        System.out.println("존재하지 않는 명령어입니다.");
    }

    System.out.println("== 프로그램 종료 ==");
  }

  private static void makeTestData() {
    System.out.println("테스트 데이터 생성");

    for (int i = 0; i < 4; i++) {
      int id = lastId + 1;
      lastId = id;
      String title = "테스트 " + id;
      String content = "내용 " + id;

      String regData = Util.getNowDateStr();

      Article article = new Article(id, title, content, regData, id*10);
      articles.add(article);
    }
  }
}