<%@page import="in.co.rays.bean.RoleBean"%>
<%@page import="in.co.rays.controller.ORSView"%>
<%@page import="in.co.rays.controller.LoginCtl"%>
<%@page import="in.co.rays.bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>ORS Project</title>

<!-- Include jQuery -->
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<!-- Include jQuery UI -->
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.min.js"></script>
<!-- Include jQuery UI CSS -->
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">

<script src="/Project-04/js/checkbox.js"></script>
<script src="/Project-04/js/datepicker.js"></script>
</head>

<body>
<%
    UserBean user = (UserBean) session.getAttribute("user");
%>
<%
    if (user != null) {
%>

<table width="100%" style="border-collapse: collapse;">
    <tr>
        <td style="text-align: left; vertical-align: middle;">
            <h3>
                Hi, <%=user.getFirstname()%> (<%=session.getAttribute("role")%>)
            </h3>
        </td>
        <td style="text-align: right; vertical-align: middle;">
            <img src="<%=ORSView.APP_CONTEXT%>/img/customLogo.jpg" width="175" height="50">
        </td>
    </tr>
</table>

<%
    if (user.getRoleId() == RoleBean.ADMIN) {
%>
	<a href="<%=ORSView.MY_PROFILE_CTL%>">My Profile</a> |
	<a href="<%=ORSView.CHANGE_PASSWORD_CTL%>">Change Password</a> |
	<a href="<%=ORSView.USER_CTL%>">Add User</a> |
	<a href="<%=ORSView.USER_LIST_CTL%>">User List</a> |
	<a href="<%=ORSView.COLLEGE_CTL%>">Add College</a> |
	<a href="<%=ORSView.COLLEGE_LIST_CTL%>">College List</a> |
	<a href="<%=ORSView.ROLE_CTL%>">Add Role</a> |
	<a href="<%=ORSView.ROLE_LIST_CTL%>">Role List</a> |
	<a href="<%=ORSView.STUDENT_CTL%>">Add Student</a> |
	<a href="<%=ORSView.STUDENT_LIST_CTL%>">Student List</a> |
	<a href="<%=ORSView.COURSE_CTL%>">Add Course</a> |
	<a href="<%=ORSView.COURSE_LIST_CTL%>">Course List</a> |
	<a href="<%=ORSView.SUBJECT_CTL%>">Add Subject</a> |
	<a href="<%=ORSView.SUBJECT_LIST_CTL%>">Subject List</a> |
	<a href="<%=ORSView.MARKSHEET_CTL%>">Add Marksheet</a> |
	<a href="<%=ORSView.MARKSHEET_LIST_CTL%>">Marksheet List</a> |
	<a href="<%=ORSView.GET_MARKSHEET_CTL%>">Get Marksheet</a> |
	<a href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>">Marksheet Merit List</a> |
	<a href="<%=ORSView.FACULTY_CTL%>">Add Faculty</a> |
	<a href="<%=ORSView.FACULTY_LIST_CTL%>">Faculty List</a> |
	<a href="<%=ORSView.TIMETABLE_CTL%>">Add Timetable</a> |
	<a href="<%=ORSView.TIMETABLE_LIST_CTL%>">Timetable List</a> |
	<a href="<%=ORSView.JAVA_DOC_VIEW%>" target="_blank">Java Doc</a> |
	<a href="<%=ORSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>">Logout</a>

<%
    } else if (user.getRoleId() == RoleBean.FACULTY) {
%>
	<a href="<%=ORSView.MARKSHEET_CTL%>">Add Marksheet</a> |
	<a href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>">Marksheet Merit List</a> |
	<a href="<%=ORSView.COLLEGE_LIST_CTL%>">College List</a> |
	<a href="<%=ORSView.STUDENT_CTL%>">Add Student</a> |
	<a href="<%=ORSView.STUDENT_LIST_CTL%>">Student List</a> |
	<a href="<%=ORSView.COURSE_CTL%>">Add Course</a> |
	<a href="<%=ORSView.COURSE_LIST_CTL%>">Course List</a> |
	<a href="<%=ORSView.SUBJECT_CTL%>">Add Subject</a> |
	<a href="<%=ORSView.SUBJECT_LIST_CTL%>">Subject List</a> |
	<a href="<%=ORSView.TIMETABLE_CTL%>">Add Timetable</a> |
	<a href="<%=ORSView.TIMETABLE_LIST_CTL%>">Timetable List</a>|
	<a href="<%=ORSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>">Logout</a>
	
	<%
    } else if (user.getRoleId() == RoleBean.STUDENT) {
%>
    	<a href="<%=ORSView.STUDENT_LIST_CTL%>">Student List</a> |
	<a href="<%=ORSView.COURSE_LIST_CTL%>">Course List</a> |
	<a href="<%=ORSView.SUBJECT_LIST_CTL%>">Subject List</a> |
	<a href="<%=ORSView.FACULTY_LIST_CTL%>">Faculty List</a> |
	<a href="<%=ORSView.TIMETABLE_LIST_CTL%>">Timetable List</a>|
	<a href="<%=ORSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>">Logout</a>


<%
    } else if(user.getRoleId() == RoleBean.COLLEGE) {
%>
	<a href="<%=ORSView.MARKSHEET_CTL%>">Add Marksheet</a> |
	<a href="<%=ORSView.MARKSHEET_LIST_CTL%>">Marksheet List</a> |
	<a href="<%=ORSView.STUDENT_CTL%>">Add Student</a> |
	<a href="<%=ORSView.STUDENT_LIST_CTL%>">Student List</a> |
	<a href="<%=ORSView.FACULTY_LIST_CTL%>">Faculty List</a> |
	<a href="<%=ORSView.TIMETABLE_LIST_CTL%>">Timetable List</a> |
	<a href="<%=ORSView.COURSE_LIST_CTL%>">Course List</a>|
	<a href="<%=ORSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>">Logout</a>

<%
    } else if (user.getRoleId() == RoleBean.KIOSK) {
%>
  	<a href="<%=ORSView.COLLEGE_LIST_CTL%>">College List</a> |
	<a href="<%=ORSView.TIMETABLE_LIST_CTL%>">Timetable List</a> |
	<a href="<%=ORSView.COURSE_LIST_CTL%>">Course List</a>|
	<a href="<%=ORSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>">Logout</a>
				
<%
    }
%>

<%
    } else {
%>
<table width="100%" style="border-collapse: collapse;">
    <tr>
        <td style="text-align: left; vertical-align: middle;">
            <h3>Hi, Guest</h3>
            <a href="<%=ORSView.WELCOME_CTL%>"><b>Welcome</b></a> |
            <a href="<%=ORSView.LOGIN_CTL%>"><b>Login</b></a>
        </td>
        <td style="text-align: right; vertical-align: middle;">
            <img src="<%=ORSView.APP_CONTEXT%>/img/customLogo.jpg" width="175" height="50">
        </td>
    </tr>
</table>
<%
    }
%>
	<hr>

	<%@include file="Footer.jsp"%>
</body>
</html>
