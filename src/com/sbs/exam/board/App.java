package com.sbs.exam.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class App {

   void run() {

    // ArrayList<Article>  articles = new ArrayList<Article>();
    Article lastArticle = null;
    Session session = Container.getSession();






    System.out.println("== 게시판 v 0.1.1 ==");
    System.out.println("== 프로그램 시작 ==");


    while (true) {

      Member logindMember = (Member)session.getAttribute("loginedMember");




      String promptName="명령어";
      if(logindMember !=null)
      {
        promptName = logindMember.loginId;
      }
      System.out.printf("%s )",promptName);

      String cmd = Container.sc.nextLine();

      Rq rq = new Rq(cmd);
      Map<String, String> params = rq.getParams();

      if (rq.getUrlPath().equals("exit")) {

        break;

      } else if (rq.getUrlPath().equals("/usr/article/list")) {


        Container.usrArticleController.actionList(rq);

      } else if (rq.getUrlPath().equals("/usr/article/detail")) {


        Container.usrArticleController.actionDetail(rq);


      } else if (rq.getUrlPath().equals("/usr/article/modify")) {


        Container.usrArticleController.actionModify(rq);


      } else if (rq.getUrlPath().equals("/usr/article/delete")) {


        Container.usrArticleController.actionDelete(rq);


      } else if (rq.getUrlPath().equals("/usr/article/write")) {

        Container.usrArticleController.actionWrite();


      } else if (rq.getUrlPath().equals("/usr/member/join")) {

        Container.usrMemberController.actionJoin(rq);


      } else if (rq.getUrlPath().equals("/usr/member/login")) {

        Container.usrMemberController.actionLogin(rq);


      } else if (rq.getUrlPath().equals("/usr/member/logout")) {

        Container.usrMemberController.actionLogOut(rq);


      } else {
        System.out.printf("입력된 명령어 = %s\n", cmd);
      }

    }

    System.out.println("== 프로그램 종료  ==");
    Container.sc.close();
  }

}
