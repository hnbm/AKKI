package com.example.sanhak3;

import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.renderer.PieChartRenderer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // adapter에 들어갈 list 입니다.
    private List<Item> mDataset;
    public static final int HEADER = 0;
    public static final int CHILD = 1;

    public RecyclerAdapter(List<Item> myDataset, Context context) {
        mDataset = myDataset;
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
//    public static class ItemViewHolder extends RecyclerView.ViewHolder {
//
//        private TextView textView;
//
//        ItemViewHolder(View v) {
//            super(v);
//
//            textView = v.findViewById(R.id.`textView`);
//        }
//    }

    private static class ListHeaderViewHolder extends RecyclerView.ViewHolder {
        public TextView header_title;
        public PieChart pieChart;
        public Item refferalItem;

        public ListHeaderViewHolder(View v) {
            super(v);
            header_title = (TextView) v.findViewById(R.id.textView);
            pieChart = (PieChart) v.findViewById(R.id.piechart);
        }

//        public ListHeaderViewHolder(View v, PieChart pp) {
//            super(v);
//            pieChart = (PieChart) v.findViewById(R.id.piechart);
//        }

        public PieChart getPieChart() {
            return pieChart;
        }

        public void setPieChart(PieChart pieChart) {
            this.pieChart = pieChart;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patternitem, parent, false);
//        return new ItemViewHolder(view);
        View view = null;
        View view2 = null;
        Context context = parent.getContext();
        float dp = context.getResources().getDisplayMetrics().density;
        int subItemPaddingLeft = (int) (18 * dp);
        int subItemPaddingTopAndBottom = (int) (5 * dp);
        switch (viewType) {
            case HEADER:
                LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.patternitem, parent, false);
                ListHeaderViewHolder header = new ListHeaderViewHolder(view);
                return header;
            case CHILD:
                TextView itemTextView = new TextView(context);
                itemTextView.setPadding(subItemPaddingLeft, subItemPaddingTopAndBottom, 0, subItemPaddingTopAndBottom);
                itemTextView.setTextColor(0x88000000);
                itemTextView.setLayoutParams(
                        new ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));
                return new RecyclerView.ViewHolder(itemTextView) {
                };
//                LayoutInflater inflater2 = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                view2 = inflater2.inflate(R.layout.patternitem, parent, false);
//                ListHeaderViewHolder header2 = new ListHeaderViewHolder(view2);
//                return header2;


        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
        final Item item = mDataset.get(position);
//        if(item.type == CHILD){
//            Log.d("피차트", item.pie.getData().getDataSet().toString());
//        }

        //holder.textView.setText(item.getTitle());
        switch (item.type) {
            case HEADER:
                final ListHeaderViewHolder itemController = (ListHeaderViewHolder) holder;
                itemController.refferalItem = item;
                itemController.header_title.setText(item.text);
                itemController.header_title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (item.invisibleChildren == null) {
                            item.invisibleChildren = new ArrayList<Item>();
                            int count = 0;
                            int pos = mDataset.indexOf(itemController.refferalItem);
                            while (mDataset.size() > pos + 1 && mDataset.get(pos + 1).type == CHILD) {
                                item.invisibleChildren.add(mDataset.remove(pos + 1));
                                count++;
                            }
                            notifyItemRangeRemoved(pos + 1, count);
                        } else {
                            int pos = mDataset.indexOf(itemController.refferalItem);
                            int index = pos + 1;
                            for (Item i : item.invisibleChildren) {
                                mDataset.add(index, i);
                                index++;
                            }
                            notifyItemRangeInserted(pos + 1, index - pos - 1);
                            item.invisibleChildren = null;
                        }
                    }
                });
                break;
            case CHILD:
//                TextView itemTextView = new TextView(context);
                TextView itemTextView = (TextView) holder.itemView;
                itemTextView.setText(mDataset.get(position).text);
                break;
//                ((ListHeaderViewHolder) holder).setPieChart(item.pie);
//                final ListHeaderViewHolder itemController2 = (ListHeaderViewHolder) holder;
//                itemController2.pieChart = item.pie;
//                Log.d("피피차트", itemController2.pieChart.getData().getDataSet().toString());
//                break;

        }
    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return mDataset.size();
    }

    void addItem(Item data) {
        // 외부에서 item을 추가시킬 함수입니다.
        mDataset.add(data);
    }

    @Override
    public int getItemViewType(int position) {
        return mDataset.get(position).type;
    }

    public static class Item {
        public int type;
        public String text;
        public PieChart pie;
        public List<Item> invisibleChildren;

        public Item() {
        }

        public Item(int type, String texty) {
            this.type = type;
            this.text = texty;
        }

        public Item(int type, PieChart pieC) {
            this.type = type;
            this.pie = pieC;
        }
    }
}
