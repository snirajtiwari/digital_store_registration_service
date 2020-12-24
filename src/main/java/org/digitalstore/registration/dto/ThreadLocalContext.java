package org.digitalstore.registration.dto;

public class ThreadLocalContext
{
	/** The Constant CURRENT. */
	private static final ThreadLocal<LoginContext> CURRENT = new ThreadLocal<LoginContext>();

	/**
	 * Gets the login context.
	 * @return the login context
	 */
	public static LoginContext getLoginContext()
	{
		return CURRENT.get();
	}

	/**
	 * Sets the login context.
	 * @param user the new login context
	 */
	public static void setLoginContext(LoginContext user)
	{
		CURRENT.set(user);
	}

}
