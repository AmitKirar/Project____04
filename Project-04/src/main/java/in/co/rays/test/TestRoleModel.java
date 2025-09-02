
package in.co.rays.test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.RoleBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.RoleModel;

/**
 * Test class for RoleModel. It performs unit testing for add, update, delete,
 * findByPk, findByName, and search methods.
 * 
 * @author Amit
 * @version 1.0
 */

public class TestRoleModel {

	public static void main(String[] args) throws Exception {
//	nextPk();
//	testAdd();
//	testDelete();
//	testUpdate();
//	testSearch();
//	testFindByPK();
		testfindByName();

	}

	/**
	 * Tests nextPk() method of RoleModel.
	 */

	private static void nextPk() throws DatabaseException {
		RoleModel model = new RoleModel();
		int id = model.nextPk();
		System.out.println("next pk: " + id);

	}

	/**
	 * Tests add() method of RoleModel.
	 */

	private static void testAdd() throws ApplicationException, DuplicateRecordException {

		RoleModel model = new RoleModel();

		RoleBean bean = new RoleBean();
		bean.setName("kanak");
		bean.setDescription("faculty has full access of application");
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);

	}

	/**
	 * Tests delete() method of RoleModel.
	 */

	public static void testDelete() throws ApplicationException {
		RoleBean bean = new RoleBean();
		RoleModel model = new RoleModel();
		bean.setId(6);
		model.delete(bean);

	}

	/**
	 * Tests update() method of RoleModel.
	 */

	public static void testUpdate() throws ApplicationException, DuplicateRecordException {
		RoleBean bean = new RoleBean();
		RoleModel model = new RoleModel();

		bean.setId(6);
		bean.setName("knk");
		bean.setDescription(" student has full access of application");
		bean.setCreatedBy("amit");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.update(bean);

	}

	/**
	 * Tests search() and list() methods of RoleModel.
	 */

	private static void testSearch() throws ApplicationException {

		RoleModel model = new RoleModel();
		RoleBean bean = new RoleBean();
//		bean.setName("admin");
//		bean.setId(2);
//		bean.setCreatedBy("root");
//		bean.setModifiedBy("root");
//		bean.setDescription("admin has full access of application");
//		List list = model.search(bean, 1, 4);

		List list = model.list();

		Iterator it = list.iterator();
		while (it.hasNext()) {
			bean = (RoleBean) it.next();
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		}

	}

	/**
	 * Tests findByPk() method of RoleModel.
	 */

	public static void testFindByPK() throws Exception {
		RoleBean bean = new RoleBean();
		RoleModel model = new RoleModel();
		bean = model.findByPk(2);

		if (bean != null) {

			System.out.print("\t" + bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.print("\t" + bean.getModifiedDatetime());
		} else {
			System.out.println("invalid pk");
		}

	}

	/**
	 * Tests findByName() method of RoleModel.
	 */

	private static void testfindByName() throws Exception {

		RoleModel model = new RoleModel();

		RoleBean bean = model.findByName("admin");

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		} else {
			System.out.println("name not found");
		}
	}
}
