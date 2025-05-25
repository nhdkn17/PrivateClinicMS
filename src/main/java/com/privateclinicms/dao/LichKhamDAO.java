package com.privateclinicms.dao;

import com.privateclinicms.model.LichKham;
import com.privateclinicms.model.LichKhamModel;
import com.privateclinicms.util.JDBCUtil;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LichKhamDAO implements DAO<LichKham> {

    @Override
    public void add(LichKham lichKham) {
        String sql = "INSERT INTO LichKham (MaBenhNhan, MaBacSi, NgayKham, TrangThai, GhiChu) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, lichKham.getMaBenhNhan());
            stmt.setInt(2, lichKham.getMaBacSi());
            stmt.setTimestamp(3, lichKham.getNgayKham());
            stmt.setString(4, lichKham.getTrangThai());
            stmt.setString(5, lichKham.getGhiChu());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public LichKham getById(int id) {
        String sql = "SELECT * FROM LichKham WHERE MaLichKham = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                LichKham lichKham = new LichKham();
                lichKham.setMaLichKham(rs.getInt("MaLichKham"));
                lichKham.setMaBenhNhan(rs.getInt("MaBenhNhan"));
                lichKham.setMaBacSi(rs.getInt("MaBacSi"));
                lichKham.setNgayKham(rs.getTimestamp("NgayKham"));
                lichKham.setTrangThai(rs.getString("TrangThai"));
                lichKham.setGhiChu(rs.getString("GhiChu"));
                return lichKham;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<LichKham> getAll() {
        List<LichKham> lichKhamList = new ArrayList<>();
        String sql = "SELECT * FROM LichKham";
        try (Connection conn = JDBCUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                LichKham lichKham = new LichKham();
                lichKham.setMaLichKham(rs.getInt("MaLichKham"));
                lichKham.setMaBenhNhan(rs.getInt("MaBenhNhan"));
                lichKham.setMaBacSi(rs.getInt("MaBacSi"));
                lichKham.setNgayKham(rs.getTimestamp("NgayKham"));
                lichKham.setTrangThai(rs.getString("TrangThai"));
                lichKham.setGhiChu(rs.getString("GhiChu"));
                lichKhamList.add(lichKham);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lichKhamList;
    }

    @Override
    public void update(LichKham lichKham) {
        String sql = "UPDATE LichKham SET MaBenhNhan = ?, MaBacSi = ?, NgayKham = ?, TrangThai = ?, GhiChu = ? WHERE MaLichKham = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, lichKham.getMaBenhNhan());
            stmt.setInt(2, lichKham.getMaBacSi());
            stmt.setTimestamp(3, lichKham.getNgayKham());
            stmt.setString(4, lichKham.getTrangThai());
            stmt.setString(5, lichKham.getGhiChu());
            stmt.setInt(6, lichKham.getMaLichKham());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM LichKham WHERE MaLichKham = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<LichKhamModel> getLichKhamHomNay() {
        List<LichKhamModel> list = new ArrayList<>();
        String sql = """
            SELECT lk.MaLichKham, bn.TenBenhNhan, bs.TenBacSi, lk.NgayKham, lk.TrangThai
                    FROM LichKham lk
                    JOIN BenhNhan bn ON lk.MaBenhNhan = bn.MaBenhNhan
                    JOIN BacSi bs ON lk.MaBacSi = bs.MaBacSi
                    WHERE CAST(lk.NgayKham AS DATE) = CAST(GETDATE() AS DATE)
        """;

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int maLichKham = rs.getInt("MaLichKham");
                String tenBenhNhan = rs.getString("TenBenhNhan");
                String tenBacSi = rs.getString("TenBacSi");
                LocalDateTime ngayKham = rs.getTimestamp("NgayKham").toLocalDateTime();
                String trangThai = rs.getString("TrangThai");

                LichKhamModel model = new LichKhamModel(maLichKham, tenBenhNhan, tenBacSi, ngayKham, trangThai);
                list.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<LichKhamModel> getLichKhamTheoNgay(LocalDate date) throws SQLException {
        List<LichKhamModel> list = new ArrayList<>();
        String query = """
        SELECT lk.MaLichKham, bn.TenBenhNhan, bs.TenBacSi, lk.NgayKham, lk.TrangThai
        FROM LichKham lk
        JOIN BenhNhan bn ON lk.MaBenhNhan = bn.MaBenhNhan
        JOIN BacSi bs ON lk.MaBacSi = bs.MaBacSi
        WHERE CONVERT(date, lk.NgayKham) = ?
        ORDER BY lk.NgayKham
    """;

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setDate(1, Date.valueOf(date));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int maLichKham = rs.getInt("MaLichKham");
                String tenBenhNhan = rs.getString("TenBenhNhan");
                String tenBacSi = rs.getString("TenBacSi");
                LocalDateTime ngayKham = rs.getTimestamp("NgayKham").toLocalDateTime();
                String trangThai = rs.getString("TrangThai");

                LichKhamModel model = new LichKhamModel(maLichKham, tenBenhNhan, tenBacSi, ngayKham, trangThai);
                list.add(model);
            }
        }

        return list;
    }
}
