package TimKiemvaThongKe;

import java.util.ArrayList;

public class ThongKe {

    public void xemDoanhThu(ArrayList<Ve> dsVe) {
        long tongTien = 0;
        for (int i = 0; i < dsVe.size(); i++) {
            tongTien += (long)dsVe.get(i).getGiaVe();
        }

        System.out.println("====== BAO CAO ======");
        System.out.println("So ve da ban: " + dsVe.size());
        System.out.println("Tong doanh thu: " + tongTien + " VND");
        System.out.println("=====================");
    }
}