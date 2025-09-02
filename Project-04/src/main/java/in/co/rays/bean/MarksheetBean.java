package in.co.rays.bean;

/**
 * MarksheetBean represents a student's marksheet details including roll number,
 * student ID, name, and marks in physics, chemistry, and maths. It extends
 * BaseBean to inherit common fields like id, createdBy, etc.
 * 
 * @Author Amit
 * @version 1.0
 */

public class MarksheetBean extends BaseBean {
	/** Roll number of the student */
	private String rollNo;

	/** Student ID (foreign key reference to student table) */
	private long studentId;

	/** Name of the student */
	private String name;

	/** Marks in Physics */
	private Integer physics;

	/** Marks in Chemistry */
	private Integer chemistry;

	/** Marks in Maths */
	private Integer maths;

	/**
	 * Gets the roll number of the student.
	 * 
	 * @return roll number
	 */

	public String getRollNo() {
		return rollNo;
	}

	/**
	 * Sets the roll number of the student.
	 * 
	 * @param rollNo the roll number to set
	 */

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	/**
	 * Gets the student ID.
	 * 
	 * @return student ID
	 */

	public long getStudentId() {
		return studentId;
	}

	/**
	 * Sets the student ID.
	 * 
	 * @param studentId the student ID to set
	 */

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	/**
	 * Gets the name of the student.
	 * 
	 * @return student name
	 */

	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the student.
	 * 
	 * @param name the student name to set
	 */

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the marks in Physics.
	 * 
	 * @return physics marks
	 */

	public Integer getPhysics() {
		return physics;
	}

	/**
	 * Sets the marks in Physics.
	 * 
	 * @param physics the physics marks to set
	 */

	public void setPhysics(Integer physics) {
		this.physics = physics;
	}

	/**
	 * Gets the marks in Chemistry.
	 * 
	 * @return chemistry marks
	 */

	public Integer getChemistry() {
		return chemistry;
	}

	/**
	 * Sets the marks in Chemistry.
	 * 
	 * @param chemistry the chemistry marks to set
	 */

	public void setChemistry(Integer chemistry) {
		this.chemistry = chemistry;
	}

	/**
	 * Gets the marks in Maths.
	 * 
	 * @return maths marks
	 */

	public Integer getMaths() {
		return maths;
	}

	/**
	 * Sets the marks in Maths.
	 * 
	 * @param maths the maths marks to set
	 */

	public void setMaths(Integer maths) {
		this.maths = maths;
	}

	/**
	 * Returns the key for this bean. (Currently not implemented)
	 * 
	 * @return null
	 */

	public String getkey() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns the value for this bean. (Currently not implemented)
	 * 
	 * @return null
	 */

	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
