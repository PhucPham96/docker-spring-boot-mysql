package com.example.dockerspringbootmysql.controller;

import com.example.dockerspringbootmysql.entity.Attachment;
import com.example.dockerspringbootmysql.service.CRUDAttachmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
public class CRUDAttachmentController {

    final CRUDAttachmentService crudAttachmentService;

    @PostMapping("/create")
    public ResponseEntity<Object> createAttachment(@RequestPart("attachment") String attachmentRequest,
                                                   @RequestPart("file") MultipartFile file) throws ExecutionException, InterruptedException, IOException {
        Attachment attachment = new ObjectMapper().readValue(attachmentRequest, Attachment.class);
        return crudAttachmentService.createAttachment(attachment, file);
    }
}
