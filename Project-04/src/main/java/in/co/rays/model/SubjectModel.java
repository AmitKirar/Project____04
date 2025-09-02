package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.bean.CourseBean;
import in.co.rays.bean.SubjectBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.JDBCDataSource;

/**
 * The SubjectModel class provides methods to perform CRUD operations and search
 * functionalities for the Subject entity in the database. * @author Amit
 * 
 * @version 1.0
 */

public class SubjectModel {

	Logger log = Logger.getLogger(SubjectModel.class);

	/**
	 * Returns the next primary key value for the subject table.
	 *
	 * @return the next primary key
	 * @throws DatabaseException if a database error occurs
	 */

	public Integer nextPk() throws DatabaseException {

		log.info("SubjectModel nextPk Started");

		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_subject");
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
		log.info("SubjectModel nextPk Ended");
		return pk + 1;
	}

	/**
	 * Adds a new subject record to the database.
	 *
	 * @param bean the SubjectBean containing subject data
	 * @return the generated primary key
	 * @throws ApplicationException     if a database error occurs
	 * @throws DuplicateRecordException if a subject with the same name already
	 *                                  exists
	 */

	public long add(SubjectBean bean) throws ApplicationException, DuplicateRecordException {

		log.info("SubjectModel add Started");

		Connection conn = null;

		int pk = 0;

		CourseModel courseModel = new CourseModel();
		CourseBean courseBean = courseModel.findByPk(bean.getCourseId());
		bean.setCourseName(courseBean.getName());

		SubjectBean duplicateSubject = findByName(bean.getName());
		if (duplicateSubject != null) {
			throw new DuplicateRecordException("Subject Name already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPk();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("insert into st_subject values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setLong(3, bean.getCourseId());
			pstmt.setString(4, bean.getCourseName());
			pstmt.setString(5, bean.getDescription());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());
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
			throw new ApplicationException("Exception : Exception in add Subject");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("SubjectModel add Ended");
		return pk;
	}

	/**
	 * Updates an existing subject record in the database.
	 *
	 * @param bean the SubjectBean containing updated data
	 * @throws ApplicationException     if a database error occurs
	 * @throws DuplicateRecordException if a duplicate subject name is found
	 */

	public void update(SubjectBean bean) throws ApplicationException, DuplicateRecordException {

		log.info("SubjectModel update Started");

		Connection conn = null;

		CourseModel courseModel = new CourseModel();

		CourseBean courseBean = courseModel.findByPk(bean.getCourseId());

		bean.setCourseName(courseBean.getName());
		try {
			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_subject set name = ?, course_id = ?, course_name = ?, description = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");
			pstmt.setString(1, bean.getName());
			pstmt.setLong(2, bean.getCourseId());
			pstmt.setString(3, bean.getCourseName());
			pstmt.setString(4, bean.getDescription());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
			pstmt.setLong(9, bean.getId());
			int i = pstmt.executeUpdate();
			conn.commit(); // End transaction
			System.out.println("data updated successfully " + i);
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Subject ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("SubjectModel update Ended");
	}

	/**
	 * Deletes a subject record from the database.
	 *
	 * @param bean the SubjectBean containing the ID to delete
	 * @throws ApplicationException if a database error occurs
	 */

	public void delete(SubjectBean bean) throws ApplicationException {

		log.info("SubjectModel delete Started");

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("delete from st_subject where id = ?");
			pstmt.setLong(1, bean.getId());
			int i = pstmt.executeUpdate();
			conn.commit(); // End transaction
			System.out.println("data deleted successfully " + i);
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete Subject");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("SubjectModel delete Ended");
	}

	/**
	 * Finds a subject by its primary key.
	 *
	 * @param pk the primary key
	 * @return the SubjectBean found, or null if not found
	 * @throws ApplicationException if a database error occurs
	 */

	public SubjectBean findByPk(long pk) throws ApplicationException {

		log.info("SubjectModel findByPk Started");

		StringBuffer sql = new StringBuffer("select * from st_subject where id = ?");

		SubjectBean bean = null;

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new SubjectBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setCourseId(rs.getLong(3));
				bean.setCourseName(rs.getString(4));
				bean.setDescription(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting Subject by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("SubjectModel findByPk Ended");
		return bean;
	}

	/**
	 * Finds a subject by its name.
	 *
	 * @param name the subject name
	 * @return the SubjectBean found, or null if not found
	 * @throws ApplicationException if a database error occurs
	 */

	public SubjectBean findByName(String name) throws ApplicationException {

		log.info("SubjectModel findByName Started");

		StringBuffer sql = new StringBuffer("select * from st_subject where name = ?");

		SubjectBean bean = null;

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				bean = new SubjectBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setCourseId(rs.getLong(3));
				bean.setCourseName(rs.getString(4));
				bean.setDescription(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting Subject by Subject Name");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("SubjectModel findByName Ended");
		return bean;
	}

	/**
	 * Returns a list of all subjects in the database.
	 *
	 * @return the list of SubjectBeans
	 * @throws ApplicationException if a database error occurs
	 */

	public List<SubjectBean> list() throws ApplicationException {
		log.info("SubjectModel list Method");
		return search(null, 0, 0);
	}

	/**
	 * Searches subjects based on the provided criteria and pagination.
	 *
	 * @param bean     the SubjectBean with search criteria
	 * @param pageNo   the page number
	 * @param pageSize the number of records per page
	 * @return the list of matching SubjectBeans
	 * @throws ApplicationException if a database error occurs
	 */

	public List<SubjectBean> search(SubjectBean bean, int pageNo, int pageSize) throws ApplicationException {

		log.info("SubjectModel search Started");

		StringBuffer sql = new StringBuffer("select * from st_subject where 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}
			if (bean.getCourseId() > 0) {
				sql.append(" and course_id = " + bean.getCourseId());
			}
			if (bean.getCourseName() != null && bean.getCourseName().length() > 0) {
				sql.append(" and course_name like '" + bean.getCourseName() + "%'");
			}
			if (bean.getDescription() != null && bean.getDescription().length() > 0) {
				sql.append(" and description like '" + bean.getDescription() + "%'");
			}

		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}

		ArrayList<SubjectBean> list = new ArrayList<SubjectBean>();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new SubjectBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setCourseId(rs.getLong(3));
				bean.setCourseName(rs.getString(4));
				bean.setDescription(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
				list.add(bean);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in search Subject");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("SubjectModel search Ended");
		return list;
	}

}
