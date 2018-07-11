package io.entrance.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
public class News {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	public News() {
		
	}
	
	public News(String title,String date, String body) {
		this.title = title;
		this.date = date;
		this.body = body;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	@NotEmpty(message = "Entry cannot be left blank")
	private String title;
	
	@NotEmpty(message = "Entry cannot be left blank")
	private String date;
	
	@NotEmpty(message = "Entry cannot be left blank")
	@Column(columnDefinition="text")
	private String body;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
