package in.co.rays.bean;

import java.util.Date;

/**
 * FacultyBean represents a faculty entity containing personal, academic, and
 * college-related details. It extends BaseBean to include common fields.
 * 
 * @Author Amit
 * @version 1.0
 */

public class FacultyBean extends BaseBean {

	/** First name of the faculty */
	private String firstName;

	/** Last name of the faculty */
	private String lastName;

	/** Date of birth of the faculty */
	private Date dob;

	/** Gender of the faculty */
	private String gender;

	/** Mobile number of the faculty */
	private String moblileNo;

	/** Email address of the faculty */
	private String email;

	/** College ID associated with the faculty */
	private long collegeId;

	/** College name associated with the faculty */
	private String collegeName;

	/** Course ID associated with the faculty */
	private long cousreId;

	/** Course name associated with the faculty */
	private String courseName;

	/** Subject ID associated with the faculty */
	private long subjectid;

	/** Subject name associated with the faculty */
	private String subjectName;

	/**
	 * Gets the first name of the faculty.
	 * 
	 * @return first name
	 */

	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name of the faculty.
	 * 
	 * @param firstName the first name to set
	 */

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name of the faculty.
	 * 
	 * @return last name
	 */

	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name of the faculty.
	 * 
	 * @param lastName the last name to set
	 */

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the date of birth of the faculty.
	 * 
	 * @return date of birth
	 */

	public Date getDob() {
		return dob;
	}

	/**
	 * Sets the date of birth of the faculty.
	 * 
	 * @param dob the date of birth to set
	 */

	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * Gets the gender of the faculty.
	 * 
	 * @return gender
	 */

	public String getGender() {
		return gender;
	}

	/**
	 * Sets the gender of the faculty.
	 * 
	 * @param gender the gender to set
	 */

	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Gets the mobile number of the faculty.
	 * 
	 * @return mobile number
	 */

	public String getMoblileNo() {
		return moblileNo;
	}

	/**
	 * Sets the mobile number of the faculty.
	 * 
	 * @param moblileNo the mobile number to set
	 */

	public void setMoblileNo(String moblileNo) {
		this.moblileNo = moblileNo;
	}

	/**
	 * Gets the email of the faculty.
	 * 
	 * @return email
	 */

	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email of the faculty.
	 * 
	 * @param email the email to set
	 */

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the college ID associated with the faculty.
	 * 
	 * @return college ID
	 */

	public long getCollegeId() {
		return collegeId;
	}

	/**
	 * Sets the college ID associated with the faculty.
	 * 
	 * @param collegeId the college ID to set
	 */

	public void setCollegeId(long collegeId) {
		this.collegeId = collegeId;
	}

	/**
	 * Gets the college name associated with the faculty.
	 * 
	 * @return college name
	 */

	public String getCollegeName() {
		return collegeName;
	}

	/**
	 * Sets the college name associated with the faculty.
	 * 
	 * @param collegeName the college name to set
	 */

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	/**
	 * Gets the course ID associated with the faculty.
	 * 
	 * @return course ID
	 */

	public long getCousreId() {
		return cousreId;
	}

	/**
	 * Sets the course ID associated with the faculty.
	 * 
	 * @param cousreId the course ID to set
	 */

	public void setCousreId(long cousreId) {
		this.cousreId = cousreId;
	}

	/**
	 * Gets the course name associated with the faculty.
	 * 
	 * @return course name
	 */

	public String getCourseName() {
		return courseName;
	}

	/**
	 * Sets the course name associated with the faculty.
	 * 
	 * @param courseName the course name to set
	 */

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * Gets the subject ID associated with the faculty.
	 * 
	 * @return subject ID
	 */

	public long getSubjectid() {
		return subjectid;
	}

	/**
	 * Sets the subject ID associated with the faculty.
	 * 
	 * @param subjectid the subject ID to set
	 */

	public void setSubjectid(long subjectid) {
		this.subjectid = subjectid;
	}

	/**
	 * Gets the subject name associated with the faculty.
	 * 
	 * @return subject name
	 */

	public String getSubjectName() {
		return subjectName;
	}

	/**
	 * Sets the subject name associated with the faculty.
	 * 
	 * @param subjectName the subject name to set
	 */

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
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
