package com.privateclinicms.model;

public class ToaThuoc {
    private int maToaThuoc;
    private int maLichKham;
    private int maThuoc;
    private int soLuong;
    private String lieuLuong;

    public ToaThuoc(int maToaThuoc, int maLichKham, int maThuoc, int soLuong, String lieuLuong) {
        this.maToaThuoc = maToaThuoc;
        this.maLichKham = maLichKham;
        this.maThuoc = maThuoc;
        this.soLuong = soLuong;
        this.lieuLuong = lieuLuong;
    }

    public ToaThuoc() {
    }

    public int getMaToaThuoc() {
        return maToaThuoc;
    }

    public void setMaToaThuoc(int maToaThuoc) {
        this.maToaThuoc = maToaThuoc;
    }

    public int getMaLichKham() {
        return maLichKham;
    }

    public void setMaLichKham(int maLichKham) {
        this.maLichKham = maLichKham;
    }

    public int getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(int maThuoc) {
        this.maThuoc = maThuoc;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getLieuLuong() {
        return lieuLuong;
    }

    public void setLieuLuong(String lieuLuong) {
        this.lieuLuong = lieuLuong;
    }

    @Override
    public String toString() {
        return "ToaThuoc{" +
                "maToaThuoc=" + maToaThuoc +
                ", maLichKham=" + maLichKham +
                ", maThuoc=" + maThuoc +
                ", soLuong=" + soLuong +
                ", lieuLuong='" + lieuLuong + '\'' +
                '}';
    }
}
