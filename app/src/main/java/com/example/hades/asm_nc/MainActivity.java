package com.example.hades.asm_nc;

import android.content.Intent;
import android.media.FaceDetector;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView imv_Course,imv_Map,imv_News,imv_Social;
    TextView tv_Course,tv_Map,tv_News,tv_Social;
    String link="https://vnexpress.net/rss/giao-duc.rss";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imv_Course=findViewById(R.id.imv_Course);
        tv_Course=findViewById(R.id.tv_Course);
        imv_Map=findViewById(R.id.imv_Map);
        tv_Map=findViewById(R.id.tv_Map);
        imv_News=findViewById(R.id.imv_News);
        tv_News=findViewById(R.id.tv_News);
        imv_Social=findViewById(R.id.imv_Social);
        tv_Social=findViewById(R.id.tv_Social);
        imv_Course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Menu.class);
                startActivity(intent);
            }
        });
        tv_Course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Menu.class);
                startActivity(intent);
            }
        });
        imv_News.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,TinTheoLoaiActivity.class);
                intent.putExtra("link",link);
                startActivity(intent);
            }
        });
        tv_News.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,TinTheoLoaiActivity.class);
                intent.putExtra("link",link);
                startActivity(intent);
            }
        });


        imv_Map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MapsActivity.class);
                startActivity(intent);
            }
        });
        tv_Map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MapsActivity.class);
                startActivity(intent);
            }
        });
        imv_Social.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Facebook.class);
                startActivity(intent);
            }
        });
        tv_Social.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Facebook.class);
                startActivity(intent);
            }
        });
    }
}
