package in.co.rays.bean;

/**
 * StudentBean represents a student entity including personal and college-related details.
 * It extends BaseBean to inherit common fields like id, createdBy, etc.
 * @Author Amit
 * @version 1.0
 */
import java.util.Date;

public class StudentBean extends BaseBean {

	/** First name of the student */
	private String firstName;

	/** Last name of the student */
	private String lastName;

	/** Date of birth of the student */
	private Date dob;

	/** Gender of the student */
	private String gender;

	/** Mobile number of the student */
	private String mobileNo;

	/** Email address of the student */
	private String email;

	/** ID of the college the student belongs to */
	private long collegeId;

	/** Name of the college the student belongs to */
	private String collegeName;

	/**
	 * Gets the first name of the student.
	 * 
	 * @return first name
	 */

	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name of the student.
	 * 
	 * @param firstName the first name to set
	 */

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name of the student.
	 * 
	 * @return last name
	 */

	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name of the student.
	 * 
	 * @param lastName the last name to set
	 */

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the date of birth of the student.
	 * 
	 * @return date of birth
	 */

	public Date getDob() {
		return dob;
	}

	/**
	 * Sets the date of birth of the student.
	 * 
	 * @param dob the date of birth to set
	 */

	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * Gets the gender of the student.
	 * 
	 * @return gender
	 */

	public String getGender() {
		return gender;
	}

	/**
	 * Sets the gender of the student.
	 * 
	 * @param gender the gender to set
	 */

	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Gets the mobile number of the student.
	 * 
	 * @return mobile number
	 */

	public String getMobileNo() {
		return mobileNo;
	}

	/**
	 * Sets the mobile number of the student.
	 * 
	 * @param mobileNo the mobile number to set
	 */

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * Gets the email of the student.
	 * 
	 * @return email
	 */

	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email of the student.
	 * 
	 * @param email the email to set
	 */

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the college ID of the student.
	 * 
	 * @return college ID
	 */

	public long getCollegeId() {
		return collegeId;
	}

	/**
	 * Sets the college ID of the student.
	 * 
	 * @param collegeId the college ID to set
	 */

	public void setCollegeId(long collegeId) {
		this.collegeId = collegeId;
	}

	/**
	 * Gets the name of the college the student belongs to.
	 * 
	 * @return college name
	 */

	public String getCollegeName() {
		return collegeName;
	}

	/**
	 * Sets the name of the college the student belongs to.
	 * 
	 * @param collegeName the college name to set
	 */

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	/**
	 * Returns the key for this bean (usually the ID).
	 * 
	 * @return key as string
	 */

	public String getkey() {

		return id + "";
	}

	/**
	 * Returns the display value for this bean (first name + last name).
	 * 
	 * @return full name
	 */

	public String getValue() {

		return firstName + " " + lastName;
	}

}
