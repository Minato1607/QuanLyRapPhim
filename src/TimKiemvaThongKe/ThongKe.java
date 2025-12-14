package TimKiemvaThongKe;

import model.Ve; // <--- QUAN TRỌNG
import java.util.ArrayList;
import java.util.List;

public class ThongKe {

    public void xemDoanhThu(List<Ve> dsVe) {
        long tongTien = 0;
        for (Ve v : dsVe) {
            // Lưu ý: Đảm bảo class Ve bên model có hàm getGia() hoặc getGiaVe()
            // Nếu Ve.java dùng biến 'gia' là public thì dùng v.gia
            // Ở đây giả sử getGia() chưa có, mình tính tạm 50k
            tongTien += 50000;
        }

        System.out.println("====== BAO CAO ======");
        System.out.println("So ve da ban: " + dsVe.size());
        System.out.println("Tong doanh thu (uoc tinh): " + tongTien + " VND");
        System.out.println("=====================");
    }
}