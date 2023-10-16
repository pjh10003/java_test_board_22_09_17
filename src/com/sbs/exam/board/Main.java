package com.sbs.exam.board;
import java.sql.SQLOutput;
import java.util.*;


public class Main {

  static void make_Test_data(List<Article> articles) {

    articles.add(new Article(1,"제목1","내용1"));
    articles.add(new Article(2,"제목2","내용2"));
    articles.add(new Article(3,"제목3","내용3"));



  }




  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
   // ArrayList<Article>  articles = new ArrayList<Article>();
    Article lastArticle = null;
    int articlelastId = 0;
    List<Article> articles = new ArrayList<>();


    make_Test_data(articles);
    if(articles.size() > 0 ) {
      articlelastId = articles.get(articles.size()-1).id;


   //   articles.add()

    }

    System.out.println("== 게시판 v 0.1.1 ==");
    System.out.println("== 프로그램 시작 ==");





    while(true)
    {
      System.out.printf("명령 ) ");
      String cmd = sc.nextLine();




      Rq rq = new Rq(cmd);
      Map<String,String> params = rq.getParams();



          if(rq.getUrlPath().equals("exit")) {

            break;


          }else if(rq.getUrlPath().equals("/usr/article/list")){


            System.out.println("== 게시물 리스트==");
            System.out.println("-------------------");
            System.out.println("번호 / 제목");
            System.out.println("-------------------");


            for(int i = articles.size()-1  ;i >= 0;i--){
              Article article = articles.get(i);
              System.out.printf("%d /%s\n",article.id,article.title);

            }

            System.out.println("-------------------");

          }else if(rq.getUrlPath().equals("/usr/article/detail")){

            // System.out.println("== 게시물 번호입력 ==");
           // int num = sc.nextInt();
            int id =0;
            try {

               id = Integer.parseInt(params.get("id"));

            }catch(NumberFormatException e){

              System.out.println("id를 정수 형태로 입력하세요!");
              continue;

            }

            if(params.containsKey("id")==false) {

              System.out.println("id를 입력해주세요");
            }

            if(id > articles.size()) {
              System.out.println("게시물이 존재하지 않습니다.");
              continue;
            }



            //Article article = articles.get(articles.size() - 1);
            Article article = articles.get(id -1);

            System.out.println("== 게시물 상세내용==");
            System.out.printf("번호 : %s\n", article.id);
            System.out.printf("제목 : %s\n", article.title);
            System.out.printf("내용 : %s\n", article.body);





            //System.out.println(articles.get(articlelastId-1));










          }else if(rq.getUrlPath().equals("/usr/article/write")){

            System.out.println("== 게시물 등록 ==");

            System.out.printf("제목 : ");
            String title = sc.nextLine();

            System.out.printf("내용 : ");
            String body = sc.nextLine();


            int id = articlelastId +1;
            articlelastId = id;
            Article article  = new Article(id,title,body);
            //System.out.println("생성된 게시물 객체 :" + article);
            lastArticle =article;
            articles.add(article);
            System.out.printf("%d 번 게시물이 등록되었습니다.\n",id);

          }else {
            System.out.printf("입력된 명령어 = %s\n", cmd);
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


     @Override
     public  String toString()
      {

        return String.format("id : %d,title:\"%s\",body:\"%s\"",this.id,this.title,this.body);

      }

  }


class Rq{

  String url;
  Map<String,String> params = null;
  String urlPath;
  Rq(String url) {
    this.url = url;
    params = Util.getParamsFromUrl(url);
    urlPath = Util.getPathFromUrl(url);
  }

  public  Map<String, String> getParams() {

    return params;
  }

  public String getUrlPath() {


    return urlPath;

  }
}


class Util{


  public static Map<String, String> getParamsFromUrl(String url) {
    Map<String, String> params = new HashMap<>();
    String[] urlBits = url.split("\\?",2);

    if(urlBits.length==1){
      return params;
    }

    for(String bit : urlBits[1].split("&",2))
    {

      String[] bitBits = bit.split("=",2);

      if(bitBits.length==1)
      {
        continue;
      }

      params.put(bitBits[0],bitBits[1]);


    }
    return params;
  }

  public static String getPathFromUrl(String url) {

    String[] urlPath =url.split("\\?",2);
    return urlPath[0];
  }
}
