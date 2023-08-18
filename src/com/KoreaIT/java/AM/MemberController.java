package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberController extends Controller {
  private List<Member> members;
  private Scanner scan;
  private String cmd;
  //  private String actionMethodName; // 없어도 OK
  public int lastUserId = 0; // public static int

  MemberController(Scanner scan) {
    this.members = new ArrayList<>(); // 배열로 생성, private(접근지정자) static
    this.scan = scan;
  }

  @Override
  public void doActiton(String cmd, String actionMethodName) {
    this.cmd = cmd;
//    this.actionMethodName = actionMethodName; // 없어도 OK

    switch (actionMethodName) {
      case "join":
        doJoin();
        break;
      case "login":
        doLogin();
        break;
      case "check":
        doCheckOnline();
        break;

      default:
        System.out.println("자세한 명령어는 help를 입력해주세요.");
        break;
    }
  }


  private void doJoin() {
    int userID = lastUserId + 1;
    lastUserId = userID;

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

  private void doLogin() {
    System.out.println("-- 로그인 --");

    System.out.printf("아이디 : ");
    String loginID = scan.nextLine();
    System.out.printf("비밀번호 : ");
    String loginPW = scan.nextLine();

    for (Member member : members) {
      if (member.id.equals(loginID)) {
        if (member.password.equals(loginPW)) {
          member.online = true;
          System.out.printf("%s님이 로그인 했습니다.\n", member.nickname);
        } else
          System.out.println("비밀번호를 다시 확인해주세요.");
      }
    }

  }

  private void doCheckOnline() {
    System.out.println("-- 상태 확인 --");

    String index[] = cmd.split(" ");
    if (index.length < 3) {
      System.out.println("아이디를 입력해야 합니다.");
      return;
    }

    String id = index[2];
    for (Member member : members) {
      if (member.id.equals(id)) {
        if (member.online == true)
          System.out.printf("%s님은 로그인 상태입니다.\n", member.id);
        else
          System.out.printf("%s님은 로그아웃 상태입니다.\n", member.id);
      }
    }
  }


}