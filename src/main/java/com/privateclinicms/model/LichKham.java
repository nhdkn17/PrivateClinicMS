package com.privateclinicms.model;

import java.sql.Timestamp;

public class LichKham {
    private int maLichKham;
    private int maBenhNhan;
    private int maBacSi;
    private Timestamp ngayKham;
    private String trangThai;
    private String ghiChu;

    public LichKham(int maLichKham, int maBenhNhan, int maBacSi, Timestamp ngayKham, String trangThai, String ghiChu) {
        this.maLichKham = maLichKham;
        this.maBenhNhan = maBenhNhan;
        this.maBacSi = maBacSi;
        this.ngayKham = ngayKham;
        this.trangThai = trangThai;
        this.ghiChu = ghiChu;
    }

    public LichKham() {
    }

    public int getMaLichKham() {
        return maLichKham;
    }

    public void setMaLichKham(int maLichKham) {
        this.maLichKham = maLichKham;
    }

    public int getMaBenhNhan() {
        return maBenhNhan;
    }

    public void setMaBenhNhan(int maBenhNhan) {
        this.maBenhNhan = maBenhNhan;
    }

    public int getMaBacSi() {
        return maBacSi;
    }

    public void setMaBacSi(int maBacSi) {
        this.maBacSi = maBacSi;
    }

    public Timestamp getNgayKham() {
        return ngayKham;
    }

    public void setNgayKham(Timestamp ngayKham) {
        this.ngayKham = ngayKham;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @Override
    public String toString() {
        return "LichKham{" +
                "maLichKham=" + maLichKham +
                ", maBenhNhan=" + maBenhNhan +
                ", maBacSi=" + maBacSi +
                ", ngayKham=" + ngayKham +
                ", trangThai='" + trangThai + '\'' +
                ", ghiChu='" + ghiChu + '\'' +
                '}';
    }
}
