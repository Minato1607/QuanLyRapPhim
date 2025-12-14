package utils; // Đổi từ ck sang utils

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ScannerUtils { // Đổi tên từ input sang ScannerUtils
    private static final Scanner sc = new Scanner(System.in);

    // Hàm nhập số nguyên (chống trôi lệnh, chống nhập chữ)
    public static int nhapSoNguyen(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Vui lòng nhập SỐ NGUYEN!");
            }
        }
    }

    // Hàm nhập chuỗi (bắt buộc nhập, không được để trống)
    public static String nhapChuoiKhongRong(String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("❌ Lỗi: Không được để trống!");
        }
    }

    // Hàm nhập ngày tháng
    public static LocalDate nhapNgayThang(String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            try {
                System.out.print(message);
                String input = sc.nextLine().trim();
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("❌ Lỗi: Ngày không hợp lệ (dd/MM/yyyy)!");
            }
        }
    }
}