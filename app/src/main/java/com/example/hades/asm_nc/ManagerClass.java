package com.example.hades.asm_nc;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.hades.asm_nc.Adapter.ClassAdapter;
import com.example.hades.asm_nc.model.LopHoc;

import java.util.ArrayList;

public class ManagerClass extends AppCompatActivity {
    FloatingActionButton flb;
    private Dialog dialog;
    ListView lv;
    Database db;
    EditText etAdd;
    ArrayList<LopHoc> ds=new ArrayList<LopHoc>();
    android.support.v7.widget.SearchView searchLop;
    ClassAdapter adapter=new ClassAdapter(ManagerClass.this,ds);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_manager);
        setTitle("Quản lý lớp học");
        flb=findViewById(R.id.flbClass);
        lv=findViewById(R.id.listClass);
        searchLop=findViewById(R.id.searchClass);
        db=new Database(ManagerClass.this);
        updateUI();
        searchLop.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                LopHoc lopHoc=ds.get(position);
                final Dialog dialog=new Dialog(ManagerClass.this);
                dialog.setContentView(R.layout.dialog_class);
                final EditText etClassName;
                Button bt_Them;
                etClassName=dialog.findViewById(R.id.editText3);
                bt_Them=dialog.findViewById(R.id.button7);
                dialog.show();
                etClassName.setText(lopHoc.ClassName);
                bt_Them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int _id=ds.get(position)._id;
                        String className=etClassName.getText().toString();
                        Database db=new Database(ManagerClass.this);
                        LopHoc lopHoc1=new LopHoc(_id,className);
                        db.updateClass(lopHoc1,_id);
                        updateUI();
                        Toast.makeText(ManagerClass.this, "Success", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
            }
        });

        flb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(ManagerClass.this);
                // khởi tạo dialog
                dialog.setContentView(R.layout.dialog_class);
                // xét layout cho dialog
                dialog.setTitle("AddClass");
                // xét tiêu đề cho dialog
                Button dialogButton = dialog.findViewById(R.id.button7);
                etAdd = dialog.findViewById(R.id.editText3);
                // khai báo control trong dialog để bắt sự kiện

                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String ClassName = etAdd.getText().toString();
                        LopHoc lh=new LopHoc(ClassName);
                        db.addClass(lh);
                        etAdd.setText("");
                        updateUI();
                        Toast.makeText(ManagerClass.this, "Success", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                // bắt sự kiện cho nút đăng kí
                dialog.show();

            }
        });
    }


    //Hàm show class lên ListView và cập nhật giao diện
    public void updateUI(){
        ds=db.showClass();
        adapter=new ClassAdapter(ManagerClass.this,ds);
        lv.setAdapter(adapter);
    }
}
