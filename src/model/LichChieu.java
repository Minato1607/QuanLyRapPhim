package model;

public class LichChieu {
    private String maLich;
    private String maPhim;
    private String phong;
    private String gio;

    public LichChieu() {}

    public LichChieu(String maLich, String maPhim, String phong, String gio) {
        this.maLich = maLich;
        this.maPhim = maPhim;
        this.phong = phong;
        this.gio = gio;
    }

    public String getMaLich() { return maLich; }
    public String getMaPhim() { return maPhim; }
    public String getPhong() { return phong; }
    public String getGio() { return gio; }

    @Override
    public String toString() {
        return maLich + ";" + maPhim + ";" + phong + ";" + gio;
    }

    public static LichChieu parse(String line) {
        String[] p = line.split(";");
        return new LichChieu(p[0], p[1], p[2], p[3]);
    }
}
