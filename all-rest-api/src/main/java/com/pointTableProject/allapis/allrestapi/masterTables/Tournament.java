package com.pointTableProject.allapis.allrestapi.masterTables;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tournament {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private int maxTeams;
	private int maxGroups;
	
	public Tournament() {
		
	}
	
	public Tournament(String name, int maxTeams, int maxGroups) {
		super();
		this.name = name;
		this.maxTeams = maxTeams;
		this.maxGroups = maxGroups;
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

	public int getMaxTeams() {
		return maxTeams;
	}

	public void setMaxTeams(int maxTeams) {
		this.maxTeams = maxTeams;
	}

	public int getMaxGroups() {
		return maxGroups;
	}

	public void setMaxGroups(int maxGroups) {
		this.maxGroups = maxGroups;
	}

	@Override
	public String toString() {
		return "Tournament [id=" + id + ", name=" + name + ", maxTeams=" + maxTeams + ", maxGroups=" + maxGroups + "]";
	}
	
	
	
	
}
