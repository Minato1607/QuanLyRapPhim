package QuanLyPhimvaLich;

import model.LichChieu;
import model.Phim;
import model.Ve;
import utils.ScannerUtils;
import data.FileManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuanLyPhim {

    // Danh sách lưu trữ dữ liệu
    private List<Phim> dsPhim;
    private List<LichChieu> dsLichChieu;
    private List<Ve> dsVe; // Danh sách vé để thống kê

    // Constructor 1: Nhận dữ liệu từ Người 1 (FileManager load lên)
    public QuanLyPhim(List<Phim> phimLoaded, List<LichChieu> lichLoaded, List<Ve> veLoaded) {
        this.dsPhim = phimLoaded != null ? phimLoaded : new ArrayList<>();
        this.dsLichChieu = lichLoaded != null ? lichLoaded : new ArrayList<>();
        this.dsVe = veLoaded != null ? veLoaded : new ArrayList<>();
    }

    // Constructor 2: Mặc định (để test khi chưa có file)
    public QuanLyPhim() {
        this.dsPhim = new ArrayList<>();
        this.dsLichChieu = new ArrayList<>();
        this.dsVe = new ArrayList<>(); // <--- QUAN TRỌNG: Phải khởi tạo cái này

        // Giả lập dữ liệu mẫu
        dsPhim.add(new Phim("P01", "Mai", 120));
        dsPhim.add(new Phim("P02", "Dune 2", 165));
        dsLichChieu.add(new LichChieu("LC01", "P01", "R01", "18:00"));
    }

    // ================= CHỨC NĂNG 1: THÊM PHIM (LƯU LUÔN) =================
    public void themPhim(Scanner sc) {
        System.out.println("\n--- THÊM PHIM MỚI ---");
        // Dùng ScannerUtils cho an toàn (hoặc để sc.nextLine() như cũ nếu chưa đổi)
        System.out.print("Nhập Mã Phim (VD: P03): ");
        String id = sc.nextLine();

        if (timPhimTheoId(id) != null) {
            System.out.println("❌ Mã phim này đã tồn tại!");
            return;
        }

        System.out.print("Nhập Tên Phim: ");
        String ten = sc.nextLine();

        System.out.print("Nhập Thời Lượng (phút): ");
        int thoiLuong = 0;
        try {
            thoiLuong = Integer.parseInt(sc.nextLine());
        } catch (Exception e) { return; }

        Phim p = new Phim(id, ten, thoiLuong);
        dsPhim.add(p);

        // --- LƯU NGAY LẬP TỨC ---
        FileManager.saveToFile("phim.txt", dsPhim);

        System.out.println("✅ Thêm thành công và đã lưu vào file!");
    }

    // ================= CHỨC NĂNG 2: SỬA PHIM (LƯU LUÔN) =================
    public void suaPhim(Scanner sc) {
        System.out.println("\n--- SỬA PHIM ---");
        System.out.print("Nhập Mã Phim cần sửa: ");
        String id = sc.nextLine();

        Phim p = timPhimTheoId(id);
        if (p == null) {
            System.out.println("❌ Không tìm thấy phim!");
            return;
        }

        System.out.println("Đang sửa phim: " + p.getTenPhim());
        System.out.print("Tên mới (Enter để giữ nguyên): ");
        String tenMoi = sc.nextLine();
        if(!tenMoi.isEmpty()) p.setTenPhim(tenMoi);

        System.out.print("Thời lượng mới (Enter để giữ nguyên): ");
        String tlMoi = sc.nextLine();
        try {
            if(!tlMoi.isEmpty()) p.setThoiLuong(Integer.parseInt(tlMoi));
        } catch(Exception e) {}

        // --- LƯU NGAY LẬP TỨC ---
        FileManager.saveToFile("phim.txt", dsPhim);

        System.out.println("✅ Đã cập nhật và lưu file.");
    }

    // ================= CHỨC NĂNG 3: XÓA PHIM (LƯU CẢ PHIM VÀ LỊCH) =================
    public void xoaPhim(Scanner sc) {
        System.out.println("\n--- XÓA PHIM ---");
        System.out.print("Nhập Mã Phim cần xóa: ");
        String id = sc.nextLine();

        Phim p = timPhimTheoId(id);
        if (p == null) {
            System.out.println("❌ Không tìm thấy phim!");
            return;
        }

        dsPhim.remove(p);
        // Xóa luôn lịch chiếu của phim đó
        dsLichChieu.removeIf(lc -> lc.getMaPhim().equals(id));

        // --- LƯU NGAY LẬP TỨC (Phải lưu cả 2 file vì Xóa Phim ảnh hưởng cả Lịch) ---
        FileManager.saveToFile("phim.txt", dsPhim);
        FileManager.saveToFile("lich.txt", dsLichChieu);

        System.out.println("✅ Đã xóa phim và lịch chiếu liên quan (Dữ liệu đã được cập nhật).");
    }

    // ================= CHỨC NĂNG 4: XẾP LỊCH CHIẾU =================
    public void xepLichChieu(Scanner sc) {
        System.out.println("\n--- XẾP LỊCH CHIẾU ---");
        hienThiDanhSachPhimDonGian();

        System.out.print("Nhập Mã Phim: ");
        String maPhim = sc.nextLine();

        if (timPhimTheoId(maPhim) == null) {
            System.out.println("❌ Không tìm thấy phim!");
            return;
        }

        System.out.print("Nhập Mã Lịch (VD: LC05): ");
        String maLich = sc.nextLine();
        System.out.print("Nhập Phòng (VD: Rạp 01): ");
        String phong = sc.nextLine();
        System.out.print("Nhập Giờ (VD: 19:30): ");
        String gio = sc.nextLine();

        LichChieu lc = new LichChieu(maLich, maPhim, phong, gio);
        dsLichChieu.add(lc);
        FileManager.saveToFile("lich.txt", dsLichChieu);

        System.out.println("✅ Đã thêm lịch chiếu.");
    }

    // ================= CHỨC NĂNG 5: HIỂN THỊ =================
    public void hienThiDanhSach() {
        System.out.println("\n" + "=".repeat(114));
        System.out.printf("| %-6s | %-35s | %-15s | %-45s |\n", "MÃ", "TÊN PHIM", "KHUNG GIỜ", "LỊCH CHIẾU");
        System.out.println("|" + "-".repeat(8) + "|" + "-".repeat(37) + "|" + "-".repeat(17) + "|" + "-".repeat(47) + "|");

        for (Phim p : dsPhim) {
            StringBuilder lichStr = new StringBuilder();
            for (LichChieu lc : dsLichChieu) {
                if (lc.getMaPhim().equals(p.getId())) {
                    lichStr.append("[").append(lc.getPhong()).append("-").append(lc.getGio()).append("] ");
                }
            }
            String lichHienThi = lichStr.toString();
            if (lichHienThi.isEmpty()) lichHienThi = "---";

            System.out.printf("| %-6s | %-35s | %-15d | %-45s |\n",
                    p.getId(), p.getTenPhim(), p.getThoiLuong(), lichHienThi);
        }
        System.out.println("=".repeat(114));
    }

    // --- CÁC HÀM GETTER / HỖ TRỢ ---

    // Hàm này Main đang cần để thống kê doanh thu
    public List<Ve> getDsVe() {
        return dsVe;
    }

    public List<Phim> getDsPhim() { return dsPhim; }
    public List<LichChieu> getDsLichChieu() { return dsLichChieu; }

    private Phim timPhimTheoId(String id) {
        for (Phim p : dsPhim) {
            if (p.getId().equalsIgnoreCase(id)) return p;
        }
        return null;
    }

    private void hienThiDanhSachPhimDonGian() {
        System.out.print("DS Phim: ");
        for(Phim p : dsPhim) {
            System.out.print("[" + p.getId() + ":" + p.getTenPhim() + "] ");
        }
        System.out.println();
    }

}