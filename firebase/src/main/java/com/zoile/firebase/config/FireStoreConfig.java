package com.zoile.firebase.config;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

@Configuration	
public class FireStoreConfig {
	@Bean
	public Firestore firestore() throws IOException{
		//json 파일 읽어오기
		InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("mydata-53d11-firebase-adminsdk-dxweb-42018c072b.json");
		
		//json 파일 경로가 존재하지 않을 경우 오류 처리
		if(serviceAccount == null) {
			throw new IOException("Firestore의 JSON파일 경로를 찾을 수 없습니다.");
		}
		
		//FirebaseApp 초기화 확인
		if(FirebaseApp.getApps().isEmpty()) {
			FirebaseOptions options = new FirebaseOptions.Builder()
					  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
					  .build();

					FirebaseApp.initializeApp(options);
		}
		return FirestoreClient.getFirestore();
	}
}
