package com.alarmy.konyang.alarmy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    private ArrayList<MyItem> mItems = new ArrayList<>();

    public int getCount() {
        return mItems.size();
    }

    public MyItem getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public View getView(int position, View convertView, ViewGroup parent){

        Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.notice_list, parent, false);
        }

        TextView idx = (TextView) convertView.findViewById(R.id.idx);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView  name = (TextView) convertView.findViewById(R.id.name);
        TextView hit = (TextView) convertView.findViewById(R.id.hit);
        TextView time = (TextView) convertView.findViewById(R.id.time);

        MyItem myItem = getItem(position);

        idx.setText(myItem.getIdx());
        title.setText(myItem.getTitle());
        name.setText(myItem.getName());
        hit.setText(myItem.getHit());
        time.setText(myItem.getTime());

        return convertView;
    }

    public void addItem(String idx, String title, String name, String hit, String time){
        MyItem mItem = new MyItem();

        mItem.setIdx(idx);
        mItem.setTitle(title);
        mItem.setName(name);
        mItem.setHit(hit);
        mItem.setTime(time);

        mItems.add(mItem);
    }
}
