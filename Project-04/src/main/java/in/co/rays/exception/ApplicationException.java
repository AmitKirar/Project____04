
package in.co.rays.exception;

/**
 * ApplicationException is used to handle generic application-level exceptions.
 * It is thrown when an unexpected error occurs in the application logic.
 * 
 * @author Amit
 * @version 1.0
 */

public class ApplicationException extends Exception {

	/**
	 * Constructs an ApplicationException with the specified detail message.
	 *
	 * @param msg the detail message
	 */

	public ApplicationException(String msg) {
		super(msg);
	}
}
