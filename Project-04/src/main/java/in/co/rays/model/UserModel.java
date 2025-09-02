package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.bean.UserBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.exception.RecordNotFoundException;
import in.co.rays.util.EmailBuilder;
import in.co.rays.util.EmailMessage;
import in.co.rays.util.EmailUtility;
import in.co.rays.util.JDBCDataSource;

/**
 * UserModel.java
 * 
 * This class provides database operations related to User entities. It includes
 * functionalities such as registration, authentication, password management,
 * search, and CRUD operations.
 * 
 * @author Amit
 * @version 1.0
 */

public class UserModel {

	public static Logger log = Logger.getLogger(UserModel.class);

	/**
	 * Returns the next primary key value.
	 *
	 * @return next primary key
	 * @throws DatabaseException if a database error occurs
	 */

	public Integer nextPk() throws DatabaseException {

		log.info("UserModel nextPk started");

		int pk = 0;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_user");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}

		} catch (Exception e) {
			throw new DatabaseException("Exception : Exception in getting PK" + e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("UserModel nextPk ended");
		return pk + 1;

	}

	/**
	 * Adds a new user to the database.
	 *
	 * @param bean the UserBean object
	 * @return primary key of the new user
	 * @throws ApplicationException     if a generic application error occurs
	 * @throws DuplicateRecordException if login ID already exists
	 */

	public long add(UserBean bean) throws ApplicationException, DuplicateRecordException {

		log.info("UserModel add Started");

		int pk = 0;
		UserBean existbean = findByLogin(bean.getLogin());
		if (existbean != null) {
			throw new DuplicateRecordException("emailId already exist");
		}
		Connection conn = null;

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into st_user values(?,?,?,?,?,?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getFirstname());
			pstmt.setString(3, bean.getLastname());
			pstmt.setString(4, bean.getLogin());
			pstmt.setString(5, bean.getPassword());
			pstmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(7, bean.getMobileNo());
			pstmt.setLong(8, bean.getRoleId());
			pstmt.setString(9, bean.getGender());
			pstmt.setString(10, bean.getCreatedBy());
			pstmt.setString(11, bean.getModifiedBy());
			pstmt.setTimestamp(12, bean.getCreatedDatetime());
			pstmt.setTimestamp(13, bean.getModifiedDatetime());

			int i = pstmt.executeUpdate();

			conn.commit();

			System.out.println("data inserted ...." + i);

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e2) {
				throw new ApplicationException("Exception : add rollback exception " + e2.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add User" + e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("UserModel add Ended");
		return pk;
	}

	/**
	 * Deletes a user from the database.
	 *
	 * @param bean the UserBean to be deleted
	 * @throws SQLException         if a database access error occurs
	 * @throws ApplicationException if a generic application error occurs
	 */

	public void delete(UserBean bean) throws SQLException, ApplicationException {

		log.info("UserModel delete Started");

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from st_user where id=?");
			pstmt.setLong(1, bean.getId());
			int i = pstmt.executeUpdate();

			conn.commit();
			System.out.println("data deleted successfully " + i);
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e2) {
				throw new ApplicationException("Exception : add rollback exception " + e2.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete User" + e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("UserModel delete Ended");
	}

	/**
	 * Finds a user by login.
	 *
	 * @param login the login ID
	 * @return UserBean object if found, otherwise null
	 * @throws ApplicationException if a database error occurs
	 */

	public UserBean findByLogin(String login) throws ApplicationException {

		log.info("UserModel findByLogin Started");

		Connection conn = null;
		UserBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select * from st_user where login=?");
			pstmt.setString(1, login);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstname(rs.getString(2));
				bean.setLastname(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getLong(8));
				bean.setGender(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));
			}
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting User by login " + e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("UserModel findByLogin Ended");
		return bean;

	}

	/**
	 * Finds a user by primary key.
	 *
	 * @param Pk the user ID
	 * @return UserBean object
	 * @throws ApplicationException if a database error occurs
	 */

	public UserBean findByPk(long Pk) throws ApplicationException {

		log.info("UserModel findByPk Started");

		Connection conn = null;
		UserBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select * from st_user where id=?");
			pstmt.setLong(1, Pk);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstname(rs.getString(2));
				bean.setLastname(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getLong(8));
				bean.setGender(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));

				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));
			}
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting User by PK" + e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("UserModel findByPk Ended");
		return bean;

	}

	/**
	 * Updates the given user in the database.
	 *
	 * @param bean the UserBean to update
	 * @throws DuplicateRecordException if login ID already exists for another user
	 * @throws ApplicationException     if a database error occurs
	 */

	public void update(UserBean bean) throws DuplicateRecordException, ApplicationException {

		log.info("UserModel update Started");

		Connection conn = null;
		UserBean existbean = findByLogin(bean.getLogin());

		if (existbean != null && bean.getId() != existbean.getId()) {
			throw new DuplicateRecordException("login id already exist");
		}

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_user set first_name=?,last_name=?,login=?,password=?,dob=?,mobile_no=?,role_id=?,gender=?,created_by=?,modified_by=?,created_datetime=?,modified_datetime=? where id=?");

			pstmt.setString(1, bean.getFirstname());
			pstmt.setString(2, bean.getLastname());
			pstmt.setString(3, bean.getLogin());
			pstmt.setString(4, bean.getPassword());
			pstmt.setDate(5, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(6, bean.getMobileNo());
			pstmt.setLong(7, bean.getRoleId());
			pstmt.setString(8, bean.getGender());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDatetime());
			pstmt.setTimestamp(12, bean.getModifiedDatetime());
			pstmt.setLong(13, bean.getId());

			int i = pstmt.executeUpdate();

			conn.commit();

			System.out.println("data updated ...." + i);

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e2) {
				throw new ApplicationException("Exception : Delete rollback exception " + e2.getMessage());
			}
			throw new ApplicationException("Exception in updating User" + e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("UserModel update Ended");
	}

	/**
	 * Authenticates a user with login ID and password.
	 *
	 * @param loginId  the login ID
	 * @param password the password
	 * @return UserBean if credentials are valid, else null
	 * @throws ApplicationException if a database error occurs
	 */

	public UserBean authenticate(String loginId, String password) throws ApplicationException {

		log.info("UserModel authenticate Started");

		Connection conn = null;
		UserBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select * from st_user where login = ? and password = ?");

			pstmt.setString(1, loginId);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstname(rs.getString(2));
				bean.setLastname(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getLong(8));
				bean.setGender(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));
			}
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in get roles " + e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("UserModel authenticate Ended");
		return bean;
	}

	/**
	 * Sends the user's password to their email if they forget it.
	 *
	 * @param login the login ID (email)
	 * @return true if email sent successfully
	 * @throws RecordNotFoundException if login does not exist
	 * @throws ApplicationException    if email sending fails
	 */

	public boolean forgetPassword(String login) throws RecordNotFoundException, ApplicationException {

		log.info("UserModel forgetPassword Started");

		UserBean userData = findByLogin(login);
		boolean flag = false;

		if (userData == null) {
			throw new RecordNotFoundException("Email ID does not exists..!!");
		}

		try {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("login", userData.getLogin());
			map.put("password", userData.getPassword());
			map.put("firstName", userData.getFirstname());
			map.put("lastName", userData.getLastname());

			String message = EmailBuilder.getForgetPasswordMessage(map);

			EmailMessage msg = new EmailMessage();
			msg.setTo(login);
			msg.setSubject("ORSProject-04 Password Reset");
			msg.setMessage(message);
			msg.setMessageType(EmailMessage.HTML_MSG);

			EmailUtility.sendMail(msg);
			flag = true;
		} catch (Exception e) {
			throw new ApplicationException("Please check your internet connection..!!");
		}
		log.info("UserModel forgetPassword Ended");
		return flag;
	}

	/**
	 * Changes the password of the user.
	 *
	 * @param id          user ID
	 * @param oldPassword current password
	 * @param newPassword new password to set
	 * @return true if password changed successfully
	 * @throws RecordNotFoundException if old password is invalid
	 * @throws ApplicationException    if database or email error occurs
	 */

	public boolean changePassword(Long id, String oldPassword, String newPassword)
			throws RecordNotFoundException, ApplicationException {

		log.info("UserModel changePassword Started");

		boolean flag = false;

		UserBean beanExist = findByPk(id);

		if (beanExist != null && beanExist.getPassword().equals(oldPassword)) {
			beanExist.setPassword(newPassword);
			try {
				update(beanExist);
				flag = true;
			} catch (DuplicateRecordException e) {
				throw new ApplicationException("Login Id already exist");
			}
		} else {
			throw new RecordNotFoundException("Old Password is Invalid");
		}

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", beanExist.getLogin());
		map.put("password", beanExist.getPassword());
		map.put("firstName", beanExist.getFirstname());
		map.put("lastName", beanExist.getLastname());

		String message = EmailBuilder.getChangePasswordMessage(map);

		EmailMessage msg = new EmailMessage();
		msg.setTo(beanExist.getLogin());
		msg.setSubject("ORSProject-04 Password has been changed Successfully.");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);
		log.info("UserModel changePassword Ended");
		return flag;
	}

	/**
	 * Registers a new user and sends confirmation email.
	 *
	 * @param bean the UserBean to register
	 * @return primary key of the new user
	 * @throws DuplicateRecordException if login ID already exists
	 * @throws ApplicationException     if database or email error occurs
	 */

	public long registerUser(UserBean bean) throws DuplicateRecordException, ApplicationException {

		log.info("UserModel registerUser Started");

		long pk = add(bean);

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", bean.getLogin());
		map.put("password", bean.getPassword());

		String message = EmailBuilder.getUserRegistrationMessage(map);

		EmailMessage msg = new EmailMessage();

		msg.setTo(bean.getLogin());
		msg.setSubject("Registration is successful for ORSProject-04");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);
		log.info("UserModel registerUser Ended");
		return pk;
	}

	/**
	 * Returns a list of all users.
	 *
	 * @return list of all users
	 * @throws Exception if a database error occurs
	 */

	public List list() throws Exception {
		return search(null, 0, 0);
	}

	/**
	 * Searches users based on criteria in UserBean with pagination.
	 *
	 * @param bean     UserBean containing search criteria
	 * @param pageNo   current page number
	 * @param pageSize number of records per page
	 * @return list of matched users
	 * @throws Exception if a database error occurs
	 */

	public List search(UserBean bean, int pageNo, int pageSize) throws Exception {

		log.info("UserModel search Started");

		StringBuffer sql = new StringBuffer("select * from st_user where 1=1");

		if (bean != null) {
			if (bean.getFirstname() != null && bean.getFirstname().length() > 0) {
				sql.append(" and first_name like '" + bean.getFirstname() + "%'");
			}

			if (bean.getDob() != null && bean.getDob().getTime() > 0) {
				sql.append(" and dob like '" + new java.sql.Date(bean.getDob().getTime()) + "%'");
			}

			if (bean.getRoleId() > 0) {
				sql.append(" and role_id = " + bean.getRoleId());
			}
			if (bean.getGender() != null && bean.getGender().length() > 0) {
				sql.append(" and gender like '" + bean.getGender() + "%'");
			}
			if (bean.getLogin() != null && bean.getLogin().length() > 0) {
				sql.append(" and login like '" + bean.getLogin() + "%'");
			}
			if (bean.getPassword() != null && bean.getPassword().length() > 0) {
				sql.append(" and password like '" + bean.getPassword() + "%'");
			}
			if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {
				sql.append(" and mobile_no like '" + bean.getMobileNo() + "%'");
			}
			if (bean.getCreatedBy() != null && bean.getCreatedBy().length() > 0) {
				sql.append(" and created_by like '" + bean.getCreatedBy() + "%'");
			}
			if (bean.getModifiedBy() != null && bean.getModifiedBy().length() > 0) {
				sql.append(" and modified_by like '" + bean.getModifiedBy() + "%'");
			}

		}
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}

		System.out.println("sql..." + sql.toString());

		Connection conn = null;
		List list = new ArrayList();

		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new UserBean();

				bean.setId(rs.getLong(1));
				bean.setFirstname(rs.getString(2));
				bean.setLastname(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getLong(8));
				bean.setGender(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));

				list.add(bean);

			}

		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in search user + e");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("UserModel search Ended");
		return list;
	}
}