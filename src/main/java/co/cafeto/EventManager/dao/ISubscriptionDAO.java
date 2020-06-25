package co.cafeto.EventManager.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.cafeto.EventManager.model.Subscription;
import co.cafeto.EventManager.model.SubscriptionId;

public interface ISubscriptionDAO extends JpaRepository<Subscription, SubscriptionId> {

	@Query("select s.suscriptionId.event.id from Subscription s where s.suscriptionId.person.email = ?1")
    List<Object> findByPerson(String email);

}
