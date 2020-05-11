package com.example.sanhak3;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentPattern extends Fragment {

    MainActivity activity;
    View v;
    View v2;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
//    private String[] mDataset = {"소비 패턴 분석", "수입 패턴 분석", "카드별 지출 분석", "월별 지출 분석"};
    List<RecyclerAdapter.Item> mData = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        v = inflater.inflate(R.layout.fragment_pattern, container, false);
        v2 = inflater.inflate(R.layout.piechartitem, container, false);
        return v;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) activity.findViewById(R.id.patternRecyclerView);

        PieChart pieChart = (PieChart) v2.findViewById(R.id.piechart);
        getPieData(pieChart);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManager);

        getPattern();


        recyclerView.setAdapter(new RecyclerAdapter(mData, activity));
    }

    private void getPattern() {
//        List<PatternData> pattern = new ArrayList<>();

        mData.add(new RecyclerAdapter.Item(RecyclerAdapter.HEADER, "소비 패턴 분석"));
        mData.add(new RecyclerAdapter.Item(RecyclerAdapter.HEADER, "수입 패턴 분석"));
        mData.add(new RecyclerAdapter.Item(RecyclerAdapter.HEADER, "카드별 지출 분석"));
        mData.add(new RecyclerAdapter.Item(RecyclerAdapter.HEADER, "월별 지출 분석"));

//        for(int i = 0, j = mDataset.length; i < j; i++) {
//            PatternData patternData = new PatternData();
//            patternData.setTitle(mDataset[i]);
//            pattern.add(patternData);
//        }
//
//        mAdapter = new RecyclerAdapter(pattern, activity);
//        recyclerView.setAdapter(mAdapter);

    }

        //----------------------------------------------------------------------------------
    public void getPieData(PieChart ppChart) {
        PieChart pChart;
        pChart = ppChart;

        pChart.setUsePercentValues(true);
        pChart.getDescription().setEnabled(false);
        pChart.setExtraOffsets(5,10,5,5);

        pChart.setDragDecelerationFrictionCoef(0.95f);

        pChart.setDrawHoleEnabled(true);
        pChart.setHoleColor(Color.WHITE);
        pChart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> yValues = new ArrayList<PieEntry>();

        yValues.add(new PieEntry(34f,"학원"));
        yValues.add(new PieEntry(23f,"카페"));
        yValues.add(new PieEntry(14f,"병원"));
        yValues.add(new PieEntry(35f,"교통"));
        yValues.add(new PieEntry(40f,"음식"));
        yValues.add(new PieEntry(40f,"편의점"));

        Description description = new Description();
        description.setText("소비"); //라벨
        description.setTextSize(15);
        pChart.setDescription(description);

        //pieChart.animateY(1000, Easing.EasingOption.EaseInOutCubic); //애니메이션

        PieDataSet dataSet = new PieDataSet(yValues,"expenses");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data = new PieData((dataSet));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);

        pChart.setData(data);

        mData.add(new RecyclerAdapter.Item(RecyclerAdapter.HEADER, "PieChart"));
        mData.add(new RecyclerAdapter.Item(RecyclerAdapter.CHILD, pChart));
        mData.add(new RecyclerAdapter.Item(RecyclerAdapter.CHILD, "aaa"));


    }


//    }
}
