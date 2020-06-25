package co.cafeto.EventManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.cafeto.EventManager.dto.ResponseDTO;
import co.cafeto.EventManager.model.Person;
import co.cafeto.EventManager.service.IPersonService;

@RestController
@RequestMapping("/api/person")
public class PersonController {

	@Autowired
	private IPersonService personService;
	
	@GetMapping
	public ResponseEntity<Object> get(@RequestHeader("token") String jwtToken) {
		
		try {
			System.out.println("jwtToken: "+ jwtToken);
			Person person = this.personService.findByToken(jwtToken);
			ResponseDTO  responseDTO = new ResponseDTO(HttpStatus.OK, "Person delivered correctly.", person);
			return new ResponseEntity<>(responseDTO, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			ResponseDTO  errorResponseDTO = new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
			return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
