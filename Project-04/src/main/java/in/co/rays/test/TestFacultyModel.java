package in.co.rays.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.FacultyBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.FacultyModel;

/**
 * Test class for FacultyModel to perform CRUD operations.
 * 
 * @author Amit 
 * @version 1.0
 */

public class TestFacultyModel {

	public static void main(String[] args) throws Exception {

//testAdd();
testDelete();
//	testUpdate();
//testNextPk();
//testFindByPK();
//testFindByEmail();
//	testSearch();

	}
	
	/**
     * Tests add() method of FacultyModel.
     */

	public static void testAdd() throws ParseException, ApplicationException, DuplicateRecordException  {
		FacultyBean bean = new FacultyBean();
		FacultyModel model = new FacultyModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setFirstName("silla");
		bean.setLastName("rajpoot");
		bean.setDob(sdf.parse("2005-01-23"));
		bean.setGender("female");
		bean.setMoblileNo("9179016753");
		bean.setEmail("sila@gmailll.com");
		bean.setCollegeId(7);

		bean.setCousreId(1);

		bean.setSubjectid(1);

		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);

	}
	
	/**
     * Tests delete() method of FacultyModel.
     */

	public static void testDelete() throws ApplicationException  {
		FacultyBean bean = new FacultyBean();
		FacultyModel model = new FacultyModel();
		bean.setId(1);
		model.delete(bean);
	}
	
	/**
     * Tests update() method of FacultyModel.
     */

	public static void testUpdate() throws ApplicationException, DuplicateRecordException, ParseException {
		FacultyBean bean = new FacultyBean();
		FacultyModel model = new FacultyModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setFirstName("munni");
		bean.setLastName("kirar");
		bean.setDob(sdf.parse("2001-01-03"));
		bean.setGender("female");
		bean.setMoblileNo("8120891470");
		bean.setEmail("sunni"
				+ "@gmail.com");
		bean.setCollegeId(1);
//		bean.setCollegeName("jiwaji");
		bean.setCousreId(1);
//		bean.setCourseName("java corporate");
		bean.setSubjectid(1);
//		bean.setSubjectName("maths");
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		bean.setId(1);

		model.update(bean);
	}
	
	/**
     * Tests nextPk() method of FacultyModel.
     */

	private static void testNextPk() throws DatabaseException {

		FacultyModel model = new FacultyModel();

		int i = model.nextPk();

		System.out.println("nexPk is: " + i);
	}
	
	/**
     * Tests findByPk() method of FacultyModel.
     */

	public static void testFindByPK() throws ApplicationException  {
		FacultyBean bean = new FacultyBean();
		FacultyModel model = new FacultyModel();
		bean = model.findByPk(1);
		if (bean != null) {

			System.out.print("\t" + bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getMoblileNo());
			System.out.print("\t" + bean.getEmail());
			System.out.print("\t" + bean.getCollegeId());
			System.out.print("\t" + bean.getCollegeName());
			System.out.print("\t" + bean.getCousreId());
			System.out.print("\t" + bean.getCourseName());
			System.out.print("\t" + bean.getSubjectid());
			System.out.print("\t" + bean.getSubjectName());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.print("\t" + bean.getModifiedDatetime());

		} else {
			System.out.println("invalid pk");
		}
	}
	
	/**
     * Tests findByEmail() method of FacultyModel.
     */

	private static void testFindByEmail() throws ApplicationException  {
		FacultyModel model = new FacultyModel();

		FacultyBean bean = model.findByEmail("munni@gmail.com");

		if (bean != null) {

			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getEmail());
			System.out.print("\t" + bean.getMoblileNo());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		} else {
			System.out.println("login id not found");
		}

	}
	
	 /**
     * Tests search() method of FacultyModel.
     */

	private static void testSearch() throws ApplicationException {
		FacultyBean bean = new FacultyBean();
		FacultyModel model = new FacultyModel();
//bean.setMobileNo("8120891470");

//		List list = model.search(bean, 1, 3);
		List list= model.list();

		Iterator it = list.iterator();

		while (it.hasNext()) {
			bean = (FacultyBean) it.next();

			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getEmail());
			System.out.print("\t" + bean.getMoblileNo());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		}

	}
}
