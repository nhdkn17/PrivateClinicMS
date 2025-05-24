package com.privateclinicms.dao;

import com.privateclinicms.model.ToaThuoc;
import com.privateclinicms.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToaThuocDAO implements DAO<ToaThuoc> {

    @Override
    public void add(ToaThuoc toaThuoc) {
        String sql = "INSERT INTO ToaThuoc (MaLichKham, MaThuoc, SoLuong, LieuLuong) VALUES (?, ?, ?, ?)";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, toaThuoc.getMaLichKham());
            stmt.setInt(2, toaThuoc.getMaThuoc());
            stmt.setInt(3, toaThuoc.getSoLuong());
            stmt.setString(4, toaThuoc.getLieuLuong());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ToaThuoc getById(int id) {
        String sql = "SELECT * FROM ToaThuoc WHERE MaToaThuoc = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ToaThuoc toaThuoc = new ToaThuoc();
                toaThuoc.setMaToaThuoc(rs.getInt("MaToaThuoc"));
                toaThuoc.setMaLichKham(rs.getInt("MaLichKham"));
                toaThuoc.setMaThuoc(rs.getInt("MaThuoc"));
                toaThuoc.setSoLuong(rs.getInt("SoLuong"));
                toaThuoc.setLieuLuong(rs.getString("LieuLuong"));
                return toaThuoc;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ToaThuoc> getAll() {
        List<ToaThuoc> toaThuocList = new ArrayList<>();
        String sql = "SELECT * FROM ToaThuoc";
        try (Connection conn = JDBCUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ToaThuoc toaThuoc = new ToaThuoc();
                toaThuoc.setMaToaThuoc(rs.getInt("MaToaThuoc"));
                toaThuoc.setMaLichKham(rs.getInt("MaLichKham"));
                toaThuoc.setMaThuoc(rs.getInt("MaThuoc"));
                toaThuoc.setSoLuong(rs.getInt("SoLuong"));
                toaThuoc.setLieuLuong(rs.getString("LieuLuong"));
                toaThuocList.add(toaThuoc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toaThuocList;
    }

    @Override
    public void update(ToaThuoc toaThuoc) {
        String sql = "UPDATE ToaThuoc SET MaLichKham = ?, MaThuoc = ?, SoLuong = ?, LieuLuong = ? WHERE MaToaThuoc = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, toaThuoc.getMaLichKham());
            stmt.setInt(2, toaThuoc.getMaThuoc());
            stmt.setInt(3, toaThuoc.getSoLuong());
            stmt.setString(4, toaThuoc.getLieuLuong());
            stmt.setInt(5, toaThuoc.getMaToaThuoc());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM ToaThuoc WHERE MaToaThuoc = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
