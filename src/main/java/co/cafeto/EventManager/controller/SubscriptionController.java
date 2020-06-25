package co.cafeto.EventManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.cafeto.EventManager.dto.EventRequestDTO;
import co.cafeto.EventManager.dto.ResponseDTO;
import co.cafeto.EventManager.exception.EventNotFoundException;
import co.cafeto.EventManager.exception.JWTFormatException;
import co.cafeto.EventManager.exception.PersonNotFoundException;
import co.cafeto.EventManager.service.ISubscriptionService;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {

	@Autowired
	private ISubscriptionService subscriptionService;
	
	@GetMapping(value="/person")
	public ResponseEntity<Object> get(@RequestHeader("token") String jwtToken) {
		
		try {
			List<Object> subscriptionList = this.subscriptionService.findByPerson(jwtToken);
			ResponseDTO  responseDTO = new ResponseDTO(HttpStatus.OK, "Subscriptions list delivered correctly.", subscriptionList);
			return new ResponseEntity<>(responseDTO, HttpStatus.OK);
			
		}   catch (PersonNotFoundException e) {
			ResponseDTO  errorResponseDTO = new ResponseDTO(HttpStatus.NOT_FOUND, e.getMessage(), null);
			return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
		}  catch (JWTFormatException e) {
			ResponseDTO  errorResponseDTO = new ResponseDTO(HttpStatus.BAD_REQUEST, e.getMessage(), null);
			return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseDTO  errorResponseDTO = new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
			return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<Object> create(@RequestHeader("token") String jwtToken, @RequestBody EventRequestDTO eventRequestDTO) {
		
		try {
			if(eventRequestDTO.getId() != null ) {

				ResponseDTO  responseDTO = this.subscriptionService.create(jwtToken, eventRequestDTO.getId());
				return new ResponseEntity<>(responseDTO, HttpStatus.OK);
			} else {
				ResponseDTO  errorResponseDTO = new ResponseDTO(HttpStatus.BAD_REQUEST, "Event ID can not be null", null);
				return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
			}
		}   catch (EventNotFoundException e) {
			ResponseDTO  errorResponseDTO = new ResponseDTO(HttpStatus.NOT_FOUND, e.getMessage(), null);
			return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
		}  catch (JWTFormatException e) {
			ResponseDTO  errorResponseDTO = new ResponseDTO(HttpStatus.BAD_REQUEST, e.getMessage(), null);
			return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseDTO  errorResponseDTO = new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
			return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
