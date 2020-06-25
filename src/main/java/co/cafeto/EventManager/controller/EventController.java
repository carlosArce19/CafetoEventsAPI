package co.cafeto.EventManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sun.istack.NotNull;

import co.cafeto.EventManager.dto.ResponseDTO;
import co.cafeto.EventManager.exception.AWSFailUploadException;
import co.cafeto.EventManager.exception.AWSFileNotValidException;
import co.cafeto.EventManager.exception.EventNotAllowedException;
import co.cafeto.EventManager.exception.EventNotFoundException;
import co.cafeto.EventManager.exception.JWTFormatException;
import co.cafeto.EventManager.dto.EventDTO;
import co.cafeto.EventManager.dto.EventRequestDTO;
import co.cafeto.EventManager.service.IEventService;

@RestController
@RequestMapping("/api/event")
public class EventController {

	@Autowired
	private IEventService eventService;
	
	@PostMapping
	public ResponseEntity<Object> create(@RequestHeader("token") String jwtToken,
			@Nullable @RequestPart(value= "image") final MultipartFile multipartFile, 
			@NotNull @RequestParam("title") String title,
			@Nullable @RequestParam("placeName") String placeName,
			@Nullable @RequestParam("description") String description,
			@NotNull @RequestParam("latitude") float latitude,
			@NotNull @RequestParam("longitude") float longitude) {
		
		try {

			System.out.println("Entro create");
			this.eventService.save(jwtToken, multipartFile, title, placeName, description, latitude, longitude);
			ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK, "Event created correctly.", null);
			
			return new ResponseEntity<>(responseDTO, HttpStatus.OK);
			
		} catch (JWTFormatException e) {
			ResponseDTO  errorResponseDTO = new ResponseDTO(HttpStatus.BAD_REQUEST, e.getMessage(), null);
			return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
		} catch (AWSFailUploadException e) {
			ResponseDTO  errorResponseDTO = new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
			return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}  catch (AWSFileNotValidException e) {
			ResponseDTO  errorResponseDTO = new ResponseDTO(HttpStatus.BAD_REQUEST, e.getMessage(), null);
			return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ResponseDTO  errorResponseDTO = new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
			return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping
	public ResponseEntity<Object> update(@RequestHeader("token") String jwtToken,
			@Nullable @RequestPart(value= "image") final MultipartFile multipartFile, 
			@NotNull @RequestParam("id") int id,
			@NotNull @RequestParam("title") String title,
			@Nullable @RequestParam("placeName") String placeName,
			@Nullable @RequestParam("description") String description,
			@NotNull @RequestParam("latitude") float latitude,
			@NotNull @RequestParam("longitude") float longitude) {
		
		try {

			System.out.println("Entro update");
			this.eventService.update(jwtToken, multipartFile, id, title, placeName, description, latitude, longitude);
			ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK, "Event updated correctly.", null);
			
			return new ResponseEntity<>(responseDTO, HttpStatus.OK);
			
		} catch (EventNotAllowedException e) {
			ResponseDTO  errorResponseDTO = new ResponseDTO(HttpStatus.FORBIDDEN, e.getMessage(), null);
			return new ResponseEntity<>(errorResponseDTO, HttpStatus.FORBIDDEN);
		} catch (EventNotFoundException e) {
			ResponseDTO  errorResponseDTO = new ResponseDTO(HttpStatus.NOT_FOUND, e.getMessage(), null);
			return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
		} catch (JWTFormatException e) {
			ResponseDTO  errorResponseDTO = new ResponseDTO(HttpStatus.BAD_REQUEST, e.getMessage(), null);
			return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
		} catch (AWSFailUploadException e) {
			ResponseDTO  errorResponseDTO = new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
			return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}  catch (AWSFileNotValidException e) {
			ResponseDTO  errorResponseDTO = new ResponseDTO(HttpStatus.BAD_REQUEST, e.getMessage(), null);
			return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ResponseDTO  errorResponseDTO = new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
			return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Object> get(@NotNull @PathVariable("id") Integer id) {
		
		try {
			if(id != null ) {
				EventDTO eventDTO = this.eventService.findOne(id);
				ResponseDTO  responseDTO = new ResponseDTO(HttpStatus.OK, "Event delivered correctly.", eventDTO);
				return new ResponseEntity<>(responseDTO, HttpStatus.OK);
			} else {
				ResponseDTO  errorResponseDTO = new ResponseDTO(HttpStatus.BAD_REQUEST, "Event ID can not be null", null);
				return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ResponseDTO  errorResponseDTO = new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
			return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/all")
	public ResponseEntity<Object> getAll() {
		try {
			
			List<EventDTO> eventsDTO = this.eventService.findAllEvents();
			ResponseDTO  responseDTO = new ResponseDTO(HttpStatus.OK, "Event list delivered correctly.", eventsDTO);
			return new ResponseEntity<>(responseDTO, HttpStatus.OK);
		} catch (Exception e) {
			ResponseDTO  errorResponseDTO = new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
			return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
