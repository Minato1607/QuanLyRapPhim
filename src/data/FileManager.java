package data;

import model.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    // --- 1. GHI FILE (Dùng chung cho tất cả - Code của Người 1) ---
    public static <T> void saveToFile(String filename, List<T> list) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (T item : list) {
                pw.println(item.toString());
            }
            System.out.println("✅ Đã lưu file: " + filename);
        } catch (Exception e) {
            System.out.println("❌ Lỗi ghi file " + filename + ": " + e.getMessage());
        }
    }

    // --- 2. ĐỌC FILE (Phải viết riêng từng cái vì hàm parse khác nhau) ---

    // Đọc Phim
    public static List<Phim> loadPhim(String filename) {
        List<Phim> list = new ArrayList<>();
        File f = new File(filename);
        if (!f.exists()) return list; // Nếu file chưa có thì trả về list rỗng

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(!line.trim().isEmpty()) // Kiểm tra dòng trống
                    list.add(Phim.parse(line));
            }
        } catch (Exception e) {
            System.out.println("Lỗi đọc file Phim: " + e.getMessage());
        }
        return list;
    }

    // Đọc Lịch Chiếu (Bổ sung giúp Người 1)
    public static List<LichChieu> loadLichChieu(String filename) {
        List<LichChieu> list = new ArrayList<>();
        File f = new File(filename);
        if (!f.exists()) return list;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(!line.trim().isEmpty())
                    list.add(LichChieu.parse(line));
            }
        } catch (Exception e) {
            System.out.println("Lỗi đọc file Lịch Chiếu: " + e.getMessage());
        }
        return list;
    }

    // Đọc Vé (Bổ sung giúp Người 1)
    public static List<Ve> loadVe(String filename) {
        List<Ve> list = new ArrayList<>();
        File f = new File(filename);
        if (!f.exists()) return list;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(!line.trim().isEmpty())
                    list.add(Ve.parse(line));
            }
        } catch (Exception e) {
            System.out.println("Lỗi đọc file Vé: " + e.getMessage());
        }
        return list;
    }
}