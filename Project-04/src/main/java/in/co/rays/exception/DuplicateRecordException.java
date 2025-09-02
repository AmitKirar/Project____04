
package in.co.rays.exception;

/**
 * DuplicateRecordException is thrown when a duplicate record is found in the
 * database, such as when attempting to insert a record that already exists.
 * 
 * @author Amit
 * @version 1.0
 */
public class DuplicateRecordException extends Exception {

	/**
	 * Constructs a DuplicateRecordException with the specified detail message.
	 *
	 * @param msg the detail message
	 */

	public DuplicateRecordException(String msg) {
		super(msg);
	}
}
