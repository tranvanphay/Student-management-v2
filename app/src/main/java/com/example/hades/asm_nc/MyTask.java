package com.example.hades.asm_nc;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MyTask extends AsyncTask<Void,Void,Void> {
    Context c;
    String link;
    ListView lv;
    ArrayList<Item> ds=new ArrayList<Item>();


    public MyTask(Context c, String link, ListView lv) {
        this.c = c;
        this.link = link;
        this.lv = lv;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url=new URL(link);
            URLConnection urlConnection=url.openConnection();
            InputStream inputStream= urlConnection.getInputStream();
            MySaxParser parser=new MySaxParser();
            ds=parser.xmlParser(inputStream);
            Log.d("dulieu","size"+ds.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        MyAdapter adapter=new MyAdapter(c,ds);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String link= ds.get(position).link;
                Intent intent=new Intent(c,XemTinActivity.class);
                intent.putExtra("link",link);
                c.startActivity(intent);
            }
        });
        super.onPostExecute(aVoid);
    }
}
