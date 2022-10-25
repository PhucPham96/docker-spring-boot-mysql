package com.example.dockerspringbootmysql.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.Singleton;
import com.cloudinary.utils.ObjectUtils;
import com.example.dockerspringbootmysql.entity.Attachment;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.cloud.storage.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.cloud.StorageClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class CRUDAttachmentService {
    private final Cloudinary cloudinary = Singleton.getCloudinary();

    public ResponseEntity<Object> createAttachment(Attachment attachment, MultipartFile file) throws ExecutionException, InterruptedException, IOException {
        Firestore dbFileStore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectonsApiFuture = dbFileStore.collection("crud_attachment").document(attachment.getName()).set(attachment);

//        Bucket bucket = StorageClient.getInstance().bucket();
//        String name = generateFileName(file.getOriginalFilename());
//        bucket.create(name, file.getBytes(), file.getContentType());
        Map params = ObjectUtils.asMap(
                "public_id", file.getOriginalFilename(),
                "folder", "PHUC",
                "overwrite", true
//                "notification_url", "https://mysite.com/notify_endpoint",
//                "resource_type", "video"
        );
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), params);
        String publicId = uploadResult.get("public_id").toString();

        // Firebase upload
        String projectId = "fir-tutorial-b7fcb";
//        String bucketName = "fir-tutorial-b7fcb.appspot.com";
//        String objectName = "";
        String storePath = "gs://fir-tutorial-b7fcb.appspot.com/attachment";
        Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
        BlobId blobId = BlobId.fromGsUtilUri(storePath);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        storage.createFrom(blobInfo, new ByteArrayInputStream(file.getBytes()));
        return ResponseEntity.ok(collectonsApiFuture.get().getUpdateTime().toString());
    }

    private String generateFileName(String originalFileName) {
        return UUID.randomUUID().toString() + getExtension(originalFileName);
    }

    private String getExtension(String originalFileName) {
        return StringUtils.getFilenameExtension(originalFileName);
    }
}
