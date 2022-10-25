package com.example.dockerspringbootmysql;

import org.apache.commons.lang3.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

public class TestJava {

    public static void main(String[] args) {
        String gsUtilUri = "gs://fir-tutorial-b7fcb.appspot.com/";
        System.out.println(Pattern.matches("gs://.*/.*", gsUtilUri));
//        System.out.println(TestJava.hmacSHA512("", "vnp_Inv_Phone=0333333333"));
        System.out.println(TestJava.hmacSHA512("IUUVEFLBGLIRHVGOHVROSTGGUJJDIZB", "vnp_Amount=1000000&vnp_BankCode=NCB&vnp_Command=pay&vnp_CreateDate=20221020134235&vnp_CurrCode=VND&vnp_ExpireDate=20221020135735&vnp_IpAddr=113.160.225.216&vnp_Locale=vn&vnp_OrderInfo=PHUC&vnp_OrderType=topup&vnp_ReturnUrl=https%3A%2F%2Fsandbox.vnpayment.vn%2Ftryitnow%2FHome%2FVnPayReturn&vnp_TmnCode=2QXUI4J4&vnp_TxnRef=106276&vnp_Version=2.1.0"));

    }

    public static String hmacSHA512(final String key, final String data) {
        try {

            if (key == null || data == null) {
                throw new NullPointerException();
            }
            final Mac hmac512 = Mac.getInstance("HmacSHA512");
            byte[] hmacKeyBytes = key.getBytes();
            final SecretKeySpec secretKey = new SecretKeySpec(hmacKeyBytes, "HmacSHA512");
            hmac512.init(secretKey);
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            byte[] result = hmac512.doFinal(dataBytes);
            StringBuilder sb = new StringBuilder(2 * result.length);
            for (byte b : result) {
                sb.append(String.format("%02x", b & 0xff));
            }
            System.out.println();
            return sb.toString();
        } catch (Exception ex) {
            return "";
        }
    }

}
