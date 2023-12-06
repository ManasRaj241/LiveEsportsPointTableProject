package com.pointTableProject.allapis.allrestapi.masterTables;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class MasterTableController {

	private final TeamsService teamsService;
	private final PlayersService playersService;
	private final TournamentService tournamentService;
	
	@Autowired
    public MasterTableController(TeamsService teamsService, PlayersService playersService, TournamentService tournamentService) {
        this.teamsService = teamsService;
        this.playersService = playersService;
        this.tournamentService = tournamentService;
    }

	@RequestMapping(value="/teams", method= RequestMethod.GET)
	public List<Teams> allTeams() {
		return teamsService.getAllTeam();
	}
	
	@RequestMapping(value="/teams", method= RequestMethod.POST)
	public ResponseEntity<Object> addNewTeam(@RequestBody Teams team) {
		int newTeamId = teamsService.addNewTeam(team);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{newTeamId}").buildAndExpand(newTeamId).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@RequestMapping(value="/teams/{teamId}", method= RequestMethod.GET)
	public Teams retrieveTeamById(@PathVariable int teamId) {
		Teams singleTeam = teamsService.retrieveTeamById(teamId);
		if(singleTeam == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return singleTeam;
	}
	
	@RequestMapping(value="/players", method= RequestMethod.POST)
	public ResponseEntity<Object> addNewPlayer(@RequestBody Players player) {
		int newPlayerId = playersService.addNewPlayer(player);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{newPlayerId}").buildAndExpand(newPlayerId).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@RequestMapping(value="/teams/{teamId}", method= RequestMethod.PUT)
	public ResponseEntity<Object> updateTeamById(@PathVariable int teamId, @RequestBody Teams team) {
		teamsService.updateTeamById(teamId,team);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/teams/{teamId}", method= RequestMethod.DELETE)
	public ResponseEntity<Object> deleteTeamById(@PathVariable int teamId) {
		teamsService.deleteTeamById(teamId);
		return ResponseEntity.noContent().build();
	}
	
	
	
	@RequestMapping(value="/players/{teamId}",method=RequestMethod.GET)
	public List<Players> getAllPlayers(@PathVariable int teamId){
		return playersService.getAllPlayersById(teamId);
	}
	
	@RequestMapping(value="/players/{playerId}",method=RequestMethod.PUT)
	public ResponseEntity<Object> updatePlayerById(@PathVariable int playerId, @RequestBody Players player){
		playersService.updatePlayerById(playerId, player);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/players/name/{playerName}",method=RequestMethod.GET)
	public List<Players> getPlayersByName(@PathVariable String playerName){
		return playersService.getPlayerByPlayerName(playerName);
	}
	
	@RequestMapping(value="/tournament",method=RequestMethod.POST)
	public ResponseEntity<Object> createNewTournament(@RequestBody Tournament tournament){
		int tournamentId = tournamentService.addNewTournament(tournament);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{newTeamId}").buildAndExpand(tournamentId).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@RequestMapping(value="/tournament",method=RequestMethod.GET)
    public ResponseEntity<List<Tournament>> getAllTournaments() {
        List<Tournament> tournaments = tournamentService.getAllTournaments();
        return new ResponseEntity<>(tournaments, HttpStatus.OK);
    }
	
	@RequestMapping(value="/tournament/{tournamentName}",method=RequestMethod.GET)
    public ResponseEntity<List<Tournament>> getTournamentById(@PathVariable String tournamentName) {
		List<Tournament> tournament = tournamentService.getTournamentByTournamentName(tournamentName);
        return new ResponseEntity<>(tournament, HttpStatus.OK);
    }

	@RequestMapping(value="/tournament/{tournamentName}",method=RequestMethod.PUT)
    public ResponseEntity<Tournament> updateTournamentByTournamentName(@PathVariable String tournamentName, @RequestBody Tournament updatedTournament) {
        Tournament updated = tournamentService.updateTournament(tournamentName, updatedTournament);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
	
	@RequestMapping(value="/tournament/{tournamentId}",method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteTournament(@PathVariable int tournamentId) {
        tournamentService.deleteTournamentById(tournamentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
