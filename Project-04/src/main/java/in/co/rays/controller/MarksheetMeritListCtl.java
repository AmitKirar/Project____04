package in.co.rays.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.MarksheetBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.model.MarksheetModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

/**
 * MarksheetMeritListCtl servlet handles the logic to display the top-scoring
 * students (merit list) from the marksheet records.
 * 
 * It supports only two operations:
 * <ul>
 * <li>GET: Displays the merit list with pagination</li>
 * <li>POST: Handles the back navigation</li>
 * </ul>
 * 
 * @author Amit
 * @version 1.0
 */

@WebServlet(name = "MarksheetMeritListCtl", urlPatterns = { "/ctl/MarksheetMeritListCtl" })
public class MarksheetMeritListCtl extends BaseCtl {

	/**
	 * Handles the HTTP GET request. Fetches and displays the top merit list.
	 *
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int pageNo = 1;

		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

//		int pageNo = -1;
//		int pageSize = 10;
		MarksheetModel model = new MarksheetModel();

		try {

			List<MarksheetBean> list = model.getMeritList(pageNo, pageSize);

			if (list == null || list.isEmpty()) {
				ServletUtility.setErrorMessage("No record found", request);
			}

			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);

			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			e.printStackTrace();
			System.out.println("<-------------><------------->");
			ServletUtility.handleException(e, request, response);
			return;
		}
	}

	/**
	 * Handles the HTTP POST request. Currently supports only the BACK operation to
	 * return to the welcome page.
	 *
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));

		if (OP_BACK.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.WELCOME_CTL, request, response);
			return;
		}
	}

	/**
	 * Returns the view path for the merit list page.
	 *
	 * @return a String constant representing the view path
	 */

	@Override
	protected String getView() {

		return ORSView.MARKSHEET_MERIT_LIST_VIEW;
	}

}
