package co.cafeto.EventManager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Person {

	@Id
	@Column(name="EMAIL", length = 255)
	private String email;
	
	@Column(name="NICKNAME", length = 255)
	private String nickname;
	
	@Column(name="NAME", length = 255)
	private String name;
	
	@Column(name="SUB", length = 255)
	private String sub;
	
	@Column(name="EMAIL_VERIFIED")
	private boolean emailVerified;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public boolean isEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

}
