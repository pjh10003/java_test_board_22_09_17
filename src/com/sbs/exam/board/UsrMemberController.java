package com.sbs.exam.board;


import java.util.ArrayList;
import java.util.List;



public class UsrMemberController {

   private int memberlastId;
  private List<Member> members;


  UsrMemberController() {
    memberlastId = 0;
    members = new ArrayList<>();


    make_Test_data();

    if (members.size() > 0) {
      memberlastId = members.get(members.size() - 1).id;


    }

  }

  void make_Test_data() {

    for (int i = 1; i < 3; i++) {
      members.add(new Member(i, "user" + i, "user" + i));
    }


  }


  public void actionJoin(Rq rq) {


    System.out.println("== 회원 가입 ==");

    System.out.printf("로그인 아이디 : ");
    String loginId = Container.sc.nextLine();

    System.out.printf("비밀번호 : ");
    String loginPw = Container.sc.nextLine();


    System.out.printf("비밀번호확인 : ");
    String loginPwConfirm = Container.sc.nextLine();


    if (loginPw.equals(loginPwConfirm)==false)
    {
      System.out.println("비밀번호가 일치 하지 않습니다");
      return;
    }



    int id = ++memberlastId;
    memberlastId = id;
    Member member = new Member(id, loginId, loginPw);
    //System.out.println("생성된 게시물 객체 :" + article);

    members.add(member);
    System.out.printf("%s 님 가입을 환영합니다\n",member.loginId) ;
    System.out.printf("%d번 회원이 생성되었습니다.\n",member.id) ;




  }

  public void actionLogin(Rq rq) {


    System.out.println("== 회원 가입 ==");

    System.out.printf("로그인 아이디 : ");
    String loginId = Container.sc.nextLine().trim();


    Member member = getMemberLoginId(loginId);

    if(member == null)
    {

      System.out.println("해당 회원은 존재 하지 않습니다");
      return;

    }




    System.out.printf("비밀번호 : ");
    String loginPw = Container.sc.nextLine().trim();


    if(member.loginPw.equals(loginPw)==false) {

      System.out.println("비밀번호가 일치하지 않습니다.");
      return;
    }

    if(loginId.length() ==0)
    {
      System.out.println("로그인 아이디를 입력해주세요");
      return;
    }


    if(loginPw.length() ==0)
    {
      System.out.println("로그인 비밀번호를 입력해주세요");
      return;
    }


    rq.setSessionAttr("loginedMember",member);

    System.out.printf("%s 님 환영합니다\n",loginId);




  }

  private Member getMemberLoginId(String login) {

        for(Member member : members){
          if(member.loginId.equals(login))
          {
            return member;
          }
        }
        return null;



  }

  public void actionLogOut(Rq rq) {


    Member logindMember = (Member)Container.session.getAttribute("loginedMember");



    if(logindMember != null) {
      rq.removeSessionAttr("loginedMember");
      System.out.println("로그아웃 되었습니다");
      return;
    }else{

      System.out.println("로그인후 이용해주세요");
      return;


    }



  }
}
