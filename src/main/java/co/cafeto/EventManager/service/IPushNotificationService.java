package co.cafeto.EventManager.service;

import java.util.Map;


public interface IPushNotificationService {
	
	String send(String title, String body, Map<String, Object> data, String to, String priority);

}
