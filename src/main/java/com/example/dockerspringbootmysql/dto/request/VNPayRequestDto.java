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

    @JsonProperty("vnp_Version")
    private String vnpAmount;

    @JsonProperty("vnp_Version")
    private String vnpBankCode;

    @JsonProperty("vnp_Version")
    private String vnpCreateDate;

    @JsonProperty("vnp_Version")
    private String vnpCurrCode;
//
//    @JsonProperty("vnp_Version")
//    private String vnpIpAddr;

    @JsonProperty("vnp_Version")
    private String vnpLocale;

    @JsonProperty("vnp_Version")
    private String vnpOrderInfo;

    @JsonProperty("vnp_Version")
    private String vnpOrderType;

    @JsonProperty("vnp_Version")
    private String vnpReturnUrl;

    @JsonProperty("vnp_Version")
    private String vnpTxnRef;

    @JsonProperty("vnp_Version")
    private String vnpSecureHash;

    @JsonProperty("language")
    private String language;
}
