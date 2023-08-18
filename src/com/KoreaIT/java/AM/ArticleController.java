package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ArticleController extends Controller{
  private static List<Article> articles;
  private Scanner scan;
  private String cmd;
//  private String actionMethodName; // 없어도 OK
  public int lastId = 0;
  ArticleController(Scanner scan) {
    this.articles = new ArrayList<>(); // 배열로 생성, private(접근지정자) static
    this.scan = scan;
  }

  @Override
  public void doActiton(String cmd, String actionMethodName) {
    this.cmd = cmd;
//    this.actionMethodName = actionMethodName; // 없어도 OK

    switch (actionMethodName) {
      case "list":
        doList();
        break;
      case "write":
        doWrite();
        break;
      case "view":
        doView();
        break;
      case "delete":
        doDelete();
        break;
      case "modify":
        doModify();
        break;

      default:
        System.out.println("자세한 명령어는 help를 입력해주세요.");
        break;
    }
  }


  private void doList() {
    System.out.println("-- 게시글 목록 --");
    String keyword = cmd.substring("article list".length()).trim();
    List<Article> forPrintArticles = articles;

    if (keyword.length() > 0) {
      forPrintArticles = new ArrayList<>();
      System.out.printf("검색어 : %s\n", keyword);
      for (Article article : articles) {
        if (article.title.contains(keyword)) {
          forPrintArticles.add(article);
        }
      }
      if (forPrintArticles.size() == 0) {
        System.out.println("검색한 게시글이 존재하지 않습니다.");
        return;
      }
      System.out.printf("%-4s|%5s  %-3s|%5s\n", "번호", "제목", "", "조회수");
      for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
        Article article = forPrintArticles.get(i);
        System.out.printf("%-4s|  %6s%-3s|%5s\n", article.id, article.title, "", article.viewCnt);
      }
    } else if (articles.size() == 0)
      System.out.println("게시글이 없습니다.");

    else {
      System.out.printf("%-4s|%5s  %-3s|%5s\n", "번호", "제목", "", "조회수");
      for (int i = articles.size() - 1; i >= 0; i--) {
        Article article = articles.get(i);
        System.out.printf("%-4s|  %6s%-3s|%5s\n", article.id, article.title, "", article.viewCnt);
      }
    }
  }

  private void doWrite() {
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
  }

  private void doView() {
    String index[] = cmd.split(" ");
    if (index.length < 3) {
      System.out.println("게시글의 번호를 입력해야 합니다.");
      return;
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
    } else
      System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
  }

  private void doDelete() {
    String index[] = cmd.split(" ");
    if (index.length < 3) {
      System.out.println("게시글의 번호를 입력해야 합니다.");
      return;
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
  }

  private void doModify() {
    String index[] = cmd.split(" ");
    if (index.length < 3) {
      System.out.println("게시글의 번호를 입력해야 합니다.");
      return;
    }

    int id = Integer.parseInt(index[2]);
    int Index = getArticleIndexById(id);

    System.out.println("-- 게시글 수정 --");
    if (Index == -1)
      System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
    else {
      Article article = articles.get(Index);
      System.out.printf("제목 : ");
      String title = scan.nextLine();
      System.out.printf("내용 : ");
      String content = scan.nextLine();
      String regData = Util.getNowDateStr(); // 수정 날짜

      article.modify(id, title, content, regData);
      System.out.printf("%d번 게시글이 수정되었습니다.\n", id);
    }
  }


  private static int getArticleIndexById(int id) {
    int i = 0;
    for (Article article : articles) {
      if (article.id == id) {
        return i;
      }
      i++;
    }

    return -1;
  }

  private Article getArticleById(int id) {
    int index = getArticleIndexById(id);
    if (index == -1)
      return null;
    else {
      return articles.get(index);
    }
  }

  public void makeTestData() { // public 대신 static도 가능
    System.out.println("테스트 데이터 생성");

    for (int i = 0; i < 4; i++) {
      int id = lastId + 1;
      lastId = id;
      String title = "테스트 " + id;
      String content = "내용 " + id;

      String regData = Util.getNowDateStr();

      Article article = new Article(id, title, content, regData, id * 10);
      articles.add(article);
    }
  }



}

