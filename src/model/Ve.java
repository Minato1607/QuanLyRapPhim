package model;

public class Ve {
    private String maVe;
    private String maLich;
    private String hang;
    private int cot;
    private int gia;

    public Ve() {}

    public Ve(String maVe, String maLich, String hang, int cot, int gia) {
        this.maVe = maVe;
        this.maLich = maLich;
        this.hang = hang;
        this.cot = cot;
        this.gia = gia;
    }

    // --- 👇 BỔ SUNG ĐOẠN NÀY ĐỂ HẾT LỖI 👇 ---
    public String getMaVe() { return maVe; }
    public String getMaLich() { return maLich; }
    public String getHang() { return hang; }
    public int getCot() { return cot; }
    public int getGia() { return gia; }
    // ----------------------------------------

    @Override
    public String toString() {
        return maVe + ";" + maLich + ";" + hang + ";" + cot + ";" + gia;
    }

    public static Ve parse(String line) {
        try {
            String[] p = line.split(";");
            // Thêm try-catch nhỏ để tránh lỗi nếu file bị dòng trống
            if (p.length >= 5) {
                return new Ve(p[0], p[1], p[2], Integer.parseInt(p[3]), Integer.parseInt(p[4]));
            }
        } catch (Exception e) {
            // Bỏ qua dòng lỗi
        }
        return null;
    }
}