package co.cafeto.EventManager.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import co.cafeto.EventManager.dto.NotificationBodyDTO;
import co.cafeto.EventManager.dto.PushNotificationDTO;

@Service
public class FirebaseNotificationService implements IPushNotificationService {

	@Value("${firebase.serverKey}")
	private String serverKey;
	
	@Value("${firebase.apiUrl}")
	private String apiUrl;
	
	@Override
	@Async
	public String send(String title, String body, Map<String, Object> data, String to, String priority) {
		 
		String jsonRequest = this.createPushNotificationDTO(title, body, data, to, priority);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "key=" + serverKey);
		headers.add("Content-Type", "application/json");
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> request = new HttpEntity<String>( jsonRequest,headers);
		String respuesta = restTemplate.postForObject(apiUrl, request, String.class);
		
		return respuesta;
	}
	
	private String createPushNotificationDTO(String title, String body, Map<String, Object> data, String to, String priority) {
		NotificationBodyDTO notificationBodyDTO = new NotificationBodyDTO(title, body);
		PushNotificationDTO pushNotificationDTO = new PushNotificationDTO(notificationBodyDTO, to, priority);
		
		Gson gson = new Gson();
		JsonElement jsonRequest = gson.toJsonTree(pushNotificationDTO);
		
		if(data != null && data.size() > 0) {
			JsonElement jsonRequestData = gson.toJsonTree(new Object());
			for (Map.Entry<String, Object> key : data.entrySet()) {
				jsonRequestData.getAsJsonObject().addProperty(key.getKey(), key.getValue().toString());
		    }
			jsonRequest.getAsJsonObject().add("data", jsonRequestData);
		}
		
		gson.toJson(jsonRequest);
		return gson.toJson(jsonRequest);
	}

}
