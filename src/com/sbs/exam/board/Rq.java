package com.sbs.exam.board;

import java.util.Map;

public class Rq {

  String url;
  Map<String, String> params = null;
  String urlPath;


  Rq(String url) {
    this.url = url;
    params = Util.getParamsFromUrl(url);
    urlPath = Util.getPathFromUrl(url);
  }

  public int  getIntParam(String paramName, int defaultValue) {
    if(params.containsKey(paramName) == false) {
      return defaultValue;
    }
    try{
      return Integer.parseInt(params.get(paramName));
    }catch(NumberFormatException e){

      return defaultValue;
    }

  }


  public String  getParam(String paramName, String defaultValue) {
    if(params.containsKey(paramName) == false) {
      return defaultValue;
    }


    return params.get(paramName);
  }

  public Map<String, String> getParams() {

    return params;

  }

  public String getUrlPath() {


    return urlPath;

  }
}

