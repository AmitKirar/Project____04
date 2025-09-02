
package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.bean.CollegeBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.JDBCDataSource;

/**
 * CollegeModel handles all database operations related to the College entity.
 * It provides methods to add, update, delete, find, and search colleges in the
 * system.
 * 
 * @author Amit
 * @version 1.0
 */

public class CollegeModel {

	Logger log = Logger.getLogger(CollegeModel.class);

	/**
	 * Adds a new College record into the database.
	 * 
	 * @param bean the CollegeBean containing college data
	 * @return the generated primary key (ID)
	 * @throws ApplicationException     if a database or application error occurs
	 * @throws DuplicateRecordException if a college with the same name already
	 *                                  exists
	 */

	public long add(CollegeBean bean) throws ApplicationException, DuplicateRecordException {

		log.info("Model add Started");

		Connection conn = null;
		int pk = 0;

		CollegeBean duplicateCollegeName = findByName(bean.getName());

		if (duplicateCollegeName != null) {
			throw new DuplicateRecordException("College Name already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPk();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("insert into st_college values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getAddress());
			pstmt.setString(4, bean.getState());
			pstmt.setString(5, bean.getCity());
			pstmt.setString(6, bean.getPhoneNo());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("data added successfully " + i);
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add College");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.info("Model add Ended");
		return pk;
	}

	/**
	 * Deletes a College record from the database by ID.
	 * 
	 * @param bean the CollegeBean containing the ID to delete
	 * @throws ApplicationException if a database or rollback error occurs
	 */

	public void delete(CollegeBean bean) throws ApplicationException {

		log.info("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from st_college where id = ?");
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
			throw new ApplicationException("Exception : Exception in delete college");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.info("Model delete Ended");
	}

	/**
	 * Updates an existing College record in the database.
	 * 
	 * @param bean the CollegeBean containing updated data
	 * @throws ApplicationException     if a database or application error occurs
	 * @throws DuplicateRecordException if a duplicate college name exists
	 */

	public void update(CollegeBean bean) throws ApplicationException, DuplicateRecordException {

		log.info("Model update Started");

		Connection conn = null;

		CollegeBean beanExist = findByName(bean.getName());

		if (beanExist != null && beanExist.getId() != bean.getId()) {
			throw new DuplicateRecordException("College is already exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_college set name = ?, address = ?, state = ?, city = ?, phone_no = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getAddress());
			pstmt.setString(3, bean.getState());
			pstmt.setString(4, bean.getCity());
			pstmt.setString(5, bean.getPhoneNo());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());
			pstmt.setLong(10, bean.getId());
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
			throw new ApplicationException("Exception in updating College ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.info("Model update Ended");
	}

	/**
	 * Returns the next available primary key for a new College record.
	 * 
	 * @return next PK as Integer
	 * @throws DatabaseException if a database access error occurs
	 */

	public Integer nextPk() throws DatabaseException {

		log.info("Model nextPk Started");

		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_college");
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
		log.info("Model nextPk Ended");
		return pk + 1;
	}

	/**
	 * Finds a College record by its primary key.
	 * 
	 * @param pk the primary key (ID) of the college
	 * @return the CollegeBean object if found, otherwise null
	 * @throws ApplicationException if a database error occurs
	 */

	public CollegeBean findByPk(long pk) throws ApplicationException {

		log.info("Model findByPk Started");

		StringBuffer sql = new StringBuffer("select * from st_college where id = ?");

		CollegeBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CollegeBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setAddress(rs.getString(3));
				bean.setState(rs.getString(4));
				bean.setCity(rs.getString(5));
				bean.setPhoneNo(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting College by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("Model findByPk Ended");
		return bean;
	}

	/**
	 * Finds a College record by its name.
	 * 
	 * @param name the name of the college
	 * @return the CollegeBean object if found, otherwise null
	 * @throws ApplicationException if a database error occurs
	 */

	public CollegeBean findByName(String name) throws ApplicationException {

		log.info("Model findByName Started");

		StringBuffer sql = new StringBuffer("select * from st_college where name = ?");

		CollegeBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CollegeBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setAddress(rs.getString(3));
				bean.setState(rs.getString(4));
				bean.setCity(rs.getString(5));
				bean.setPhoneNo(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting College by Name");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.info("Model findByName Ended");
		return bean;
	}

	/**
	 * Returns a list of all colleges in the system.
	 * 
	 * @return list of all CollegeBean objects
	 * @throws ApplicationException if a database error occurs
	 */

	public List list() throws ApplicationException {
		return search(null, 0, 0);
	}

	/**
	 * Searches College records based on the provided search criteria. Supports
	 * pagination.
	 * 
	 * @param bean     the CollegeBean containing search filters
	 * @param pageNo   current page number (starts from 1)
	 * @param pageSize number of records per page
	 * @return list of matching CollegeBean records
	 * @throws ApplicationException if a database error occurs
	 */

	public List<CollegeBean> search(CollegeBean bean, int pageNo, int pageSize) throws ApplicationException {

		log.info("Model search Started");

		StringBuffer sql = new StringBuffer("select * from st_college where 1 = 1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}
			if (bean.getAddress() != null && bean.getAddress().length() > 0) {
				sql.append(" and address like '" + bean.getAddress() + "%'");
			}
			if (bean.getState() != null && bean.getState().length() > 0) {
				sql.append(" and state like '" + bean.getState() + "%'");
			}
			if (bean.getCity() != null && bean.getCity().length() > 0) {
				sql.append(" and city like '" + bean.getCity() + "%'");
			}
			if (bean.getPhoneNo() != null && bean.getPhoneNo().length() > 0) {
				sql.append(" and phone_no = " + bean.getPhoneNo());
			}
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}

		ArrayList<CollegeBean> list = new ArrayList<CollegeBean>();
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CollegeBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setAddress(rs.getString(3));
				bean.setState(rs.getString(4));
				bean.setCity(rs.getString(5));
				bean.setPhoneNo(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
				list.add(bean);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in search college");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.info("Model search Ended");
		return list;
	}

}
