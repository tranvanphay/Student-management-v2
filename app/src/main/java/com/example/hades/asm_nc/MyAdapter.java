package com.example.hades.asm_nc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    Context c;
    ArrayList<Item> ds;

    public MyAdapter(Context context, ArrayList<Item> ds) {
        this.c=context;
        this.ds=ds;
    }

    @Override
    public int getCount() {
        return ds.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inf=(LayoutInflater)
                c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowview=inf.inflate(R.layout.one_item,parent, false);
        TextView tv_title=(TextView)rowview.findViewById(R.id.textView);
        tv_title.setText(ds.get(position).title);

        return rowview;

    }
}
