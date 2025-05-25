package com.privateclinicms.model;

import javafx.beans.property.*;
import java.time.LocalDateTime;

public class LichKhamModel {
    private final IntegerProperty maLichKham;
    private final StringProperty tenBenhNhan;
    private final StringProperty tenBacSi;
    private final ObjectProperty<LocalDateTime> ngayKham;
    private final StringProperty trangThai;

    public LichKhamModel(IntegerProperty maLichKham, StringProperty tenBenhNhan, StringProperty tenBacSi, ObjectProperty<LocalDateTime> ngayKham, StringProperty trangThai) {
        this.maLichKham = maLichKham;
        this.tenBenhNhan = tenBenhNhan;
        this.tenBacSi = tenBacSi;
        this.ngayKham = ngayKham;
        this.trangThai = trangThai;
    }

    public LichKhamModel(int maLichKham, String tenBenhNhan, String tenBacSi, LocalDateTime ngayKham, String trangThai) {
        this.maLichKham = new SimpleIntegerProperty(maLichKham);
        this.tenBenhNhan = new SimpleStringProperty(tenBenhNhan);
        this.tenBacSi = new SimpleStringProperty(tenBacSi);
        this.ngayKham = new SimpleObjectProperty<>(ngayKham);
        this.trangThai = new SimpleStringProperty(trangThai);
    }

    public int getMaLichKham() {
        return maLichKham.get();
    }

    public IntegerProperty maLichKhamProperty() {
        return maLichKham;
    }

    public String getTenBenhNhan() {
        return tenBenhNhan.get();
    }

    public StringProperty tenBenhNhanProperty() {
        return tenBenhNhan;
    }

    public String getTenBacSi() {
        return tenBacSi.get();
    }

    public StringProperty tenBacSiProperty() {
        return tenBacSi;
    }

    public LocalDateTime getNgayKham() {
        return ngayKham.get();
    }

    public ObjectProperty<LocalDateTime> ngayKhamProperty() {
        return ngayKham;
    }

    public String getTrangThai() {
        return trangThai.get();
    }

    public StringProperty trangThaiProperty() {
        return trangThai;
    }
}
