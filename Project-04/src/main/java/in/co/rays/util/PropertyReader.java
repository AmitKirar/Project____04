
package in.co.rays.util;

import java.util.ResourceBundle;

/**
 * Utility class to read property values from the resource bundle. It also
 * supports message formatting using placeholders like {0}, {1}, etc.
 * 
 * Resource bundle file: in.co.rays.bundle.system.properties
 * 
 * Example usage:
 * <ul>
 * <li>PropertyReader.getValue("error.require")</li>
 * <li>PropertyReader.getValue("error.require", "loginId")</li>
 * <li>PropertyReader.getValue("error.multipleFields", new String[]{"Name",
 * "Email"})</li>
 * </ul>
 * 
 * @author Amit
 * @version 1.0
 */

public class PropertyReader {

	/** Resource bundle instance */
	private static ResourceBundle rb = ResourceBundle.getBundle("in.co.rays.bundle.system");

	/**
	 * Returns the property value for the given key. If the key is not found,
	 * returns the key itself.
	 *
	 * @param key the property key
	 * @return the property value or the key itself if not found
	 */

	public static String getValue(String key) {

		String val = null;

		try {
			val = rb.getString(key); // {0} is required
		} catch (Exception e) {
			val = key;
		}
		return val;
	}

	/**
	 * Returns the property value with a single parameter replaced in {0}.
	 *
	 * @param key   the property key
	 * @param param the parameter to replace {0}
	 * @return formatted property value
	 */

	public static String getValue(String key, String param) {
		String msg = getValue(key); // {0} is required
		msg = msg.replace("{0}", param);
		return msg;
	}

	/**
	 * Returns the property value with multiple parameters replaced. Placeholders in
	 * the format {0}, {1}, etc., will be replaced accordingly.
	 *
	 * @param key    the property key
	 * @param params the array of values to replace placeholders
	 * @return formatted property value
	 */

	public static String getValue(String key, String[] params) {
		String msg = getValue(key);
		for (int i = 0; i < params.length; i++) {
			msg = msg.replace("{" + i + "}", params[i]);
		}
		return msg;
	}

	/**
	 * Main method for testing the utility methods.
	 *
	 * @param args command-line arguments
	 */

	public static void main(String[] args) {

		System.out.println("Single key example:");
		System.out.println(PropertyReader.getValue("error.require"));

		System.out.println("\nSingle parameter replacement example:");
		System.out.println(PropertyReader.getValue("error.require", "loginId"));

		System.out.println("\nMultiple parameter replacement example:");
		String[] params = { "Roll No", "Student Name" };
		System.out.println(PropertyReader.getValue("error.multipleFields", params));
	}
}
