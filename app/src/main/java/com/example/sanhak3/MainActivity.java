package com.example.sanhak3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentBalance fragmentBalance = new FragmentBalance();
    private FragmentCard fragmentCard = new FragmentCard();
    private FragmentCommunity fragmentCommunity = new FragmentCommunity();
    private FragmentInout fragmentInout = new FragmentInout();
    private FragmentPattern fragmentPattern = new FragmentPattern();
    OnBackPressedListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragmentInout).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());
    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch(menuItem.getItemId())
            {
                case R.id.inout:
                    transaction.replace(R.id.frameLayout, fragmentInout).commitAllowingStateLoss();
                    break;
                case R.id.balance:
                    transaction.replace(R.id.frameLayout, fragmentBalance).commitAllowingStateLoss();
                    break;
                case R.id.pattern:
                    transaction.replace(R.id.frameLayout, fragmentPattern).commitAllowingStateLoss();
                    break;
                case R.id.card:
                    transaction.replace(R.id.frameLayout, fragmentCard).commitAllowingStateLoss();
                    break;
                case R.id.community:
                    transaction.replace(R.id.frameLayout, fragmentCommunity).commitAllowingStateLoss();
                    break;
            }
            return true;
        }
    }

    public void setOnBackPressedListener(OnBackPressedListener listener){
        this.listener = listener;
    }

    @Override
    public void onBackPressed() {
        if(listener != null){
            listener.onBackPressed();
        }else{
            super.onBackPressed();
        }
    }
}
