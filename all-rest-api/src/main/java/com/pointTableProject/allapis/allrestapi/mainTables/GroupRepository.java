package com.pointTableProject.allapis.allrestapi.mainTables;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Integer> {

	List<Group> findAllByGroupName(String groupName);
}
