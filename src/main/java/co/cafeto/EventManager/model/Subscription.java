package co.cafeto.EventManager.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Subscription {

	@EmbeddedId
	private SubscriptionId suscriptionId;

	public SubscriptionId getSuscriptionId() {
		return suscriptionId;
	}

	public void setSuscriptionId(SubscriptionId suscriptionId) {
		this.suscriptionId = suscriptionId;
	}
	
}
