package com.pointTableProject.allapis.allrestapi.masterTables;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Teams {
	
	public Teams() {
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int id;
	public String name;
	public String image;
	
	public Teams(String name, String image) {
		super();
		this.name = name;
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Teams [id=" + id + ", name=" + name + ", image=" + image + "]";
	}
	
	
	
}
