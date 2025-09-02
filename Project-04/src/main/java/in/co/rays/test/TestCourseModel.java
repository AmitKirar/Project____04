package in.co.rays.test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import in.co.rays.bean.CourseBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.CourseModel;

/**
 * Test class for CourseModel to perform CRUD operations and search functionality.
 * 
 * @author Amit 
 * @version 1.0
 */
public class TestCourseModel {
	
	/**
     * Main method to test individual methods of CourseModel.
     */
	
	public static void main(String[] args) throws Exception {
//       testAdd();
//	testUpdate();
//		 testDelete();
//		 testFindByPk();
//			 testFindByName();
	testSearch();
// testNextPk();
	}
	

    /**
     * Tests the add() method of CourseModel.
     */

	private static void testAdd() throws ApplicationException, DuplicateRecordException  {

		CourseBean bean = new CourseBean();
		bean.setName("B.tech");
		bean.setDuration("4 year");
		bean.setDescription(" engneering course");
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		CourseModel model = new CourseModel();

		model.add(bean);

	}
	
	/**
     * Tests the update() method of CourseModel.
     */

	private static void testUpdate() throws ApplicationException, DuplicateRecordException {

		CourseModel model = new CourseModel();


		CourseBean bean = new CourseBean();
		bean.setId(1);
		bean.setName("BE");
		bean.setDuration("4 year");
		bean.setDescription("BE");
		bean.setCreatedBy("lucky");
		bean.setModifiedBy("lucky");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.update(bean);
	}
	
	/**
     * Tests the delete() method of CourseModel.
     */

	public static void testDelete() throws ApplicationException {
		CourseModel model = new CourseModel();
		CourseBean bean = new CourseBean();
		bean.setId(2);
		model.delete(bean);
		
		
	}
	
	 /**
     * Tests the nextPk() method of CourseModel.
     */

	private static void testNextPk() throws DatabaseException  {
		CourseModel model = new CourseModel();
		int i = model.nextPk();
		System.out.println("next pk is " + i);
	}
	
	/**
     * Tests the findByPk() method of CourseModel.
     */

	public static void testFindByPk() throws ApplicationException  {

		CourseModel model = new CourseModel();

		CourseBean bean = model.findByPk(1);

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getDuration());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		} else {
			System.out.println("id not found");
		}
	}
	
	 /**
     * Tests the findByName() method of CourseModel.
     */

	private static void testFindByName() throws ApplicationException {

		CourseModel model = new CourseModel();

		CourseBean bean = model.findByName("BE");

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getDuration());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		} else {
			System.out.println("name not found");
		}
	}
	
	/**
     * Tests the search() or list() method of CourseModel.
     */

	private static void testSearch() throws ApplicationException   {

		CourseBean bean = new CourseBean();
//			bean.setName("b");

		CourseModel model = new CourseModel();

//			List list = model.search(bean, 1, 10);
		List list = model.list();

		Iterator it = list.iterator();

		while (it.hasNext()) {
			bean = (CourseBean) it.next();
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getDuration());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		}
	}
}
