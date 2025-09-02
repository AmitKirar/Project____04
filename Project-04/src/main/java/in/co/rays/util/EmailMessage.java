package in.co.rays.util;

/**
 * Represents an email message containing recipient, subject, content, and
 * message type.
 * <p>
 * Supports two message types:
 * <ul>
 * <li>{@code HTML_MSG} - for HTML content</li>
 * <li>{@code TEXT_MSG} - for plain text content</li>
 * </ul>
 * </p>
 * 
 * Used to encapsulate email data for email sending utilities.
 * 
 * @author Amit
 * @version 1.0
 */

public class EmailMessage {

	/** Email recipient address */
	private String to;

	/** Subject of the email */
	private String subject;

	/** Content of the email */
	private String message;

	/** Type of the email message (HTML or TEXT), default is TEXT */
	private int messageType = TEXT_MSG;

	/** Constant for HTML message type */
	public static final int HTML_MSG = 1;

	/** Constant for plain text message type */
	public static final int TEXT_MSG = 2;

	/**
	 * Default constructor
	 */

	public EmailMessage() {

	}

	/**
	 * Parameterized constructor to initialize email details.
	 *
	 * @param to      the recipient's email address
	 * @param subject the email subject
	 * @param message the email content
	 */

	public EmailMessage(String to, String subject, String message) {
		this.to = to;
		this.subject = subject;
		this.message = message;
	}

	/**
	 * Gets the recipient email address.
	 *
	 * @return recipient address
	 */

	public String getTo() {
		return to;
	}

	/**
	 * Sets the recipient email address.
	 *
	 * @param to the recipient address
	 */

	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * Gets the subject of the email.
	 *
	 * @return subject text
	 */

	public String getSubject() {
		return subject;
	}

	/**
	 * Sets the subject of the email.
	 *
	 * @param subject the email subject
	 */

	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Gets the email message content.
	 *
	 * @return email body
	 */

	public String getMessage() {
		return message;
	}

	/**
	 * Sets the email message content.
	 *
	 * @param message the email body
	 */

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the type of the email message.
	 *
	 * @return message type (HTML_MSG or TEXT_MSG)
	 */

	public int getMessageType() {
		return messageType;
	}

	/**
	 * Sets the type of the email message.
	 *
	 * @param messageType the message type (HTML_MSG or TEXT_MSG)
	 */

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

}
