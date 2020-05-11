package com.cg.dd.app.service;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cg.dd.app.entities.User;
import com.cg.dd.app.exceptions.UserExistsException;
import com.cg.dd.app.exceptions.UserNotFoundException;
import com.cg.dd.app.payload.SignupRequest;
import com.cg.dd.app.repository.UserRepository;

/**
 *
 */
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository userRepository;

	public UserServiceImp() {
		super();
	}

	@Override
	public User forgotPassword(String email) throws UserNotFoundException {
		Optional<User> user = userRepository.findByEmail(email);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User Not Found");
		}
		return user.get();
	}

	@Override
	public Optional<User> findUserByResetToken(String resetToken) throws UserNotFoundException {
		Optional<User> user = userRepository.findByResetToken(resetToken);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User not found");
		}
		return user;
	}

	@Override
	public void saveUser(User user) {
		userRepository.save(user);
	}
}
