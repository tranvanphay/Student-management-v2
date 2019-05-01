package com.example.hades.asm_nc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Menu extends AppCompatActivity {
    ImageView iv_Class,iv_Student;
    TextView tv_Class,tv_Student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        iv_Class=findViewById(R.id.ivClass);
        iv_Student=findViewById(R.id.ivStudent);
        tv_Class=findViewById(R.id.tvClass);
        tv_Student=findViewById(R.id.tvStudent);
        tv_Class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu.this,ManagerClass.class);
                startActivity(intent);
            }
        });
        iv_Class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu.this,ManagerClass.class);
                startActivity(intent);
            }
        });
        iv_Student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu.this,ManagementStudent.class);
                startActivity(intent);
            }
        });
        tv_Student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu.this,ManagementStudent.class);
                startActivity(intent);
            }
        });
    }
}
