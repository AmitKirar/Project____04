package in.co.rays.bean;

/**
 * SubjectBean represents a subject entity including its name, related course ID
 * and name, and a description. It extends BaseBean to inherit common properties
 * like id.
 * 
 * @Author Amit
 * @version 1.0
 */

public class SubjectBean extends BaseBean {

	/** Name of the subject */
	private String name;

	/** ID of the course associated with the subject */
	private long courseId;

	/** Name of the course associated with the subject */
	private String courseName;

	/** Description of the subject */
	private String description;

	/**
	 * Gets the name of the subject.
	 * 
	 * @return subject name
	 */

	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the subject.
	 * 
	 * @param name the subject name to set
	 */

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the course ID associated with the subject.
	 * 
	 * @return course ID
	 */

	public long getCourseId() {
		return courseId;
	}

	/**
	 * Sets the course ID associated with the subject.
	 * 
	 * @param courseId the course ID to set
	 */

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	/**
	 * Gets the name of the course associated with the subject.
	 * 
	 * @return course name
	 */

	public String getCourseName() {
		return courseName;
	}

	/**
	 * Sets the name of the course associated with the subject.
	 * 
	 * @param courseName the course name to set
	 */

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * Gets the description of the subject.
	 * 
	 * @return subject description
	 */

	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the subject.
	 * 
	 * @param description the description to set
	 */

	public void setDescription(String description) {
		this.description = description;
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
	 * Returns the display value for this bean (usually the name).
	 * 
	 * @return subject name
	 */

	public String getValue() {

		return name;
	}

}
