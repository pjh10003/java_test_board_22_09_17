package com.sbs.exam.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UsrArticleController {


  int articlelastId;
  List<Article> articles;


  UsrArticleController() {
    articlelastId = 0;
    articles = new ArrayList<>();


    make_Test_data();

    if (articles.size() > 0) {
      articlelastId = articles.get(articles.size() - 1).id;

    }

  }

  void make_Test_data() {

    for (int i = 1; i < 101; i++) {
      articles.add(new Article(i, "제목" + i, "내용" + i));
    }


  }


  public void actionDelete(Rq rq) {



    String title = "";
    String body = "";


    Map<String, String> params = rq.getParams();


    int id = rq.getIntParam("id", 0);


    if (id == 0) {
      System.out.println("id를 올바르게 입력해주세요");
      return;
    }


    if (articles.isEmpty()) {
      System.out.println("존재 하지 않는 게시물입니다.");
      return;

    }


    if (id > articles.size()) {
      System.out.println("존재 하지 않는 게시물입니다11.");
      return;

    }

    Article foundArticle = null;

    for (Article article : articles) {
      if (article.id == id) {
        foundArticle = article;
        break;
      }


    }

    articles.remove(foundArticle);

    System.out.printf("%d 번 게시물을 삭제 하였습니다", id);
  }

  public void actionModify(Rq rq) {



    String title = "";
    String body = "";


    Map<String, String> params = rq.getParams();


    int id = rq.getIntParam("id", 0);


    if (id == 0) {
      System.out.println("id를 올바르게 입력해주세요");
      return;
    }


    if (articles.isEmpty()) {
      System.out.println("존재 하지 않는 게시물입니다.");
      return;

    }


    if (id > articles.size()) {
      System.out.println("존재 하지 않는 게시물입니다11.");
      return;

    }


    Article article = articles.get(id - 1);


    System.out.println("==게시물수정==");
    System.out.println("==새제목==");
    article.title = Container.sc.nextLine();

    System.out.println("==새내용==");
    article.body = Container.sc.nextLine();


    System.out.printf("%d 번 게시물을 수정 하였습니다", id);


  }


  public void actionWrite() {

    System.out.println("== 게시물 등록 ==");

    System.out.printf("제목 : ");
    String title = Container.sc.nextLine();

    System.out.printf("내용 : ");
    String body = Container.sc.nextLine();


    int id = ++articlelastId;
    articlelastId = id;
    Article article = new Article(id, title, body);
    //System.out.println("생성된 게시물 객체 :" + article);

    articles.add(article);
    System.out.printf("%d 번 게시물이 등록되었습니다.\n", id);
  }


  public void actionDetail(Rq rq) {


    int id = rq.getIntParam("id", 0);


    if (id == 0) {
      System.out.println("id를 올바르게 입력해주세요");
      return;
    }


    if (id > articles.size()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }


    Article article = articles.get(id - 1);
    System.out.println("== 게시물 상세내용==");
    System.out.printf("번호 : %s\n", article.id);
    System.out.printf("제목 : %s\n", article.title);
    System.out.printf("내용 : %s\n", article.body);


  }

  public void actionList(Rq rq) {

    System.out.println("== 게시물 리스트==");
    System.out.println("-------------------");
    System.out.println("번호 / 제목");
    System.out.println("-------------------");

    // 검색시작

    List<Article> filterdArticles = new ArrayList<>();
    List<Article> sortedArticles = articles;


    Map<String, String> params = rq.getParams();
    if (params.containsKey("searchKeyword")) {

      String searchKeyword = rq.getParam("searchKeyword", "");

      if (searchKeyword.length() > 0) {
        for (Article article : articles) {
          if (article.body.contains(searchKeyword) || article.title.contains(searchKeyword)) {
            filterdArticles.add(article);
          }
          sortedArticles = filterdArticles;
        }
      }

    }


    String orderBy = rq.getParam("orderBy","");

    boolean orderByIdDesc = orderBy.equals("idDesc");

    if (orderByIdDesc) {

      sortedArticles = Util.reverseList(sortedArticles);
    }

    for (Article article : sortedArticles) {
      System.out.printf("%d /%s\n", article.id, article.title);
    }


    System.out.println("-------------------");


  }


}
