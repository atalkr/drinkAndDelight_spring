package com.cg.dd.app.service;

import java.math.BigInteger;
import java.util.Optional;

import com.cg.dd.app.entities.User;
import com.cg.dd.app.exceptions.UserExistsException;
import com.cg.dd.app.exceptions.UserNotFoundException;
import com.cg.dd.app.payload.SignupRequest;

/**
 *
 */
public interface UserService {

	/**
	 * Method check if the email is associated with any user or not.If email is not
	 * associated to any user it throws user not found exception
	 * 
	 * @param email
	 * @return
	 * @throws UserNotFoundException
	 */
	public abstract User forgotPassword(String email) throws UserNotFoundException;

	/**
	 * Method check if the token is associated with any user or not.If token is not
	 * associated to any user it throws user not found exception
	 * 
	 * @param resetToken
	 * @return
	 * @throws UserNotFoundException
	 */
	public abstract Optional<User> findUserByResetToken(String resetToken) throws UserNotFoundException;

	/**
	 * Method to persist user.
	 * 
	 * @param user
	 */
	public abstract void saveUser(User user);
}
