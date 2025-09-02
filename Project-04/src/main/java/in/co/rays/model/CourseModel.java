package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.bean.CourseBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.JDBCDataSource;

/**
 * Model class responsible for managing Course data in the database. Provides
 * methods for performing CRUD operations and searching records using JDBC.
 * 
 * @author Amit
 * @version 1.0
 */

public class CourseModel {

	Logger log = Logger.getLogger(CourseModel.class);

	/**
	 * Gets the next primary key for course entries.
	 * 
	 * @return next PK as Integer
	 * @throws DatabaseException if an error occurs during PK retrieval
	 */

	public Integer nextPk() throws DatabaseException {

		log.info("CourseModel nextPk Started");

		Connection conn = null;

		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_course");
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
		log.info("CourseModel nextPk Ended");
		return pk + 1;
	}

	/**
	 * Adds a new course record to the database.
	 * 
	 * @param bean CourseBean object containing course data
	 * @return generated primary key
	 * @throws ApplicationException     if an application error occurs
	 * @throws DuplicateRecordException if the course name already exists
	 */

	public long add(CourseBean bean) throws ApplicationException, DuplicateRecordException {

		log.info("CourseModel add Started");

		Connection conn = null;

		int pk = 0;

		CourseBean duplicateCourse = findByName(bean.getName());

		if (duplicateCourse != null) {
			throw new DuplicateRecordException("Course Name already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPk();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into st_course values(?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getDuration());
			pstmt.setString(4, bean.getDescription());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("data added successfully " + i);
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Course");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("CourseModel add Ended");
		return pk;
	}

	/**
	 * Updates an existing course record.
	 * 
	 * @param bean CourseBean object containing updated course data
	 * @throws ApplicationException     if an application error occurs
	 * @throws DuplicateRecordException if a course with the same name exists
	 */

	public void update(CourseBean bean) throws ApplicationException, DuplicateRecordException {

		log.info("CourseModel update Started");

		Connection conn = null;

		CourseBean duplicateCourse = findByName(bean.getName());

		if (duplicateCourse != null && duplicateCourse.getId() != bean.getId()) {
			throw new DuplicateRecordException("Course already exists");
		}
		try {
			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_course set name = ?, duration = ?, description = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getDuration());
			pstmt.setString(3, bean.getDescription());
			pstmt.setString(4, bean.getCreatedBy());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getCreatedDatetime());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());
			pstmt.setLong(8, bean.getId());
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
			throw new ApplicationException("Exception in updating Course ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("CourseModel update Ended");
	}

	/**
	 * Deletes a course record based on the provided bean ID.
	 * 
	 * @param bean CourseBean with the ID to be deleted
	 * @throws ApplicationException if a delete or rollback error occurs
	 */

	public void delete(CourseBean bean) throws ApplicationException {

		log.info("CourseModel delete Started");

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from st_course where id = ?");
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
			throw new ApplicationException("Exception : Exception in delete Course");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("CourseModel delete Ended");
	}

	/**
	 * Finds a course by its primary key.
	 * 
	 * @param pk primary key of the course
	 * @return CourseBean object if found, else null
	 * @throws ApplicationException if an error occurs during lookup
	 */

	public CourseBean findByPk(long pk) throws ApplicationException {

		log.info("CourseModel findByPk Started");

		StringBuffer sql = new StringBuffer("select * from st_course where id = ?");

		CourseBean bean = null;

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CourseBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDuration(rs.getString(3));
				bean.setDescription(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting Course by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("CourseModel findByPk Ended");
		return bean;
	}

	/**
	 * Finds a course by its name.
	 * 
	 * @param name course name
	 * @return CourseBean object if found, else null
	 * @throws ApplicationException if an error occurs during lookup
	 */

	public CourseBean findByName(String name) throws ApplicationException {

		log.info("CourseModel findByName Started");

		StringBuffer sql = new StringBuffer("select * from st_course where name = ?");

		CourseBean bean = null;

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CourseBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDuration(rs.getString(3));
				bean.setDescription(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));

			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting Course by Course Name");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("CourseModel findByName Ended");
		return bean;
	}

	/**
	 * Returns a list of all courses.
	 * 
	 * @return List of CourseBean
	 * @throws ApplicationException if an error occurs during retrieval
	 */

	public List list() throws ApplicationException {

		return search(null, 0, 0);
	}

	/**
	 * Searches course records based on the provided search criteria. Supports
	 * pagination.
	 * 
	 * @param bean     CourseBean with search filters
	 * @param pageNo   current page number (starting from 1)
	 * @param pageSize number of records per page
	 * @return List of matching CourseBean objects
	 * @throws ApplicationException if an error occurs during search
	 */

	public List<CourseBean> search(CourseBean bean, int pageNo, int pageSize) throws ApplicationException {

		log.info("CourseModel search Started");

		StringBuffer sql = new StringBuffer("select * from st_course where 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}
			if (bean.getDuration() != null && bean.getDuration().length() > 0) {
				sql.append(" and duration like '" + bean.getDuration() + "%'");
			}
			if (bean.getDescription() != null && bean.getDescription().length() > 0) {
				sql.append(" and description like '" + bean.getDescription() + "%'");
			}
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}

		ArrayList<CourseBean> list = new ArrayList<CourseBean>();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CourseBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDuration(rs.getString(3));
				bean.setDescription(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
				list.add(bean);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in search Course");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("CourseModel search Ended");
		return list;
	}

}
