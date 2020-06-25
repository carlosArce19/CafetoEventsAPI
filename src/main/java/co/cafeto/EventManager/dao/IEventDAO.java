package co.cafeto.EventManager.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.cafeto.EventManager.model.Event;

public interface IEventDAO extends JpaRepository<Event, Integer> {

	@Query(value="select * from Event e ORDER BY e.creation_date ASC", nativeQuery = true)
    List<Event> findAllOrderByDate();
	
}
