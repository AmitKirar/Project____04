package in.co.rays.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.UserBean;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.ServletUtility;

/**
 * BaseCtl is an abstract controller that provides base functionality to all
 * controllers such as preload data, populate common fields, validate input, and
 * manage operation constants.
 * <p>
 * This class centralizes repetitive logic such as handling
 * creation/modification metadata, performing validations, and delegating
 * requests.
 * </p>
 * 
 * @author Amit
 * @version 1.0
 */

public abstract class BaseCtl extends HttpServlet {

	public static final String OP_SAVE = "Save";
	public static final String OP_UPDATE = "Update";
	public static final String OP_CANCEL = "Cancel";
	public static final String OP_DELETE = "Delete";
	public static final String OP_LIST = "List";
	public static final String OP_SEARCH = "Search";
	public static final String OP_VIEW = "View";
	public static final String OP_NEXT = "Next";
	public static final String OP_PREVIOUS = "Previous";
	public static final String OP_NEW = "New";
	public static final String OP_GO = "Go";
	public static final String OP_BACK = "Back";
	public static final String OP_RESET = "Reset";
	public static final String OP_LOG_OUT = "Logout";

	public static final String MSG_SUCCESS = "success";

	public static final String MSG_ERROR = "error";

	/**
	 * Validates the input data coming from the request. Subclasses can override
	 * this method to provide specific validation logic.
	 * 
	 * @param request HTTP request object
	 * @return true if validation passes, false otherwise
	 */

	protected boolean validate(HttpServletRequest request) {
		return true;
	}

	/**
	 * Loads data required to preload on forms like dropdowns, etc. This method can
	 * be overridden in subclasses.
	 * 
	 * @param request HTTP request object
	 * @throws Exception if an error occurs while preloading data
	 */

	protected void preload(HttpServletRequest request) throws Exception {
	}

	/**
	 * Populates the specific bean from request parameters. Subclasses must override
	 * this method to extract bean-specific values.
	 * 
	 * @param request HTTP request object
	 * @return a populated BaseBean instance
	 */

	protected BaseBean populateBean(HttpServletRequest request) {
		return null;
	}

	/**
	 * Populates generic fields such as createdBy, modifiedBy, timestamps.
	 * 
	 * @param dto     the DTO to populate
	 * @param request HTTP request object
	 * @return populated BaseBean with audit fields
	 */

	protected BaseBean populateDTO(BaseBean dto, HttpServletRequest request) {

		String createdBy = request.getParameter("createdBy");
		System.out.println("createdby  " + createdBy);
		String modifiedBy = null;

		UserBean userbean = (UserBean) request.getSession().getAttribute("user");

		if (userbean == null) {
			createdBy = "root";
			modifiedBy = "root";
		} else {
			modifiedBy = userbean.getLogin();
			if ("null".equalsIgnoreCase(createdBy) || DataValidator.isNull(createdBy)) {
				createdBy = modifiedBy;
			}
		}

		dto.setCreatedBy(createdBy);
		dto.setModifiedBy(modifiedBy);

		long cdt = DataUtility.getLong(request.getParameter("createdDatetime"));

		if (cdt > 0) {
			dto.setCreatedDatetime(DataUtility.getTimestamp(cdt));
		} else {
			dto.setCreatedDatetime(DataUtility.getCurrentTimestamp());
		}

		dto.setModifiedDatetime(DataUtility.getCurrentTimestamp());

		return dto;
	}

	/**
	 * Intercepts all HTTP methods (GET, POST) and performs common operations: -
	 * preload data - input validation - forwarding back to the view if validation
	 * fails
	 * 
	 * @param request  HTTP request object
	 * @param response HTTP response object
	 */

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			preload(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String op = DataUtility.getString(request.getParameter("operation"));
//		System.out.println(op);

		if (DataValidator.isNotNull(op) && !OP_CANCEL.equalsIgnoreCase(op) && !OP_VIEW.equalsIgnoreCase(op)
				&& !OP_DELETE.equalsIgnoreCase(op) && !OP_RESET.equalsIgnoreCase(op)) {

			if (!validate(request)) {

				BaseBean bean = (BaseBean) populateBean(request);

				ServletUtility.setBean(bean, request);
				ServletUtility.forward(getView(), request, response);

				return;
			}

		}

		super.service(request, response);

	}

	/**
	 * Subclasses must implement this to return the view page for forwarding.
	 * 
	 * @return path to the view (JSP page)
	 */

	protected abstract String getView();
}
