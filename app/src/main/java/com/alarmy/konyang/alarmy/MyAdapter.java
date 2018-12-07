package com.alarmy.konyang.alarmy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<MyItem> mItems;

    public MyAdapter(Context context, ArrayList<MyItem> mItems){
        this.context = context;
        this.mItems = mItems;
    }
    public int getCount() {
        return mItems.size();
    }

    public MyItem getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public View getView(int position, View tView, ViewGroup parent){

        View v = View.inflate(context, R.layout.notice_list,null);


        TextView idx = (TextView) v.findViewById(R.id.idx);
        TextView title = (TextView) v.findViewById(R.id.title);
        TextView  name = (TextView) v.findViewById(R.id.name);
        TextView time = (TextView) v.findViewById(R.id.time);

        idx.setText(mItems.get(position).getIdx());
        title.setText(mItems.get(position).getTitle());
        name.setText(mItems.get(position).getName());
        time.setText(mItems.get(position).getTime());

        v.setTag(mItems.get(position).getIdx());
        return v;
    }

}
