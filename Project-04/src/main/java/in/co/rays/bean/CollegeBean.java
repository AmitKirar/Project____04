package in.co.rays.bean;

/**
 * CollegeBean contains the details of a college like name, address, state,
 * city, and phone number. <br>
 * It extends BaseBean and is used to transfer college data between different
 * layers of the application.
 * 
 * @author Amit
 * @version 1.0
 */

public class CollegeBean extends BaseBean {

	/** Name of the college */
	private String name;
	/** Address of the college */
	private String address;
	/** State where the college is located */
	private String state;
	/** City where the college is located */
	private String city;
	/** Contact phone number of the college */
	private String phoneNo;

	/**
	 * Gets the name of the college.
	 *
	 * @return the name
	 */

	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the college.
	 *
	 * @param name the name to set
	 */

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the address of the college.
	 *
	 * @return the address
	 */

	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address of the college.
	 *
	 * @param address the address to set
	 */

	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the state where the college is located.
	 *
	 * @return the state
	 */

	public String getState() {
		return state;
	}

	/**
	 * Sets the state where the college is located.
	 *
	 * @param state the state to set
	 */

	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Gets the city where the college is located.
	 *
	 * @return the city
	 */

	public String getCity() {
		return city;
	}

	/**
	 * Sets the city where the college is located.
	 *
	 * @param city the city to set
	 */

	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the phone number of the college.
	 *
	 * @return the phone number
	 */

	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * Sets the phone number of the college.
	 *
	 * @param phoneNo the phone number to set
	 */

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/**
	 * Returns the unique key for this bean (usually the id).
	 *
	 * @return the key as a string
	 */

	public String getkey() {
		// TODO Auto-generated method stub
		return id + "";
	}

	/**
	 * Returns the value to display (usually the name).
	 *
	 * @return the name
	 */

	public String getValue() {
		// TODO Auto-generated method stub
		return name;
	}

}
