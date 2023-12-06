package com.pointTableProject.allapis.allrestapi.mainTables;

import com.pointTableProject.allapis.allrestapi.masterTables.Teams;
import com.pointTableProject.allapis.allrestapi.masterTables.Tournament;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "group_table")
public class Group {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;
    
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Teams team;
    
    private String groupName;
    
    public Group() {
    }

    public Group(Tournament tournament, Teams team, String groupName) {
        this.tournament = tournament;
        this.team = team;
        this.groupName = groupName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Teams getTeam() {
        return team;
    }

    public void setTeam(Teams team) {
        this.team = team;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "Group [id=" + id + ", tournament=" + tournament + ", team=" + team + ", groupName=" + groupName + "]";
    }
}
