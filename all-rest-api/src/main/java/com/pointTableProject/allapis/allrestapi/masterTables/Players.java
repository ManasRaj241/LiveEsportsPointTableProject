package com.pointTableProject.allapis.allrestapi.masterTables;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Players {
	
	public Players() {
		
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String pic;

    @ManyToOne
    @JoinColumn(name = "teamid")
    private Teams team;

	public Players(String name, String pic, Teams team) {
		super();
		this.name = name;
		this.pic = pic;
		this.team = team;
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

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Teams getTeam() {
		return team;
	}

	public void setTeam(Teams team) {
		this.team = team;
	}

	@Override
	public String toString() {
		return "Players [id=" + id + ", name=" + name + ", pic=" + pic + ", team=" + team + "]";
	}
	
	
    
}
