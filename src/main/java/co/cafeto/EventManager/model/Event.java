package co.cafeto.EventManager.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Event {
	
	@Id
	@SequenceGenerator(name = "SEQ_EVENT_ID", sequenceName = "SEQ_EVENT_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EVENT_ID")
	@Column(name="ID")
	private int id;
	
	@Column(name="TITLE", length = 255)
	private String title;
	
	@Column(name="DESCRIPTION", length = 500)
	private String description;
	
	@Column(name="IMAGE", length = 4000)
	private String image;
	
	@Column(name="PLACE_NAME", length = 500)
	private String placeName;
	
	@Column(name="LATITUDE")
	private float latitude;
	
	@Column(name="LONGITUDE")
	private float longitude;
	
	@Column(name="CREATION_DATE")
	private Date creationDate;
	
	@ManyToOne()
	@JoinColumn(name="ID_PERSON", referencedColumnName = "EMAIL", insertable = true, updatable = true)
	@JsonIgnore
	private Person person;

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

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
}
