package com.example.sanhak3;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static com.example.sanhak3.R.layout;

public class FragmentCard2_2 extends Fragment {

    ListView listView;
    ArrayList<cards> cardsArrayLIst = new ArrayList<cards>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card2_2,container,false);
        //String[] menuItems = {"Do somethins!","Do something else","Do yet another thinsgs"};
        //return inflater.inflate(layout.fragment_card2_2, container, false);

        ListViewAdapter adapter;

        adapter = new ListViewAdapter();

        listView = (ListView) view.findViewById(R.id.list_card2_2);
        new GetCardAsync().execute();
        //listview.setAdapter(adapter);

//        adapter.addItem("1  ","https://akkicardimagebucket.s3.ap-northeast-2.amazonaws.com/woori_cookie.jpg","카드의 정석 COOKIE CHCECK","우리은행");
//        adapter.addItem("2  ","https://akkicardimagebucket.s3.ap-northeast-2.amazonaws.com/shinhan_dream.png","Deep Dream 체크카드","신한은행");
//        adapter.addItem("3  ","https://akkicardimagebucket.s3.ap-northeast-2.amazonaws.com/post_hangbok.jpg","행복한 체크카드","우체국");
//        adapter.addItem("4  ","https://akkicardimagebucket.s3.ap-northeast-2.amazonaws.com/kookmin_nori.png","노리 체크카드","국민은행");
//        adapter.addItem("5  ","https://akkicardimagebucket.s3.ap-northeast-2.amazonaws.com/nh_haebom.png","NH20 해봄 체크카드","농협은행");
//        adapter.addItem("6  ","https://akkicardimagebucket.s3.ap-northeast-2.amazonaws.com/post_youngri.jpg","영리한 체크카드","우체국");
//        adapter.addItem("7  ","https://akkicardimagebucket.s3.ap-northeast-2.amazonaws.com/kakao_friends.jpg","카카오 프렌즈 체크카드","카카오뱅크");
//        adapter.addItem("8  ","https://akkicardimagebucket.s3.ap-northeast-2.amazonaws.com/woori_sso3.png","카드의 정석 SSO3 체크카드","우리은행");
//        adapter.addItem("9  ","https://akkicardimagebucket.s3.ap-northeast-2.amazonaws.com/shinhan_s20.png","S20 체크카드","신한은행");
//        adapter.addItem("10 ","https://akkicardimagebucket.s3.ap-northeast-2.amazonaws.com/kookmin_liiv_mate.png","Liiv Mate 체크카드","국민은행");
//        adapter.addItem("11 ","https://akkicardimagebucket.s3.ap-northeast-2.amazonaws.com/nh_point.png","올바른POINT 체크카드","농협은행");
//        adapter.addItem("12 ","https://akkicardimagebucket.s3.ap-northeast-2.amazonaws.com/kookmin_kakaopay.jpg","카카오페이 KB국민 체크카드","국민은행");
//        adapter.addItem("13 ","https://akkicardimagebucket.s3.ap-northeast-2.amazonaws.com/shinhan_naverpay.jpg","네이버페이 신한 체크카드","신한은행");
//        adapter.addItem("14 ","https://akkicardimagebucket.s3.ap-northeast-2.amazonaws.com/woori_creamteens.jpg","카드의 정석 CREAM TEENS 체크카드","우리은행");
//        adapter.addItem("15 ","https://akkicardimagebucket.s3.ap-northeast-2.amazonaws.com/woori_lolChampions.png","LoL Champions Korea 체크카드","우리은행");
//        adapter.addItem("16 ","https://akkicardimagebucket.s3.ap-northeast-2.amazonaws.com/woori_transport_cookie.jpg","광역알뜰 교통카드 Cookie 체크카드","우리은행");
//        adapter.addItem("17 ","https://akkicardimagebucket.s3.ap-northeast-2.amazonaws.com/woori_sum.jpg","썸타는 우리 체크","우리은행");
//        adapter.addItem("18 ","https://akkicardimagebucket.s3.ap-northeast-2.amazonaws.com/woori_kakaopay.png","우리 카카오페이 체크카드","우리은행");
//        adapter.addItem("19 ","https://akkicardimagebucket.s3.ap-northeast-2.amazonaws.com/nh_1934.png","NH1934 체크카드","농협은행");
//        adapter.addItem("20 ","https://akkicardimagebucket.s3.ap-northeast-2.amazonaws.com/kookmin_chungchoon.jpg","청춘대로 싱글 체크카드","국민은행");
//        adapter.addItem("21 ","https://akkicardimagebucket.s3.ap-northeast-2.amazonaws.com/post_forme.jpg","포미 하이브리드 체크카드","우체국");
//        adapter.addItem("22 ","https://akkicardimagebucket.s3.ap-northeast-2.amazonaws.com/woori_baemin.jpg","배달의 민족 체크카드","우리은행");

        return view;
    }

    class GetCardAsync extends AsyncTask<Void, String, Void>
    {
        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            cardAdapter adapter = new cardAdapter(cardsArrayLIst);
            listView.setAdapter(adapter);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String url = "https://65rjhzgzwk.execute-api.ap-northeast-2.amazonaws.com/dynamo/dynamoget";
            String jsonData="";
            try {
                jsonData = Jsoup.connect(url).ignoreContentType(true).execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            jsonData = jsonData.replaceAll("\\\\","");
            jsonData = jsonData.substring(1,jsonData.length()-1);
            Log.i("zzzzzzzz",jsonData);
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(jsonData);
                JSONArray cardArray = jsonObject.getJSONArray("Items");
                for(int i=0;i<cardArray.length();i++) {
                    cards temp = new cards();
                    JSONObject cardObject = cardArray.getJSONObject(i);
                    ArrayList<String> options = new ArrayList<String>();
                    Iterator<String> iter = cardObject.keys();
                    temp.Cname = cardObject.getString("Cname");
                    Log.i("Cnamezzz",temp.Cname);

                    temp.url = "https://akkicardimagebucket.s3.ap-northeast-2.amazonaws.com/"+ cardObject.getString("image");
                    while (iter.hasNext()) {
                        String b = iter.next().toString();
                        if (!b.equals("Cname")&&!b.equals("image")) {
                            temp.options.add(new pair(b, cardObject.getString(b)));
                        }
                    }
                    cardsArrayLIst.add(temp);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            publishProgress();
            return null;
        }
    }
}


