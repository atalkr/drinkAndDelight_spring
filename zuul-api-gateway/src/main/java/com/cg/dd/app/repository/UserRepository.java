package com.cg.dd.app.repository;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.cg.dd.app.entities.User;
import com.cg.dd.app.payload.SignupRequest;

public interface UserRepository extends CrudRepository<User, BigInteger> {

	/**
	 * Method to find user by using email
	 * 
	 * @param userEmail
	 * @return
	 * 
	 */
	Optional<User> findByEmail(String userEmail);

	/**
	 * Method to finds user using token
	 * 
	 * @param resetToken
	 * @return
	 *
	 */
	Optional<User> findByResetToken(String resetToken);

	Optional<User> findByUserName(String userName);

	Boolean existsByUserName(String username);

	Boolean existsByEmail(String email);
}
