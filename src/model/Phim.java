package model;

public class Phim {
    private String id;
    private String tenPhim;
    private int thoiLuong; // phút
    private long doanhThu = 0;

    public Phim() {}

    public Phim(String id, String tenPhim, int thoiLuong) {
        this.id = id;
        this.tenPhim = tenPhim;
        this.thoiLuong = thoiLuong;
    }

    public String getId() { return id; }
    public String getTenPhim() { return tenPhim; }
    public int getThoiLuong() { return thoiLuong; }

    public void setId(String id) { this.id = id; }
    public void setTenPhim(String tenPhim) { this.tenPhim = tenPhim; }
    public void setThoiLuong(int thoiLuong) { this.thoiLuong = thoiLuong; }

    public long getDoanhThu() { return doanhThu; }
    public void setDoanhThu(long doanhThu) { this.doanhThu = doanhThu; }

    @Override
    public String toString() {
        return id + ";" + tenPhim + ";" + thoiLuong;
    }

    // convert from CSV
    public static Phim parse(String line) {
        String[] p = line.split(";");
        return new Phim(p[0], p[1], Integer.parseInt(p[2]));
    }
}
