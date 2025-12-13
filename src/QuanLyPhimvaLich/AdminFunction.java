package QuanLyPhimvaLich;

import java.util.Scanner;

public class AdminFunction {

    public static void hienThiMenu() {
        System.out.println("\n==================================");
        System.out.println("🌟 ADMIN - QUẢN LÝ PHIM & LỊCH 🌟");
        System.out.println("==================================");
        System.out.println("1. Thêm QuanLyPhimvaLich.Phim");
        System.out.println("2. Sửa QuanLyPhimvaLich.Phim");
        System.out.println("3. Xóa QuanLyPhimvaLich.Phim");
        System.out.println("4. Xếp Lịch Chiếu (Nhập Mã QuanLyPhimvaLich.Phim -> Nhập Giờ -> Lưu)");
        System.out.println("5. Hiển Thị Danh Sách QuanLyPhimvaLich.Phim & Lịch");
        System.out.println("0. Thoát Chương Trình");
        System.out.print(">>> Nhập lựa chọn của bạn: ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Đọc dữ liệu khi khởi động
        QuanLyPhim quanLy = QuanLyPhim.docDuLieu();

        int luaChon;

        do {
            hienThiMenu();
            try {
                luaChon = scanner.nextInt();
                switch (luaChon) {
                    case 1:
                        quanLy.themPhim(scanner);
                        break;
                    case 2:
                        quanLy.suaPhim(scanner);
                        break;
                    case 3:
                        quanLy.xoaPhim(scanner);
                        break;
                    case 4:
                        quanLy.xepLichChieu(scanner);
                        break;
                    case 5:
                        quanLy.hienThiDanhSach();
                        break;
                    case 0:
                        // Lưu dữ liệu trước khi thoát
                        quanLy.luuDuLieu();
                        System.out.println("👋 Chương trình kết thúc.");
                        break;
                    default:
                        System.out.println("❗ Lựa chọn không hợp lệ. Vui lòng thử lại.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("❌ Lỗi nhập liệu. Vui lòng chỉ nhập số cho lựa chọn menu.");
                scanner.next();
                luaChon = -1;
            }

        } while (luaChon != 0);

        scanner.close();
    }
}