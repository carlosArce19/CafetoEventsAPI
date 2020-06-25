package co.cafeto.EventManager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import co.cafeto.EventManager.dao.ISubscriptionDAO;
import co.cafeto.EventManager.dto.PersonTokenDTO;
import co.cafeto.EventManager.dto.ResponseDTO;
import co.cafeto.EventManager.exception.EventNotFoundException;
import co.cafeto.EventManager.exception.PersonNotFoundException;
import co.cafeto.EventManager.model.Event;
import co.cafeto.EventManager.model.Person;
import co.cafeto.EventManager.model.Subscription;
import co.cafeto.EventManager.model.SubscriptionId;
import co.cafeto.EventManager.util.IJWTUtil;

@Service
public class SubscriptionService implements ISubscriptionService {
	
	@Autowired
	private ISubscriptionDAO suscriptionDAO;
	
	@Autowired
	private IPersonService personService;
	
	@Autowired
	private IEventService eventService;
	
	@Autowired
	private IJWTUtil jwtUtil;

	@Override
	public List<Object> findByPerson(String jwtToken) {
		
		PersonTokenDTO personTokenDTO = this.jwtUtil.getPersonFronJWT(jwtToken);
		
		Person owner = this.personService.findOne(personTokenDTO.getEmail());
		if(owner != null) {
			return suscriptionDAO.findByPerson(owner.getEmail());
		} else {
			throw new PersonNotFoundException();
		}
	}

	@Override
	public ResponseDTO create(String jwtToken, int eventId) {
		
		ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK, "Subscription created correctly", null);
		PersonTokenDTO personTokenDTO = this.jwtUtil.getPersonFronJWT(jwtToken);
		
		Person subscriptor = this.personService.findOne(personTokenDTO.getEmail());
		if(subscriptor == null) {
			subscriptor = new Person();
			subscriptor.setEmail(personTokenDTO.getEmail());
			subscriptor.setName(personTokenDTO.getName());
			subscriptor.setNickname(personTokenDTO.getNickname());
			subscriptor.setSub(personTokenDTO.getSub());
			subscriptor.setEmailVerified(personTokenDTO.isEmail_verified());
			this.personService.save(subscriptor);
		}
		
		Event event = eventService.findOneEntity(eventId);
		if(event != null ) {
			
			SubscriptionId suscriptionId = new SubscriptionId(subscriptor, event);
			
			Subscription subscription = this.suscriptionDAO.findById(suscriptionId).isPresent()?this.suscriptionDAO.findById(suscriptionId).get():null;
			if(subscription == null) {
				subscription = new Subscription();
				subscription.setSuscriptionId(suscriptionId);
				this.suscriptionDAO.save(subscription);
			} else {
				responseDTO.setMessage("Person already subscribed to Event.");
			}
			return responseDTO;
			
		} else {
			throw new EventNotFoundException();
		}		
		
	}

	

	
}
