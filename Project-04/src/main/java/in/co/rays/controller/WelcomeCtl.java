package in.co.rays.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.util.ServletUtility;

/**
 * Controller to handle the welcome screen.
 * <p>
 * This controller simply forwards the user to the welcome view. It does not
 * perform any data operations or validations.
 * </p>
 *
 * Mapped to `/WelcomeCtl`.
 * 
 * @author Amit
 * @version 1.0
 *
 */

@WebServlet(name = "WelcomeCtl", urlPatterns = { "/WelcomeCtl" })
public class WelcomeCtl extends BaseCtl {

	/**
	 * Handles GET requests for the welcome page.
	 * <p>
	 * Simply forwards the request to the welcome JSP view.
	 * </p>
	 *
	 * @param request  the HttpServletRequest object
	 * @param response the HttpServletResponse object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an input or output error is detected
	 */

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletUtility.forward(getView(), request, response);

	}

	/**
	 * Returns the view path for the welcome JSP.
	 *
	 * @return the path to the welcome view defined in ORSView
	 */

	@Override
	protected String getView() {

		return ORSView.WELCOME_VIEW;
	}

}
