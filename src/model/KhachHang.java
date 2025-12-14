package model;

public class KhachHang {
    private String maKH;
    private String ten;
    private String sdt;
    private String email;

    public KhachHang() {}

    public KhachHang(String maKH, String ten, String sdt, String email) {
        this.maKH = maKH;
        this.ten = ten;
        this.sdt = sdt;
        this.email = email;
    }

    public String getMaKH() {
        return maKH;
    }

    public String getTen() {
        return ten;
    }

    public String getSdt() {
        return sdt;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return maKH + ";" + ten + ";" + sdt + ";" + email;
    }

    public static KhachHang parse(String line) {
        String[] p = line.split(";");
        return new KhachHang(p[0], p[1], p[2], p[3]);
    }
}
