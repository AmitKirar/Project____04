
package in.co.rays.exception;

/**
 * RecordNotFoundException is thrown when a specific record is not found in the
 * database. It is commonly used for update or delete operations when the target
 * record does not exist.
 * 
 * @author Amit
 * @version 1.0
 */

public class RecordNotFoundException extends Exception {

	/**
	 * Constructs a RecordNotFoundException with the specified detail message.
	 *
	 * @param msg the detail message
	 */

	public RecordNotFoundException(String msg) {
		super(msg);
	}
}
