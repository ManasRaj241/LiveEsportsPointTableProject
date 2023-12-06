package com.pointTableProject.allapis.allrestapi.masterTables;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayersRepository extends JpaRepository<Players, Integer>{
	
	Players findById(int Id);

	List<Players> findAllByTeamId(int id);

	List<Players> findAllByName(String playerName);

}
