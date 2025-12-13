package QuanLyPhimvaLich;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LichChieu implements Serializable {
    private static final long serialVersionUID = 1L;

    private Phim phim;
    private List<String> danhSachGioChieu;

    // Constructor
    public LichChieu(Phim phim) {
        this.phim = phim;
        this.danhSachGioChieu = new ArrayList<>();
    }

    // Thêm giờ chiếu mới
    public void themGioChieu(String gioChieu) {
        this.danhSachGioChieu.add(gioChieu);
    }

    // Getter
    public Phim getPhim() {
        return phim;
    }

    public List<String> getDanhSachGioChieu() {
        return danhSachGioChieu;
    }

    // Phương thức hiển thị thông tin lịch chiếu trong bảng
    public String toPlaintextRow() {
        String gioChieuStr = String.join(", ", danhSachGioChieu);
        return String.format("| %-2s | %-15s | %-10s | %s",
                phim.getId(),
                phim.getTenPhim(),
                phim.getThoiLuong(),
                gioChieuStr.isEmpty() ? "Chưa có lịch" : gioChieuStr);
    }
}