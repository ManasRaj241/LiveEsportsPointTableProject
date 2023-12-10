package com.pointTableProject.allapis.allrestapi.masterTables;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament, Integer> {

	List<Tournament> findAllByName(String tournamentName);

	Tournament findByName(String tournamentName);
	
	Tournament findById(int id);

	void deleteByName(String tournamentName);

	List<Tournament> findAllByStatus(String status);

}