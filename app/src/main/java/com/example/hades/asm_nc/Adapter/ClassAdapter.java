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
import com.example.hades.asm_nc.ManagerClass;
import com.example.hades.asm_nc.R;
import com.example.hades.asm_nc.model.LopHoc;

import java.util.ArrayList;

public class ClassAdapter extends BaseAdapter implements Filterable{
ArrayList<LopHoc> ds=new ArrayList<LopHoc>();
Context c;
CustomFilter filter;
ArrayList<LopHoc> filterClass;
public ClassAdapter(Context c ,ArrayList<LopHoc> ds){
    this.c=c;
    this.ds=ds;
    this.filterClass=ds;
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
        return ds.indexOf(getItem(i));
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        //gan layout
        LayoutInflater inf=((Activity)c).getLayoutInflater();
        view=inf.inflate(R.layout.one_item_class,null);
        TextView tv_id=view.findViewById(R.id.tv_id);
        ImageView imv_delete=view.findViewById(R.id.iv_delete);
        LopHoc lh= ds.get(i);
        tv_id.setText("   ClassCode: "+lh._id+ "\n" + "   ClassName: "+lh.ClassName );
        imv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LopHoc lopHoc=ds.get(i);
                int lophoc=lopHoc._id;
                Database db=new Database(c);
                db.deleteClass(lophoc);
                ((ManagerClass)c).updateUI();
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
    class  CustomFilter extends Filter
    {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length()>0)
            {
                constraint = constraint.toString().toUpperCase();

                ArrayList<LopHoc> filters = new ArrayList<LopHoc>();

                for (int i=0;i<filterClass.size();i++)
                {
                    if (filterClass.get(i).ClassName.toUpperCase().contains(constraint))
                    {
                        LopHoc lopHoc = new LopHoc(filterClass.get(i)._id,filterClass.get(i).ClassName);

                        filters.add(lopHoc);
                    }
                }

                results.count=filters.size();
                results.values=filters;

            } else
            {
                results.count=filterClass.size();
                results.values=filterClass;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ds = (ArrayList<LopHoc>) results.values;
            notifyDataSetChanged();
        }
    }
}
