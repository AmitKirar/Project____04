package in.co.rays.test;

import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.MarksheetBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.MarksheetModel;

/**
 * Test class for MarksheetModel. It performs unit tests for add, update,
 * delete, findByPk, findByRollNo, search and merit list operations.
 * 
 * @author Amit
 * @version 1.0
 */

public class TestMarksheetModel {

	public static void main(String[] args) throws Exception {
//testNextPk();
//	testAdd();
//	testdelete();
//	testFindByRollNO();
//	testFindByPk();
//	testUpdate();
//	testSearch();
		testMeritList();
	}

	/**
	 * Tests nextPk() method of MarksheetModel.
	 */

	private static void testNextPk() throws DatabaseException {
		MarksheetBean bean = new MarksheetBean();
		MarksheetModel model = new MarksheetModel();
		int id = model.nextPk();
		System.out.println("nextPk is " + id);

	}

	/**
	 * Tests add() method of MarksheetModel.
	 */

	private static void testAdd() throws ApplicationException, DuplicateRecordException {
		MarksheetBean bean = new MarksheetBean();
		MarksheetModel model = new MarksheetModel();
		bean.setRollNo("BE105");
		bean.setStudentId(1);
		bean.setPhysics(1);
		bean.setChemistry(2);
		bean.setMaths(3);
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.add(bean);

	}

	/**
	 * Tests delete() method of MarksheetModel.
	 */

	private static void testdelete() throws ApplicationException {
		MarksheetBean bean = new MarksheetBean();
		MarksheetModel model = new MarksheetModel();
		bean.setId(2);
		model.delete(bean);
	}

	/**
	 * Tests findByRollNo() method of MarksheetModel.
	 */

	private static void testFindByRollNO() throws ApplicationException {

		MarksheetModel model = new MarksheetModel();
		MarksheetBean bean = model.findByRollNo("BE104");
		if (bean != null) {
			System.out.print("\t " + bean.getId());
			System.out.print("\t " + bean.getRollNo());
			System.out.print("\t " + bean.getStudentId());
			System.out.print("\t " + bean.getName());
			System.out.print("\t " + bean.getPhysics());
			System.out.print("\t " + bean.getChemistry());
			System.out.print("\t " + bean.getMaths());
			System.out.print("\t " + bean.getCreatedBy());
			System.out.print("\t " + bean.getModifiedBy());
			System.out.print("\t " + bean.getCreatedDatetime());
			System.out.print("\t " + bean.getModifiedDatetime());

		} else {
			System.out.println("invalid roll no");
		}
	}

	/**
	 * Tests findByPk() method of MarksheetModel.
	 */

	private static void testFindByPk() throws ApplicationException {
		MarksheetModel model = new MarksheetModel();
		MarksheetBean bean = model.findByPk(1);

		if (bean != null) {
			System.out.print(bean.getId());

			System.out.print("\t" + bean.getRollNo());
			System.out.print("\t" + bean.getStudentId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getPhysics());
			System.out.print("\t" + bean.getChemistry());
			System.out.print("\t" + bean.getMaths());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		} else {
			System.out.println("record not found");
		}

	}

	/**
	 * Tests update() method of MarksheetModel.
	 */

	public static void testUpdate() throws ApplicationException, DuplicateRecordException {

		MarksheetModel model = new MarksheetModel();

		MarksheetBean bean = model.findByPk(1);
		bean.setRollNo("BE105");
		bean.setStudentId(1);

		model.update(bean);
	}

	/**
	 * Tests search() method of MarksheetModel.
	 */

	public static void testSearch() throws ApplicationException {

		MarksheetBean bean = new MarksheetBean();
		// bean.setName("b");

		MarksheetModel model = new MarksheetModel();

//	List list = model.search(bean, 1, 10);
		List list = model.list();

		Iterator it = list.iterator();

		while (it.hasNext()) {
			bean = (MarksheetBean) it.next();
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getRollNo());
			System.out.print("\t" + bean.getStudentId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getPhysics());
			System.out.print("\t" + bean.getChemistry());
			System.out.print("\t" + bean.getMaths());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		}
	}

	/**
	 * Tests getMeritList() method of MarksheetModel.
	 */

	public static void testMeritList() throws ApplicationException {
		MarksheetBean bean = new MarksheetBean();

		MarksheetModel model = new MarksheetModel();
		List list = model.getMeritList(1, 10);

		Iterator it = list.iterator();

		while (it.hasNext()) {
			bean = (MarksheetBean) it.next();
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getRollNo());
			System.out.print("\t" + bean.getStudentId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getPhysics());
			System.out.print("\t" + bean.getChemistry());
			System.out.print("\t" + bean.getMaths());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		}
	}

}
