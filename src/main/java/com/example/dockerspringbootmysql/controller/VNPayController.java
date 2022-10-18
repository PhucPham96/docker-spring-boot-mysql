package com.example.dockerspringbootmysql.controller;

import com.example.dockerspringbootmysql.dto.request.VNPayRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class VNPayController {

    @PostMapping("/doPay")
    public ResponseEntity<Object> doPay(@RequestBody VNPayRequestDto vnPayRequestDto, HttpServletRequest request) {
        return null;
    }
}
