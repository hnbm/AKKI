package com.example.sanhak3;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentInout extends Fragment implements View.OnClickListener {

//    View v;
//    private TextView edtHtml;

    final static String openInoutURL = "https://testapi.openbanking.or.kr/v2.0/account/transaction_list/fin_num";
    String tran_date;
    String inout_type;
    String print_content;
    String tran_amt;
    String after_balance_amt;
    String branch_name;

    private FragmentManager fragmentManager;
    private FragmentInout2_1 fragmentA;
    private FragmentInout2_2 fragmentB;
    private FragmentTransaction transaction;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_inout,container,false);
        fragmentManager = getFragmentManager();
        fragmentA = new FragmentInout2_1();
        fragmentB = new FragmentInout2_2();
        fragmentManager.beginTransaction().replace(R.id.frameLayout3,fragmentA).commitAllowingStateLoss();
        Button cButton = view.findViewById(R.id.btn_fragment_inout2_1);
        Button dButton = view.findViewById(R.id.btn_fragment_inout2_2);
        cButton.setOnClickListener(this);
        dButton.setOnClickListener(this);
        return view;

//        v = inflater.inflate(R.layout.fragment_inout, container, false);
//        edtHtml = (TextView) v.findViewById(R.id.inoutView);
//
//        new Thread() {
//            public void run() {
//                String btID = "T991605530U00" + Math.round(Math.random() * 10000000);
//                OpenTransactionList otl = getOpenTransactionList("199160553057881514541922", btID, "20200429163540", 20190102, 20190104);
//                Log.d("오픈거래내역", otl.res_list.toString());
//                String openTransactionList = null;
//                try {
////                    openTransactionList = otl.res_list.getJSONObject(0).toString();
//                    tran_date = otl.res_list.getJSONObject(1).get("tran_date").toString();
//                    inout_type = otl.res_list.getJSONObject(1).get("inout_type").toString();
//                    print_content = otl.res_list.getJSONObject(1).get("print_content").toString();
//                    tran_amt = otl.res_list.getJSONObject(1).get("tran_amt").toString();
//                    after_balance_amt = otl.res_list.getJSONObject(1).get("after_balance_amt").toString();
//                    branch_name = otl.res_list.getJSONObject(1).get("branch_name").toString();
//
//                    openTransactionList = "날짜: " + tran_date + " 구분: " + inout_type + "\n상호명: " + print_content +
//                            " 지점명: " + branch_name + "\n금액: " + tran_amt + " 거래 후 잔액: " + after_balance_amt;
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                Bundle bun = new Bundle();
//                bun.putString("HTML_TRANSACTION", openTransactionList);
//
//                Message msg = handler.obtainMessage();
//                msg.setData(bun);
//                handler.sendMessage(msg);
//            }
//        }.start();
//
//        return v;
    }

    @Override
    public void onClick(View view){
        switch(view.getId())
        {
            case R.id.btn_fragment_inout2_1:
                fragmentManager.beginTransaction().replace(R.id.frameLayout3, fragmentA).commitAllowingStateLoss();
                break;
            case R.id.btn_fragment_inout2_2:
                fragmentManager.beginTransaction().replace(R.id.frameLayout3, fragmentB).commitAllowingStateLoss();
                break;
        }
    }

//    Handler handler = new Handler() {
//        public void handleMessage(Message msg) {
//            Bundle bun = msg.getData();
//            String openTransactionList = bun.getString("HTML_TRANSACTION");
//            edtHtml.setText(openTransactionList);
//        }
//    };
//
//
//
//    public OpenTransactionList getOpenTransactionList(String finUseNum, String bankTranID, String tranDtime, int fDate, int tDate) {
//        OpenTransactionList OTL = new OpenTransactionList();
//        String urlString = openInoutURL + "?bank_tran_id=" + bankTranID + "&fintech_use_num=" +
//                finUseNum + "&inquiry_type=A&inquiry_base=D&from_date=" + fDate + "&to_date=" + tDate +
//                "&sort_order=D&tran_dtime=" + tranDtime + "&befor_inquiry_trace_info=1T201806172";
//
//        try {
//            // call API by using HTTPURLConnection
//            URL url = new URL(urlString);
//            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//            urlConnection.setRequestMethod("GET");
//            urlConnection.setRequestProperty("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMTAwNzUyNTcwIiwic2NvcGUiOlsiaW5xdWlyeSIsImxvZ2luIiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOjE1OTU3NTI0NzUsImp0aSI6ImY4MDIzMjAwLTFkYTctNDUzNS1iZjYyLTQ4NzQ5NGI3NzI2ZSJ9.o8055wGBDcIzt6iTOf7yiAcBZLmIDfuK-F5G6uJ45kE");
//            urlConnection.setConnectTimeout(5000);
//            urlConnection.setReadTimeout(5000);
//
//            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
//            JSONObject json = new JSONObject(getStringFromInputStream(in));
//            Log.d("오픈", json.toString());
//
//            // parse JSON
//            OTL = parseJSON(json);
//            OTL.setFintech_use_num(finUseNum);
//            OTL.setBank_tran_id(bankTranID);
//            OTL.setApi_tran_dtm(tranDtime);
//
//        } catch (MalformedURLException e) {
//            System.err.println("Malformed URL");
//            e.printStackTrace();
//            return null;
//        } catch (IOException e) {
//            System.err.println("JSON parsing error");
//            e.printStackTrace();
//            return null;
//        } catch (JSONException e) {
//            System.err.println("URL Connection failed");
//            e.printStackTrace();
//            return null;
//        }
//
//        return OTL;
//    }
//
//    private OpenTransactionList parseJSON(JSONObject json) throws JSONException {
//        OpenTransactionList OTL = new OpenTransactionList();
//        OTL.setRes_list(json.getJSONArray("res_list"));
//        Log.d("오픈레스리스트", OTL.res_list.toString());
//
//        return OTL;
//    }
//
//    private static String getStringFromInputStream(InputStream is) {
//        BufferedReader br = null;
//        StringBuilder sb = new StringBuilder();
//
//        String line;
//
//        try {
//            br = new BufferedReader(new InputStreamReader(is));
//            while((line = br.readLine()) != null) {
//                sb.append(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        return sb.toString();
//    }
}
