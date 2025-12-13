import QuanLyPhimvaLich.Phim;
import TimKiemvaThongKe.QuanLyTaiKhoan;
import TimKiemvaThongKe.ThongKe;
import TimKiemvaThongKe.XuLyPhim;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Khởi tạo các "chuyên gia"
        QuanLyTaiKhoan qlTaiKhoan = new QuanLyTaiKhoan();
        XuLyPhim xuLyPhim = new XuLyPhim();
        ThongKe thongKe = new ThongKe();

        // 2. Xử lý Đăng nhập trước
        System.out.print("Nhap User: "); String u = sc.nextLine();
        System.out.print("Nhap Pass: "); String p = sc.nextLine();

        if (qlTaiKhoan.dangNhap(u, p)) {
            System.out.println("LOGIN THANH CONG! Vao Menu...");

            // --- GIẢ SỬ CÓ DỮ LIỆU ---
            ArrayList<Phim> listPhim = new ArrayList<>();
            listPhim.add(new Phim("Mai", "Avatar","2h00"));
            listPhim.add(new Phim("Dao", "Zootopia 2","1h39"));

            // --- GỌI CÁC CHỨC NĂNG ---

            // Tìm kiếm
            xuLyPhim.timKiem(listPhim, "Mai");

            // Sắp xếp
            xuLyPhim.sapXep(listPhim, 2); // 2 là theo doanh thu

            // Thống kê (cần list Vé)
            // thongKe.xemDoanhThu(listVe);

        } else {
            System.out.println("Sai tai khoan mat khau!");
        }
    }
}