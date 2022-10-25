package com.example.dockerspringbootmysql;

import com.cloudinary.Cloudinary;
import com.cloudinary.SingletonManager;
import com.cloudinary.utils.ObjectUtils;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

@SpringBootApplication
public class DockerSpringBootMysqlApplication {

	public static void main(String[] args) throws IOException {
//		ClassLoader classLoader = DockerSpringBootMysqlApplication.class.getClassLoader();
//
//		File file = new File(Objects.requireNonNull(classLoader.getResource("serviceAccountKey.json")).getFile());
//		FileInputStream serviceAccount = new FileInputStream(file.getAbsoluteFile());
//
//		FirebaseOptions options = new FirebaseOptions.Builder()
//				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
//				.setProjectId("fir-tutorial-b7fcb")
////				.setDatabaseUrl("https://<DATABASE_NAME>.firebaseio.com/")
////				.setStorageBucket("PHUC_BUCKET")
//				.build();
//
//		if (FirebaseApp.getApps().isEmpty()) {
//			FirebaseApp.initializeApp(options);
//		}

		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
				"cloud_name", "dus1qijtv", // insert here you cloud name
				"api_key", "874477434676499", // insert here your api code
				"api_secret", "sBUATmgMNr99eESSQR9GIZr6NiI")); // insert here your api secret
		SingletonManager manager = new SingletonManager();
		manager.setCloudinary(cloudinary);
		manager.init();

		SpringApplication.run(DockerSpringBootMysqlApplication.class, args);
	}

}
