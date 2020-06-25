package co.cafeto.EventManager.dto;

public class PushNotificationDTO {

	private NotificationBodyDTO notification;
	private String to;
	private String priority;

	public PushNotificationDTO() {
		super();
	}

	public PushNotificationDTO(NotificationBodyDTO notification,String to, String priority) {
		super();
		this.notification = notification;
		this.to = to;
		this.priority = priority;
	}

	public NotificationBodyDTO getNotification() {
		return notification;
	}
	public void setNotification(NotificationBodyDTO notification) {
		this.notification = notification;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	
	
}
