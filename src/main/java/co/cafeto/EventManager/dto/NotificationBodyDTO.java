package co.cafeto.EventManager.dto;

public class NotificationBodyDTO {

	private String title;
	private String body;
	public NotificationBodyDTO() {
		super();
	}

	public NotificationBodyDTO(String title, String body) {
		super();
		this.title = title;
		this.body = body;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}	
}
