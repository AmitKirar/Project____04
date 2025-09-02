package in.co.rays.bean;

/**
 * CourseBean represents the details of a course such as name, duration, and
 * description. It extends BaseBean to inherit common properties like id.
 * 
 * @author Amit
 * @version 1.0
 */

public class CourseBean extends BaseBean {
	/** Name of the course */
	private String name;
	/** Duration of the course */
	private String duration;
	/** Description of the course */
	private String description;

	/**
	 * Gets the name of the course.
	 * 
	 * @return name of the course
	 */

	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the course.
	 * 
	 * @param name the course name to set
	 */

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the duration of the course.
	 * 
	 * @return duration of the course
	 */

	public String getDuration() {
		return duration;
	}

	/**
	 * Sets the duration of the course.
	 * 
	 * @param duration the duration to set
	 */

	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * Gets the description of the course.
	 * 
	 * @return description of the course
	 */

	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the course.
	 * 
	 * @param description the description to set
	 */

	public void setDescription(String description) {
		this.description = description;
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
	 * Returns the value to display (usually the name).
	 * 
	 * @return name of the course
	 */

	public String getValue() {

		return name;
	}

}
