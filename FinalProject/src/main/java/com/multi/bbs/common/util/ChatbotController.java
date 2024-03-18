package com.multi.bbs.common.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class ChatbotController {

	@GetMapping("/chatbot")
	public String chatbotPage() {
		return "common/chatbot";
	}
	
	@PostMapping(value = "/chatbotAjax.do", produces = "text/html; charset=utf8")
	@ResponseBody
	public String chatbotAjax(String userChat) throws IOException {
//		// API 호출을 위한 URL 설정
//        String apiUrl = "https://47rv9xlkxz.apigw.ntruss.com/custom/v1/13797/4ebde42874f9cb13f73e4ea12b87f7c4f3c6f8a88a828bd45883b10667d483b5";
//
//        // HttpURLConnection 객체 생성
//        URL url = new URL(apiUrl);
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//
//        // 헤더 설정
//        connection.setRequestMethod("POST");
//        String secretKey = "eEVxdmhpdmFieWltTG9NRFpzY0dtcVhLTVphb0pZWkk=";
//        byte[] secretKeyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
//        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyBytes, "HmacSHA256");
//        Mac mac = Mac.getInstance("HmacSHA256");
//        mac.init(secretKeySpec);
//        byte[] signature = mac.doFinal(userChat.getBytes(StandardCharsets.UTF_8));
//        String signatureHeader = Base64.getEncoder().encodeToString(signature);
//        
//        connection.setRequestProperty("Content-Type", "application/json;UTF-8");
//        connection.setRequestProperty("X-NCP-CHATBOT_SIGNATURE", signatureHeader);

        // API 호출 및 응답 처리
        // ...
		return userChat;
	}
	
}
