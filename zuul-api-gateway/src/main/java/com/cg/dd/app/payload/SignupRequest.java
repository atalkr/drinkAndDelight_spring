package com.cg.dd.app.payload;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/**
 * 
 *
 */
public class SignupRequest {

	@NotEmpty(message = "Name cannot be blank")
	@Size(min = 4, max = 24, message = "Name length should be between 4 to 24")
	private String fullName;

	@NotEmpty(message = "User Name cannot be blank")
	@Size(min = 4, max = 24, message = "User Name length should be between 4 to 24")
	private String userName;

	@NotEmpty(message = "Password cannot be blank")
	@Size(min = 8, max = 24, message = "Password length should be between 8 to 24")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String userPassword;

	@NotEmpty(message = "Phone cannot be blank")
	@Pattern(regexp = "^[1-9][0-9]{9}$", message = "Invalid phone number should be 10digit and must not start with 0")
	private String userPhone;

	@NotEmpty(message = "Email cannot be blank")
	@Email(message = "Invalid email")
	private String email;

	private Set<String> role;

	public SignupRequest() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Set<String> getRole() {
		return role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}

}
