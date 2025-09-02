package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.bean.MarksheetBean;
import in.co.rays.bean.StudentBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.JDBCDataSource;

/**
 * Model class for performing CRUD operations and merit list generation on the
 * st_marksheet table using JDBC.
 * 
 * @author Amit
 * @version 1.0
 */
public class MarksheetModel {

	Logger log = Logger.getLogger(MarksheetModel.class);
	
	/**
	 * Returns the next primary key value for the marksheet table.
	 * 
	 * @return next primary key (int)
	 * @throws DatabaseException if any database error occurs
	 */

	public Integer nextPk() throws DatabaseException {
		
		log.info("MarksheetModel nextPk Started");
		
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_marksheet");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new DatabaseException("Exception in Marksheet getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("MarksheetModel nextPk Ended");
		return pk + 1;
	}

	/**
	 * Adds a new Marksheet record to the database.
	 * 
	 * @param bean MarksheetBean containing marksheet data
	 * @return generated primary key (long)
	 * @throws ApplicationException     if any application error occurs
	 * @throws DuplicateRecordException if the roll number already exists
	 */

	public long add(MarksheetBean bean) throws ApplicationException, DuplicateRecordException {

		log.info("MarksheetModel add Started");
		
		Connection conn = null;

		int pk = 0;

		StudentModel studentModel = new StudentModel();
		StudentBean studentbean = studentModel.findByPk(bean.getStudentId());
		bean.setName(studentbean.getFirstName() + " " + studentbean.getLastName());

		MarksheetBean duplicateMarksheet = findByRollNo(bean.getRollNo());

		if (duplicateMarksheet != null) {
			throw new DuplicateRecordException("Roll Number already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPk();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("insert into st_marksheet values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getRollNo());
			pstmt.setLong(3, bean.getStudentId());
			pstmt.setString(4, bean.getName());
			pstmt.setInt(5, bean.getPhysics());
			pstmt.setInt(6, bean.getChemistry());
			pstmt.setInt(7, bean.getMaths());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreatedDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());
			int i = pstmt.executeUpdate();
			conn.commit(); // End transaction
			System.out.println("data added successfully " + i);
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in add marksheet");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("MarksheetModel add Ended");
		return pk;
	}

	/**
	 * Updates an existing Marksheet record in the database.
	 * 
	 * @param bean MarksheetBean containing updated data
	 * @throws ApplicationException     if any application error occurs
	 * @throws DuplicateRecordException if roll number already exists for another ID
	 */

	public void update(MarksheetBean bean) throws ApplicationException, DuplicateRecordException {

		log.info("MarksheetModel update Started");
		
		Connection conn = null;

		MarksheetBean beanExist = findByRollNo(bean.getRollNo());

		if (beanExist != null && beanExist.getId() != bean.getId()) {
			throw new DuplicateRecordException("Roll No is already exist");
		}

		StudentModel studentModel = new StudentModel();
		StudentBean studentbean = studentModel.findByPk(bean.getStudentId());
		bean.setName(studentbean.getFirstName() + " " + studentbean.getLastName());

		try {
			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_marksheet set roll_no = ?, student_id = ?, name = ?, physics = ?, chemistry = ?, maths = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");
			pstmt.setString(1, bean.getRollNo());
			pstmt.setLong(2, bean.getStudentId());
			pstmt.setString(3, bean.getName());
			pstmt.setInt(4, bean.getPhysics());
			pstmt.setInt(5, bean.getChemistry());
			pstmt.setInt(6, bean.getMaths());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());
			pstmt.setLong(11, bean.getId());
			int i = pstmt.executeUpdate();
			conn.commit(); // End transaction
			System.out.println("data updated successfully " + i);
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Update rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Marksheet ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("MarksheetModel update Ended");
	}

	/**
	 * Deletes a Marksheet record from the database.
	 * 
	 * @param bean MarksheetBean with ID to be deleted
	 * @throws ApplicationException if any application error occurs
	 */

	public void delete(MarksheetBean bean) throws ApplicationException {

		log.info("MarksheetModel delete Started");
		
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("delete from st_marksheet where id = ?");
			pstmt.setLong(1, bean.getId());

			int i = pstmt.executeUpdate();
			conn.commit(); // End transaction
			System.out.println("data deleted successfully " + i);
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in delete marksheet");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("MarksheetModel delete Ended");
	}

	/**
	 * Finds a Marksheet by its primary key.
	 * 
	 * @param pk primary key (long)
	 * @return MarksheetBean with corresponding ID or null if not found
	 * @throws ApplicationException if any application error occurs
	 */

	public MarksheetBean findByPk(long pk) throws ApplicationException {

		log.info("MarksheetModel findByPk Started");

		
		StringBuffer sql = new StringBuffer("select * from st_marksheet where id = ?");
		
		MarksheetBean bean = null;
		
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new MarksheetBean();
				bean.setId(rs.getLong(1));
				bean.setRollNo(rs.getString(2));
				bean.setStudentId(rs.getLong(3));
				bean.setName(rs.getString(4));
				bean.setPhysics(rs.getInt(5));
				bean.setChemistry(rs.getInt(6));
				bean.setMaths(rs.getInt(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception in getting marksheet by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("MarksheetModel findByPk Ended");
		return bean;
	}

	/**
	 * Finds a Marksheet by its roll number.
	 * 
	 * @param rollNo roll number (String)
	 * @return MarksheetBean with corresponding roll number or null if not found
	 * @throws ApplicationException if any application error occurs
	 */

	public MarksheetBean findByRollNo(String rollNo) throws ApplicationException {

		log.info("MarksheetModel findbyRollNo Started");
		
		StringBuffer sql = new StringBuffer("select * from st_marksheet where roll_no = ?");
		
		MarksheetBean bean = null;
		
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, rollNo);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new MarksheetBean();
				bean.setId(rs.getLong(1));
				bean.setRollNo(rs.getString(2));
				bean.setStudentId(rs.getLong(3));
				bean.setName(rs.getString(4));
				bean.setPhysics(rs.getInt(5));
				bean.setChemistry(rs.getInt(6));
				bean.setMaths(rs.getInt(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception in getting marksheet by roll no");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("MarksheetModel findbyRollNo Ended");
		return bean;
	}

	/**
	 * Returns a list of all Marksheet records.
	 * 
	 * @return List of MarksheetBean
	 * @throws ApplicationException if any application error occurs
	 */

	public List list() throws ApplicationException {
		log.info("MarksheetModel list");
		return search(null, 0, 0);
	}

	/**
	 * Searches Marksheet records based on the criteria in the bean. Supports
	 * pagination.
	 * 
	 * @param bean     search criteria (nullable)
	 * @param pageNo   page number for pagination (1-based index)
	 * @param pageSize number of records per page
	 * @return List of MarksheetBean matching criteria
	 * @throws ApplicationException if any application error occurs
	 */

	public List<MarksheetBean> search(MarksheetBean bean, int pageNo, int pageSize) throws ApplicationException {

		log.info("MarksheetModel search Started");

		StringBuffer sql = new StringBuffer("select * from st_marksheet where 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getRollNo() != null && bean.getRollNo().length() > 0) {
				sql.append(" and roll_no like '" + bean.getRollNo() + "%'");
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}
			if (bean.getPhysics() != null && bean.getPhysics() > 0) {
				sql.append(" and physics = " + bean.getPhysics());
			}
			if (bean.getChemistry() != null && bean.getChemistry() > 0) {
				sql.append(" and chemistry = " + bean.getChemistry());
			}
			if (bean.getMaths() != null && bean.getMaths() > 0) {
				sql.append(" and maths = '" + bean.getMaths());
			}
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}

		ArrayList<MarksheetBean> list = new ArrayList<MarksheetBean>();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new MarksheetBean();
				bean.setId(rs.getLong(1));
				bean.setRollNo(rs.getString(2));
				bean.setStudentId(rs.getLong(3));
				bean.setName(rs.getString(4));
				bean.setPhysics(rs.getInt(5));
				bean.setChemistry(rs.getInt(6));
				bean.setMaths(rs.getInt(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			throw new ApplicationException("Update rollback exception " + e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("MarksheetModel search Ended");
		return list;
	}

	/**
	 * Retrieves the merit list of students sorted by total marks in descending
	 * order. Only students who passed in all subjects are included.
	 * 
	 * @param pageNo   page number for pagination
	 * @param pageSize number of records per page
	 * @return List of top MarksheetBean
	 * @throws ApplicationException if any application error occurs
	 */

	public List<MarksheetBean> getMeritList(int pageNo, int pageSize) throws ApplicationException {

		log.info("MarksheetModel getMeritList Started");
		
		ArrayList<MarksheetBean> list = new ArrayList<MarksheetBean>();
		
		StringBuffer sql = new StringBuffer(
				"select id, roll_no, name, physics, chemistry, maths, (physics + chemistry + maths) as total from st_marksheet where physics > 33 and chemistry > 33 and maths > 33 order by total desc");

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			System.out.println(sql.toString());
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				MarksheetBean bean = new MarksheetBean();
				bean.setId(rs.getLong(1));
				bean.setRollNo(rs.getString(2));
				bean.setName(rs.getString(3));
				bean.setPhysics(rs.getInt(4));
				bean.setChemistry(rs.getInt(5));
				bean.setMaths(rs.getInt(6));
				list.add(bean);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception in getting merit list of Marksheet");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("MarksheetModel getMeritList Ended");
		return list;
	}

}
