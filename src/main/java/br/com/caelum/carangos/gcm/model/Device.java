package br.com.caelum.carangos.gcm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Device {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private Sender sender;
	
	private String registrationId;
	
	@Deprecated
	Device() {}
	
	public Device(Sender sender, String registrationId) {
		this.sender = sender;
		this.registrationId = registrationId;
	}

	public String getRegistrationId() {
		return this.registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}
}
