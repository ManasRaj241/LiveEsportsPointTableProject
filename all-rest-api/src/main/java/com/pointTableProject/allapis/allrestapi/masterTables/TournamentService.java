package com.pointTableProject.allapis.allrestapi.masterTables;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TournamentService {

	private final TournamentRepository tournamentRepository;
	
	@Autowired
    public TournamentService(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }
	
	List<Tournament> AllTournamentList = new ArrayList<Tournament>();

	public List<Tournament> getAllTournaments() {
    	List<Tournament> allTournaments = tournamentRepository.findAll();
        return allTournaments;
    }
	
	public int addNewTournament(Tournament tournament) {
		tournamentRepository.save(tournament);
    	return tournament.getId();
	}

	public List<Tournament> getTournamentByTournamentName(String tournamentName) {
		List<Tournament> tournaments = tournamentRepository.findAllByName(tournamentName);
		if(tournaments.isEmpty()) return null;
		return tournaments;
	}

	public Tournament updateTournament(String tournamentName, Tournament updatedTournament) {
		Tournament existingTournament = tournamentRepository.findByName(tournamentName);
		if (existingTournament != null) {
			updatedTournament.setId(existingTournament.getId());
			tournamentRepository.saveAndFlush(updatedTournament);
        }
		return existingTournament;
	}

	public void deleteTournament(String tournamentName) {
		Tournament existingTournament = tournamentRepository.findByName(tournamentName);
		if (existingTournament != null) {
			tournamentRepository.deleteById(existingTournament.getId());
        }	
	}
	
	public void deleteTournamentById(int id) {
		Tournament existingTournament = tournamentRepository.findById(id);
		if (existingTournament != null) {
			tournamentRepository.deleteById(id);
        }	
	}

	public Tournament getTournamentByTournamentId(int tournamentId) {
		Tournament tournament = tournamentRepository.findById(tournamentId);
		if(tournament == null) return null;
		return tournament;
	}

	
}
