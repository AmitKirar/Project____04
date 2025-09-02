<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.controller.LoginCtl"%>
<%@page import="in.co.rays.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>

<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>

	
	<%@include file="Header.jsp" %>
	
	<%
		
		String uri = (String) request.getAttribute("uri");
	%>

	<div align="center">
		<form action="<%=ORSView.LOGIN_CTL%>" method="post">

			<jsp:useBean id="bean" class="in.co.rays.bean.UserBean"
				scope="request" />

			<h1 align="centre" > <font size="10px" color="navy"> User Login</font></h1>


			<h1>
				<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
			</h1>
			<h2>
				<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
			</h2>

			<table>
				<tr>
					<th>LoginId <span style="color: red">*</span> :
					</th>
					<td><input type="text" name="login"
						placeholder="Enter valid Email-Id" size="26"
						value="<%=DataUtility.getStringData(bean.getLogin())%>"></td>
					<td><font color="red"><%=ServletUtility.getErrorMessage("login", request)%></font>
					</td>
				</tr>

				<tr>
					<th>Password <span style="color: red">*</span> :
					</th>
					<td><input type="password" name="password"
						placeholder="Enter Password" size="26"
						value="<%=DataUtility.getStringData(bean.getPassword())%>">
					</td>
					<td><font color="red"><%=ServletUtility.getErrorMessage("password", request)%></font>
					</td>
				</tr>

				<tr>
					<th></th>
					<td colspan="2"><input type="submit" name="operation"
						value="<%=LoginCtl.OP_SIGN_IN%>"> &nbsp; <input
						type="submit" name="operation" value="<%=LoginCtl.OP_SIGN_UP%>">
						&nbsp;</td>
				</tr>
				<tr>
					<th></th>
					<td></td>
				</tr>
				
				</tr>
				<tr>
					<th></th>
					<td><a href="<%=ORSView.FORGET_PASSWORD_CTL%>"><b>Forget my password?</b></a>&nbsp;</td>
				</tr>
			</table>
			
			<input type="hidden" name="uri" value="<%=uri%>">

		</form>
	</div>

<%@include file="Footer.jsp"%>
</body>
</html>