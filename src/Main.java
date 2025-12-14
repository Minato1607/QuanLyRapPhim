import QuanLyPhimvaLich.AdminFunction;
import ChucNangDatVe.BookingService;
import QuanLyPhimvaLich.QuanLyPhim;
import data.FileManager;
import TimKiemvaThongKe.QuanLyTaiKhoan;
import TimKiemvaThongKe.ThongKe;
import TimKiemvaThongKe.XuLyPhim;
import utils.ScannerUtils;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Load dữ liệu
        QuanLyPhim quanLy = new QuanLyPhim(
                FileManager.loadPhim("phim.txt"),
                FileManager.loadLichChieu("lich.txt"),
                FileManager.loadVe("ve.txt")
        );

        // Khởi tạo các chức năng
        AdminFunction adminPage = new AdminFunction();
        QuanLyTaiKhoan qlTaiKhoan = new QuanLyTaiKhoan();
        XuLyPhim xuLyPhim = new XuLyPhim();
        ThongKe thongKe = new ThongKe();
        BookingService datVePage = new BookingService(); // <--- CHỨC NĂNG MỚI

        int luaChon;
        do {
            System.out.println("\n=== HỆ THỐNG RẠP CHIẾU PHIM ===");
            System.out.println("1. Đăng nhập ADMIN (Quản lý)");
            System.out.println("2. Đăng nhập KHÁCH HÀNG (Mua vé)");
            System.out.println("3. Đăng ký tài khoản mới");
            System.out.println("0. Thoát & Lưu dữ liệu");

            luaChon = ScannerUtils.nhapSoNguyen("Mời bạn chọn: ");

            switch (luaChon) {
                case 1: // --- ADMIN ---
                    System.out.println("--- ĐĂNG NHẬP ADMIN ---");
                    String adUser = ScannerUtils.nhapChuoiKhongRong("User: ");
                    String adPass = ScannerUtils.nhapChuoiKhongRong("Pass: ");

                    // Check cứng hoặc check file users.txt tuỳ bạn
                    if (adUser.equals("admin") && adPass.equals("123456")) {
                        System.out.println("👋 Hello Boss!");
                        // Vào menu Admin cũ
                        menuAdmin(scanner, adminPage, quanLy, thongKe);
                    } else {
                        System.out.println("❌ Sai tài khoản Admin rùi!");
                    }
                    break;

                case 2: // --- KHÁCH HÀNG ---
                    System.out.println("--- ĐĂNG NHẬP KHÁCH HÀNG ---");
                    String khUser = ScannerUtils.nhapChuoiKhongRong("User: ");
                    String khPass = ScannerUtils.nhapChuoiKhongRong("Pass: ");

                    if (qlTaiKhoan.dangNhap(khUser, khPass)) {
                        System.out.println("👋 Xin chào " + khUser + "!");
                        // Vào menu Khách Hàng mới
                        menuKhachHang(scanner, quanLy, xuLyPhim, datVePage);
                    } else {
                        System.out.println("❌ Đăng nhập thất bại! (Chưa có nick thì chọn số 3 để ĐK nhé)");
                    }
                    break;

                case 3: // Đăng ký
                    String newUser = ScannerUtils.nhapChuoiKhongRong("Nhập User mới: ");
                    String newPass = ScannerUtils.nhapChuoiKhongRong("Nhập Pass mới: ");
                    qlTaiKhoan.dangKy(newUser, newPass);
                    break;

                case 0:
                    FileManager.saveToFile("phim.txt", quanLy.getDsPhim());
                    FileManager.saveToFile("lich.txt", quanLy.getDsLichChieu());
                    FileManager.saveToFile("ve.txt", quanLy.getDsVe());
                    System.out.println("Bye bye!");
                    break;
                default:
                    System.out.println("Chọn sai rồi!");
            }
        } while (luaChon != 0);
    }

    // --- MENU CON CHO ADMIN ---
    public static void menuAdmin(Scanner sc, AdminFunction adminFn, QuanLyPhim ql, ThongKe tk) {
        int chon;
        do {
            System.out.println("\n--- ADMIN DASHBOARD ---");
            System.out.println("1. Quản lý Phim & Lịch");
            System.out.println("2. Xem Thống Kê Doanh Thu");
            System.out.println("0. Đăng xuất");
            chon = ScannerUtils.nhapSoNguyen("Admin chọn: ");

            switch (chon) {
                case 1: adminFn.hienThiManHinhAdmin(sc, ql); break;
                case 2: tk.xemDoanhThu(ql.getDsVe()); break;
                case 0: break;
            }
        } while (chon != 0);
    }

    // --- MENU CON CHO KHÁCH HÀNG (MỚI) ---
    public static void menuKhachHang(Scanner sc, QuanLyPhim ql, XuLyPhim xuLy, BookingService datVe) {
        int chon;
        do {
            System.out.println("\n--- MENU KHÁCH HÀNG ---");
            System.out.println("1. Xem danh sách Phim & Tìm kiếm");
            System.out.println("2. Xem Lịch Chiếu & Giá Vé");
            System.out.println("3. MUA VÉ NGAY");
            System.out.println("0. Đăng xuất");
            chon = ScannerUtils.nhapSoNguyen("Bạn muốn làm gì: ");

            switch (chon) {
                case 1:
                    // Tận dụng code tìm kiếm của Người 5
                    System.out.println("1. Xem tất cả A-Z");
                    System.out.println("2. Tìm theo tên");
                    int k = ScannerUtils.nhapSoNguyen("Chọn: ");
                    if (k == 1)
                    {
                        xuLy.sapXep(ql.getDsPhim()); ql.hienThiDanhSach();
                    }
                    else
                    {
                        xuLy.timKiem(sc, ql.getDsPhim());
                    }
                    break;
                case 2:
                    datVe.hienThiLichChieu(ql.getDsPhim(), ql.getDsLichChieu());
                    break;
                case 3:
                    // Show lịch trước cho dễ chọn rồi mới mua
                    datVe.hienThiLichChieu(ql.getDsPhim(), ql.getDsLichChieu());
                    datVe.xuLyMuaVe(ql);
                    break;
                case 0: break;
            }
        } while (chon != 0);
    }
}