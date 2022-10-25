package com.example.dockerspringbootmysql.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VNPayRequestDto {

//    @JsonProperty("vnp_Version")
//    private String vnpVersion;

//    @JsonProperty("vnp_Version")
//    private String vnpCommand;

//    @JsonProperty("vnp_Version")
//    private String vnpTmnCode;

    @JsonProperty("vnp_Amount")
    private String vnpAmount;

    @JsonProperty("vnp_BankCode")
    private String vnpBankCode;
//
//    @JsonProperty("vnp_CreateDate")
//    private String vnpCreateDate;

    @JsonProperty("vnp_Command")
    private String vnpCommand;

//    @JsonProperty("vnp_CurrCode")
//    private String vnpCurrCode = "VND";
//
//    @JsonProperty("vnp_Version")
//    private String vnpIpAddr;

    @JsonProperty("vnp_Locale")
    private String vnpLocale;

    @JsonProperty("vnp_OrderInfo")
    private String vnpOrderInfo;

    @JsonProperty("vnp_OrderType")
    private String vnpOrderType;

//    @JsonProperty("vnp_ReturnUrl")
//    private String vnpReturnUrl;

//    @JsonProperty("vnp_Version")
//    private String vnpTxnRef;
//
//    @JsonProperty("vnp_Version")
//    private String vnpSecureHash;
//
//    @JsonProperty("language")
//    private String language;
}
