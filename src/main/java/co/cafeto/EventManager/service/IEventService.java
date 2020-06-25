package co.cafeto.EventManager.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import co.cafeto.EventManager.dto.EventDTO;
import co.cafeto.EventManager.model.Event;

public interface IEventService {

	List<EventDTO> findAllEvents();
	EventDTO findOne(Integer id);
	Event findOneEntity(Integer id);
	Event save(String jwtToken, MultipartFile multipartFile, String title, String placeName,String description, float latitude, float longitude)  throws Exception;
	Event update(String jwtToken, MultipartFile multipartFile, int id, String title, String placeName, String description, float latitude, float longitude)  throws Exception;
}
