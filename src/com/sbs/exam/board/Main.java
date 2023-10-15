package com.sbs.exam.board;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    int articlelastId = 0;
    Scanner sc = new Scanner(System.in);

    System.out.println("== 게시판 v 0.1.1 ==");
    System.out.println("== 프로그램 시작 ==");



    while(true)
    {
      System.out.printf("명령 ) ");
      String cmd = sc.nextLine();



          if(cmd.equals("exit")) {

            break;

         // }else if(cmd.equals("/usr/article/detail")){

          //  System.out.println("== 게시물 번호입력 ==");
          //  int num = sc.nextInt();
          //  HashMap<Integer,String> article = new HashMap<Integer,String>();

          //  System.out.println(article.get(num));




          }else if(cmd.equals("/usr/article/write")){

            System.out.println("== 게시물 등록 ==");

            System.out.printf("제목 : ");
            String title = sc.nextLine();

            System.out.printf("내용 : ");
            String body = sc.nextLine();

            Article article=new Article();

            int id = articlelastId +1;
            article.id=id;
            article.title=title;
            article.body=body;
            System.out.println("생성된 게시물 객체 :" + article);

           //HashMap<Integer,Article> articles = new HashMap<Integer,Article>();
           // articles.put(id,article);

            System.out.printf("%d 번 게시물이 등록되었습니다.\n",article.id);

          }else {
            System.out.printf("입력된 명령어 = %S\n", cmd);
          }

      }

    System.out.println("== 프로그램 종료  ==");
    sc.close();
    }


  }

  class Article{

      int id;
      String title;
      String body;
      Article(){

      }


      Article(int id,String title,String body)
      {

        this.id = id;
        this.title = title;
        this.body = body;



      }

    //  public String toString()
    //  {

    //    return title;

    //  }

  }
