package com.example.hades.asm_nc;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hades.asm_nc.Adapter.ClassSpinnerStudentAdapter;
import com.example.hades.asm_nc.Adapter.StudentAdapter;
import com.example.hades.asm_nc.model.LopHoc;
import com.example.hades.asm_nc.model.Sinhvien;

import java.util.ArrayList;

public class ManagementStudent extends AppCompatActivity {
    FloatingActionButton flb;
    Spinner spn,spn1;
    ListView lv;
    EditText et,et1;
    ArrayList<Sinhvien> dssv=new ArrayList<>();
    ArrayList<LopHoc> ds=new ArrayList<LopHoc>();
    Database db = new Database(this);
    SearchView searchStudent;
    private Dialog dialog;
    StudentAdapter adapter2=new StudentAdapter(ManagementStudent.this,dssv);
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_management);
        setTitle("Quản lý sinh viên");
        flb=findViewById(R.id.flbStudent);
        lv=findViewById(R.id.listStudent);
        searchStudent=findViewById(R.id.searchStudent);
        ds=db.showClass();
        ClassSpinnerStudentAdapter adapter1=new ClassSpinnerStudentAdapter(ManagementStudent.this,ds);
        capnhatgiaodien();
        searchStudent.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter2.getFilter().filter(newText);
                return false;
            }
        });


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Sinhvien sinhvien=dssv.get(position);
                final Dialog dialog=new Dialog(ManagementStudent.this);
                dialog.setContentView(R.layout.dialog_student);
                final EditText etName,etEmail;
                final Spinner spnClass;
                Button bt_Them;
                etName=dialog.findViewById(R.id.editText4);
                etEmail=dialog.findViewById(R.id.editText);
                spnClass=dialog.findViewById(R.id.spinner);
                bt_Them=dialog.findViewById(R.id.button8);
                db=new Database(ManagementStudent.this);
                ds= db.showClass();
                ClassSpinnerStudentAdapter adapter=new ClassSpinnerStudentAdapter(ManagementStudent.this,ds);
                spnClass.setAdapter(adapter);
                dialog.show();
                etName.setText(sinhvien.tensv);
                etEmail.setText(sinhvien.email);
                bt_Them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int _id=dssv.get(position)._id;
                        String studentName=etName.getText().toString();
                        String email=etEmail.getText().toString();
                        int index=spnClass.getSelectedItemPosition();
                        int _idClass=ds.get(index)._id;
                        db=new Database(ManagementStudent.this);
                        Sinhvien sinhvien1=new Sinhvien(_id,studentName,email,_idClass);
                        db.updateStudent(sinhvien1,_id);
                        capnhatgiaodien();
                        Toast.makeText(ManagementStudent.this, "Success", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
            }
        });
        flb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(ManagementStudent.this);
                // khởi tạo dialog
                dialog.setContentView(R.layout.dialog_student);
                // xét layout cho dialog
                dialog.setTitle("AddStudent");
                // xét tiêu đề cho dialog
                Button dialogButton = (Button) dialog.findViewById(R.id.button8);
                spn1=dialog.findViewById(R.id.spinner);
                et=dialog.findViewById(R.id.editText4);
                et1=dialog.findViewById(R.id.editText);
                ds=db.showClass();
                ClassSpinnerStudentAdapter adapter1=new ClassSpinnerStudentAdapter(ManagementStudent.this,ds);
                spn1.setAdapter(adapter1);


                // khai báo control trong dialog để bắt sự kiện
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String StudentName=et.getText().toString();
                        String Email=et1.getText().toString();
                        int index= spn1.getSelectedItemPosition();
                        int _idClass=ds.get(index)._id;
                        Sinhvien sv=new Sinhvien(StudentName,Email,_idClass);
                        db.addStudent(sv);
                        et.setText("");
                        et1.setText("");
                        spn1.setSelection(1);
                        capnhatgiaodien();
                        dialog.dismiss();

                    }
                });
                // bắt sự kiện cho nút đăng kí
                dialog.show();
                // hiển thị dialog
            }
        });
    }
    public void capnhatgiaodien(){

        dssv=db.showStudent();
        adapter2=new StudentAdapter(ManagementStudent.this,dssv);
        lv.setAdapter(adapter2);
    }
}
