package com.pointTableProject.allapis.allrestapi.masterTables;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamsService {

	private final TeamsRepository teamsRepository;

    @Autowired
    public TeamsService(TeamsRepository teamsRepository) {
        this.teamsRepository = teamsRepository;
    }
    
    public List<Teams> allTeams = new ArrayList<Teams>();

    public List<Teams> getAllTeam() {
    	allTeams = teamsRepository.findAll();
        return allTeams;
    }
    
    public int addNewTeam(Teams team) {
    	allTeams = getAllTeam();
    	allTeams.add(team);
    	teamsRepository.save(team);
    	return team.getId();
    }
    
    public Teams retrieveTeamById(int teamId) {
    	allTeams = getAllTeam();
    	Predicate<? super Teams> predicate = team -> team.getId() == teamId;
    	Optional<Teams> optionalTeam = allTeams.stream().filter(predicate).findFirst();
    	if(optionalTeam.isEmpty()) return null;
    	return optionalTeam.get(); 
    }
    
    public void updateTeamById(int teamId, Teams updatedTeam) {
        Optional<Teams> existingTeam = teamsRepository.findById(teamId);
        if (existingTeam.isPresent()) {
            updatedTeam.setId(teamId);
            teamsRepository.save(updatedTeam); 
        }
    }

    public int deleteTeamById(int teamId) {
        Optional<Teams> existingTeam = teamsRepository.findById(teamId);
        if (existingTeam.isPresent()) {
            teamsRepository.deleteById(teamId);
            return teamId;
        }
        return -1;
    }
}
