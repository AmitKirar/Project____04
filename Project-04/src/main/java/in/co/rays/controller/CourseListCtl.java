package in.co.rays.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.CourseBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.model.CourseModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

/**
 * CourseListCtl handles the display, search, pagination, deletion, and
 * navigation of course records.
 * 
 * Functionality includes: - Searching courses by name, duration, or ID -
 * Paginating results - Deleting selected courses - Redirecting to course form
 * 
 * Mapped to /CourseListCtl.
 * 
 * @author Amit
 * @version 1.0
 */

@WebServlet(name = "CourseListCtl", urlPatterns = { "/ctl/CourseListCtl" })
public class CourseListCtl extends BaseCtl {

	Logger log = Logger.getLogger(CourseListCtl.class);

	/**
	 * Loads the preload data required on the course list screen.
	 *
	 * @param request the HttpServletRequest
	 */

	@Override
	protected void preload(HttpServletRequest request) {

		log.info("CourseListCtl preload Method Started");

		CourseModel courseModel = new CourseModel();
		try {
			List courseList = courseModel.list();
			request.setAttribute("courseList", courseList);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return;
		}

		log.info("CourseListCtl preload Method Ended");
	}

	/**
	 * Populates CourseBean using request parameters.
	 *
	 * @param request the HttpServletRequest
	 * @return the populated CourseBean
	 */

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.info("CourseListCtl populateBean Method Started");

		CourseBean bean = new CourseBean();

		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setId(DataUtility.getLong(request.getParameter("courseId")));
		bean.setDuration(DataUtility.getString(request.getParameter("duration")));

		log.info("CourseListCtl populateBean Method Ended");

		return bean;
	}

	/**
	 * Handles HTTP GET requests for course list with default page 1.
	 *
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.info("CourseListCtl doGet Method Started");

		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		CourseBean bean = (CourseBean) populateBean(request);
		CourseModel model = new CourseModel();

		try {
			List<CourseBean> list = model.search(bean, pageNo, pageSize);
			List<CourseBean> next = model.search(bean, pageNo + 1, pageSize);

			if (list == null || list.isEmpty()) {
				ServletUtility.setErrorMessage("No record found", request);
			}

			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.setBean(bean, request);
			request.setAttribute("nextListSize", next.size());

		} catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		}
		log.info("CourseListCtl doGet Method Ended");
		ServletUtility.forward(getView(), request, response);

	}

	/**
	 * Handles HTTP POST requests for operations like Search, Next, Previous, New,
	 * Delete, Reset, and Back.
	 *
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.info("CourseListCtl doPost Method Started");

		List list = null;
		List next = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		CourseBean bean = (CourseBean) populateBean(request);
		CourseModel model = new CourseModel();

		String op = DataUtility.getString(request.getParameter("operation"));
		String[] ids = request.getParameterValues("ids");

		try {

			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {

				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				}

			} else if (OP_NEW.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.COURSE_CTL, request, response);
				return;

			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null && ids.length > 0) {
					CourseBean deletebean = new CourseBean();
					for (String id : ids) {
						deletebean.setId(DataUtility.getInt(id));
						model.delete(deletebean);
						ServletUtility.setSuccessMessage("Course deleted successfully", request);
					}
				} else {
					ServletUtility.setErrorMessage("Select at least one record", request);
				}

			} else if (OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.COURSE_LIST_CTL, request, response);
				return;

			} else if (OP_BACK.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.COURSE_LIST_CTL, request, response);
				return;
			}

			list = model.search(bean, pageNo, pageSize);
			next = model.search(bean, pageNo + 1, pageSize);

			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);
			}

			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.setBean(bean, request);
			request.setAttribute("nextListSize", next.size());

			log.info("CourseListCtl doPost Method Ended");
			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);

			return;
		}
		log.info("CourseListCtl doPost Method Ended");
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * Returns the view page for Course List.
	 *
	 * @return path to COURSE_LIST_VIEW
	 */

	@Override
	protected String getView() {

		return ORSView.COURSE_LIST_VIEW;
	}

}
