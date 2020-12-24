package org.digitalstore.registration.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class DigitalStoreRegistrationJoinPointConfig {

	@Pointcut("within(@org.springframework.stereotype.Repository *)"
			+ " || within(@org.springframework.stereotype.Service *)"
			+ " || within(@org.springframework.web.bind.annotation.RestController *)")
	public void springBeanPointcut() {
	}

	@Pointcut("execution(* org.digitalstore.registration.bo.impl.*.*(..))")
	public void businessLayerExecution() {
	}

}
