package com.pointTableProject.allapis.allrestapi.mainTables;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

	private final GroupRepository groupRepository;

	@Autowired
	public GroupService(GroupRepository groupRepository) {
		this.groupRepository = groupRepository;
	}

	public List<Group> allGroupsData = new ArrayList<Group>();

	public List<Group> getAllGroups() {
		allGroupsData = groupRepository.findAll();
		return allGroupsData;
	}
	
	public List<Group> getAllTeamsByGroupName(String groupName){
		List<Group> allTeamsByGroupName = groupRepository.findAllByGroupName(groupName); 
		if(allTeamsByGroupName.isEmpty()) return null;
		return allTeamsByGroupName;
	}

	public List<Group> getGroupById(int id) {
		allGroupsData = getAllGroups();
		List<Group> allGroupsOfTournament = groupRepository.findAllByTournamentId(id);
		if (allGroupsOfTournament.isEmpty())
			return null;
		return allGroupsOfTournament;
	}

	public Group createGroup(Group group) {
		allGroupsData = getAllGroups();
		allGroupsData.add(group);
		groupRepository.save(group);
		return group;
	}

	public Group updateGroup(int id, Group group) {
		Optional<Group> existingGroup = groupRepository.findById(id);
		if (existingGroup.isPresent()) {
			group.setId(id);
			groupRepository.save(group);
			return group;
		}
		return null;
	}

	public boolean deleteGroup(int id) {
		Optional<Group> existingGroup = groupRepository.findById(id);
		if (existingGroup.isPresent()) {
			groupRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
