package in.co.rays.bean;

/**
 * DropdownListBean interface is used to define a standard structure for beans
 * that are used in dropdown lists. It ensures that implementing classes provide
 * a key and a display value.
 * 
 * @Author Amit
 * @version 1.0
 */
public interface DropdownListBean {

	/**
	 * Returns the key of the dropdown item (usually the ID).
	 * 
	 * @return the key as a string
	 */

	public String getkey();

	/**
	 * Returns the display value of the dropdown item (usually the name).
	 * 
	 * @return the value as a string
	 */

	public String getValue();
}
