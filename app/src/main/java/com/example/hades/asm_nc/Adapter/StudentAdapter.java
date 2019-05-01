package com.example.hades.asm_nc.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hades.asm_nc.Database;
import com.example.hades.asm_nc.ManagementStudent;
import com.example.hades.asm_nc.R;
import com.example.hades.asm_nc.model.LopHoc;
import com.example.hades.asm_nc.model.Sinhvien;

import java.util.ArrayList;

public class StudentAdapter extends BaseAdapter implements Filterable {
    ArrayList<Sinhvien> dssv=new ArrayList<Sinhvien>();
    Context c;
    CustomFilter filter;
    ArrayList<Sinhvien> filterStudent;
    public StudentAdapter(Context c ,ArrayList<Sinhvien> dssv){
        this.c=c;
        this.dssv=dssv;
        this.filterStudent=dssv;
    }

    @Override
    public int getCount() {
        return dssv.size();
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        //gan layout
        LayoutInflater inf=((Activity)c).getLayoutInflater();
        view=inf.inflate(R.layout.one_item_student,null);
        TextView tv_id=view.findViewById(R.id.idSv);
        Sinhvien sv= dssv.get(i);
        tv_id.setText("StudentCode:"+ sv._id+"\n"+"StudentName: "+sv.tensv+"\n"+"Email: "+sv.email+"\n"+"ClassCode: "+sv._idlop);
        ImageView ivDelete=view.findViewById(R.id.ivDelete);
        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sinhvien sinhvien=dssv.get(i);
                int sinhVien=sinhvien._id;
                Database database=new Database(c);
                database.deleteStudent(sinhVien);
                ((ManagementStudent)c).capnhatgiaodien();
                Toast.makeText(c, "Success", Toast.LENGTH_SHORT).show();
            }
        });



        return view;
    }

    @Override
    public Filter getFilter() {
        if (filter == null)
        {
            filter = new CustomFilter();
        }
        return filter;
    }
    class CustomFilter extends  Filter{


        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length()>0)
            {
                constraint = constraint.toString().toUpperCase();

                ArrayList<Sinhvien> filters = new ArrayList<Sinhvien>();

                for (int i=0;i<filterStudent.size();i++)
                {
                    if (filterStudent.get(i).tensv.toUpperCase().contains(constraint))
                    {
                        Sinhvien sinhvien = new Sinhvien(filterStudent.get(i)._id,filterStudent.get(i).tensv,filterStudent.get(i).email,filterStudent.get(i)._idlop);

                        filters.add(sinhvien);
                    }
                }

                results.count=filters.size();
                results.values=filters;

            } else
            {
                results.count=filterStudent.size();
                results.values=filterStudent;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            dssv = (ArrayList<Sinhvien>) results.values;
            notifyDataSetChanged();
        }
    }
}
