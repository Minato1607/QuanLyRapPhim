package TimKiemvaThongKe;

import java.io.*;
import java.util.Scanner;

public class QuanLyTaiKhoan {
    private String tenFile = "users.txt";

    public boolean dangKy(String user, String pass) {
        try {
            // Tham số 'true' nghĩa là ghi nối tiếp (append), không xóa dữ liệu cũ
            FileWriter fw = new FileWriter(tenFile, true);
            BufferedWriter bw = new BufferedWriter(fw);

            // Ghi dữ liệu
            bw.write(user + "-" + pass);
            bw.newLine(); // <--- QUAN TRỌNG: Lệnh này giúp xuống dòng sạch sẽ

            // Đóng file để lưu ngay lập tức
            bw.close();
            fw.close();

            System.out.println("✅ Đăng ký thành công! (Dữ liệu đã được lưu)");
            return true;
        } catch (Exception e) {
            System.out.println("❌ Lỗi ghi file!");
            return false;
        }
    }

    public boolean dangNhap(String user, String pass) {
        try {
            File f = new File(tenFile);
            if (!f.exists()) return false;

            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String dong = sc.nextLine();
                String[] data = dong.split("-");
                // data[0] la user, data[1] la pass
                if (data.length == 2) {
                    if (data[0].equals(user) && data[1].equals(pass)) {
                        sc.close();
                        return true;
                    }
                }
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Lỗi đọc file!");
        }
        return false;
    }
}