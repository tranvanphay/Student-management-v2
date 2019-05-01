package com.example.hades.asm_nc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.hades.asm_nc.model.LopHoc;
import com.example.hades.asm_nc.model.Sinhvien;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public Database(Context context) {
        super(context, "QuanLySinhVien", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /*
        create table class(
        _id integer primary key autoincrement,
        ClassName text
        )
        create table student(
        _id integer primary key autoincrement,
        StudentName text,
        Email text
        )
         */
        String sql = "create table class" + "("
                + "_id integer primary key autoincrement," +
                "ClassName text" +
                ")";
        String sql1 = "create table student" + "(" +
                "_id integer primary key autoincrement," +
                "StudentName text," +
                "Email text," +
                "_idclass integer" +
                ")";
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL(sql1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists class");
        sqLiteDatabase.execSQL("drop table if exists student");
        onCreate(sqLiteDatabase);
    }

    //Hàm thêm lớp vào database
    public void addClass(LopHoc lh) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ClassName", lh.ClassName);
        sqLiteDatabase.insert("class", null, values);
    }

    //Them sinh vien
    public void addStudent(Sinhvien sv) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("StudentName", sv.tensv);
        values.put("Email", sv.email);
        values.put("_idClass", sv._idlop);
        sqLiteDatabase.insert("student", null, values);
    }

    //Xem lop
    public ArrayList<LopHoc> showClass() {
        ArrayList<LopHoc> ds = new ArrayList<LopHoc>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query("class", null, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                int _id = c.getInt(0);
                String ClassName = c.getString(1);
                LopHoc lh = new LopHoc(_id, ClassName);
                ds.add(lh);

            }
            while (c.moveToNext());
        }

        return ds;
    }

    //Xem sinh vien
    public ArrayList<Sinhvien> showStudent() {
        ArrayList<Sinhvien> dssv = new ArrayList<Sinhvien>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query("student", null, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                int _id = c.getInt(0);
                String StudentName = c.getString(1);
                String Email = c.getString(2);
                int _idClass = c.getInt(3);
                Sinhvien sv = new Sinhvien(_id, StudentName, Email, _idClass);
                dssv.add(sv);
            }
            while (c.moveToNext());
        }
        return dssv;
    }
//dELETE
    public void deleteClass(int i) {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete("Class", "_id=?", new String[] {Integer.toString(i)});

    }
    public void deleteStudent(int i) {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete("student", "_id=?", new String[] {Integer.toString(i)});

    }
    //Update
    public void updateClass(LopHoc lopHoc,int _id){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("_id",lopHoc._id);
        values.put("ClassName",lopHoc.ClassName);
        db.update("class",values,"_id=?",new String[]{_id+""});

    }
    public void updateStudent(Sinhvien sinhvien,int _id){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("_id",sinhvien._id);
        values.put("StudentName",sinhvien.tensv);
        values.put("Email",sinhvien.email);
        values.put("_idclass",sinhvien._idlop);
        db.update("student",values,"_id=?",new String[]{_id+""});

    }




}
