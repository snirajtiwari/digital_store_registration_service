package org.digitalstore.registration.constant;

public class RegistrationPath {
	public static final String USER = "/user";
	public static final String BLOCK = "/block";
	public static final String UN_BLOCK = "/unblock";

	public static final String CHANGE_PASSWORD = "/users/changePassword";
	public static final String CHANGEPROFILEPASSWORD = "/users/changeProfilePassword";
	public static final String FORGOT_PASSWORD = "/users/forgotPassword/{email_id}";
	public static final String FORGOT_USERNAME = "/users/forgotUsername/{email_id}";
	public static final String SET_PASSWORD = "/users/setPassword";
	public static final String PC_TOKEN = "/{token}";

	public static final String USER_NAME = "user_name";
	public static final String ROLE_NAME = "role_name";

	public static final String USERS = "/users";
	public static final String AUTH = "/auth";
	public static final String OTP = "otp";
	public static final String REQUIRED = "required";

	public static final String HEADER_USER_ID = "userid";
	public static final String HEADER_USER_NAME = "username";

	public static final String CONTEXT_PATH = "/registration";

	public static final String[] EXCLUSION_URI_LIST = new String[] { "swagger", "api-docs", "csrf", "users/generateOTP",
			"users/validateOTP", "users/validate", "users/forgotPassword", "users/forgotUsername", "/login",
			"users/setPassword", "users/changePassword", "actuator/health" };
	public static final String[] NO_SECURITY_URL_LIST = new String[] { "/swagger-ui.html/**", "/v2/api-docs/**",
			"/csrf/**", "/webjars/springfox-swagger-ui/**", "**/swagger-resources/**", "/swagger-resources/**",
			RegistrationPath.FORGOT_PASSWORD + "/**", RegistrationPath.FORGOT_USERNAME + "/**",
			RegistrationPath.CHANGE_PASSWORD + "/**", RegistrationPath.SET_PASSWORD + "/**" };

}
