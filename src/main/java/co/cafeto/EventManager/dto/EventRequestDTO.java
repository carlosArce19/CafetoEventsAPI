package co.cafeto.EventManager.dto;

public class EventRequestDTO {
	
	private Integer id;

	public EventRequestDTO(Integer id) {
		super();
		this.id = id;
	}

	public EventRequestDTO() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
