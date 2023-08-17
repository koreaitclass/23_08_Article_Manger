package com.KoreaIT.java.AM;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class App {
  public static int lastId = 0;
  public static int lastUser = 0;
  private static List<Article> articles = articles = new ArrayList<>(); // 배열로 생성, private(접근지정자) static
  private static List<Member> members = members = new ArrayList<>(); // 배열로 생성, private(접근지정자) static
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

      else if (cmd.equals("help") || cmd.endsWith("help")){
        System.out.println("member join - 회원가입");
        System.out.println("article list [keyword] - 게시글 목록 [키워드 검색]");
        System.out.println("article write - 게시글 작성");
        System.out.println("article view (number) - 게시글 열람");
        System.out.println("article delete (number) - 게시글 삭제");
        System.out.println("system exit - 프로그램 종료");
      } else if (cmd.equals("article") || cmd.equals("member"))
        System.out.println("자세한 명령어는 help를 입력해주세요.");


      else if (cmd.equals("member join")) {
        int userID = lastUser + 1;
        lastUser = userID;

        System.out.println("-- 회원가입 --");
        String loginID;

        while (true) {
          boolean checkID = true;
          System.out.printf("회원 아이디 : ");
          loginID = scan.nextLine();
          for (Member member : members) {
            if (member.id.equals(loginID)) {
              System.out.println("해당 아이디는 사용이 불가능합니다.");
              checkID = false;
            }
          }
          if (checkID)
            break;
        }

        String loginPW;
        String checkPW;
        while (true) {
          System.out.printf("회원 비밀번호 : ");
          loginPW = scan.nextLine();
          System.out.printf("비밀번호 확인 : ");
          checkPW = scan.nextLine();

          if (checkPW.equals(loginPW))
            break;
          else
            System.out.println("비밀번호가 일치하지 않습니다.");
        }

        System.out.printf("닉네임 : ");
        String nickname = scan.nextLine();
        String regData = Util.getNowDateStr();

        Member member = new Member(userID, loginID, loginPW, nickname, regData);
        members.add(member);
        System.out.printf("%d번째 회원이 되었습니다.\n", userID);
      }


      else if (cmd.startsWith("article list")) {
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
            continue;
          }
          System.out.printf("%-4s|%5s  %-3s|%5s\n", "번호", "제목", "", "조회수");
          for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
            Article article = forPrintArticles.get(i);
            System.out.printf("%-4s|  %6s%-3s|%5s\n", article.id, article.title, "", article.viewCnt);
          }
        }

        else if (articles.size() == 0)
          System.out.println("게시글이 없습니다.");

        else {
          System.out.printf("%-4s|%5s  %-3s|%5s\n", "번호", "제목", "", "조회수");
          for (int i = articles.size() - 1; i >= 0; i--) {
            Article article = articles.get(i);
            System.out.printf("%-4s|  %6s%-3s|%5s\n", article.id, article.title, "", article.viewCnt);
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

        String regData = Util.getNowDateStr();

        Article article = new Article(id, title, content, regData);
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
      }



      else if (cmd.startsWith("article modify")) {
        String index[] = cmd.split(" ");
        if (index.length < 3) {
          System.out.println("게시글의 번호를 입력해야 합니다.");
          continue;
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



      else
        System.out.println("존재하지 않는 명령어입니다.");
    }

    System.out.println("== 프로그램 종료 ==");
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
    int index =  getArticleIndexById(id);
    if (index == -1)
      return null;
    else {
      return articles.get(index);
    }
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

