package co.cafeto.EventManager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.cafeto.EventManager.dao.IPersonDAO;
import co.cafeto.EventManager.dto.PersonTokenDTO;
import co.cafeto.EventManager.model.Person;
import co.cafeto.EventManager.util.IJWTUtil;

@Service
public class PersonService implements IPersonService {

	@Autowired
	private IPersonDAO personDAO;
	
	@Autowired
	private IJWTUtil jwtUtil;
	
	@Override
	public Person findOne(String email) {
		Person  person = personDAO.findById(email).isPresent()?personDAO.findById(email).get():null;
		return person;
	}

	@Override
	public void save(Person person) {
		personDAO.save(person);
	}

	@Override
	public void delete(String email) {
		personDAO.deleteById(email);
	}

	@Override
	public Person findByToken(String jwtToken) {
		
		PersonTokenDTO personTokenDTO = this.jwtUtil.getPersonFronJWT(jwtToken);

		Person person = this.findOne(personTokenDTO.getEmail());
		if(person == null) {
			person = new Person();
			person.setEmail(personTokenDTO.getEmail());
			person.setName(personTokenDTO.getName());
			person.setNickname(personTokenDTO.getNickname());
			person.setSub(personTokenDTO.getSub());
			person.setEmailVerified(personTokenDTO.isEmail_verified());
			this.save(person);
		}
		return person;
	}

}
