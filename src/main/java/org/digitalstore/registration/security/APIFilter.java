package org.digitalstore.registration.security;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.digitalstore.registration.constant.RegistrationConstants;
import org.digitalstore.registration.constant.RegistrationPath;
import org.digitalstore.registration.dto.LoginContext;
import org.digitalstore.registration.dto.ThreadLocalContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class APIFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Value("${sdx.security.enable}")
	private Boolean securityEnabled;

	@Value("${spring.data.cassandra.keyspace-name:servicedx}")
	private String tenantName;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		// Request Processing
		Map<String, String> headers = Collections.list(httpRequest.getHeaderNames()).stream()
				.collect(Collectors.toMap(h -> h, httpRequest::getHeader));

		if (logger.isDebugEnabled())
			logger.debug("URI path : {}", httpRequest.getRequestURI());

		// Exclusion URL's
		List<String> exclusionURI = Arrays.asList(RegistrationPath.EXCLUSION_URI_LIST);

		boolean isExcluded = exclusionURI.stream().anyMatch(s -> httpRequest.getRequestURI().contains(s));
		if (RegistrationPath.CONTEXT_PATH.equals(httpRequest.getRequestURI())) {
			isExcluded = true;
		}

		if (!isExcluded) {
			// Fetch User details from headers
			String userName = headers.get(RegistrationPath.HEADER_USER_NAME);
			String authToken = null;

			if (logger.isDebugEnabled())
				logger.debug("Login User Name : {}", userName);

			if (securityEnabled) {
				SecurityContext securityContext = SecurityContextHolder.getContext();
				OAuth2Authentication oauth = (OAuth2Authentication) securityContext.getAuthentication();
				OAuth2AuthenticationDetails oauthDetails = (OAuth2AuthenticationDetails) oauth.getDetails();
				authToken = oauthDetails.getTokenValue();
			}

			if (StringUtils.isEmpty(userName)) {
				if (logger.isDebugEnabled())
					logger.debug("User Name Missing in Header - Setting default user name");
				userName = "UnAuthUser";
			}

			// should take tenant name from header
			// Set in Local Context
			LoginContext context = new LoginContext(userName, authToken);
			ThreadLocalContext.setLoginContext(context);
		} else {
			LoginContext context = new LoginContext(RegistrationConstants.OAUTH_CLIENT_USER, null);
			ThreadLocalContext.setLoginContext(context);

		}

		chain.doFilter(request, response);
	}

}
