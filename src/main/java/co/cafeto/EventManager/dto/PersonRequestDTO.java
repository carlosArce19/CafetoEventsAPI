package co.cafeto.EventManager.dto;

public class PersonRequestDTO {

	private String email;

	public PersonRequestDTO() {
		super();
	}

	public PersonRequestDTO(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
