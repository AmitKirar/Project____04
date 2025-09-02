
package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.bean.CollegeBean;
import in.co.rays.bean.StudentBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.JDBCDataSource;

/**
 * The StudentModel class provides methods to interact with the st_student
 * database table. It supports CRUD operations and searching student records.
 * 
 * @author Amit
 * @version 1.0
 */

public class StudentModel {

	Logger log = Logger.getLogger(StudentModel.class);

	/**
	 * Gets the next primary key for the student table.
	 *
	 * @return next available primary key
	 * @throws DatabaseException if a database error occurs
	 */

	public Integer nextPk() throws DatabaseException {

		log.info("StudentModel nextPk Started");

		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_student");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("StudentModel nextPk Ended");
		return pk + 1;
	}

	/**
	 * Adds a new student record to the database.
	 *
	 * @param bean the StudentBean containing student details
	 * @return the generated primary key
	 * @throws DuplicateRecordException if a student with the same email already
	 *                                  exists
	 * @throws ApplicationException     if a database error occurs
	 */

	public long add(StudentBean bean) throws DuplicateRecordException, ApplicationException {

		log.info("StudentModel add Started");

		Connection conn = null;

		CollegeModel collegeModel = new CollegeModel();
		CollegeBean collegeBean = collegeModel.findByPk(bean.getCollegeId());
		bean.setCollegeName(collegeBean.getName());

		StudentBean existBean = findByEmail(bean.getEmail());
		int pk = 0;

		if (existBean != null) {
			throw new DuplicateRecordException("Email already exists");
		}

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("insert into st_student values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getFirstName());
			pstmt.setString(3, bean.getLastName());
			pstmt.setDate(4, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(5, bean.getGender());
			pstmt.setString(6, bean.getMobileNo());
			pstmt.setString(7, bean.getEmail());
			pstmt.setLong(8, bean.getCollegeId());
			pstmt.setString(9, bean.getCollegeName());
			pstmt.setString(10, bean.getCreatedBy());
			pstmt.setString(11, bean.getModifiedBy());
			pstmt.setTimestamp(12, bean.getCreatedDatetime());
			pstmt.setTimestamp(13, bean.getModifiedDatetime());
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("data added successfully " + i);
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in add Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("StudentModel add Ended");
		return pk;
	}

	/**
	 * Deletes a student record from the database.
	 *
	 * @param bean the StudentBean containing the student ID
	 * @throws ApplicationException if a database error occurs
	 */

	public void delete(StudentBean bean) throws ApplicationException {

		log.info("StudentModel delete Started");

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from st_student where id = ?");
			pstmt.setLong(1, bean.getId());
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Data deleted successfully " + i);
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("StudentModel delete Ended");
	}

	/**
	 * Updates an existing student record in the database.
	 *
	 * @param bean the StudentBean with updated student data
	 * @throws ApplicationException     if a database error occurs
	 * @throws DuplicateRecordException if the email is already used by another
	 *                                  student
	 */

	public void update(StudentBean bean) throws ApplicationException, DuplicateRecordException {

		log.info("StudentModel update Started");

		CollegeModel collegeModel = new CollegeModel();

		CollegeBean collegeBean = collegeModel.findByPk(bean.getCollegeId());

		bean.setCollegeName(collegeBean.getName());

		StudentBean existBean = findByEmail(bean.getEmail());

		if (existBean != null && existBean.getId() != bean.getId()) {
			throw new DuplicateRecordException("Student email Already exists");
		}

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_student set first_name = ?, last_name = ?, dob = ?, gender = ?, mobile_no = ?, email_id = ?, college_id = ?, college_name = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");
			pstmt.setString(1, bean.getFirstName());
			pstmt.setString(2, bean.getLastName());
			pstmt.setDate(3, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(4, bean.getGender());
			pstmt.setString(5, bean.getMobileNo());
			pstmt.setString(6, bean.getEmail());
			pstmt.setLong(7, bean.getCollegeId());
			pstmt.setString(8, bean.getCollegeName());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDatetime());
			pstmt.setTimestamp(12, bean.getModifiedDatetime());
			pstmt.setLong(13, bean.getId());
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("data updated successfully " + i);
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			e.printStackTrace();
			throw new ApplicationException("Exception in updating Student ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.info("StudentModel update Ended");
	}

	/**
	 * Finds a student by primary key.
	 *
	 * @param pk the primary key (student ID)
	 * @return the StudentBean found, or null if not found
	 * @throws ApplicationException if a database error occurs
	 */

	public StudentBean findByPk(long pk) throws ApplicationException {

		log.info("StudentModel findByPk Started");

		StringBuffer sql = new StringBuffer("select * from st_student where id = ?");

		StudentBean bean = null;

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new StudentBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setDob(rs.getDate(4));
				bean.setGender(rs.getString(5));
				bean.setMobileNo(rs.getString(6));
				bean.setEmail(rs.getString(7));
				bean.setCollegeId(rs.getLong(8));
				bean.setCollegeName(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("StudentModel findByPk Ended");
		return bean;
	}

	/**
	 * Finds a student by email address.
	 *
	 * @param Email the student's email address
	 * @return the StudentBean found, or null if not found
	 * @throws ApplicationException if a database error occurs
	 */

	public StudentBean findByEmail(String Email) throws ApplicationException {

		log.info("StudentModel findbyEmail Started");

		StringBuffer sql = new StringBuffer("select * from st_student where email_id = ?");

		StudentBean bean = null;

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, Email);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new StudentBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setDob(rs.getDate(4));
				bean.setGender(rs.getString(5));
				bean.setMobileNo(rs.getString(6));
				bean.setEmail(rs.getString(7));
				bean.setCollegeId(rs.getLong(8));
				bean.setCollegeName(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
//			throw new ApplicationException("Exception : Exception in getting User by Email");
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("StudentModel findbyEmail Ended");
		return bean;
	}

	/**
	 * Returns a list of all students.
	 *
	 * @return a list of StudentBean objects
	 * @throws ApplicationException if a database error occurs
	 */

	public List list() throws ApplicationException {
		log.info("StudentModel list Method");
		return search(null, 0, 0);
	}

	/**
	 * Searches student records based on the given criteria with optional
	 * pagination.
	 *
	 * @param bean     the StudentBean containing search filters
	 * @param pageNo   the page number to retrieve (starting from 1)
	 * @param pageSize the number of records per page
	 * @return a list of matching StudentBean objects
	 * @throws ApplicationException if a database error occurs
	 */

	public List search(StudentBean bean, int pageNo, int pageSize) throws ApplicationException {

		log.info("StudentModel search Started");

		StringBuffer sql = new StringBuffer("select * from st_student where 1 = 1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" and first_name like '" + bean.getFirstName() + "%'");
			}
			if (bean.getLastName() != null && bean.getLastName().length() > 0) {
				sql.append(" and last_name like '" + bean.getLastName() + "%'");
			}
			if (bean.getDob() != null && bean.getDob().getDate() > 0) {
				sql.append(" and dob = " + bean.getDob());
			}
			if (bean.getGender() != null && bean.getGender().length() > 0) {
				sql.append(" and gender like '" + bean.getGender() + "%'");
			}
			if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {
				sql.append(" and mobile_no like '" + bean.getMobileNo() + "%'");
			}
			if (bean.getEmail() != null && bean.getEmail().length() > 0) {
				sql.append(" and email_id like '" + bean.getEmail() + "%'");
			}
			if (bean.getCollegeName() != null && bean.getCollegeName().length() > 0) {
				sql.append(" and college_name = " + bean.getCollegeName());
			}
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}

		ArrayList<StudentBean> list = new ArrayList<StudentBean>();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new StudentBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setDob(rs.getDate(4));
				bean.setGender(rs.getString(5));
				bean.setMobileNo(rs.getString(6));
				bean.setEmail(rs.getString(7));
				bean.setCollegeId(rs.getLong(8));
				bean.setCollegeName(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));
				list.add(bean);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in search Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("StudentModel search Ended");
		return list;
	}

}
