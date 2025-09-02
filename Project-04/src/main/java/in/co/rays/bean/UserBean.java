package in.co.rays.bean;

import java.util.Date;

/**
 * UserBean represents a system user with personal and login-related details. It
 * extends BaseBean to inherit common fields like id, createdBy, etc.
 * 
 * @Author Amit
 * @version 1.0
 */

public class UserBean extends BaseBean {

	/** First name of the user */
	private String firstname;

	/** Last name of the user */
	private String lastname;

	/** Login ID (usually email) */
	private String login;

	/** User's password */
	private String password;

	/** Confirmation of the user's password (used during registration/change) */
	private String confirmPassword;

	/** Date of birth of the user */
	private Date dob;

	/** Mobile number of the user */
	private String mobileNo;

	/** Role ID (refers to the user's role in the system) */
	private long roleId;

	/** Gender of the user */
	private String gender;

	/**
	 * Gets the first name of the user.
	 * 
	 * @return first name
	 */

	public String getFirstname() {
		return firstname;
	}

	/**
	 * Sets the first name of the user.
	 * 
	 * @param firstname the first name to set
	 */

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * Gets the last name of the user.
	 * 
	 * @return last name
	 */

	public String getLastname() {
		return lastname;
	}

	/**
	 * Sets the last name of the user.
	 * 
	 * @param lastname the last name to set
	 */

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * Gets the login ID (email) of the user.
	 * 
	 * @return login ID
	 */

	public String getLogin() {
		return login;
	}

	/**
	 * Sets the login ID (email) of the user.
	 * 
	 * @param login the login ID to set
	 */

	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Gets the user's password.
	 * 
	 * @return password
	 */

	public String getPassword() {
		return password;
	}

	/**
	 * Sets the user's password.
	 * 
	 * @param password the password to set
	 */

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the confirmation password.
	 * 
	 * @return confirmation password
	 */

	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * Sets the confirmation password.
	 * 
	 * @param confirmPassword the confirmation password to set
	 */

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * Gets the date of birth of the user.
	 * 
	 * @return date of birth
	 */

	public Date getDob() {
		return dob;
	}

	/**
	 * Sets the date of birth of the user.
	 * 
	 * @param dob the date of birth to set
	 */

	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * Gets the mobile number of the user.
	 * 
	 * @return mobile number
	 */

	public String getMobileNo() {
		return mobileNo;
	}

	/**
	 * Sets the mobile number of the user.
	 * 
	 * @param mobileNo the mobile number to set
	 */

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * Gets the role ID of the user.
	 * 
	 * @return role ID
	 */

	public long getRoleId() {
		return roleId;
	}

	/**
	 * Sets the role ID of the user.
	 * 
	 * @param roleId the role ID to set
	 */

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	/**
	 * Gets the gender of the user.
	 * 
	 * @return gender
	 */

	public String getGender() {
		return gender;
	}

	/**
	 * Sets the gender of the user.
	 * 
	 * @param gender the gender to set
	 */

	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Returns the key for this bean (usually the ID).
	 * 
	 * @return key as a string
	 */

	public String getkey() {

		return id + "";
	}

	/**
	 * Returns the display value for this bean (first name + last name).
	 * 
	 * @return full name of the user
	 */

	public String getValue() {

		return firstname + " " + lastname;
	}

}
