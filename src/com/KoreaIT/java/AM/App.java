package com.KoreaIT.java.AM;

import java.util.Scanner;


public class App {
  public static void start() {
    System.out.println("== 프로그램 시작 ==");
    Scanner scan = new Scanner(System.in);
    MemberController memberController = new MemberController(scan);
    ArticleController articleController = new ArticleController(scan);

    articleController.makeTestData();


    while (true) {
      System.out.printf("\n명령어 ) ");
      String cmd = scan.nextLine().trim(); // trim == 양 끝의 공백 제거
      String[] cmdBits = cmd.split(" "); // if cmd = "article list"
      String controllerName = cmdBits[0];       // "article" list

      if (cmd.length() == 0) {
        System.out.println("명령어를 입력하세요.");
        continue;
      }

      if (cmd.equals("system exit")) {
        break;
      }

      else if (cmd.equals("help")) {
        System.out.println("member join - 회원가입");
        System.out.println("article list [keyword] - 게시글 목록 [키워드 검색]");
        System.out.println("article write - 게시글 작성");
        System.out.println("article view (number) - 게시글 열람");
        System.out.println("article delete (number) - 게시글 삭제");
        System.out.println("system exit - 프로그램 종료");
        continue;
      }

      else if (cmd.equals("article") || cmd.equals("member")) {
        System.out.println("자세한 명령어는 help를 입력해주세요.");
        continue;
      }
      else if (cmdBits.length == 1) {
        System.out.println("존재하지 않는 명령어입니다.");
        continue;
      }


      String actionMethodName = cmdBits[1];     // article "list"
      Controller controller = null;

      if (controllerName.equals("article"))
        controller = articleController;
      else if (controllerName.equals("member"))
        controller = memberController;

      if (controller == null) {
        System.out.println("존재하지 않는 명령어입니다.");
        continue;
      }

      controller.doActiton(cmd, actionMethodName);
    }



    System.out.println("== 프로그램 종료 ==");
  }
}




