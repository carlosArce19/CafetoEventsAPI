package co.cafeto.EventManager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.cafeto.EventManager.model.Person;

public interface IPersonDAO extends JpaRepository<Person, String> {

}
