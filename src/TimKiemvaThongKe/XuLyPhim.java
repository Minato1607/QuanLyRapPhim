package TimKiemvaThongKe;

import QuanLyPhimvaLich.Phim;

import java.util.ArrayList;

public class XuLyPhim {

    // Chức năng 1: Tìm kiếm
    public void timKiem(ArrayList<Phim> dsPhim, String tuKhoa) {
        System.out.println("--- KET QUA TIM KIEM: " + tuKhoa + " ---");
        boolean coPhim = false;

        for (int i = 0; i < dsPhim.size(); i++) {
            Phim p = dsPhim.get(i);
            if (p.getTenPhim().toLowerCase().contains(tuKhoa.toLowerCase())) {
                System.out.println(p.toString());
                coPhim = true;
            }
        }

        if (!coPhim) System.out.println("Khong tim thay phim nao!");
    }

    // Chức năng 2: Sắp xếp (Dùng Bubble Sort cơ bản)
    public void sapXep(ArrayList<Phim> dsPhim, int kieu) {
        int n = dsPhim.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {

                Phim p1 = dsPhim.get(i);
                Phim p2 = dsPhim.get(j);
                boolean doiCho = false;

                if (kieu == 1) { // A-Z
                    if (p1.getTenPhim().compareTo(p2.getTenPhim()) > 0) doiCho = true;
                } else if (kieu == 2) { // Doanh thu cao -> thấp
                    if (p1.getDoanhThu() < p2.getDoanhThu()) doiCho = true;
                }

                if (doiCho) {
                    dsPhim.set(i, p2);
                    dsPhim.set(j, p1);
                }
            }
        }
        System.out.println("-> Đã sắp xếp xong!");
    }
}