package ex1;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Phong Nha
 */
public class DocSo {

    public static HashMap<String, String> hm_tien = new HashMap<String, String>() {
        {
            put("0", "không");
            put("1", "một");
            put("2", "hai");
            put("3", "ba");
            put("4", "bốn");
            put("5", "năm");
            put("6", "sáu");
            put("7", "bảy");
            put("8", "tám");
            put("9", "chín");
        }
    };
    public static HashMap<String, String> hm_hanh = new HashMap<String, String>() {
        {
            put("1", "");
            put("2", "mươi");
            put("3", "trăm");
            put("4", "nghìn");
            put("5", "mươi");
            put("6", "trăm");
            put("7", "triệu");
            put("8", "mươi");
            put("9", "trăm");
            put("10", "tỷ");
            put("11", "mươi");
            put("12", "trăm");
            put("13", "nghìn");
            put("14", "mươi");
            put("15", "trăm");

        }
    };


    public static String ChuyenSangChu(String m) {
        String kq = "";
       
        int dem = m.length();
        String dau = "";
        int flag10 = 1; //biến để kiểm tra xem có được gọi là "mốt" hay không. 1 là k được gọi
        int flag5=1;//biến để kiểm tra xem có được gọi là  "lăm" hay không. 1 là không được gọi
        int flag4=1;//biến để kiểm tra xem có được gọi là  "tư" hay không. 1 là không được gọi
        if(Long.parseLong(m) == 0) kq="không";
        else while (!m.equals("")) {
            if (m.length() <= 3 && m.length() > 1 && Long.parseLong(m) == 0) {
            	
            } else {
                dau = m.substring(0, 1);
                if (dem % 3 == 1 && m.startsWith("1") && flag10 == 0) { 
                    kq += "mốt ";
                    flag10 = 0;
                    flag5= 0;
                    flag4=0;
                } else if (dem % 3 == 2 && m.startsWith("1")) {
                    kq += "mười ";
                    flag10 =1;
                    flag5 = 0;
                    flag4=1;
                }
                else if (dem % 3 == 1 && m.startsWith("5") && flag5==0) {
                    kq += "lăm ";
                    flag10 = 0;
                    flag5= 0;
                    flag4=0;
                } else if (dem % 3 == 1 && m.startsWith("4") && flag4==0) {
                    kq += "tư ";
                    flag10 = 0;
                    flag5= 0;
                    flag4=0;
                }
                else if (dem % 3 == 2 && m.startsWith("0") && m.length() >= 2 && !m.substring(1, 2).equals("0")) {
                   
                    kq += "lẻ ";
                    flag10 = 1;
                    flag5 =1;
                    flag4=1;
                  
                } else {
                    if (!m.startsWith("0")) {
                        kq += hm_tien.get(dau) + " ";
                        flag10 = 0;
                        flag5=0;
                        flag4=0;
                    }
                }
                //gán hàng
                if (dem%3!=1 &&m.startsWith("0") && m.length()>1) {
                } else {
                    if (dem % 3 == 2 && (m.startsWith("1") || m.startsWith("0"))) {//mười/lẻ
                    } else {
                        kq += hm_hanh.get(dem + "") + " ";
                        flag5=0;
                        flag10=0;
                        flag4=0;
                    }
                }
            }
            m = m.substring(1);
            dem = m.length();
        }
        kq=kq.substring(0, kq.length() - 1);
        return kq;
    }

   
}