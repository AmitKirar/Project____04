
package in.co.rays.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.StudentBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.StudentModel;

/**
 * Test class for StudentModel. It performs unit testing for add, update,
 * delete, findByPk, findByEmail, and search methods.
 * 
 * @author Amit
 * @version 1.0
 */

public class TestStudentModel {

	public static void main(String[] args) throws Exception {
//	testAdd();
//	testDelete();
//	testUpdate();
//	 testNextPk();
//	testFindByPk();
//testFindByEmail();
		testSearch();
	}

	/**
	 * Tests add() method of StudentModel.
	 */

	public static void testAdd() throws ParseException, DuplicateRecordException, ApplicationException {
		StudentBean bean = new StudentBean();
		StudentModel model = new StudentModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setFirstName("priyanka");
		bean.setLastName("rajpoot");
		bean.setDob(sdf.parse("2005-01-09"));
		bean.setGender("female");
		bean.setMobileNo("9304028129");
		bean.setEmail("priyanka@gmail.com");
		bean.setCollegeId(1);

		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);
	}

	/**
	 * Tests delete() method of StudentModel.
	 */

	private static void testDelete() throws ApplicationException {
		StudentModel model = new StudentModel();
		StudentBean bean = new StudentBean();
		bean.setId(3);
		model.delete(bean);

	}

	/**
	 * Tests update() method of StudentModel.
	 */

	private static void testUpdate() throws ApplicationException, DuplicateRecordException, ParseException {
		StudentModel model = new StudentModel();
		StudentBean bean = new StudentBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setId(1);
		bean.setFirstName("sumit");
		bean.setLastName("Kirar");
		bean.setDob(sdf.parse("2002-02-15"));
		bean.setGender("Male");
		bean.setMobileNo("8120891470");
		bean.setEmail("sumit@gmail.com");
		bean.setCollegeId(7);
		bean.setCollegeName("jiwaji");
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.update(bean);

	}

	/**
	 * Tests nextPk() method of StudentModel.
	 */

	private static void testNextPk() throws DatabaseException {

		StudentModel model = new StudentModel();

		int i = model.nextPk();

		System.out.println("nexPk is: " + i);

	}

	/**
	 * Tests findByPk() method of StudentModel.
	 */

	private static void testFindByPk() throws ApplicationException {
		StudentModel model = new StudentModel();
		StudentBean bean = new StudentBean();
		bean = model.findByPk(1);

		if (bean != null) {

			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getEmail());
			System.out.print("\t" + bean.getCollegeId());
			System.out.print("\t" + bean.getCollegeName());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		} else {
			System.out.println("invalid pk");
		}
	}

	/**
	 * Tests findByEmail() method of StudentModel.
	 */

	private static void testFindByEmail() throws Exception {
		StudentModel model = new StudentModel();
		StudentBean bean = model.findByEmail("amit@gmail.com");

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getEmail());
			System.out.print("\t" + bean.getCollegeId());
			System.out.print("\t" + bean.getCollegeName());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		} else {
			System.out.println("Student email not found");
		}

	}

	/**
	 * Tests search() and list() methods of StudentModel.
	 */

	private static void testSearch() throws Exception {
		StudentModel model = new StudentModel();
		StudentBean bean = new StudentBean();
//	   bean.setFirstName("Amit");
		bean.setEmail("khushi@gmail.com");

//	List list = model.search(bean, 1, 3);

		List list = model.list();

		Iterator it = list.iterator();

		while (it.hasNext()) {
			bean = (StudentBean) it.next();

			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getEmail());
			System.out.print("\t" + bean.getCollegeId());
			System.out.print("\t" + bean.getCollegeName());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		}

	}
}
