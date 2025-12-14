package TimKiemvaThongKe;

import model.Phim; // <--- QUAN TRỌNG: Phải import cái này
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class XuLyPhim {

    // Chức năng 1: Tìm kiếm (Đã nâng cấp giao diện)
    public void timKiem(Scanner sc, List<Phim> dsPhim) {
        // Dùng ScannerUtils nếu có, hoặc sc.nextLine()
        System.out.print("Nhập tên phim cần tìm: ");
        String tuKhoa = sc.nextLine();

        System.out.println("\n🔍 KẾT QUẢ TÌM KIẾM CHO: \"" + tuKhoa.toUpperCase() + "\"");

        // Kẻ bảng Header (Giống bên QuanLyPhim)
        System.out.println("+" + "-".repeat(8) + "+" + "-".repeat(37) + "+" + "-".repeat(14) + "+");
        System.out.printf("| %-6s | %-35s | %-12s |\n", "ID", "TÊN PHIM", "THỜI LƯỢNG");
        System.out.println("+" + "-".repeat(8) + "+" + "-".repeat(37) + "+" + "-".repeat(14) + "+");

        boolean coPhim = false;
        for (Phim p : dsPhim) {
            // So sánh không phân biệt hoa thường
            if (p.getTenPhim().toLowerCase().contains(tuKhoa.toLowerCase())) {
                // In ra dòng dữ liệu đẹp
                System.out.printf("| %-6s | %-35s | %-12d |\n",
                        p.getId(), p.getTenPhim(), p.getThoiLuong());
                coPhim = true;
            }
        }

        // Kẻ đường đóng bảng
        System.out.println("+" + "-".repeat(8) + "+" + "-".repeat(37) + "+" + "-".repeat(14) + "+");

        if (!coPhim) {
            System.out.println("❌ Rất tiếc, không tìm thấy phim nào!");
        }
    }

    // Chức năng 2: Sắp xếp
    public void sapXep(List<Phim> dsPhim) {
        int n = dsPhim.size();
        // Bubble Sort
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                Phim p1 = dsPhim.get(i);
                Phim p2 = dsPhim.get(j);

                // Sắp xếp theo tên A-Z
                if (p1.getTenPhim().compareToIgnoreCase(p2.getTenPhim()) > 0) {
                    dsPhim.set(i, p2);
                    dsPhim.set(j, p1);
                }
            }
        }
        System.out.println("-> Đã sắp xếp danh sách A-Z!");
    }
}