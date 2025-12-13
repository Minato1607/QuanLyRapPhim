package TimKiemvaThongKe;

import java.io.*;
import java.util.Scanner;

public class QuanLyTaiKhoan {
    private String tenFile = "users.txt";

    public boolean dangKy(String user, String pass) {
        try {
            // true nghĩa là ghi nối tiếp, không xóa dữ liệu cũ
            FileWriter fw = new FileWriter(tenFile, true);
            fw.write(user + "-" + pass + "\n");
            fw.close();
            System.out.println("-> Đăng ký thành công!");
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi ghi file!");
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