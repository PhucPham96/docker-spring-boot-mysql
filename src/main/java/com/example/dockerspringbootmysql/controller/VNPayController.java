package com.example.dockerspringbootmysql.controller;

import com.example.dockerspringbootmysql.dto.request.VNPayRequestDto;
import com.example.dockerspringbootmysql.service.VnPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
public class VNPayController {

    final VnPayService vnPayService;

    @PostMapping("/createPaymentURLVnPay")
    public ResponseEntity<Object> createPaymentURLVnPay(@RequestBody VNPayRequestDto vnPayRequestDto, HttpServletRequest request) throws UnsupportedEncodingException {
        return ResponseEntity.ok(vnPayService.getPaymentURLVnPay(vnPayRequestDto, request));
    }

    @GetMapping("/vnPay-success")
    public ResponseEntity<Object> displayVnPaySucessPage(@RequestParam(value = "vnp_ResponseCode", required = true) String vnpResponseCode) {
        return ResponseEntity.ok("Ban da thanh toan thanh cong: " + vnpResponseCode);
    }
}
