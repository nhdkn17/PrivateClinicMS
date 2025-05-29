package com.privateclinicms.controller.medicalThread;

import com.privateclinicms.controller.DashboardController;
import com.privateclinicms.dao.BenhNhanDAO;
import com.privateclinicms.model.BenhNhan;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.Date;

public class AddPatientController {

    @FXML
    private TextField txtTenBenhNhan;
    @FXML
    private DatePicker dpNgaySinh;
    @FXML
    private RadioButton rbNam, rbNu;
    @FXML
    private TextField txtSoDienThoai, txtEmail, txtDiaChi;
    @FXML
    private DatePicker dpNgayKham;
    @FXML
    private Label lblThongBao;
    @FXML
    private final ToggleGroup gioiTinhGroup = new ToggleGroup();

    private DashboardController dashboardController;

    private Integer maBenhNhanVuaThem;
    private final BenhNhanDAO dao = new BenhNhanDAO();

    @FXML
    private void initialize() {
        rbNam.setToggleGroup(gioiTinhGroup);
        rbNu.setToggleGroup(gioiTinhGroup);
        rbNam.setSelected(true);
    }

    @FXML
    private void handleLuuBenhNhan() {
        try {
            String ten = txtTenBenhNhan.getText();
            Date ngaySinh = Date.valueOf(dpNgaySinh.getValue());
            String gioiTinh = rbNam.isSelected() ? "Nam" : "Nữ";
            String sdt = txtSoDienThoai.getText();
            String email = txtEmail.getText();
            String diaChi = txtDiaChi.getText();
            Date ngayKham = Date.valueOf(dpNgayKham.getValue());

            BenhNhan benhNhan = new BenhNhan(ten, ngaySinh, gioiTinh, sdt, email, diaChi, ngayKham);
            Integer maBenhNhan = dao.addReturnId(benhNhan);

            if (maBenhNhan != null) {
                lblThongBao.setText("Đã lưu bệnh nhân, mã là: " + maBenhNhan);

                Runnable process = new PatientWorkflow(maBenhNhan, dashboardController);
                Thread thread = new Thread(process);
                thread.setDaemon(true);
                thread.start();

                if (dashboardController != null) {
                    dashboardController.addPatientThread(maBenhNhan, thread);
                }
            } else {
                lblThongBao.setText("Lưu thất bại.");
                lblThongBao.setTextFill(javafx.scene.paint.Color.RED);
            }

        } catch (Exception e) {
            lblThongBao.setText("Lỗi dữ liệu đầu vào!");
            lblThongBao.setTextFill(javafx.scene.paint.Color.RED);
            e.printStackTrace();
        }
    }

    private void startPatientThread(int maBenhNhan) {
        PatientWorkflow workflow = new PatientWorkflow(maBenhNhan, dashboardController);
        Thread thread = new Thread(workflow);
        thread.setDaemon(true);
        thread.start();

        dashboardController.addPatientThread(maBenhNhan, thread);
    }

    public Integer getMaBenhNhanVuaThem() {
        return maBenhNhanVuaThem;
    }

    public void setMaBenhNhanVuaThem(Integer maBenhNhanVuaThem) {
        this.maBenhNhanVuaThem = maBenhNhanVuaThem;
    }

    public DashboardController getDashboardController() {
        return dashboardController;
    }

    public void setDashboardController(DashboardController controller) {
        this.dashboardController = controller;
    }
}
