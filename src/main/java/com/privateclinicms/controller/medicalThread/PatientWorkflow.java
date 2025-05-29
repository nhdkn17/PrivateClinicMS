package com.privateclinicms.controller.medicalThread;

public class PatientWorkflow implements Runnable {
    private final int maBenhNhan;
    private final PatientThreadListener listener;

    public PatientWorkflow(int maBenhNhan, PatientThreadListener listener) {
        this.maBenhNhan = maBenhNhan;
        this.listener = listener;
    }

    @Override
    public void run() {
        try {
            System.out.println("🔄 Bắt đầu quy trình khám cho bệnh nhân ID: " + maBenhNhan);

            // Ví dụ các bước khám bệnh
            Thread.sleep(2000); // Bước 1: Tiếp nhận
            System.out.println("✅ Tiếp nhận xong cho bệnh nhân " + maBenhNhan);

            Thread.sleep(2000); // Bước 2: Đo sinh hiệu
            System.out.println("✅ Đo sinh hiệu xong cho bệnh nhân " + maBenhNhan);

            Thread.sleep(3000); // Bước 3: Khám bác sĩ
            System.out.println("✅ Khám bác sĩ xong cho bệnh nhân " + maBenhNhan);

            Thread.sleep(2000); // Bước 4: Lấy thuốc
            System.out.println("✅ Lấy thuốc xong cho bệnh nhân " + maBenhNhan);

            System.out.println("🎉 Hoàn tất quy trình cho bệnh nhân: " + maBenhNhan);
        } catch (InterruptedException e) {
            System.out.println("⚠️ Luồng khám bệnh bị gián đoạn cho bệnh nhân: " + maBenhNhan);
        } finally {
            if (listener != null) {
                listener.onPatientThreadComplete(maBenhNhan);
            }
        }
    }
}
