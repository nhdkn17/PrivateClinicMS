package com.privateclinicms.controller;

import com.privateclinicms.controller.other.Dialog;
import com.privateclinicms.dao.DashboardService;
import com.privateclinicms.dao.LichKhamDAO;
import com.privateclinicms.model.LichKhamModel;
import com.privateclinicms.util.JDBCUtil;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class DashboardController {
    @FXML
    private Label lblSoBenhNhan;
    @FXML
    private Label lblSoLichHen;
    @FXML
    private Label lblSoDonThuoc;
    @FXML
    private Label lblDoanhThu;
    @FXML
    private TableView<LichKhamModel> tableAppointments;
    @FXML
    private DatePicker datePickerLichHen;
    @FXML
    private TableView<LichKhamModel> tableLichHen;
    @FXML
    private TableColumn<LichKhamModel, Integer> colMaLichKham;
    @FXML
    private TableColumn<LichKhamModel, String> colTenBenhNhan;
    @FXML
    private TableColumn<LichKhamModel, String> colTenBacSi;
    @FXML
    private TableColumn<LichKhamModel, String> colNgayKham;
    @FXML
    private TableColumn<LichKhamModel, String> colTrangThai;
    @FXML
    private ComboBox<Integer> cbThang;
    @FXML
    private ComboBox<Integer> cbNam;
    @FXML
    private Pane paneBieuDo;

    private DashboardService dashboardService;
    private LichKhamDAO lichKhamDAO;

    public void initialize() {
        dashboardService = new DashboardService(JDBCUtil.getConnection());
        lichKhamDAO = new LichKhamDAO();
        loadDashboardData();

        colMaLichKham.setCellValueFactory(new PropertyValueFactory<>("maLichKham"));
        colTenBenhNhan.setCellValueFactory(new PropertyValueFactory<>("tenBenhNhan"));
        colTenBacSi.setCellValueFactory(new PropertyValueFactory<>("tenBacSi"));
        colNgayKham.setCellValueFactory(new PropertyValueFactory<>("ngayKham"));
        colTrangThai.setCellValueFactory(new PropertyValueFactory<>("trangThai"));

        datePickerLichHen.valueProperty().addListener((obs, oldDate, newDate) -> {
            if (newDate != null) {
                loadAppointmentsByDate(newDate);
            }
        });

        datePickerLichHen.setValue(LocalDate.now());
        loadAppointmentsByDate(LocalDate.now());

        cbThang.setItems(FXCollections.observableArrayList(IntStream.rangeClosed(1, 12).boxed().toList()));

        int currentYear = LocalDate.now().getYear();
        List<Integer> years = IntStream.rangeClosed(currentYear - 5, currentYear).boxed().toList();
        cbNam.setItems(FXCollections.observableArrayList(years));

        cbThang.setValue(LocalDate.now().getMonthValue());
        cbNam.setValue(currentYear);

        handleXemDoanhThuTheoThang();
    }

    public void loadDashboardData() {
        Connection conn = null;
        try {
            conn = JDBCUtil.getConnection();
            DashboardService dashboardService = new DashboardService(conn);

            lblSoBenhNhan.setText(String.valueOf(dashboardService.getSoBenhNhanHomNay()));
            lblSoLichHen.setText(String.valueOf(dashboardService.getSoLichHenHomNay()));
            lblSoDonThuoc.setText(String.valueOf(dashboardService.getSoDonThuocHomNay()));

            BigDecimal doanhThu = dashboardService.getDoanhThuHomNay();
            lblDoanhThu.setText(doanhThu != null ? doanhThu.toString() + " VNĐ" : "0 VNĐ");

            loadAppointmentsToday();
        } catch (SQLException e) {
            e.printStackTrace();
            if (!isInAnimation()) {
                Dialog.showNotice("Lỗi", "Không thể tải dữ liệu từ Database", false);
            }
            resetUI();
        } finally {
            if (conn != null) {
                JDBCUtil.closeConnection(conn);
            }
        }
    }

    private void resetUI() {
        lblSoBenhNhan.setText("0");
        lblSoLichHen.setText("0");
        lblSoDonThuoc.setText("0");
        lblDoanhThu.setText("0 VND");
    }

    private boolean isInAnimation() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement element : stackTrace) {
            if (element.getClassName().contains("Animation") ||
                    element.getClassName().contains("Timeline")) {
                return true;
            }
        }
        return false;
    }

    private void loadAppointmentsToday() {
        try {
            List<LichKhamModel> appointments = lichKhamDAO.getLichKhamHomNay();

            ObservableList<LichKhamModel> data = FXCollections.observableArrayList(appointments);
            tableAppointments.setItems(data);

        } catch (Exception e) {
            e.printStackTrace();
            Dialog.showNotice("Lỗi", "Không thể tải danh sách lịch hẹn", false);
        }
    }

    private void loadAppointmentsByDate(LocalDate selectedDate) {
        try {
            List<LichKhamModel> appointments = lichKhamDAO.getLichKhamTheoNgay(selectedDate);

            ObservableList<LichKhamModel> data = FXCollections.observableArrayList(appointments);
            tableAppointments.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            Dialog.showNotice("Lỗi", "Không thể tải danh sách lịch hẹn theo ngày", false);
        }
    }

    @FXML
    private void handleXemDoanhThuTheoThang() {
        Integer thang = cbThang.getValue();
        Integer nam = cbNam.getValue();

        if (thang != null && nam != null) {
            try {
                Map<Integer, BigDecimal> map = dashboardService.getDoanhThuTheoNgayTrongThang(thang, nam);

                paneBieuDo.getChildren().clear();

                YearMonth yearMonth = YearMonth.of(nam, thang);
                int daysInMonth = yearMonth.lengthOfMonth();

                NumberAxis xAxis = new NumberAxis(1, daysInMonth, 1);
                NumberAxis yAxis = new NumberAxis();
                xAxis.setLabel("Ngày");
                yAxis.setLabel("Doanh thu (VNĐ)");

                LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
                lineChart.setTitle("Doanh thu tháng " + thang + "/" + nam);
                lineChart.setLegendVisible(false);
                lineChart.setPrefSize(paneBieuDo.getPrefWidth(), paneBieuDo.getPrefHeight());

                XYChart.Series<Number, Number> series = new XYChart.Series<>();
                for (Map.Entry<Integer, BigDecimal> entry : map.entrySet()) {
                    series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
                }

                lineChart.getData().add(series);
                paneBieuDo.getChildren().add(lineChart);

            } catch (SQLException e) {
                e.printStackTrace();
                Dialog.showNotice("Lỗi", "Không thể tải dữ liệu biểu đồ doanh thu.", false);
            }
        }
    }
}
