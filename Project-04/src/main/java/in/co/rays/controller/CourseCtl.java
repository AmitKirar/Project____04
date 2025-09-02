package in.co.rays.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.CourseBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.CourseModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

/**
 * CourseCtl handles Course form operations like Add, Update, Reset, and Cancel.
 * It performs input validation, populates CourseBean, and invokes the model
 * layer for persistence.
 * 
 * Mapped to /CourseCtl.
 * 
 * @author Amit
 * @version 1.0
 */

@WebServlet(name = "CourseCtl", urlPatterns = { "/ctl/CourseCtl" })
public class CourseCtl extends BaseCtl {
	
	Logger log = Logger.getLogger(CourseCtl.class);


	/**
	 * Validates input fields from Course form.
	 * 
	 * @param request HttpServletRequest
	 * @return true if all fields are valid, otherwise false
	 */

	@Override
	protected boolean validate(HttpServletRequest request) {
		
		log.info("CourseCtl validate Method Started");


		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("name"))) {
			request.setAttribute("name", "Invalid Name");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("duration"))) {
			request.setAttribute("duration", PropertyReader.getValue("error.require", "Duration"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", PropertyReader.getValue("error.require", "Description"));
			pass = false;
		}

		log.info("CourseCtl validate Method Ended");
		
		return pass;
	}

	/**
	 * Populates a CourseBean object from request parameters.
	 * 
	 * @param request HttpServletRequest
	 * @return populated CourseBean
	 */

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		
		log.info("CourseCtl populateBean Method Started");

		CourseBean bean = new CourseBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDuration(DataUtility.getString(request.getParameter("duration")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));

		populateDTO(bean, request);
		
		log.info("CourseCtl populateBean Method Ended");

		return bean;
	}

	/**
	 * Handles GET request for displaying Course form. If id is provided, it
	 * pre-fills the form for editing.
	 * 
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		log.info("CourseCtl doGet Method Started");

		long id = DataUtility.getLong(request.getParameter("id"));

		CourseModel model = new CourseModel();

		if (id > 0) {
			try {
				CourseBean bean = model.findByPk(id);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				e.printStackTrace();
				return;
			}
		}
		
		log.info("CourseCtl doGet Method Ended");

		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * Handles POST request for Course form: Save, Update, Cancel, or Reset.
	 * 
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		log.info("CourseCtl doPost Method Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		CourseModel model = new CourseModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op)) {
			CourseBean bean = (CourseBean) populateBean(request);
			try {
				long pk = model.add(bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Course added successfully", request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Course already exists", request);
			}
		} else if (OP_UPDATE.equalsIgnoreCase(op)) {
			CourseBean bean = (CourseBean) populateBean(request);
			try {
				if (id > 0) {
					model.update(bean);
				}
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Course updated successfully", request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Course already exists", request);
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.COURSE_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.COURSE_CTL, request, response);
			return;
		}
		
		log.info("CourseCtl doPost Method Ended");
		
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * Returns the view path for the course form.
	 * 
	 * @return view path constant
	 */

	@Override
	protected String getView() {

		return ORSView.COURSE_VIEW;
	}

}
