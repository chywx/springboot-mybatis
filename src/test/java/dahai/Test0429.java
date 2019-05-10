package dahai;

import org.springframework.util.StringUtils;

public class Test0429 {
    public static void main(String[] args) {
        String a = "a %s %d b";
        String format = String.format(a, "---",123);
        System.out.println(format);

        System.out.println(test1(""));

    }


    public static String test1(String qishu){
        String payOrder = "{" +
                "\"out_trade_no\":\"123\"," +
                "\"total_amount\":123," +
                "\"product_code\":\"QUICK_WAP_WAY\","+
//				"\"extend_params\":{\"hb_fq_num\":\"3\",\"hb_fq_seller_percent\":\"0\"}, "+
                "%s" +
                "\"subject\":\"aaa\"" +
                "}";

        if(!StringUtils.isEmpty(qishu)){
            return String.format(payOrder, "\"extend_params\":{\"hb_fq_num\":\""+qishu+"\",\"hb_fq_seller_percent\":\"0\"}, ");
        }else{
            return String.format(payOrder, "");
        }
    }

}
