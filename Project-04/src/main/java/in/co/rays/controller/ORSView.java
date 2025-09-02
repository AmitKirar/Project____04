package in.co.rays.controller;

/**
 * ORSView interface holds the constants for all JSP view paths and Controller
 * URLs used throughout the ORS (Online Result System) application. This central
 * location ensures consistent usage of paths across all servlets and views.
 *
 * <p>
 * Constants follow this format:
 * <ul>
 * <li><strong>_VIEW</strong>: Path to a JSP page</li>
 * <li><strong>_CTL</strong>: Path to a Controller servlet</li>
 * </ul>
 * </p>
 * 
 * <p>
 * Example usage:
 * 
 * <pre>
 * response.sendRedirect(ORSView.LOGIN_CTL);
 * RequestDispatcher rd = request.getRequestDispatcher(ORSView.MY_PROFILE_VIEW);
 * </pre>
 * </p>
 * 
 * @author Amit
 * @version 1.0
 */

public interface ORSView {

	/** Application context path */
	public String APP_CONTEXT = "/Project-04";

	/** Common folder where all JSP pages are stored */
	public String PAGE_FOLDER = "/jsp";

	/** Path to JavaDoc documentation */
	public String JAVA_DOC_VIEW = APP_CONTEXT + "/doc/index.html";

	/** View and controller for My Profile */
	public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyProfileView.jsp";

	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl//MyProfileCtl";

	/** View and controller for changing password */

	public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER + "/ChangePasswordView.jsp";

	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ctl/ChangePasswordCtl";

	/** View and controller for user registration */

	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/UserRegistrationView.jsp";

	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/UserRegistrationCtl";

	/** View and controller for forget password */
	public String FORGET_PASSWORD_VIEW = PAGE_FOLDER + "/ForgetPasswordView.jsp";

	public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/ForgetPasswordCtl";

	/** View and controller for login */
	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";

	public String LOGIN_CTL = APP_CONTEXT + "/LoginCtl";

	/** Welcome screen view and controller */

	public String WELCOME_VIEW = PAGE_FOLDER + "/Welcome.jsp";

	public String WELCOME_CTL = APP_CONTEXT + "/WelcomeCtl";

	/** User form view and controller */

	public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";

	public String USER_CTL = APP_CONTEXT + "/ctl/UserCtl";

	/** User list view and controller */

	public String USER_LIST_VIEW = PAGE_FOLDER + "/UserListView.jsp";

	public String USER_LIST_CTL = APP_CONTEXT + "/ctl/UserListCtl";

	/** College form view and controller */

	public String COLLEGE_VIEW = PAGE_FOLDER + "/CollegeView.jsp";

	public String COLLEGE_CTL = APP_CONTEXT + "/ctl/CollegeCtl";

	public String COLLEGE_LIST_CTL = APP_CONTEXT + "/ctl/CollegeListCtl";

	/** College list view and controller */

	public String COLLEGE_LIST_VIEW = PAGE_FOLDER + "/CollegeListView.jsp";

	public String ROLE_CTL = APP_CONTEXT + "/ctl//RoleCtl";

	/** Role form view and controller */

	public String ROLE_VIEW = PAGE_FOLDER + "/RoleView.jsp";

	public String ROLE_LIST_CTL = APP_CONTEXT + "/ctl//RoleListCtl";

	/** Role list view and controller */

	public String ROLE_LIST_VIEW = PAGE_FOLDER + "/RoleListView.jsp";

	/** Role list view and controller */

	public String STUDENT_CTL = APP_CONTEXT + "/ctl/StudentCtl";

	/** Student form view and controller */
	public String STUDENT_VIEW = PAGE_FOLDER + "/StudentView.jsp";

	public String STUDENT_LIST_CTL = APP_CONTEXT + "/ctl/StudentListCtl";

	public String STUDENT_LIST_VIEW = PAGE_FOLDER + "/StudentListView.jsp";

	public String COURSE_CTL = APP_CONTEXT + "/ctl/CourseCtl";

	/** Course form view and controller */

	public String COURSE_VIEW = PAGE_FOLDER + "/CourseView.jsp";

	public String COURSE_LIST_CTL = APP_CONTEXT + "/ctl/CourseListCtl";

	/** Course list view and controller */

	public String COURSE_LIST_VIEW = PAGE_FOLDER + "/CourseListView.jsp";

	public String SUBJECT_CTL = APP_CONTEXT + "/ctl/SubjectCtl";

	/** Subject form view and controller */

	public String SUBJECT_VIEW = PAGE_FOLDER + "/SubjectView.jsp";

	public String SUBJECT_LIST_CTL = APP_CONTEXT + "/ctl/SubjectListCtl";

	/** Subject list view and controller */

	public String SUBJECT_LIST_VIEW = PAGE_FOLDER + "/SubjectListView.jsp";

	public String MARKSHEET_CTL = APP_CONTEXT + "/ctl/MarksheetCtl";

	/** Marksheet form view and controller */

	public String MARKSHEET_VIEW = PAGE_FOLDER + "/MarksheetView.jsp";

	public String MARKSHEET_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetListCtl";

	/** Marksheet list view and controller */

	public String MARKSHEET_LIST_VIEW = PAGE_FOLDER + "/MarksheetListView.jsp";

	/** Get marksheet view and controller */
	public String GET_MARKSHEET_VIEW = PAGE_FOLDER + "/GetMarksheetView.jsp";

	public String GET_MARKSHEET_CTL = APP_CONTEXT + "/ctl/GetMarksheetCtl";

	/** Marksheet merit list view and controller */

	public String MARKSHEET_MERIT_LIST_VIEW = PAGE_FOLDER + "/MarksheetMeritListView.jsp";

	public String MARKSHEET_MERIT_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetMeritListCtl";

	public String FACULTY_CTL = APP_CONTEXT + "/ctl/FacultyCtl";

	/** Faculty form view and controller */

	public String FACULTY_VIEW = PAGE_FOLDER + "/FacultyView.jsp";

	public String FACULTY_LIST_CTL = APP_CONTEXT + "/ctl/FacultyListCtl";

	/** Faculty list view and controller */

	public String FACULTY_LIST_VIEW = PAGE_FOLDER + "/FacultyListView.jsp";

	public String TIMETABLE_CTL = APP_CONTEXT + "/ctl/TimetableCtl";

	/** Timetable form view and controller */

	public String TIMETABLE_VIEW = PAGE_FOLDER + "/TimetableView.jsp";

	public String TIMETABLE_LIST_CTL = APP_CONTEXT + "/ctl/TimetableListCtl";

	/** Timetable list view and controller */

	public String TIMETABLE_LIST_VIEW = PAGE_FOLDER + "/TimetableListView.jsp";

	public String ERROR_CTL = APP_CONTEXT + "/ctl/ErrorCtl";

	/** Error page view and controller */

	public String ERROR_VIEW = PAGE_FOLDER + "/ErrorView.jsp";

}
