package in.co.rays.bean;

import java.util.Date;

/**
 * TimetableBean represents the exam schedule including semester, course,
 * subject, exam date, and time details. It extends BaseBean to include common
 * fields like id, createdBy, etc.
 * 
 * @Author Amit
 * @version 1.0
 */

public class TimetableBean extends BaseBean {

	/** Semester in which the exam is scheduled */
	private String semester;

	/** Description or notes related to the timetable */
	private String description;

	/** Date of the exam */
	private Date examDate;

	/** Time of the exam */
	private String examTime;

	/** ID of the course associated with the exam */
	private long courseId;

	/** Name of the course associated with the exam */
	private String courseName;

	/** ID of the subject for which the exam is scheduled */
	private long subjectId;

	/** Name of the subject for which the exam is scheduled */
	private String subjectName;

	/**
	 * Gets the semester of the timetable.
	 * 
	 * @return semester
	 */

	public String getSemester() {
		return semester;
	}

	/**
	 * Sets the semester of the timetable.
	 * 
	 * @param semester the semester to set
	 */

	public void setSemester(String semester) {
		this.semester = semester;
	}

	/**
	 * Gets the description of the timetable.
	 * 
	 * @return description
	 */

	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the timetable.
	 * 
	 * @param description the description to set
	 */

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the exam date.
	 * 
	 * @return exam date
	 */

	public Date getExamDate() {
		return examDate;
	}

	/**
	 * Sets the exam date.
	 * 
	 * @param examDate the exam date to set
	 */

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	/**
	 * Gets the exam time.
	 * 
	 * @return exam time
	 */

	public String getExamTime() {
		return examTime;
	}

	/**
	 * Sets the exam time.
	 * 
	 * @param examTime the exam time to set
	 */

	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}

	/**
	 * Gets the course ID.
	 * 
	 * @return course ID
	 */

	public long getCourseId() {
		return courseId;
	}

	/**
	 * Sets the course ID.
	 * 
	 * @param courseId the course ID to set
	 */

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	/**
	 * Gets the course name.
	 * 
	 * @return course name
	 */

	public String getCourseName() {
		return courseName;
	}

	/**
	 * Sets the course name.
	 * 
	 * @param courseName the course name to set
	 */

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * Gets the subject ID.
	 * 
	 * @return subject ID
	 */

	public long getSubjectId() {
		return subjectId;
	}

	/**
	 * Sets the subject ID.
	 * 
	 * @param subjectId the subject ID to set
	 */

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * Gets the subject name.
	 * 
	 * @return subject name
	 */

	public String getSubjectName() {
		return subjectName;
	}

	/**
	 * Sets the subject name.
	 * 
	 * @param subjectName the subject name to set
	 */

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	/**
	 * Returns the key for this bean. (Not implemented)
	 * 
	 * @return null
	 */

	public String getkey() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns the value for this bean. (Not implemented)
	 * 
	 * @return null
	 */

	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
