
package in.co.rays.util;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.DropdownListBean;
import in.co.rays.model.RoleModel;

/**
 * Utility class to generate HTML select (dropdown) elements from maps or lists.
 * 
 * @author Amit
 * @version 1.0
 */

public class HTMLUtility {

	/**
	 * Generates an HTML select dropdown using a HashMap.
	 * 
	 * @param name        the name of the select tag
	 * @param selectedVal the value to be marked as selected
	 * @param map         a map containing key-value pairs for options
	 * @return HTML string representing the dropdown
	 */

	public static String getList(String name, String selectedVal, HashMap<String, String> map) {

		StringBuffer sb = new StringBuffer(
				"<select style=\"width: 170px;text-align-last: center;\"; class='form-control' name='" + name + "'>");

		sb.append("\n<option selected value=''>-------------Select-------------</option>");

		Set<String> keys = map.keySet();
		String val = null;

		for (String key : keys) {
			val = map.get(key);
			if (key.trim().equals(selectedVal)) {
				sb.append("\n<option selected value='" + key + "'>" + val + "</option>");
			} else {
				sb.append("\n<option value='" + key + "'>" + val + "</option>");
			}
		}
		sb.append("\n</select>");
		return sb.toString();
	}

	/**
	 * Generates an HTML select dropdown using a list of DropdownListBean.
	 * 
	 * @param name        the name of the select tag
	 * @param selectedVal the value to be marked as selected
	 * @param list        list of DropdownListBean
	 * @return HTML string representing the dropdown
	 */

	public static String getList(String name, String selectedVal, List list) {

		List<DropdownListBean> dd = (List<DropdownListBean>) list;

		StringBuffer sb = new StringBuffer("<select style=\"width: 170px;text-align-last: center;\"; "
				+ "class='form-control' name='" + name + "'>");

		sb.append("\n<option selected value=''>-------------Select-------------</option>");

		String key = null;
		String val = null;

		for (DropdownListBean obj : dd) {
			key = obj.getkey();
			val = obj.getValue();

			if (key.trim().equals(selectedVal)) {
				sb.append("\n<option selected value='" + key + "'>" + val + "</option>");
			} else {
				sb.append("\n<option value='" + key + "'>" + val + "</option>");
			}
		}
		sb.append("\n</select>");
		return sb.toString();
	}

	/**
	 * Test method for getList using a HashMap.
	 */

	public static void testGetListByMap() {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("male", "male");
		map.put("female", "female");

		String selectedValue = "";
		String htmlSelectFromMap = HTMLUtility.getList("gender", selectedValue, map);

		System.out.println(htmlSelectFromMap);
	}

	/**
	 * Test method for getList using a list from RoleModel.
	 * 
	 * @throws Exception if any error occurs
	 */

	public static void testGetListByList() throws Exception {

		RoleModel model = new RoleModel();

		List<DropdownListBean> list = model.list();

		String selectedValue = null;

		String htmlSelectFromList = HTMLUtility.getList("fname", selectedValue, list);

		System.out.println(htmlSelectFromList);
	}

	/**
	 * Main method to test the HTML dropdown generation methods.
	 * 
	 * @param args command-line arguments
	 * @throws Exception if any error occurs
	 */

	public static void main(String[] args) throws Exception {

		 testGetListByMap();

//		testGetListByList();

	}
}
