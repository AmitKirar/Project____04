package in.co.rays.util;

import java.util.HashMap;

/**
 * Utility class for generating email message content for different user actions
 * such as registration, password recovery, and password change.
 * 
 * <p>
 * This class returns HTML formatted strings containing user details.
 * </p>
 * 
 * @author Amit
 * @version 1.0
 */
public class EmailBuilder {

	/**
	 * Builds a welcome email message for new user registration.
	 *
	 * @param map a HashMap containing "login" and "password"
	 * @return HTML content as a String for user registration email
	 */

	public static String getUserRegistrationMessage(HashMap<String, String> map) {
		StringBuilder msg = new StringBuilder();
		msg.append("<HTML><BODY>");
		msg.append("<H1>Welcome to ORS, ").append(map.get("login")).append("!</H1>");
		msg.append("<P>Your registration is successful. You can now log in and manage your account.</P>");
		msg.append("<P><B>Login Id: ").append(map.get("login")).append("<BR>Password: ").append(map.get("password"))
				.append("</B></P>");
		msg.append("<P>Change your password after logging in for security reasons.</P>");
		msg.append("<P>For support, contact +91 98273 60504 or hrd@sunrays.co.in.</P>");
		msg.append("</BODY></HTML>");
		return msg.toString();
	}

	/**
	 * Builds a password recovery email message for the user.
	 *
	 * @param map a HashMap containing "firstName", "lastName", "login", and
	 *            "password"
	 * @return HTML content as a String for password recovery email
	 */

	public static String getForgetPasswordMessage(HashMap<String, String> map) {
		StringBuilder msg = new StringBuilder();
		msg.append("<HTML><BODY>");
		msg.append("<H1>Password Recovery</H1>");
		msg.append("<P>Hello, ").append(map.get("firstName")).append(" ").append(map.get("lastName")).append(".</P>");
		msg.append("<P>Your login details are:</P>");
		msg.append("<P><B>Login Id: ").append(map.get("login")).append("<BR>Password: ").append(map.get("password"))
				.append("</B></P>");
		msg.append("</BODY></HTML>");
		return msg.toString();
	}

	/**
	 * Builds a confirmation email message after the user changes their password.
	 *
	 * @param map a HashMap containing "firstName", "lastName", "login", and
	 *            "password"
	 * @return HTML content as a String for password change confirmation email
	 */

	public static String getChangePasswordMessage(HashMap<String, String> map) {
		StringBuilder msg = new StringBuilder();
		msg.append("<HTML><BODY>");
		msg.append("<H1>Password Changed Successfully</H1>");
		msg.append("<P>Dear ").append(map.get("firstName")).append(" ").append(map.get("lastName"))
				.append(", your password has been updated.</P>");
		msg.append("<P>Your updated login details are:</P>");
		msg.append("<P><B>Login Id: ").append(map.get("login")).append("<BR>New Password: ").append(map.get("password"))
				.append("</B></P>");
		msg.append("</BODY></HTML>");
		return msg.toString();
	}
}
