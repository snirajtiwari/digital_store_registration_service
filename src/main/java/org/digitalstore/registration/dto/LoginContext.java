package org.digitalstore.registration.dto;

public class LoginContext {
	private String userName;

	private String oauthToken;

	public LoginContext(String userName) {
		super();
		this.userName = userName;
	}

	public LoginContext(String userName, String oauthToken) {
		super();
		this.userName = userName;
		this.oauthToken = oauthToken;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the oauthToken
	 */
	public String getOauthToken() {
		return oauthToken;
	}

	/**
	 * @param oauthToken the oauthToken to set
	 */
	public void setOauthToken(String oauthToken) {
		this.oauthToken = oauthToken;
	}

}
