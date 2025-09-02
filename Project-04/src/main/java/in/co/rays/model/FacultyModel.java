package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.bean.CollegeBean;
import in.co.rays.bean.CourseBean;
import in.co.rays.bean.FacultyBean;
import in.co.rays.bean.SubjectBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.JDBCDataSource;

/**
 * Model class responsible for managing Faculty data in the database. Provides
 * methods for CRUD operations and searching records using JDBC.
 * 
 * @author Amit
 * @version 1.0
 */

public class FacultyModel {

	Logger log = Logger.getLogger(FacultyModel.class);

	/**
	 * Generates the next primary key for faculty records.
	 *
	 * @return next primary key (Integer)
	 * @throws DatabaseException if database error occurs while fetching max ID
	 */

	public Integer nextPk() throws DatabaseException {

		log.info("FacultyModel nextPk started");

		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_faculty");
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
		log.info("FacultyModel nextPk Ended");
		return pk + 1;
	}

	/**
	 * Adds a new faculty record to the database.
	 *
	 * @param bean FacultyBean containing faculty data
	 * @return generated primary key
	 * @throws ApplicationException     if application-level exception occurs
	 * @throws DuplicateRecordException if email already exists
	 */

	public long add(FacultyBean bean) throws ApplicationException, DuplicateRecordException {

		log.info("FacultyModel add started");

		Connection conn = null;

		int pk = 0;

		CollegeModel collegeModel = new CollegeModel();
		CollegeBean collegeBean = collegeModel.findByPk(bean.getCollegeId());
		bean.setCollegeName(collegeBean.getName());

		CourseModel courseModel = new CourseModel();
		CourseBean courseBean = courseModel.findByPk(bean.getCousreId());
		bean.setCourseName(courseBean.getName());

		SubjectModel subjectModel = new SubjectModel();
		SubjectBean subjectBean = subjectModel.findByPk(bean.getSubjectid());
		bean.setSubjectName(subjectBean.getName());

		FacultyBean existbean = findByEmail(bean.getEmail());

		if (existbean != null) {
			throw new DuplicateRecordException("Email Id already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPk();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"insert into st_faculty values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getFirstName());
			pstmt.setString(3, bean.getLastName());
			pstmt.setDate(4, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(5, bean.getGender());
			pstmt.setString(6, bean.getMoblileNo());
			pstmt.setString(7, bean.getEmail());
			pstmt.setLong(8, bean.getCollegeId());
			pstmt.setString(9, bean.getCollegeName());
			pstmt.setLong(10, bean.getCousreId());
			pstmt.setString(11, bean.getCourseName());
			pstmt.setLong(12, bean.getSubjectid());
			pstmt.setString(13, bean.getSubjectName());
			pstmt.setString(14, bean.getCreatedBy());
			pstmt.setString(15, bean.getModifiedBy());
			pstmt.setTimestamp(16, bean.getCreatedDatetime());
			pstmt.setTimestamp(17, bean.getModifiedDatetime());
			int i = pstmt.executeUpdate();
			conn.commit(); // End transaction
			System.out.println("data added successfully " + i);
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Faculty");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("FacultyModel add Ended");
		return pk;
	}

	/**
	 * Updates an existing faculty record.
	 *
	 * @param bean FacultyBean with updated data
	 * @throws ApplicationException     if any database/application error occurs
	 * @throws DuplicateRecordException if email already exists for another faculty
	 */

	public void update(FacultyBean bean) throws ApplicationException, DuplicateRecordException {

		log.info("FacultyModel update Started");

		Connection conn = null;

		// get College Name
		CollegeModel collegeModel = new CollegeModel();

		CollegeBean collegeBean = collegeModel.findByPk(bean.getCollegeId());

		bean.setCollegeName(collegeBean.getName());

		// get Course Name
		CourseModel courseModel = new CourseModel();
		CourseBean courseBean = courseModel.findByPk(bean.getCousreId());
		bean.setCourseName(courseBean.getName());

		// get Subject Name
		SubjectModel subjectModel = new SubjectModel();
		SubjectBean subjectBean = subjectModel.findByPk(bean.getSubjectid());
		bean.setSubjectName(subjectBean.getName());

		FacultyBean beanExist = findByEmail(bean.getEmail());
		if (beanExist != null && !(beanExist.getId() == bean.getId())) {
			throw new DuplicateRecordException("EmailId is already exist");
		}
		try {
			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_faculty set first_name = ?, last_name = ?, dob = ?, gender = ?, mobile_no = ?, email = ?, college_id = ?, college_name = ?, course_id = ?, course_name = ?, subject_id = ?, subject_name = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");

			pstmt.setString(1, bean.getFirstName());
			pstmt.setString(2, bean.getLastName());
			pstmt.setDate(3, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(4, bean.getGender());
			pstmt.setString(5, bean.getMoblileNo());
			pstmt.setString(6, bean.getEmail());
			pstmt.setLong(7, bean.getCollegeId());
			pstmt.setString(8, bean.getCollegeName());
			pstmt.setLong(9, bean.getCousreId());
			pstmt.setString(10, bean.getCourseName());
			pstmt.setLong(11, bean.getSubjectid());
			pstmt.setString(12, bean.getSubjectName());
			pstmt.setString(13, bean.getCreatedBy());
			pstmt.setString(14, bean.getModifiedBy());
			pstmt.setTimestamp(15, bean.getCreatedDatetime());
			pstmt.setTimestamp(16, bean.getModifiedDatetime());
			pstmt.setLong(17, bean.getId());
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
			throw new ApplicationException("Exception in updating Faculty ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("FacultyModel update Ended");
	}

	/**
	 * Deletes a faculty record based on ID.
	 *
	 * @param bean FacultyBean with ID to delete
	 * @throws ApplicationException if any exception occurs during deletion
	 */

	public void delete(FacultyBean bean) throws ApplicationException {

		log.info("FacultyModel delete Started");

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from st_faculty where id = ?");
			pstmt.setLong(1, bean.getId());
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("data deleted successfully " + i);
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete Faculty");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("FacultyModel delete Ended");
	}

	/**
	 * Finds a faculty record by primary key.
	 *
	 * @param pk primary key
	 * @return FacultyBean if found, otherwise null
	 * @throws ApplicationException if any database error occurs
	 */

	public FacultyBean findByPk(long pk) throws ApplicationException {

		log.info("FacultyModel findByPk Started");

		StringBuffer sql = new StringBuffer("select * from st_faculty where id = ?");

		FacultyBean bean = null;

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new FacultyBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setDob(rs.getDate(4));
				bean.setGender(rs.getString(5));
				bean.setMoblileNo(rs.getString(6));
				bean.setEmail(rs.getString(7));
				bean.setCollegeId(rs.getLong(8));
				bean.setCollegeName(rs.getString(9));
				bean.setCousreId(rs.getLong(10));
				bean.setCourseName(rs.getString(11));
				bean.setSubjectid(rs.getLong(12));
				bean.setSubjectName(rs.getString(13));
				bean.setCreatedBy(rs.getString(14));
				bean.setModifiedBy(rs.getString(15));
				bean.setCreatedDatetime(rs.getTimestamp(16));
				bean.setModifiedDatetime(rs.getTimestamp(17));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting Faculty by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("FacultyModel findByPk Ended");
		return bean;
	}

	/**
	 * Finds a faculty record by email address.
	 *
	 * @param email Email of the faculty
	 * @return FacultyBean if found, otherwise null
	 * @throws ApplicationException if any error occurs
	 */

	public FacultyBean findByEmail(String email) throws ApplicationException {

		log.info("FacultyModel findbyEmail Started");

		StringBuffer sql = new StringBuffer("select * from st_faculty where email = ?");

		FacultyBean bean = null;

		Connection conn = null;

		System.out.println("sql" + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new FacultyBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setDob(rs.getDate(4));
				bean.setGender(rs.getString(5));
				bean.setMoblileNo(rs.getString(6));
				bean.setEmail(rs.getString(7));
				bean.setCollegeId(rs.getLong(8));
				bean.setCollegeName(rs.getString(9));
				bean.setCousreId((rs.getLong(10)));
				bean.setCourseName(rs.getString(11));
				bean.setSubjectid(rs.getLong(12));
				bean.setSubjectName(rs.getString(13));
				bean.setCreatedBy(rs.getString(14));
				bean.setModifiedBy(rs.getString(15));
				bean.setCreatedDatetime(rs.getTimestamp(16));
				bean.setModifiedDatetime(rs.getTimestamp(17));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting Faculty by Email");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("FacultyModel findbyEmail Ended");
		return bean;
	}

	/**
	 * Returns all faculty records from the database.
	 *
	 * @return list of all FacultyBeans
	 * @throws ApplicationException if error occurs during database access
	 */

	public List list() throws ApplicationException {
		log.info("FacultyModel list");
		return search(null, 0, 0);

	}

	/**
	 * Searches faculty records based on the provided search criteria. Supports
	 * pagination.
	 *
	 * @param bean     FacultyBean containing search filters
	 * @param pageNo   current page number (1-based index)
	 * @param pageSize number of records per page
	 * @return list of FacultyBeans matching the criteria
	 * @throws ApplicationException if error occurs during database access
	 */

	public List<FacultyBean> search(FacultyBean bean, int pageNo, int pageSize) throws ApplicationException {

		log.info("FacultyModel search Started");

		StringBuffer sql = new StringBuffer("select * from st_faculty where 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getCollegeId() > 0) {
				sql.append(" and college_id = " + bean.getCollegeId());
			}
			if (bean.getSubjectid() > 0) {
				sql.append(" and subject_id = " + bean.getSubjectid());
			}
			if (bean.getCousreId() > 0) {
				sql.append(" and course_id = " + bean.getCousreId());
			}
			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" and first_name like '" + bean.getFirstName() + "%'");
			}
			if (bean.getLastName() != null && bean.getLastName().length() > 0) {
				sql.append(" and last_name like '" + bean.getLastName() + "%'");
			}
			if (bean.getGender() != null && bean.getGender().length() > 0) {
				sql.append(" and gender like '" + bean.getGender() + "%'");
			}
			if (bean.getDob() != null && bean.getDob().getDate() > 0) {
				sql.append(" and dob = " + bean.getDob());
			}
			if (bean.getEmail() != null && bean.getEmail().length() > 0) {
				sql.append(" and email like '" + bean.getEmail() + "%'");
			}
			if (bean.getMoblileNo() != null && bean.getMoblileNo().length() > 0) {
				sql.append(" and mobile_no = " + bean.getMoblileNo());
			}
			if (bean.getCourseName() != null && bean.getCourseName().length() > 0) {
				sql.append(" and course_name like '" + bean.getCourseName() + "%'");
			}
			if (bean.getCollegeName() != null && bean.getCollegeName().length() > 0) {
				sql.append(" and college_name like '" + bean.getCollegeName() + "%'");
			}
			if (bean.getSubjectName() != null && bean.getSubjectName().length() > 0) {
				sql.append(" and subject_name like '" + bean.getSubjectName() + "%'");
			}
		}
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}

		ArrayList<FacultyBean> list = new ArrayList<FacultyBean>();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new FacultyBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setDob(rs.getDate(4));
				bean.setGender(rs.getString(5));
				bean.setMoblileNo(rs.getString(6));
				bean.setEmail(rs.getString(7));
				bean.setCollegeId(rs.getLong(8));
				bean.setCollegeName(rs.getString(9));
				bean.setCousreId(rs.getLong(10));
				bean.setCourseName(rs.getString(11));
				bean.setSubjectid(rs.getLong(12));
				bean.setSubjectName(rs.getString(13));
				bean.setCreatedBy(rs.getString(14));
				bean.setModifiedBy(rs.getString(15));
				bean.setCreatedDatetime(rs.getTimestamp(16));
				bean.setModifiedDatetime(rs.getTimestamp(17));
				list.add(bean);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in search Faculty");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("FacultyModel search Ended");
		return list;
	}
}
