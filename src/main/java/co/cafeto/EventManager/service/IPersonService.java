package co.cafeto.EventManager.service;

import co.cafeto.EventManager.model.Person;

public interface IPersonService {

	Person findByToken(String jwtToken);
	Person findOne(String email);
	void save(Person person);
	void delete(String email);
}
