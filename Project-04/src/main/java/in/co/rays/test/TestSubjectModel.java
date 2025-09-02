package in.co.rays.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.SubjectBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.SubjectModel;

/**
 * Test class for SubjectModel. It tests CRUD operations, search and other
 * utility methods.
 * 
 * @author Amit
 * @version 1.0
 */
public class TestSubjectModel {

	public static void main(String[] args) throws Exception {
		testAdd();
//		 testDelete();
//	testUpdate();
//	testNextPk();
//			testFindByPk();
//	testSearch();
//		testFindByName();

	}

	/**
	 * Tests add() method of SubjectModel.
	 */

	private static void testAdd() throws ApplicationException, DuplicateRecordException {
		SubjectModel model = new SubjectModel();
		SubjectBean bean = new SubjectBean();

		bean.setName("evs");
		bean.setCourseId(2);

		bean.setDescription("evs");
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);
	}

	/**
	 * Tests delete() method of SubjectModel.
	 */

	private static void testDelete() throws ApplicationException {
		SubjectModel model = new SubjectModel();
		SubjectBean bean = new SubjectBean();
		bean.setId(1);
		model.delete(bean);
	}

	/**
	 * Tests update() method of SubjectModel.
	 */

	private static void testUpdate() throws Exception {
		SubjectModel model = new SubjectModel();
		SubjectBean bean = new SubjectBean();

		bean.setName("EVS");
		bean.setCourseId(4l);
		bean.setDescription("database");
		bean.setCreatedBy("root");
		bean.setModifiedBy("chetan");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		bean.setId(2);
		model.update(bean);
	}

	/**
	 * Tests nextPk() method of SubjectModel.
	 */

	private static void testNextPk() throws DatabaseException {
		SubjectModel model = new SubjectModel();

		System.out.println("Next Pk: " + model.nextPk());

	}

	/**
	 * Tests findByPk() method of SubjectModel.
	 */

	private static void testFindByPk() throws ApplicationException {
		SubjectModel model = new SubjectModel();
		SubjectBean bean = model.findByPk(1);

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getCourseId());
			System.out.print("\t" + bean.getCourseName());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		} else {
			System.out.println("record not found");
		}
	}

	/**
	 * Tests findByName() method of SubjectModel.
	 */

	private static void testFindByName() throws ApplicationException {
		SubjectModel model = new SubjectModel();
		SubjectBean bean = model.findByName("mathamatics");

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getCourseId());
			System.out.print("\t" + bean.getCourseName());
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
	 * Tests search() and list() methods of SubjectModel.
	 */

	private static void testSearch() throws ApplicationException {
		SubjectModel model = new SubjectModel();
		SubjectBean bean = new SubjectBean();
//		bean.setName("computer");

//		List list = model.search(bean, 1, 10);
		List list = model.list();

		Iterator it = list.iterator();

		while (it.hasNext()) {
			bean = (SubjectBean) it.next();

			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getCourseId());
			System.out.print("\t" + bean.getCourseName());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		}
	}

}
