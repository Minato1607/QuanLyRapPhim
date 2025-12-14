package QuanLyPhimvaLich;

import java.util.Scanner;

public class AdminFunction {

    public void hienThiManHinhAdmin(Scanner scanner, QuanLyPhim quanLy) {
        int luaChon;
        do {
            System.out.println("\n--- KHU VỰC ADMIN ---");
            System.out.println("1. Thêm Phim");
            System.out.println("2. Xóa Phim");
            System.out.println("3. Xếp Lịch Chiếu");
            System.out.println("4. Hiển Thị Danh Sách");
            System.out.println("0. Quay lại Menu Chính"); // Không phải thoát chương trình nữa
            System.out.print("Chọn chức năng: ");

            try {
                luaChon = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) { luaChon = -1; }

            switch (luaChon) {
                case 1: quanLy.themPhim(scanner); break;
                case 2: quanLy.xoaPhim(scanner); break;
                case 3: quanLy.xepLichChieu(scanner); break;
                case 4: quanLy.hienThiDanhSach(); break;
                case 0:
                    System.out.println("--> Đang quay lại menu chính...");
                    break;
                default: System.out.println("Sai lựa chọn!");
            }
        } while (luaChon != 0);
    }
}