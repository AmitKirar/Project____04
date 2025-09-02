package in.co.rays.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.util.ServletUtility;

/**
 * ErrorCtl handles the forwarding to the error view when an unhandled exception
 * or error condition occurs in the application.
 * 
 * This controller is invoked when the user is redirected to a general error
 * page. Mapped to /ErrorCtl.
 * 
 * @author Amit
 * @version 1.0
 */

@WebServlet(name = "ErrorCtl", urlPatterns = { "/ctl/ErrorCtl" })
public class ErrorCtl extends BaseCtl {

	/**
	 * Handles HTTP GET request by forwarding to the error view.
	 * 
	 * @param request  the HttpServletRequest object
	 * @param response the HttpServletResponse object
	 * @throws ServletException
	 * @throws IOException
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * Handles HTTP POST request by forwarding to the error view.
	 * 
	 * @param request  the HttpServletRequest object
	 * @param response the HttpServletResponse object
	 * @throws ServletException
	 * @throws IOException
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * Returns the path to the error view.
	 * 
	 * @return the error view path
	 */

	@Override
	protected String getView() {

		return ORSView.ERROR_VIEW;
	}

}
