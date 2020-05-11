package com.example.sanhak3;

import java.util.ArrayList;
import java.util.Map;

class pair{
    String key;
    String value;
    public pair(String a ,String b)
    {
        key =a;
        value =b;
    }
}
public class cards {
    String Cname;
    ArrayList<pair> options;
    String url;
    public cards()
    {
        options = new ArrayList<pair>();
    }

}
