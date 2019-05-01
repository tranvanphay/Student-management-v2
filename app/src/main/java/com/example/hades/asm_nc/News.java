package com.example.hades.asm_nc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class News extends AppCompatActivity {
    ListView lv;
    String mang[]=new String[]{
            "Bóng đá",
            "Thời trang",
            "Ẩm thực"


    };
    String link[]=new String[]{
            "https://www.24h.com.vn/upload/rss/bongda.rss",
            "https://www.24h.com.vn/upload/rss/thoitrang.rss",
            "https://www.24h.com.vn/upload/rss/amthuc.rss"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        lv=findViewById(R.id.lv);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(News.this,android.R.layout.simple_list_item_1,mang);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(News.this,TinTheoLoaiActivity.class);
                intent.putExtra("link",link[position]);
                startActivity(intent);

            }
        });
    }
}
