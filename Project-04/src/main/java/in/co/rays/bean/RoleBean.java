package in.co.rays.bean;

/**
 * RoleBean represents a user role in the system such as ADMIN, STUDENT, etc. It
 * includes role-specific details like name and description. It extends BaseBean
 * to include common fields like id. Author Amit
 * @Author Amit
 * @version 1.0
 */
public class RoleBean extends BaseBean {
	/** Constant for Admin role */
	public static final int ADMIN = 1;

	/** Constant for Student role */
	public static final int STUDENT = 2;

	/** Constant for College role */
	public static final int COLLEGE = 3;

	/** Constant for Kiosk role */
	public static final int KIOSK = 4;

	/** Constant for Faculty role */
	public static final int FACULTY = 5;

	/** Name of the role */
	private String name;

	/** Description of the role */
	private String description;

	/**
	 * Gets the name of the role.
	 * 
	 * @return name of the role
	 */

	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the role.
	 * 
	 * @param name the role name to set
	 */

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the description of the role.
	 * 
	 * @return role description
	 */

	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the role.
	 * 
	 * @param description the role description to set
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
	 * @return role name
	 */

	public String getValue() {

		return name;
	}

}
