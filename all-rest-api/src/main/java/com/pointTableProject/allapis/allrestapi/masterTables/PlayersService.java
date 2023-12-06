package com.pointTableProject.allapis.allrestapi.masterTables;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayersService {
	
	private final PlayersRepository playersRepository;

    @Autowired
    public PlayersService(PlayersRepository playersRepository) {
        this.playersRepository = playersRepository;
    }

	List<Players> allPlayers = new ArrayList<Players>();
	
	public List<Players> getAllPlayers() {
    	allPlayers = playersRepository.findAll();
        return allPlayers;
    }
	
	public List<Players> getAllPlayersById(int id){
		List<Players> sameTeamPlayers = playersRepository.findAllByTeamId(id);
		if(sameTeamPlayers.isEmpty()) return null;
		return sameTeamPlayers;
	}

	public List<Players> getPlayerByPlayerName(String playerName) {
		List<Players> Players = playersRepository.findAllByName(playerName);
		if(Players.isEmpty()) return null;
		return Players;
	}
	
	public void updatePlayerById(int playerId, Players updatedPlayer) {
        Players existingPlayer = playersRepository.findById(playerId);
        if (existingPlayer != null) {
        	updatedPlayer.setId(playerId);
            playersRepository.save(updatedPlayer); 
        }
    }

	public int addNewPlayer(Players player) {
		allPlayers = getAllPlayers();
		allPlayers.add(player);
		playersRepository.save(player);
    	return player.getId();
	}
}
