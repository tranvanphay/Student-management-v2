package com.example.hades.asm_nc.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hades.asm_nc.R;
import com.example.hades.asm_nc.model.LopHoc;

import java.util.ArrayList;

public class ClassSpinnerStudentAdapter extends BaseAdapter {
    ArrayList<LopHoc> ds=new ArrayList<LopHoc>();
    Context c;
    public ClassSpinnerStudentAdapter(Context c ,ArrayList<LopHoc> ds){
        this.c=c;
        this.ds=ds;
    }

    @Override
    public int getCount() {
        return ds.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //gan layout
        LayoutInflater inf=((Activity)c).getLayoutInflater();
        view=inf.inflate(R.layout.one_item_spinner,null);
        TextView tv_spn=view.findViewById(R.id.tv_spn);
        LopHoc lh= ds.get(i);
        tv_spn.setText(lh._id+"-"+lh.ClassName);

        return view;
    }
}
