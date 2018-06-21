package io.entrance.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	public void setId(long id) {
		this.id = id;
	}
	@NotEmpty(message = "Entry cannot be left blank")
	private String name;
	
	@NotEmpty(message = "Entry cannot be left blank")
	@Column(columnDefinition="varchar(8192)")
	private String body;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}
	

}
