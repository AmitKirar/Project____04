package in.co.rays.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.TimetableBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.TimetableModel;

/**
 * Test class for TimetableModel. It performs unit testing of methods like add,
 * update, delete, search, findByPk and various custom checks.
 * 
 * @author Amit
 * @version 1.0
 */
public class TesttimetableModel {

	public static void main(String[] args) throws Exception {
//	testAdd();
//	testDelete();
//	testUpdate();
//testNextPk();
//	testFindByPK();
//	testSearch();
//		testCoursetName();
//		 testSubjectName();
//		 testexamTime();
		testSemester();

	}

	/**
	 * Tests add() method of TimetableModel.
	 */

	public static void testAdd() throws ApplicationException, DuplicateRecordException, ParseException {
		TimetableBean bean = new TimetableBean();
		TimetableModel model = new TimetableModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setSemester("first");
		bean.setDescription("amit");
		bean.setExamDate(sdf.parse("2025-08-09"));
		bean.setExamTime("10 to 12");
		bean.setCourseId(2);

		bean.setSubjectId(2);

		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);
	}

	/**
	 * Tests delete() method of TimetableModel.
	 */

	public static void testDelete() throws ApplicationException {
		TimetableBean bean = new TimetableBean();
		TimetableModel model = new TimetableModel();
		bean.setId(4);
		model.delete(bean);
	}

	/**
	 * Tests update() method of TimetableModel.
	 */

	public static void testUpdate() throws ParseException, ApplicationException, DuplicateRecordException {
		TimetableBean bean = new TimetableBean();
		TimetableModel model = new TimetableModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setSemester("second");
		bean.setDescription("amit");
		bean.setExamDate(sdf.parse("2025-08-09"));
		bean.setExamTime("10 to 12");
		bean.setCourseId(1);

		bean.setSubjectId(1);

		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		bean.setId(1);

		model.update(bean);

	}

	/**
	 * Tests nextPk() method of TimetableModel.
	 */

	public static void testNextPk() throws DatabaseException {

		TimetableModel model = new TimetableModel();
		int i = model.nextPk();

		System.out.println("nexPk is: " + i);
	}

	/**
	 * Tests findByPk() method of TimetableModel.
	 */

	public static void testFindByPK() throws ApplicationException {
		TimetableBean bean = new TimetableBean();
		TimetableModel model = new TimetableModel();
		bean = model.findByPk(1);
		if (bean != null) {
			System.out.print("\t" + bean.getId());
			System.out.print("\t" + bean.getSemester());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getExamDate());
			System.out.print("\t" + bean.getExamTime());
			System.out.print("\t" + bean.getCourseId());
			System.out.print("\t" + bean.getCourseName());
			System.out.print("\t" + bean.getSubjectId());
			System.out.print("\t" + bean.getSubjectName());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.print("\t" + bean.getModifiedDatetime());

		} else {
			System.out.println("invalid record ");
		}

	}

	/**
	 * Tests checkByCourseName() method of TimetableModel.
	 */

	public static void testCoursetName() throws ApplicationException, ParseException {

		TimetableModel model = new TimetableModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		TimetableBean bean = model.checkByCourseName(1l, sdf.parse("2025-08-09 00:00:00"));

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getSemester());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getExamDate());
			System.out.print("\t" + bean.getExamTime());
			System.out.print("\t" + bean.getCourseId());
			System.out.print("\t" + bean.getCourseName());
			System.out.print("\t" + bean.getSubjectId());
			System.out.print("\t" + bean.getSubjectName());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		} else {
			System.out.println("Course name not found");

		}
	}

	/**
	 * Tests checkBySubjectName() method of TimetableModel.
	 */

	public static void testSubjectName() throws ApplicationException, ParseException {

		TimetableModel model = new TimetableModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		TimetableBean bean = model.checkBySubjectName(2l, 2l, sdf.parse("2025-08-09 00:00:00"));

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getSemester());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getExamDate());
			System.out.print("\t" + bean.getExamTime());
			System.out.print("\t" + bean.getCourseId());
			System.out.print("\t" + bean.getCourseName());
			System.out.print("\t" + bean.getSubjectId());
			System.out.print("\t" + bean.getSubjectName());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		} else {
			System.out.println("subject  name not found");

		}
	}

	/**
	 * Tests checkBySemester() method of TimetableModel.
	 */

	public static void testSemester() throws ApplicationException, ParseException {

		TimetableModel model = new TimetableModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		TimetableBean bean = model.checkBySemester(2l, 2l, "first", sdf.parse("2025-08-09 00:00:00"));

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getSemester());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getExamDate());
			System.out.print("\t" + bean.getExamTime());
			System.out.print("\t" + bean.getCourseId());
			System.out.print("\t" + bean.getCourseName());
			System.out.print("\t" + bean.getSubjectId());
			System.out.print("\t" + bean.getSubjectName());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		} else {
			System.out.println("semester name not found");

		}
	}

	/**
	 * Tests checkByExamTime() method of TimetableModel.
	 */

	public static void testexamTime() throws ApplicationException, ParseException {

		TimetableModel model = new TimetableModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		TimetableBean bean = model.checkByExamTime(2l, 2l, "first", (sdf.parse("2025-08-09 00:00:00")), "10 to 12",
				"amit");

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getSemester());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getExamDate());
			System.out.print("\t" + bean.getExamTime());
			System.out.print("\t" + bean.getCourseId());
			System.out.print("\t" + bean.getCourseName());
			System.out.print("\t" + bean.getSubjectId());
			System.out.print("\t" + bean.getSubjectName());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		} else {
			System.out.println("exam time  not found");

		}
	}

	/**
	 * Tests list() method of TimetableModel.
	 */

	public static void testSearch() throws ApplicationException {
		TimetableBean bean = new TimetableBean();
		TimetableModel model = new TimetableModel();
//		bean.setSemester("fourth");

//		List list = model.search(bean, 1, 2);
		List list = model.list();
		Iterator it = list.iterator();

		while (it.hasNext()) {
			bean = (TimetableBean) it.next();

			System.out.print("\t" + bean.getId());
			System.out.print("\t" + bean.getSemester());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getExamDate());
			System.out.print("\t" + bean.getExamTime());
			System.out.print("\t" + bean.getCourseId());
			System.out.print("\t" + bean.getCourseName());
			System.out.print("\t" + bean.getSubjectId());
			System.out.print("\t" + bean.getSubjectName());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		}
	}

}
