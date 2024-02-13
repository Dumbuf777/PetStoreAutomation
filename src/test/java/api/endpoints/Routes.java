package api.endpoints;

public class Routes {
	/*
	 * Swagger URL - https://petstore.swagger.io/ crate user post
	 * https://petstore.swagger.io/v2/user update user put
	 * https://petstore.swagger.io/v2/user/{username} get user get
	 * https://petstore.swagger.io/v2/user/{username} delete user delete
	 * https://petstore.swagger.io/v2/user/{username}
	 * 
	 * 
	 */

	public static String base_url = "https://petstore.swagger.io/v2/";

	//user module
	public static String post_url = base_url + "user";
	public static String get_url = base_url + "user/{username}";
	public static String update_url = base_url + "user/{username}";
	public static String delete_url = base_url + "user/{username}";

}
