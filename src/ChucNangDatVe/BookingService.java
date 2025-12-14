package ChucNangDatVe;

import QuanLyPhimvaLich.QuanLyPhim;
import model.LichChieu;
import model.Phim;
import model.Ve;
import utils.ScannerUtils;

import java.util.List;

public class BookingService {

    // 1. Hiển thị lịch chiếu
    public void hienThiLichChieu(List<Phim> dsPhim, List<LichChieu> dsLich) {
        System.out.println("\n--- DANH SÁCH SUẤT CHIẾU ---");
        System.out.printf("| %-10s | %-25s | %-10s | %-10s |\n", "MA LICH", "TEN PHIM", "PHONG", "GIO");
        System.out.println("-----------------------------------------------------------------");

        for (LichChieu lc : dsLich) {
            String tenPhim = "Unknown";
            for (Phim p : dsPhim) {
                if (p.getId().equals(lc.getMaPhim())) {
                    tenPhim = p.getTenPhim();
                    break;
                }
            }
            System.out.printf("| %-10s | %-25s | %-10s | %-10s |\n",
                    lc.getMaLich(), tenPhim, lc.getPhong(), lc.getGio());
        }
    }

    // 2. Vẽ sơ đồ ghế (Giữ nguyên logic cũ)
    private void hienThiSoDoGhe(String maLich, List<Ve> dsVe) {
        System.out.println("\n--- SƠ ĐỒ GHẾ (X: Đã bán, O: Trống) ---");
        System.out.println("   1  2  3  4  5");

        char[] hangs = {'A', 'B', 'C'};

        for (char h : hangs) {
            System.out.print(h + "  ");
            for (int cot = 1; cot <= 5; cot++) {
                String gheHienTai = "" + h + cot; // Ví dụ: A1

                boolean daBan = false;
                for (Ve v : dsVe) {
                    // Logic check vé đã bán
                    // Giả sử v.getHang() lưu mã ghế "A1"
                    if (v.getMaLich().equals(maLich) && v.getHang().equals(gheHienTai)) {
                        daBan = true;
                        break;
                    }
                }

                if (daBan) System.out.print("X  ");
                else System.out.print("O  ");
            }
            System.out.println();
        }
        System.out.println("---------------------------");
    }

    // 3. Xử lý mua vé
    public void xuLyMuaVe(QuanLyPhim quanLy) {
        System.out.println("\n>>> MUA VÉ XEM PHIM <<<");

        String maLich = ScannerUtils.nhapChuoiKhongRong("Nhập MÃ LỊCH muốn xem (VD: LC01): ");
        boolean coLich = false;
        // Vì QuanLyPhim nằm ở package khác, đảm bảo các hàm getDs... phải là public nhé
        for (LichChieu lc : quanLy.getDsLichChieu()) {
            if (lc.getMaLich().equalsIgnoreCase(maLich)) {
                coLich = true;
                break;
            }
        }
        if (!coLich) {
            System.out.println("❌ Mã lịch không tồn tại!");
            return;
        }

        hienThiSoDoGhe(maLich, quanLy.getDsVe());

        String maGhe = ScannerUtils.nhapChuoiKhongRong("Chọn ghế (VD: A1, B3...): ").toUpperCase();

        if (!maGhe.matches("[ABC][1-5]")) {
            System.out.println("❌ Ghế không hợp lệ! (Chỉ có hàng A,B,C và cột 1-5)");
            return;
        }

        for (Ve v : quanLy.getDsVe()) {
            if (v.getMaLich().equalsIgnoreCase(maLich) && v.getHang().equalsIgnoreCase(maGhe)) {
                System.out.println("❌ Ghế " + maGhe + " đã có người mua rồi!");
                return;
            }
        }

        int giaVe = 50000;
        System.out.println("💰 Giá vé: " + giaVe + " VND");
        String xacNhan = ScannerUtils.nhapChuoiKhongRong("Xác nhận mua? (Y/N): ");

        if (xacNhan.equalsIgnoreCase("Y")) {
            String maVe = "V" + System.currentTimeMillis() % 10000;
            Ve veMoi = new Ve(maVe, maLich, maGhe, 0, giaVe);
            quanLy.getDsVe().add(veMoi);
            System.out.println("✅ MUA VÉ THÀNH CÔNG! (" + maVe + ")");
        } else {
            System.out.println("❌ Đã hủy.");
        }
    }
}