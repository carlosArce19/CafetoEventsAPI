package co.cafeto.EventManager.service;

import java.util.List;

import co.cafeto.EventManager.dto.ResponseDTO;

public interface ISubscriptionService {

	List<Object> findByPerson(String jwtToken);
	ResponseDTO create(String jwtToken, int eventId);
}
