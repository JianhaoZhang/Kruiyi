package io.entrance.web.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import org.hibernate.validator.constraints.NotEmpty;


@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	public void setId(long id) {
		this.id = id;
	}
	
	@NotEmpty(message = "Entry cannot be left blank")
	private String name;
	
	@NotEmpty(message = "Entry cannot be left blank")
	private String password;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}
	

}
