package org.digitalstore.registration.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * The Class ApplicationContextUtil.
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ApplicationContextUtil implements ApplicationContextAware
{

	/** The ctx. */
	private static ApplicationContext ctx;

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException
	{
		ctx = context;
	}

	/**
	 * Gets the bean.
	 * @param <T> the generic type
	 * @param clazz the clazz
	 * @return the bean
	 */
	public static <T> T getBean(Class<T> clazz)
	{
		return ctx.getBean(clazz);
	}

	/**
	 * Gets the application context.
	 * @return the application context
	 */
	public static ApplicationContext getApplicationContext()
	{
		return ctx;
	}
}
