package com.cg.dd.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.dd.app.entities.ERole;
import com.cg.dd.app.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	/**
	 * Method to find roles name from role table
	 * 
	 * @param name
	 * @return
	 */
	Optional<Role> findByName(ERole name);
}
