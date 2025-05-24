package com.privateclinicms.model;

public class Thuoc {
    private int maThuoc;
    private String tenThuoc;
    private String loaiThuoc;
    private double gia;
    private int soLuongTon;

    public Thuoc(int maThuoc, String tenThuoc, String loaiThuoc, double gia, int soLuongTon) {
        this.maThuoc = maThuoc;
        this.tenThuoc = tenThuoc;
        this.loaiThuoc = loaiThuoc;
        this.gia = gia;
        this.soLuongTon = soLuongTon;
    }

    public Thuoc() {
    }

    public int getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(int maThuoc) {
        this.maThuoc = maThuoc;
    }

    public String getTenThuoc() {
        return tenThuoc;
    }

    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
    }

    public String getLoaiThuoc() {
        return loaiThuoc;
    }

    public void setLoaiThuoc(String loaiThuoc) {
        this.loaiThuoc = loaiThuoc;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    @Override
    public String toString() {
        return "Thuoc{" +
                "maThuoc=" + maThuoc +
                ", tenThuoc='" + tenThuoc + '\'' +
                ", loaiThuoc='" + loaiThuoc + '\'' +
                ", gia=" + gia +
                ", soLuongTon=" + soLuongTon +
                '}';
    }
}
