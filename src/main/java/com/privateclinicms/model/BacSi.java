package com.privateclinicms.model;

public class BacSi {
    private int maBacSi;
    private String hoTen;
    private String chuyenKhoa;
    private String soDienThoai;
    private String email;

    public BacSi(int maBacSi, String hoTen, String chuyenKhoa, String soDienThoai, String email) {
        this.maBacSi = maBacSi;
        this.hoTen = hoTen;
        this.chuyenKhoa = chuyenKhoa;
        this.soDienThoai = soDienThoai;
        this.email = email;
    }

    public BacSi() {
    }

    public int getMaBacSi() {
        return maBacSi;
    }

    public void setMaBacSi(int maBacSi) {
        this.maBacSi = maBacSi;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getChuyenKhoa() {
        return chuyenKhoa;
    }

    public void setChuyenKhoa(String chuyenKhoa) {
        this.chuyenKhoa = chuyenKhoa;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "BacSi{" +
                "maBacSi=" + maBacSi +
                ", hoTen='" + hoTen + '\'' +
                ", chuyenKhoa='" + chuyenKhoa + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
