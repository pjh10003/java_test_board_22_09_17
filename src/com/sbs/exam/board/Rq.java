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

  public Map<String, String> getParams() {

    return params;
  }

  public String getUrlPath() {


    return urlPath;

  }
}

