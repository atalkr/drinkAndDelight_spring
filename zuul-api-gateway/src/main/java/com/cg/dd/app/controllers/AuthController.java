package com.cg.dd.app.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dd.app.entities.ERole;
import com.cg.dd.app.entities.Role;
import com.cg.dd.app.entities.User;
import com.cg.dd.app.exceptions.UserNotFoundException;
import com.cg.dd.app.jwt.JwtUtils;
import com.cg.dd.app.payload.LoginRequest;
import com.cg.dd.app.payload.RestResponse;
import com.cg.dd.app.payload.SignupRequest;
import com.cg.dd.app.payload.UserResponse;
import com.cg.dd.app.repository.RoleRepository;
import com.cg.dd.app.repository.UserRepository;
import com.cg.dd.app.service.EmailService;
import com.cg.dd.app.service.UserDetailsImpl;
import com.cg.dd.app.service.UserService;

/**
 * 
 * Rest controller to handle all authentication related endpoints.
 * 
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private JwtUtils jwtUtils;

	/**
	 * 
	 * Rest controller that take username and password as input generates a jwt
	 * token and sends user details along with it.
	 * 
	 * @param loginRequest
	 * @return
	 */
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		// verify username and password
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		// generates jwt token
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		// check for different roles user has and add it to roles list
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		UserResponse userResponse = new UserResponse(userDetails.getFullName(), userDetails.getUsername(),
				userDetails.getEmail(), userDetails.getPhoneNumber(), jwt, roles);
		// response
		return ResponseEntity.ok(new RestResponse<UserResponse>(true,"Successfully authenticated",userResponse));
	}

	/**
	 * Rest controller to register user
	 * 
	 * @param signUpRequest
	 * @return
	 * 
	 */
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

		// checks if user exits by username provided in signupRequest
		if (userRepository.existsByUserName(signUpRequest.getUserName())) {
			return ResponseEntity.ok(new RestResponse<>(false,"Error: Username is already taken!"));
		}

		// checks if user exits by email provided in signupRequest
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.ok(new RestResponse<>(false,"Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getFullName(), signUpRequest.getUserName(),
				encoder.encode(signUpRequest.getUserPassword()), signUpRequest.getEmail(),
				signUpRequest.getUserPhone());

		// Fetches roles provided in the request
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		// If no role is provided in request then by default user role is provided
		if (strRoles == null) {
			// It finds if the roles provided exits in role table or not
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		// user is saved
		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new RestResponse<>(true, "User registered successfully!"));
	}

	/**
	 * 
	 * Rest controller endpoint to handle forgot request. It takes email as request
	 * parameter and sends token to email provided in order to initiate password
	 * change request
	 * 
	 * @param email
	 * @param request
	 * @return
	 * 
	 */
	@PostMapping("/forgotPassword")
	public ResponseEntity<?> forgotPassword(@RequestParam(name = "email") Optional<String> email,
			HttpServletRequest request) {

		// validation to check is email is not empty
		if (!email.isPresent() || email.get().isEmpty()) {
			return ResponseEntity.ok(new RestResponse<>(false, "Email missing"));
		}

		// try catch block to handle email present or not in db
		try {

			User user = userService.forgotPassword(email.get());

			// generating random uuid
			user.setResetToken(UUID.randomUUID().toString());

			// save token to database
			userService.saveUser(user);

			String appUrl = request.getScheme() + "://" + request.getServerName();

			// creating email message to be sent
			SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
			passwordResetEmail.setFrom("support@demo.com");

			// user email to which email is to be sent
			passwordResetEmail.setTo(user.getEmail());
			passwordResetEmail.setSubject("Password Reset Request");

			// Url sent in mail which contains token generated
			passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
					+ ":4200/update-password?token=" + user.getResetToken());

			// sending email
			emailService.sendEmail(passwordResetEmail);

			return ResponseEntity.ok(new RestResponse<>(true, "Successfully sent mail"));

		} catch (UserNotFoundException e) {
			return ResponseEntity.ok(new RestResponse<>(false, e.getMessage()));
		}catch(MailException e) {
			return ResponseEntity.ok(new RestResponse<>(false, "Failed to send email"));

		}
	}

	/**
	 * Rest controller to handle reset request. It takes token and updated password
	 * as request param and checks if user had initiated forget password request by
	 * checking for token in user reset token enitity if present updates the
	 * password
	 * 
	 * @param token
	 * @param password
	 * @return
	 * 
	 */
	@PostMapping("/reset")
	public ResponseEntity<?> setNewPassword(@RequestParam(name = "token") Optional<String> token,
			@RequestParam(name = "password") Optional<String> password) {

		// validation to check token and email is present or not
		if (!token.isPresent() || !password.isPresent() || token.get().isEmpty() || password.get().isEmpty()) {
			return ResponseEntity.ok(new RestResponse<>(false, "Failed to generate new password"));
		} else {

			try {
				// find user by using token throws user not found exception if token is not
				// present
				Optional<User> user = userService.findUserByResetToken(token.get());
				if (user.isPresent()) {
					user.get().setUserPassword(encoder.encode(password.get()));
					user.get().setResetToken(null);
					userService.saveUser(user.get());
					return ResponseEntity.ok(new RestResponse<>(true, "Successfully generate new passowrd"));
				}

			} catch (UserNotFoundException e) {
				return ResponseEntity.ok(new RestResponse<>(false, e.getMessage()));
			}

			return ResponseEntity.ok(new RestResponse<>(false, "Failed to generate new password"));

		}
	}
}
