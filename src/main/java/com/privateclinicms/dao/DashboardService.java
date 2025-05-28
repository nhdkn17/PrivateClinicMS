package com.privateclinicms.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class DashboardService {
    private Connection conn;

    public DashboardService(Connection conn) {
        this.conn = conn;
    }

    public int getSoBenhNhanHomNay() throws SQLException {
        String sql = "SELECT COUNT(DISTINCT MaBenhNhan) FROM LichKham WHERE CAST(GioBatDau AS DATE) = CAST(GETDATE() AS DATE)";
        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public int getSoLichHenHomNay() throws SQLException {
        String sql = "SELECT COUNT(*) FROM LichKham WHERE CAST(GioBatDau AS DATE) = CAST(GETDATE() AS DATE)";
        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public int getSoDonThuocHomNay() throws SQLException {
        String sql = "SELECT COUNT(*) FROM ToaThuoc WHERE CAST(NgayLayThuoc AS DATE) = CAST(GETDATE() AS DATE)";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public BigDecimal getDoanhThuHomNay() throws SQLException {
        String sql = "SELECT SUM(TongTien) FROM HoaDon WHERE CAST(NgayTao AS DATE) = CAST(GETDATE() AS DATE) AND TrangThaiThanhToan = N'Đã thanh toán'";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                BigDecimal result = rs.getBigDecimal(1);
                return result != null ? result : BigDecimal.ZERO;
            }
            return BigDecimal.ZERO;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Map<Integer, BigDecimal> getDoanhThuTheoNgayTrongThang(int thang, int nam) throws SQLException {
        Map<Integer, BigDecimal> map = new TreeMap<>();
        String sql = "SELECT DAY(NgayTao) as ngay, SUM(TongTien) as doanh_thu " +
                "FROM HoaDon " +
                "WHERE MONTH(NgayTao) = ? AND YEAR(NgayTao) = ? " +
                "GROUP BY DAY(NgayTao) " +
                "ORDER BY Ngay";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, thang);
            stmt.setInt(2, nam);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int ngay = rs.getInt("ngay");
                BigDecimal doanhThu = rs.getBigDecimal("doanh_thu");
                map.put(ngay, doanhThu);
            }
        }
        return map;
    }

}

