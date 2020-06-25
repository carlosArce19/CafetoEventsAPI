package co.cafeto.EventManager.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import co.cafeto.EventManager.dao.IEventDAO;
import co.cafeto.EventManager.dto.EventDTO;
import co.cafeto.EventManager.dto.PersonTokenDTO;
import co.cafeto.EventManager.exception.EventNotAllowedException;
import co.cafeto.EventManager.exception.EventNotFoundException;
import co.cafeto.EventManager.model.Event;
import co.cafeto.EventManager.model.Person;
import co.cafeto.EventManager.util.IJWTUtil;

@Service
public class EventService implements IEventService{
	
	@Autowired
	private IEventDAO eventDAO;
	
	@Autowired
	private IPersonService personService;
	
	@Autowired
	private IJWTUtil jwtUtil;
	
	@Autowired
	private IAWSS3Service awsService;
	
	@Autowired
	private IPushNotificationService pushNotificationService;
	
	@Value("${app.awsServices.path.public}")
	private String publicPath;

	@Override
	public EventDTO findOne(Integer id) {
		Event  event = eventDAO.findById(id).isPresent()?eventDAO.findById(id).get():null;
		
		EventDTO eventDTO = null;
		if(event!=null) {
			eventDTO = new EventDTO();
			eventDTO.setId(event.getId());
			eventDTO.setTitle(event.getTitle());
			eventDTO.setDescription(event.getDescription());
			eventDTO.setImage(event.getImage());
			eventDTO.setLatitude(event.getLatitude());
			eventDTO.setLongitude(event.getLongitude());
			eventDTO.setOwner(event.getPerson().getEmail());
			eventDTO.setPlaceName(event.getPlaceName());
		}
		return eventDTO;
	}

	@Override
	public Event save(String jwtToken, MultipartFile multipartFile, String title, String placeName, String description, float latitude, float longitude) throws Exception {
		
		PersonTokenDTO personTokenDTO = this.jwtUtil.getPersonFronJWT(jwtToken);
		
		Person owner = this.personService.findOne(personTokenDTO.getEmail());
		if(owner == null) {
			owner = new Person();
			owner.setEmail(personTokenDTO.getEmail());
			owner.setName(personTokenDTO.getName());
			owner.setNickname(personTokenDTO.getNickname());
			owner.setSub(personTokenDTO.getSub());
			owner.setEmailVerified(personTokenDTO.isEmail_verified());
			this.personService.save(owner);
		}
		
		String imageFilePath = multipartFile!=null?publicPath+this.awsService.uploadFile(multipartFile):null;
		
		Event event = new Event();
		event.setDescription(description);
		event.setImage(imageFilePath);
		event.setLatitude(latitude);
		event.setLongitude(longitude);
		event.setTitle(title);
		event.setPerson(owner);
		event.setCreationDate(new Date());
		event.setPlaceName(placeName);
		event = eventDAO.save(event);
		
		return event;
	}

	@Override
	public List<EventDTO> findAllEvents() {

		List<EventDTO> listEvents =eventDAO.findAllOrderByDate().stream().map(event -> {
			EventDTO eventDTO = new EventDTO();
			eventDTO.setId(event.getId());
			eventDTO.setTitle(event.getTitle());
			eventDTO.setDescription(event.getDescription());
			eventDTO.setImage(event.getImage());
			eventDTO.setLatitude(event.getLatitude());
			eventDTO.setLongitude(event.getLongitude());
			eventDTO.setOwner(event.getPerson().getEmail());
			eventDTO.setPlaceName(event.getPlaceName());
			return eventDTO;
		}).collect(Collectors.toList());
		return listEvents;
	}

	@Override
	public Event update(String jwtToken, MultipartFile multipartFile, int id, String title, String placeName, String description, float latitude,
			float longitude) throws Exception {
		
		PersonTokenDTO personTokenDTO = this.jwtUtil.getPersonFronJWT(jwtToken);
		
		Event  event = eventDAO.findById(id).isPresent()?eventDAO.findById(id).get():null;
		if(event != null ) {
			Person owner = this.personService.findOne(personTokenDTO.getEmail());
			if(owner != null && owner.getEmail().equals(event.getPerson().getEmail())) {
				
				String imageFilePath = multipartFile!=null?publicPath+this.awsService.uploadFile(multipartFile):null;
				if(imageFilePath != null) {
					event.setImage(imageFilePath);
				}

				event.setDescription(description);
				event.setLatitude(latitude);
				event.setLongitude(longitude);
				event.setTitle(title);
				event.setPerson(owner);
				event.setPlaceName(placeName);
				event = eventDAO.save(event);
				
				this.pushNotificationService.send("Event: " + event.getTitle(), event.getTitle() + " modiified ", null, "/topics/"+event.getId(), "high");
			} else {
				throw new EventNotAllowedException();
			}
			
			
		} else {
			throw new EventNotFoundException();
		}
		return event;
	}

	@Override
	public Event findOneEntity(Integer id) {
		return eventDAO.findById(id).isPresent()?eventDAO.findById(id).get():null;
	}
	
	

}
