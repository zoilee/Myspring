package net.musecom.comunity.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class LoginService {
	public String getAccessToken(String authorize_code) {
	    String access_Token = "";
	    String refresh_Token = "";
	    String reqURL = "https://kauth.kakao.com/oauth/token";

	    try {
	        URL url = new URL(reqURL);

	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	        //    POST 요청을 위해 기본값이 false인 setDoOutput을 true로 변경을 해주세요

	        conn.setRequestMethod("POST");
	        conn.setDoOutput(true);

	        //    POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
	        // BufferedWriter 간단하게 파일을 끊어서 보내기로 토큰값을 받아오기위해 전송

	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
	        StringBuilder sb = new StringBuilder();
	        sb.append("grant_type=authorization_code");
	        sb.append("&client_id=5872cb185b3087349e23cfeb7dac0ab8");  //발급받은 key
	        sb.append("&redirect_uri=http://localhost:8080/comunity/");     // 본인이 설정해 놓은 redirect_uri 주소
	        sb.append("&code=" + authorize_code);
	        bw.write(sb.toString());
	        bw.flush();

	        //    결과 코드가 200이라면 성공
	        // 여기서 안되는경우가 많이 있어서 필수 확인 !! **
	        int responseCode = conn.getResponseCode();
	        System.out.println("responseCode : " + responseCode + "확인");

	        //    요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        String line = "";
	        String result = "";

	        while ((line = br.readLine()) != null) {
	            result += line;
	        }
	        System.out.println("response body : " + result + "결과");

	        //    Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
	        JsonParser parser = new JsonParser();
	        JsonElement element = parser.parse(result);

	        access_Token = element.getAsJsonObject().get("access_token").getAsString();
	        refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

	        System.out.println("access_token : " + access_Token);
	        System.out.println("refresh_token : " + refresh_Token);

	        br.close();
	        bw.close();
	    } catch (IOException e) {

	        e.printStackTrace();
	    }
	    return access_Token;
	}
	
	
}
