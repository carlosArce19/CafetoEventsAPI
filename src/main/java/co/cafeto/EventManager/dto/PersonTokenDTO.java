package co.cafeto.EventManager.dto;

import java.util.Date;

public class PersonTokenDTO {
	
	private String nickname;
	private String name;
	private String picture;
	private Date updated_at;
	private String email;
	private boolean email_verified;
	private String iss;
	private String sub;
	private String aud;
	private String iat;
	private String exp;

	public PersonTokenDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PersonTokenDTO(String nickname, String name, String picture, Date updated_at, String email,
			boolean email_verified, String iss, String sub, String aud, String iat, String exp) {
		super();
		this.nickname = nickname;
		this.name = name;
		this.picture = picture;
		this.updated_at = updated_at;
		this.email = email;
		this.email_verified = email_verified;
		this.iss = iss;
		this.sub = sub;
		this.aud = aud;
		this.iat = iat;
		this.exp = exp;
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
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isEmail_verified() {
		return email_verified;
	}
	public void setEmail_verified(boolean email_verified) {
		this.email_verified = email_verified;
	}
	public String getIss() {
		return iss;
	}
	public void setIss(String iss) {
		this.iss = iss;
	}
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public String getAud() {
		return aud;
	}
	public void setAud(String aud) {
		this.aud = aud;
	}
	public String getIat() {
		return iat;
	}
	public void setIat(String iat) {
		this.iat = iat;
	}
	public String getExp() {
		return exp;
	}
	public void setExp(String exp) {
		this.exp = exp;
	}
	
	
	

}
