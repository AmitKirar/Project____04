
package in.co.rays.exception;

/**
 * DatabaseException is thrown when an error occurs while interacting with the
 * database. This is used to wrap SQL exceptions into application-specific
 * exceptions.
 * 
 * @author Amit
 * @version 1.0
 */

public class DatabaseException extends Exception {

	/**
	 * Constructs a DatabaseException with the specified detail message.
	 *
	 * @param msg the detail message
	 */

	public DatabaseException(String msg) {
		super(msg);
	}
}
