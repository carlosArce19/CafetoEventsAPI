package co.cafeto.EventManager.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class SubscriptionId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	private Person person;
	
	@ManyToOne
	private Event event;
	
	public SubscriptionId(Person person, Event event) {
		super();
		this.person = person;
		this.event = event;
	}
	
	public SubscriptionId() {
		super();
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}

}
