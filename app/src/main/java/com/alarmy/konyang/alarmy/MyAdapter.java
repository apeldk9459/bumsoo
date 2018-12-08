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
    TextView idx;
    TextView title;
    TextView name;
    TextView time;
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
    public View getView(int position, View convertView, ViewGroup parent){

        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.notice_list, null);
            idx = (TextView) convertView.findViewById(R.id.idx);
            title = (TextView) convertView.findViewById(R.id.title);
            name = (TextView) convertView.findViewById(R.id.name);
            time = (TextView) convertView.findViewById(R.id.time);
        }
        idx.setText(mItems.get(position).getIdx());
        title.setText(mItems.get(position).getTitle());
        name.setText(mItems.get(position).getName());
        time.setText(mItems.get(position).getTime());

        return convertView;
    }

}
