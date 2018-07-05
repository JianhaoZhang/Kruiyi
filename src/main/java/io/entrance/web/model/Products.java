package io.entrance.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
public class Products {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long pid;
	
	public Products() {
		
	}
	
	public Products(String name, String brand, String type, String description, String path) {
		this.name = name;
		this.brand = brand;
		this.type = type;
		this.description = description;
		this.path = path;
	}
	
	@NotEmpty(message = "Entry cannot be left blank")
	private String name;
	
	@NotEmpty(message = "Entry cannot be left blank")
	private String brand;
	
	@NotEmpty(message = "Entry cannot be left blank")
	private String type;
	

	@NotEmpty(message = "Entry cannot be left blank")
	@Column(columnDefinition="varchar(65536)")
	private String description;
	
	@NotEmpty(message = "Entry cannot be left blank")
	private String path;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}
	
	
	

}
