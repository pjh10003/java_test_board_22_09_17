package com.sbs.exam.board;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Util {


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
