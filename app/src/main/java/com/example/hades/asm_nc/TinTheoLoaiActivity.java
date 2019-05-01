package com.example.hades.asm_nc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

public class TinTheoLoaiActivity extends AppCompatActivity {
        String link;
        ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tin_theo_loai);
        link=getIntent().getExtras().getString("link");
        list=findViewById(R.id.list);
        Toast.makeText(this, ""+link, Toast.LENGTH_SHORT).show();
        MyTask task=new MyTask(TinTheoLoaiActivity.this,link,list);
        task.execute();
    }
}
