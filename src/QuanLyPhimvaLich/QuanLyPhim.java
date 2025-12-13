package QuanLyPhimvaLich;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class QuanLyPhim {
    private static final String FILE_NAME = "data_quanlyphim.txt"; // Tệp tin văn bản

    private transient AtomicInteger nextPhimId = new AtomicInteger(1);

    private List<Phim> danhSachPhim;
    private List<LichChieu> danhSachLichChieu;

    public QuanLyPhim() {
        this.danhSachPhim = new ArrayList<>();
        this.danhSachLichChieu = new ArrayList<>();
    }

    // Khởi tạo dữ liệu mẫu cho lần chạy đầu tiên
    private void khoiTaoDuLieuMau() {
        if (danhSachPhim.isEmpty()) {
            Phim p1 = new Phim("01", "Mai", "120p");
            Phim p2 = new Phim("02", "Dune 2", "166p");

            danhSachPhim.add(p1);
            danhSachPhim.add(p2);

            nextPhimId.set(3);

            LichChieu lc1 = new LichChieu(p1);
            lc1.themGioChieu("18:00");
            lc1.themGioChieu("20:30");

            LichChieu lc2 = new LichChieu(p2);

            danhSachLichChieu.add(lc1);
            danhSachLichChieu.add(lc2);

            System.out.println("ℹ️ Đã khởi tạo dữ liệu mẫu.");
        }
    }

    // Thiết lập lại ID tự động sau khi đọc file
    public void setupNextPhimId() {
        if (danhSachPhim.isEmpty()) {
            nextPhimId.set(1);
            return;
        }
        int maxId = 0;
        for (Phim p : danhSachPhim) {
            try {
                int id = Integer.parseInt(p.getId());
                if (id > maxId) {
                    maxId = id;
                }
            } catch (NumberFormatException ignored) {}
        }
        nextPhimId.set(maxId + 1);
    }


    // ==========================================================
    // --- LƯU DỮ LIỆU (Áp dụng FileWriter/PrintWriter) ---
    // Định dạng lưu: ID|TenPhim|ThoiLuong|GioChieu1,GioChieu2,...
    // ==========================================================

    public void luuDuLieu() {
        // Sử dụng PrintWriter để ghi dữ liệu văn bản vào file hiệu quả hơn
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {

            for (LichChieu lc : danhSachLichChieu) {
                Phim p = lc.getPhim();
                String gioChieuStr = String.join(",", lc.getDanhSachGioChieu());

                // Ghi dữ liệu theo định dạng: ID|Tên phim|Thời lượng|Lịch chiếu
                String line = String.format("%s|%s|%s|%s",
                        p.getId(),
                        p.getTenPhim(),
                        p.getThoiLuong(),
                        gioChieuStr);
                writer.println(line);
            }

            System.out.println("✅ Dữ liệu đã được lưu thành công vào file: " + FILE_NAME);

        } catch (IOException i) {
            System.out.println("❌ Lỗi khi ghi dữ liệu vào tệp tin:");
            i.printStackTrace();
        }
    }

    // ==========================================================
    // --- ĐỌC DỮ LIỆU (Áp dụng FileReader/BufferedReader) ---
    // ==========================================================

    public static QuanLyPhim docDuLieu() {
        QuanLyPhim quanLy = new QuanLyPhim();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("ℹ️  Không tìm thấy file dữ liệu cũ. Khởi tạo dữ liệu mới.");
            quanLy.khoiTaoDuLieuMau();
            return quanLy;
        }

        // Sử dụng BufferedReader để đọc dữ liệu văn bản từ file hiệu quả hơn
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;
            while ((line = reader.readLine()) != null) {
                // Tách các trường dữ liệu bằng dấu '|'
                String[] parts = line.split("\\|");

                if (parts.length >= 3) {
                    String id = parts[0];
                    String tenPhim = parts[1];
                    String thoiLuong = parts[2];
                    String gioChieuStr = parts.length > 3 ? parts[3] : "";

                    Phim p = new Phim(id, tenPhim, thoiLuong);
                    LichChieu lc = new LichChieu(p);

                    // Xử lý lịch chiếu (tách chuỗi bằng dấu ',')
                    if (!gioChieuStr.isEmpty()) {
                        String[] gioChieuList = gioChieuStr.split(",");
                        for (String gio : gioChieuList) {
                            lc.themGioChieu(gio.trim());
                        }
                    }

                    quanLy.danhSachPhim.add(p);
                    quanLy.danhSachLichChieu.add(lc);
                }
            }

            quanLy.setupNextPhimId();
            System.out.println("✅ Đã tải dữ liệu thành công từ file: " + FILE_NAME);
            return quanLy;

        } catch (IOException e) {
            System.out.println("❌ Lỗi I/O khi đọc dữ liệu. Khởi tạo dữ liệu mới.");
            e.printStackTrace();
            return new QuanLyPhim();
        }
    }
    // ==========================================================
    // --- CHỨC NĂNG QUẢN LÝ ---
    // ==========================================================

    private Phim timPhimTheoId(String id) {
        for (Phim p : danhSachPhim) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    private LichChieu timLichChieuTheoIdPhim(String id) {
        for (LichChieu lc : danhSachLichChieu) {
            if (lc.getPhim().getId().equals(id)) {
                return lc;
            }
        }
        return null;
    }

    public void themPhim(Scanner scanner) {
        System.out.println("\n--- Thêm QuanLyPhimvaLich.Phim Mới ---");
        // nextPhimId.getAndIncrement() đảm bảo ID mới nhất
        String id = String.format("%02d", nextPhimId.getAndIncrement());

        System.out.print("Nhập Tên QuanLyPhimvaLich.Phim: ");
        scanner.nextLine();
        String tenPhim = scanner.nextLine();

        System.out.print("Nhập Thời Lượng (ví dụ: 120p): ");
        String thoiLuong = scanner.nextLine();

        Phim newPhim = new Phim(id, tenPhim, thoiLuong);
        danhSachPhim.add(newPhim);
        danhSachLichChieu.add(new LichChieu(newPhim));

        System.out.println("✅ Đã thêm phim: " + tenPhim + " (ID: " + id + ")");
    }

    public void suaPhim(Scanner scanner) {
        System.out.println("\n--- Sửa QuanLyPhimvaLich.Phim ---");
        System.out.print("Nhập ID QuanLyPhimvaLich.Phim cần sửa: ");
        String idCanSua = scanner.next();

        Phim phim = timPhimTheoId(idCanSua);

        if (phim != null) {
            System.out.println("Đang sửa phim: " + phim.getTenPhim());

            System.out.print("Nhập Tên QuanLyPhimvaLich.Phim mới (hoặc enter để giữ nguyên): ");
            scanner.nextLine();
            String newTen = scanner.nextLine();
            if (!newTen.isEmpty()) {
                phim.setTenPhim(newTen);
            }

            System.out.print("Nhập Thời Lượng mới (hoặc enter để giữ nguyên): ");
            String newThoiLuong = scanner.nextLine();
            if (!newThoiLuong.isEmpty()) {
                phim.setThoiLuong(newThoiLuong);
            }

            System.out.println("✅ Đã cập nhật phim ID: " + idCanSua);
        } else {
            System.out.println("❌ Không tìm thấy phim với ID: " + idCanSua);
        }
    }

    public void xoaPhim(Scanner scanner) {
        System.out.println("\n--- Xóa QuanLyPhimvaLich.Phim ---");
        System.out.print("Nhập ID QuanLyPhimvaLich.Phim cần xóa: ");
        String idCanXoa = scanner.next();

        Phim phim = timPhimTheoId(idCanXoa);

        if (phim != null) {
            danhSachPhim.remove(phim);

            LichChieu lc = timLichChieuTheoIdPhim(idCanXoa);
            if(lc != null) {
                danhSachLichChieu.remove(lc);
            }

            System.out.println("✅ Đã xóa phim: " + phim.getTenPhim() + " (ID: " + idCanXoa + ")");
        } else {
            System.out.println("❌ Không tìm thấy phim với ID: " + idCanXoa);
        }
    }

    public void xepLichChieu(Scanner scanner) {
        System.out.println("\n--- Xếp Lịch Chiếu ---");
        System.out.print("Nhập ID QuanLyPhimvaLich.Phim cần xếp lịch: ");
        String idPhim = scanner.next();

        Phim phim = timPhimTheoId(idPhim);

        if (phim == null) {
            System.out.println("❌ Không tìm thấy phim với ID: " + idPhim);
            return;
        }

        LichChieu lichChieu = timLichChieuTheoIdPhim(idPhim);

        System.out.println("Đang xếp lịch cho phim: " + phim.getTenPhim());
        System.out.print("Nhập giờ chiếu mới (ví dụ: 10:00, 14:30). Nhập '0' để dừng: ");

        String gioChieu;
        while (true) {
            gioChieu = scanner.next();
            if (gioChieu.equals("0")) {
                break;
            }
            lichChieu.themGioChieu(gioChieu);
            System.out.print("Đã thêm. Nhập giờ chiếu tiếp theo (hoặc '0' để dừng): ");
        }

        System.out.println("✅ Đã cập nhật lịch chiếu cho phim ID: " + idPhim);
    }

    public void hienThiDanhSach() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("🎬 DANH SÁCH PHIM VÀ LỊCH CHIẾU");
        System.out.println("=".repeat(70));

        // Header
        String header = String.format("| %-2s | %-15s | %-10s | %-33s",
                "ID", "Ten QuanLyPhimvaLich.Phim", "Thoi Luong", "Lich Chieu");
        String separator = "|" + "-".repeat(4) + "|" + "-".repeat(17) + "|" + "-".repeat(12) + "|" + "-".repeat(34) + "|";

        System.out.println(header);
        System.out.println(separator);

        if (danhSachLichChieu.isEmpty()) {
            System.out.println("| " + " ".repeat(66) + " |");
            System.out.println("| " + " ".repeat(25) + "Chưa có bộ phim nào." + " ".repeat(26) + " |");
            System.out.println("| " + " ".repeat(66) + " |");
        } else {
            for (LichChieu lc : danhSachLichChieu) {
                System.out.println(lc.toPlaintextRow());
            }
        }
        System.out.println(separator);
    }
}