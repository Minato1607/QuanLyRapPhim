package QuanLyPhimvaLich;

import java.io.Serializable;

public class Phim implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String tenPhim;
    private String thoiLuong;

    // Constructor
    public Phim(String id, String tenPhim, String thoiLuong) {
        this.id = id;
        this.tenPhim = tenPhim;
        this.thoiLuong = thoiLuong;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public String getThoiLuong() {
        return thoiLuong;
    }

    // Setters
    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public void setThoiLuong(String thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    // Phương thức hiển thị theo định dạng bảng
    public String toPlaintextRow() {
        return String.format("| %-2s | %-15s | %-10s |", id, tenPhim, thoiLuong);
    }
}