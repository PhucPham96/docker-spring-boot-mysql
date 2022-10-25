package com.example.dockerspringbootmysql.service;

import com.example.dockerspringbootmysql.dto.request.VNPayRequestDto;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class VnPayService {

    public ResponseEntity<Object> getPaymentURLVnPay(VNPayRequestDto vnPayRequestDto, HttpServletRequest request) throws UnsupportedEncodingException {
        String vnpVersion = "2.1.0";
        String vnpCommand = vnPayRequestDto.getVnpCommand();
        String vnpOrderInfo = vnPayRequestDto.getVnpOrderInfo();
        String vnpOrderType = vnPayRequestDto.getVnpOrderType();
        String vnpTxnRef = RandomStringUtils.randomNumeric(8);
        String vnpIpAddr = request.getRemoteAddr();

        //Mã website của merchant trên hệ thống của VNPAY. Ví dụ: 2QXUI4J4
        // phai dang ky
        // Link: https://sandbox.vnpayment.vn/devreg
        String vnpTmnCode = "ZVJIVYAN";

        int amount = Integer.parseInt(vnPayRequestDto.getVnpAmount()) * 100;
        Map<String, String> vnpParams = new HashMap<>();
        vnpParams.put("vnp_Version", vnpVersion);
        vnpParams.put("vnp_Command", vnpCommand);
        vnpParams.put("vnp_TmnCode", vnpTmnCode);
        vnpParams.put("vnp_Amount", String.valueOf(amount));
        vnpParams.put("vnp_CurrCode", "VND");


        String bankCode = vnPayRequestDto.getVnpBankCode();
        if (StringUtils.isNotBlank(bankCode)) {
            vnpParams.put("vnp_BankCode", bankCode);
        }

        vnpParams.put("vnp_TxnRef", vnpTxnRef);
        vnpParams.put("vnp_OrderInfo", vnpOrderInfo);
        vnpParams.put("vnp_OrderType", vnpOrderType);

        String locate = vnPayRequestDto.getVnpLocale();
        if (StringUtils.isNotBlank(locate)) {
            vnpParams.put("vnp_Locale", locate);
        } else {
            vnpParams.put("vnp_Locale", "vn");
        }

        String vnpReturnUrl = "http://localhost:9100/vnPay-success";
        vnpParams.put("vnp_ReturnUrl", vnpReturnUrl);
        vnpParams.put("vnp_IpAddr", vnpIpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnpCreateDate = formatter.format(cld.getTime());
        vnpParams.put("vnp_CreateDate", vnpCreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnpExpireDate = formatter.format(cld.getTime());
        vnpParams.put("vnp_ExpireDate", vnpExpireDate);


        List fieldNames = new ArrayList(vnpParams.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnpParams.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                hashData.append(fieldName);
                hashData.append("=");
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append("=");
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append("&");
                    hashData.append("&");
                }
            }
        }

        String queryUrl = query.toString();

        //Chuỗi bí mật sử dụng để kiểm tra toàn vẹn dữ liệu khi hai hệ thống trao đổi thông tin (checksum)
        String vnpHashSecret = "GCHGPYKDXUQSRGBYHVPEKAOJRFGIRWEC";

//        String vnpSecureHash = hmacSHA512(vnpHashSecret, hashData.toString());
        String vnpSecureHash = new HmacUtils("HmacSHA512", vnpHashSecret).hmacHex(hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnpSecureHash;
        String vnpPayUrl = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
        String paymentUrl = vnpPayUrl + "?" + queryUrl;
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("code", "00");
        jsonObject.addProperty("message", "success");
        jsonObject.addProperty("data", paymentUrl);
        Gson gson = new Gson();
        return ResponseEntity.ok(gson.toJson(jsonObject));
    }

    private String hmacSHA512(final String key, final String data) {
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
