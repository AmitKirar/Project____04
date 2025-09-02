package in.co.rays.test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.UserBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.UserModel;

/**
 * Test class for UserModel. It contains unit tests for CRUD operations and
 * authentication-related methods.
 * 
 * @author Amit
 * @version 1.0
 */

public class TestUserModel {

	public static void main(String[] args) throws Exception {
		testNextPk();
//	testAdd();
//	testDelete();
//		testFindByLogin();
//	 testFindByPk();
//		testUpdate();
//		testSearch();
//		testAuthenticate();

	}

	/**
	 * Test nextPk() method of UserModel.
	 */

	private static void testNextPk() throws DatabaseException {

		UserModel model = new UserModel();
		int i = model.nextPk();
		System.out.println("next pk is " + i);

	}

	/**
	 * Test add() method of UserModel.
	 */

	private static void testAdd()
			throws ParseException, SQLException, DuplicateRecordException, DatabaseException, ApplicationException {
		UserBean bean = new UserBean();
		UserModel model = new UserModel();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setFirstname("lalu");
		bean.setLastname("kirar");
		bean.setLogin("Akbar@gmail.com");
		bean.setPassword("12233");
		bean.setDob(sdf.parse("2002-01-03"));
		bean.setMobileNo("8120891430");
		bean.setRoleId(2);
		bean.setGender("male");
		bean.setCreatedBy("lalu");
		bean.setModifiedBy("lalu");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);
	}

	/**
	 * Test delete() method of UserModel.
	 */

	private static void TestDelete() throws SQLException, ApplicationException {
		UserBean bean = new UserBean();
		UserModel model = new UserModel();
		bean.setId(2);
		model.delete(bean);

	}

	/**
	 * Test findByLogin() method of UserModel.
	 */

	private static void testFindByLogin() throws SQLException, ApplicationException {

		UserModel model = new UserModel();
		UserBean bean = model.findByLogin("Akbar@gmail.com");

		if (bean != null) {
			System.out.print("\t " + bean.getId());
			System.out.print("\t " + bean.getFirstname());
			System.out.print("\t " + bean.getLastname());
			System.out.print("\t " + bean.getLogin());
			System.out.print("\t " + bean.getPassword());
			System.out.print("\t " + bean.getDob());
			System.out.print("\t " + bean.getMobileNo());
			System.out.println("\t " + bean.getRoleId());
			System.out.print("\t " + bean.getGender());
			System.out.print("\t " + bean.getCreatedBy());
			System.out.print("\t " + bean.getModifiedBy());
			System.out.print("\t " + bean.getCreatedDatetime());
			System.out.print("\t " + bean.getModifiedDatetime());
		} else {
			System.out.println("invalid loginid");
		}

	}

	/**
	 * Test findByPk() method of UserModel.
	 */

	private static void testFindByPk() throws SQLException, ApplicationException {

		UserModel model = new UserModel();
		UserBean bean = model.findByPk(1);

		if (bean != null) {
			System.out.print("\t " + bean.getId());
			System.out.print("\t " + bean.getFirstname());
			System.out.print("\t " + bean.getLastname());
			System.out.print("\t " + bean.getLogin());
			System.out.print("\t " + bean.getPassword());
			System.out.print("\t " + bean.getDob());
			System.out.print("\t " + bean.getMobileNo());
			System.out.print("\t " + bean.getRoleId());

			System.out.print("\t " + bean.getGender());
			System.out.print("\t " + bean.getCreatedBy());
			System.out.print("\t " + bean.getModifiedBy());
			System.out.print("\t " + bean.getCreatedDatetime());
			System.out.print("\t " + bean.getModifiedDatetime());
		} else {
			System.out.println("invalid Pk");
		}

	}

	/**
	 * Test update() method of UserModel.
	 */

	private static void testUpdate()
			throws ParseException, SQLException, DuplicateRecordException, ApplicationException {
		UserBean bean = new UserBean();
		UserModel model = new UserModel();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		bean.setId(1);
		bean.setFirstname("amit");
		bean.setLastname("kirar");
		bean.setLogin("amit@gmail.com");
		bean.setPassword("12233");
		bean.setDob(sdf.parse("2002-01-03"));
		bean.setMobileNo("8120909090");
		bean.setRoleId(3);
		bean.setGender("male");
		bean.setCreatedBy("amt");
		bean.setModifiedBy("amt");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.update(bean);

	}

	/**
	 * Test authenticate() method of UserModel.
	 */

	private static void testAuthenticate() throws ApplicationException {

		UserModel model = new UserModel();
		UserBean bean = model.authenticate("amit@gmail.com", "12233");

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstname());
			System.out.print("\t" + bean.getLastname());
			System.out.print("\t" + bean.getLogin());
			System.out.print("\t" + bean.getPassword());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getRoleId());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		} else {
			System.out.println("login & password invalid");
		}
	}

	/**
	 * Test search() or list() methods of UserModel.
	 */

	private static void testSearch() throws Exception {
		UserBean bean = new UserBean();
		UserModel model = new UserModel();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//  bean.setFirstname("amit");
//		bean.setDob(sdf.parse("2002-01-03"));
//		bean.setGender("male");
//		bean.setLogin("amit@gmail.com");
//		bean.setPassword("12233");
//		bean.setMobileNo("8120909090");
//		bean.setCreatedBy("amit");
//		bean.setModifiedBy("amit");
		bean.setRoleId(3);
//		List list = model.Search(bean, 1, 3);
		List list = model.list();

		Iterator it = list.iterator();

		while (it.hasNext()) {
			bean = (UserBean) it.next();

			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstname());
			System.out.print("\t" + bean.getLastname());
			System.out.print("\t" + bean.getLogin());
			System.out.print("\t" + bean.getPassword());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getRoleId());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		}

	}

}