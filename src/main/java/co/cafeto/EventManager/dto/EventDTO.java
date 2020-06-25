package co.cafeto.EventManager.dto;

public class EventDTO {

	private int id;
	private String title;
	private String placeName;
	private String description;
	private String image;
	private float latitude;
	private float longitude;
	private String owner;
	
	public EventDTO() {
		super();
	}
	public EventDTO(int id, String title,String placeName, String description, String image, float latitude, float longitude,
			String owner) {
		super();
		this.id = id;
		this.title = title;
		this.placeName = placeName;
		this.description = description;
		this.image = image;
		this.latitude = latitude;
		this.longitude = longitude;
		this.owner = owner;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
}
