package in.co.rays.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.RoleBean;
import in.co.rays.bean.UserBean;
import in.co.rays.model.RoleModel;
import in.co.rays.model.UserModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

/**
 * LoginCtl handles user login, logout, and redirection to registration. It
 * validates user credentials and establishes a session upon successful login.
 * 
 * @author Amit
 * @version 1.0
 */

@WebServlet(name = "LoginCtl", urlPatterns = { "/LoginCtl" })
public class LoginCtl extends BaseCtl {

	public static final String OP_REGISTER = "Register";
	public static final String OP_SIGN_IN = "Sign In";
	public static final String OP_SIGN_UP = "Sign Up";
	public static final String OP_LOG_OUT = "Logout";

	/**
	 * Validates input fields on the login form.
	 *
	 * @param request the HttpServletRequest object
	 * @return true if validation passes, false otherwise
	 */

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;
		String op = DataUtility.getString(request.getParameter("operation"));
		if (OP_SIGN_UP.equals(op) || OP_LOG_OUT.equals(op)) {
			return pass;
		}

		if (DataValidator.isNull(request.getParameter("login"))) {
			request.setAttribute("login", PropertyReader.getValue("error.require", "Login Id"));
			pass = false;
		} else if (!DataValidator.isEmail(request.getParameter("login"))) {
			request.setAttribute("login", "invalid login");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password", PropertyReader.getValue("error.require", "Password"));
			pass = false;
		}

		return pass;

	}

	/**
	 * Populates the UserBean with data from the request.
	 *
	 * @param request the HttpServletRequest object
	 * @return populated UserBean object
	 */

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		UserBean bean = new UserBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));
		return bean;

	}

	/**
	 * Handles HTTP GET requests. Logs out the user if the operation is logout.
	 *
	 * @param request  the HttpServletRequest object
	 * @param response the HttpServletResponse object
	 * @throws ServletException
	 * @throws IOException
	 */

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String op = DataUtility.getString(request.getParameter("operation"));
		if (OP_LOG_OUT.equals(op)) {

			session.invalidate();
			ServletUtility.setSuccessMessage("Logout Successful!", request);
			ServletUtility.forward(getView(), request, response);
			return;

		}

		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * Handles HTTP POST requests. Authenticates the user or redirects to sign-up.
	 *
	 * @param request  the HttpServletRequest object
	 * @param response the HttpServletResponse object
	 * @throws ServletException
	 * @throws IOException
	 */

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		String op = DataUtility.getString(request.getParameter("operation"));

		UserModel model = new UserModel();
		RoleModel role = new RoleModel();

		if (OP_SIGN_IN.equalsIgnoreCase(op)) {
			UserBean bean = (UserBean) populateBean(request);

			try {

				bean = model.authenticate(bean.getLogin(), bean.getPassword());

				if (bean != null) {
					System.out.println(bean.getFirstname());
					session.setAttribute("user", bean);
					RoleBean rolebean = role.findByPk(bean.getRoleId());

					if (rolebean != null) {
						session.setAttribute("role", rolebean.getName());

					}

					String uri = (String) request.getParameter("uri");

					if (uri == null || "null".equalsIgnoreCase(uri)) {

						ServletUtility.redirect(ORSView.WELCOME_CTL, request, response);
						return;
					} else {
						ServletUtility.redirect(uri, request, response);
						return;
					}

				} else {
					bean = (UserBean) populateBean(request);
					ServletUtility.setBean(bean, request);
					ServletUtility.setErrorMessage("Invalid LoginId and Password", request);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		} else if (OP_SIGN_UP.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.USER_REGISTRATION_CTL, request, response);
			return;

		}
		ServletUtility.forward(getView(), request, response);

	}

	/**
	 * Returns the view page of Login.
	 *
	 * @return the path of login view
	 */

	@Override
	protected String getView() {
		return ORSView.LOGIN_VIEW;
	}
}
