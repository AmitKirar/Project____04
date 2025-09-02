package in.co.rays.util;

import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import in.co.rays.exception.ApplicationException;

/**
 * Utility class for sending emails using JavaMail API.
 * <p>
 * Configurations such as SMTP server, port, sender email and password are
 * loaded from a resource bundle named <b>system.properties</b>.
 * </p>
 * 
 * <p>
 * Supports sending both plain text and HTML email formats.
 * </p>
 * 
 * @author Amit
 * @version 1.0
 */

public class EmailUtility {

	/** Loads SMTP and email configuration from resource bundle */
	static ResourceBundle rb = ResourceBundle.getBundle("in.co.rays.bundle.system");

	private static final String SMTP_HOST_NAME = rb.getString("smtp.server");
	private static final String SMTP_PORT = rb.getString("smtp.port");
	private static final String emailFromAddress = rb.getString("email.login");
	private static final String emailPassword = rb.getString("email.pwd");

	private static Properties props = new Properties();

	// Static initializer block to configure mail server properties
	static {
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		props.put("mail.debug", "true");
		props.put("mail.smtp.port", SMTP_PORT);
		props.put("mail.smtp.socketFactory.port", SMTP_PORT);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
	}

	/**
	 * Sends an email using the provided {@link EmailMessage} data.
	 *
	 * @param emailMessageDTO the email message object containing recipient,
	 *                        subject, body, and type
	 * @throws ApplicationException if email sending fails
	 */

	public static void sendMail(EmailMessage emailMessageDTO) throws ApplicationException {
		try {
			// Setup mail session
			Session session = Session.getDefaultInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(emailFromAddress, emailPassword);
				}
			});

			// Create and setup the email message
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(emailFromAddress));
			msg.setRecipients(Message.RecipientType.TO, getInternetAddresses(emailMessageDTO.getTo()));
			msg.setSubject(emailMessageDTO.getSubject());

			// Set content type based on message type
			String contentType = emailMessageDTO.getMessageType() == EmailMessage.HTML_MSG ? "text/html" : "text/plain";
			msg.setContent(emailMessageDTO.getMessage(), contentType);

			// Send the email
			Transport.send(msg);

		} catch (Exception ex) {
			throw new ApplicationException("Email Error: " + ex.getMessage());
		}
	}

	/**
	 * Converts comma-separated email addresses into an array of
	 * {@link InternetAddress}.
	 *
	 * @param emails comma-separated list of email addresses
	 * @return array of InternetAddress objects
	 * @throws Exception if email format is invalid
	 */

	private static InternetAddress[] getInternetAddresses(String emails) throws Exception {
		if (emails == null || emails.isEmpty()) {
			return new InternetAddress[0];
		}
		String[] emailArray = emails.split(",");
		InternetAddress[] addresses = new InternetAddress[emailArray.length];
		for (int i = 0; i < emailArray.length; i++) {
			addresses[i] = new InternetAddress(emailArray[i].trim());
		}
		return addresses;
	}

}
