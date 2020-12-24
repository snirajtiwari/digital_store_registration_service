package org.digitalstore.registration.aspect;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

@Aspect
@Component
@ConditionalOnExpression("${aop.service.enabled:false}")
public class DigitalStoreRegistrationLoggingAspect {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Before("org.digitalstore.registration.aspect.DigitalStoreRegistrationJoinPointConfig.businessLayerExecution()")
	public void beforeAdvice(JoinPoint joinPoint) throws Throwable {

		try {
			if (logger.isInfoEnabled()) 
				logger.info("Entering into - Method : {} , Class : {} ", joinPoint.getSignature().getName(),
						joinPoint.getSignature().getDeclaringTypeName());
				
			if (logger.isDebugEnabled()) {
				logger.debug("Input Argument[s] : {}", Arrays.toString(joinPoint.getArgs()));
			}

		} catch (IllegalArgumentException e) {
			logger.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
					joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
			e.printStackTrace();
			throw e;
		}
	}
}
