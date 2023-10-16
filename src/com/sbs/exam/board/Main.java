package com.sbs.exam.board;

import java.util.*;


public class Main {

  static int articlelastId =0;
  static List<Article> articles = new ArrayList<>();
  static void make_Test_data() {

    for (int i = 1; i < 101; i++) {
      articles.add(new Article(i, "제목" + i, "내용" + i));
    }

  }


  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    // ArrayList<Article>  articles = new ArrayList<Article>();
    Article lastArticle = null;




    make_Test_data();
    if (articles.size() > 0) {
      articlelastId = articles.get(articles.size() - 1).id;


      //   articles.add()

    }

    System.out.println("== 게시판 v 0.1.1 ==");
    System.out.println("== 프로그램 시작 ==");


    while (true) {
      System.out.printf("명령 ) ");
      String cmd = sc.nextLine();

      Rq rq = new Rq(cmd);
      Map<String, String> params = rq.getParams();

      if (rq.getUrlPath().equals("exit")) {

        break;

      } else if (rq.getUrlPath().equals("/usr/article/list")) {


        actionUsrArticleList(rq);

      } else if (rq.getUrlPath().equals("/usr/article/detail")) {


        actionUsrArticleDetail(rq);


      } else if (rq.getUrlPath().equals("/usr/article/modify")) {


        actionUsrArticleModify(rq, sc);


      } else if (rq.getUrlPath().equals("/usr/article/delete")) {


        actionUsrArticleDelete(rq);


      } else if (rq.getUrlPath().equals("/usr/article/write")) {

        actionUsrArticleWrite(sc);
        articlelastId++;

      } else {
        System.out.printf("입력된 명령어 = %s\n", cmd);
      }

    }

    System.out.println("== 프로그램 종료  ==");
    sc.close();
  }

  private static void actionUsrArticleDelete(Rq rq) {


    int id = 0;
    String title = "";
    String body = "";


    Map<String, String> params = rq.getParams();


    if (params.containsKey("id") == false) {

      System.out.println("아이디를 입력해주세요");
      return;

    }

    try {
      id = Integer.parseInt(params.get("id"));

    } catch (NumberFormatException e) {

      System.out.println("정수를 입력해주세요");
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

    for(Article article :articles)
    {
        if(article.id == id)
        {
          foundArticle=article;
          break;
        }



    }

    articles.remove(foundArticle);

    System.out.printf("%d 번 게시물을 삭제 하였습니다", id);
  }

  private static void actionUsrArticleModify(Rq rq, Scanner sc) {


    int id = 0;
    String title = "";
    String body = "";


    Map<String, String> params = rq.getParams();


    if (params.containsKey("id") == false) {

      System.out.println("아이디를 입력해주세요");
      return;

    }

    try {
      id = Integer.parseInt(params.get("id"));

    } catch (NumberFormatException e) {

      System.out.println("정수를 입력해주세요");
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
    article.title = sc.nextLine();

    System.out.println("==새내용==");
    article.body = sc.nextLine();


    System.out.printf("%d 번 게시물을 수정 하였습니다", id);


  }


  private static void actionUsrArticleWrite(Scanner sc) {

    System.out.println("== 게시물 등록 ==");

    System.out.printf("제목 : ");
    String title = sc.nextLine();

    System.out.printf("내용 : ");
    String body = sc.nextLine();

    Map<String, String> params = rq.getParams();

    int id = ++articlelastId;
    articlelastId = id;
    Article article = new Article(id, title, body);
    //System.out.println("생성된 게시물 객체 :" + article);

    articles.add(article);
    System.out.printf("%d 번 게시물이 등록되었습니다.\n", id);
  }


  private static void actionUsrArticleDetail(Rq rq) {


    int id = 0;
    Map<String, String> params = rq.getParams();
    try {

      id = Integer.parseInt(params.get("id"));

    } catch (NumberFormatException e) {

      System.out.println("id를 정수 형태로 입력하세요!");
      return;

    }

    if (params.containsKey("id") == false) {

      System.out.println("id를 입력해주세요");
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

  private static void actionUsrArticleList(Rq rq) {

    System.out.println("== 게시물 리스트==");
    System.out.println("-------------------");
    System.out.println("번호 / 제목");
    System.out.println("-------------------");

    // 검색시작

    List<Article> filterdArticles = new ArrayList<>();
    List<Article> sortedArticles = articles;
    String searchKeywrod = null;


    Map<String, String> params = rq.getParams();
    if (params.containsKey("searchKeyword")) {

      searchKeywrod = params.get("searchKeyword");

      for (Article article : articles) {
        if (article.body.contains(searchKeywrod) || article.title.contains(searchKeywrod)) {
          filterdArticles.add(article);
        }
        sortedArticles = filterdArticles;
      }

    }


    boolean orderByIdDesc = true;


    if (params.containsKey("orderBy") && params.get("orderBy").equals("idAsc")) {
      // Collections.reverse(articles);
      orderByIdDesc = false;
    }


    if (orderByIdDesc) {

      sortedArticles = Util.reverseList(sortedArticles);
    }

    for (Article article : sortedArticles) {
      System.out.printf("%d /%s\n", article.id, article.title);
    }


    System.out.println("-------------------");


  }


}

class Article {

  int id;
  String title;
  String body;

  Article() {

  }


  Article(int id, String title, String body) {

    this.id = id;
    this.title = title;
    this.body = body;


  }


  @Override
  public String toString() {

    return String.format("id : %d,title:\"%s\",body:\"%s\"", this.id, this.title, this.body);

  }

}


class Rq {

  String url;
  Map<String, String> params = null;
  String urlPath;

  Rq(String url) {
    this.url = url;
    params = Util.getParamsFromUrl(url);
    urlPath = Util.getPathFromUrl(url);
  }

  public Map<String, String> getParams() {

    return params;
  }

  public String getUrlPath() {


    return urlPath;

  }
}


class Util {


  public static Map<String, String> getParamsFromUrl(String url) {
    Map<String, String> params = new HashMap<>();
    String[] urlBits = url.split("\\?", 2);

    if (urlBits.length == 1) {
      return params;
    }

    for (String bit : urlBits[1].split("&", 2)) {

      String[] bitBits = bit.split("=", 2);

      if (bitBits.length == 1) {
        continue;
      }

      params.put(bitBits[0], bitBits[1]);


    }
    return params;
  }

  public static String getPathFromUrl(String url) {

    String[] urlPath = url.split("\\?", 2);
    return urlPath[0];
  }

  public static <T> List<T> reverseList(List<T> list) {
    List<T> reverse = new ArrayList<>(list.size());

    for (int i = list.size() - 1; i >= 0; i--) {


      reverse.add(list.get(i));


    }

    return reverse;


  }
}
