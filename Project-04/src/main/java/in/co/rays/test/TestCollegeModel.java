
package in.co.rays.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.CollegeBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.CollegeModel;

/**
 * Test class for CollegeModel CRUD operations and search functionalities.
 * 
 * @author Amit
 * @version 1.0
 */
public class TestCollegeModel {

	/**
	 * Main method to call test methods individually.
	 */

	public static void main(String[] args) throws Exception {
//testAdd();
//testDelete();
		testUpdate();
//		testNextPk();
//		testFindByPk();
//		testFindByName();
//		testSearch();
	}

	/**
	 * Test method for adding a new College record.
	 */

	public static void testAdd() throws ApplicationException, DuplicateRecordException {
		CollegeBean bean = new CollegeBean();
		CollegeModel model = new CollegeModel();
		bean.setName("ASHIKA");
		bean.setAddress("LASHKAR");
		bean.setState("MAHARASTRA");
		bean.setCity("THANE");
		bean.setPhoneNo("777898989");
		bean.setCreatedBy("Admin");
		bean.setModifiedBy("Admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);
	}

	/**
	 * Test method for deleting a College record by ID.
	 */

	public static void testDelete() throws Exception {
		CollegeBean bean = new CollegeBean();
		CollegeModel model = new CollegeModel();

		bean.setId(5);
		model.delete(bean);

	}

	/**
	 * Test method for updating a College record.
	 */

	public static void testUpdate() throws ApplicationException, DuplicateRecordException {

		CollegeBean bean = new CollegeBean();
		CollegeModel model = new CollegeModel();
		bean.setName("VRG");
		bean.setAddress("GWALIOR");
		bean.setState("MADHYAPRADESH");
		bean.setCity("MURENA");
		bean.setPhoneNo("777898989");
		bean.setModifiedBy("ACCOUNTANT");
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		bean.setId(3);

		model.update(bean);

	}

	/**
	 * Test method for getting the next primary key value.
	 */

	public static void testNextPk() throws DuplicateRecordException, DatabaseException {
		CollegeModel model = new CollegeModel();

		int i = model.nextPk();

		System.out.println("Next Pk : " + i);
	}

	/**
	 * Test method for finding a College by primary key.
	 */

	private static void testFindByPk() throws ApplicationException {
		CollegeModel model = new CollegeModel();

		CollegeBean bean = model.findByPk(1);

		if (bean != null) {

			System.out.print(bean.getName());
			System.out.print("\t" + bean.getAddress());
			System.out.print("\t" + bean.getState());
			System.out.print("\t" + bean.getCity());
			System.out.print("\t" + bean.getPhoneNo());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		} else {
			System.out.println("record not found");
		}

	}

	/**
	 * Test method for finding a College by name.
	 */

	private static void testFindByName() throws ApplicationException {
		CollegeModel model = new CollegeModel();

		CollegeBean bean = model.findByName("LUCKY");

		if (bean != null) {

			System.out.print(bean.getName());
			System.out.print("\t" + bean.getAddress());
			System.out.print("\t" + bean.getState());
			System.out.print("\t" + bean.getCity());
			System.out.print("\t" + bean.getPhoneNo());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		} else {
			System.out.println("name not found");
		}

	}

	/**
	 * Test method for searching College records.
	 */

	private static void testSearch() throws ApplicationException {
		CollegeModel model = new CollegeModel();
		CollegeBean bean = new CollegeBean();
		bean.setName("LUCKY");

//	List list = model.Search(bean, 1, 3);
		List list = model.list();

		Iterator it = list.iterator();

		while (it.hasNext()) {
			bean = (CollegeBean) it.next();

			System.out.print(bean.getName());
			System.out.print("\t" + bean.getAddress());
			System.out.print("\t" + bean.getState());
			System.out.print("\t" + bean.getCity());
			System.out.print("\t" + bean.getPhoneNo());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		}
	}

}
