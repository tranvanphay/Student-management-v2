package com.example.hades.asm_nc.model;

public class Sinhvien {
    public int _id;
    public String tensv;
    public String email;
    public int _idlop;

    public Sinhvien(int _id, String tensv, String email, int _idlop) {
        this._id = _id;
        this.tensv = tensv;
        this.email = email;
        this._idlop = _idlop;
    }

    public Sinhvien(String tensv, String email, int _idlop) {
        this.tensv = tensv;
        this.email = email;
        this._idlop = _idlop;
    }
}
