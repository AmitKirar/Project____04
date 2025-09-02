package in.co.rays.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.CollegeBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.CollegeModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

/**
 * CollegeCtl handles Add and Update operations for the College entity. It
 * performs input validation, data population from request, and interaction with
 * the CollegeModel for persistence logic.
 * 
 * This controller maps to URL `/CollegeCtl`.
 * 
 * @author Amit
 * @version 1.0
 */

@WebServlet(name = "CollegeCtl", urlPatterns = { "/ctl/CollegeCtl" })
public class CollegeCtl extends BaseCtl {

	Logger log = Logger.getLogger(CollegeCtl.class);

	/**
	 * Validates input fields for the College form.
	 * 
	 * @param request the HttpServletRequest containing form parameters
	 * @return true if validation passes, false otherwise
	 */

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.info("CollegeCtl validate Method Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("name"))) {
			request.setAttribute("name", "Invalid Name");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address", PropertyReader.getValue("error.require", "Address"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("state"))) {
			request.setAttribute("state", PropertyReader.getValue("error.require", "State"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("city"))) {
			request.setAttribute("city", PropertyReader.getValue("error.require", "City"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("phoneNo"))) {
			request.setAttribute("phoneNo", PropertyReader.getValue("error.require", "Phone No"));
			pass = false;
		} else if (!DataValidator.isPhoneLength(request.getParameter("phoneNo"))) {
			request.setAttribute("phoneNo", "Phone No must have 10 digits");
			pass = false;
		} else if (!DataValidator.isPhoneNo(request.getParameter("phoneNo"))) {
			request.setAttribute("phoneNo", "Invalid Phone No");
			pass = false;
		}
		log.info("CollegeCtl validate Method Ended");
		return pass;
	}

	/**
	 * Populates CollegeBean from HTTP request parameters.
	 * 
	 * @param request the HttpServletRequest containing form inputs
	 * @return populated CollegeBean
	 */

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.info("CollegeCtl populateBean Method Started");

		CollegeBean bean = new CollegeBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		bean.setState(DataUtility.getString(request.getParameter("state")));
		bean.setCity(DataUtility.getString(request.getParameter("city")));
		bean.setPhoneNo(DataUtility.getString(request.getParameter("phoneNo")));
		populateDTO(bean, request);

		log.info("CollegeCtl populateBean Method Ended");

		return bean;
	}

	/**
	 * Handles HTTP GET requests. Used to load CollegeBean for update by ID.
	 * 
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.info("CollegeCtl doGet Method Started");

		Long id = DataUtility.getLong(request.getParameter("id"));
		CollegeModel model = new CollegeModel();

		if (id > 0) {
			CollegeBean bean;

			try {
				bean = model.findByPk(id);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				log.error(e);
				return;
			}
		}

		log.info("CollegeCtl doGet Method Ended");

		ServletUtility.forward(getView(), request, response);

	}

	/**
	 * Handles HTTP POST requests. Performs Save, Update, Reset, and Cancel
	 * operations.
	 * 
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.info("CollegeCtl doPost Method Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		CollegeModel model = new CollegeModel();
		Long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SAVE.equalsIgnoreCase(op)) {
			CollegeBean bean = (CollegeBean) populateBean(request);
			try {
				long pk = model.add(bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Data is successfully saved", request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				log.error(e);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("college name already exist", request);

			}

		} else if (OP_UPDATE.equalsIgnoreCase(op)) {
			CollegeBean bean = (CollegeBean) populateBean(request);
			try {
				if (id > 0) {
					model.update(bean);
				}
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Data is successfully Updated", request);
			} catch (ApplicationException e) {

				log.error(e);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("college name already exist", request);

			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.COLLEGE_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.COLLEGE_CTL, request, response);
			return;
		}

		log.info("CollegeCtl doPost Method Ended");

		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * Returns the view page of College form.
	 * 
	 * @return path to College view JSP
	 */

	@Override
	protected String getView() {

		return ORSView.COLLEGE_VIEW;
	}

}
