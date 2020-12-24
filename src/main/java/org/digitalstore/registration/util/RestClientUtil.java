package org.digitalstore.registration.util;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Map;

import org.digitalstore.registration.constant.RegistrationConstants;
import org.digitalstore.registration.constant.RegistrationPath;
import org.digitalstore.registration.dto.ThreadLocalContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class RestClientUtil
{

	private static final Logger		logger			= LoggerFactory
				.getLogger(MethodHandles.lookup().lookupClass());

	protected static final String	ACCEPT			= "accept";
	protected static final String	CONTENT_TYPE	= "Content-Type";
	protected static final String	AUTHORIZATION	= "authorization";

	public static <T> ResponseEntity<T> updateDepartmentInSender(String URL, String departmentName,
				String oldDepartmentName, String authToken)
	{
		HttpHeaders headers = new HttpHeaders();
		headers.add(ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		headers.add(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		headers.add(RegistrationPath.HEADER_USER_NAME, ThreadLocalContext.getLoginContext().getUserName());

		headers.add(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

		if (!StringUtils.isEmpty(authToken))
		{
			headers.add(AUTHORIZATION, RegistrationConstants.BEARER_WITH_SPACE + authToken);
		}

		// URI (URL) parameters
		Map<String, String> urlParams = new HashMap<String, String>();
		urlParams.put("department_name", oldDepartmentName);

		// Query parameters
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(URL)
					// Add query parameter
					.queryParam("new_department_name", departmentName);

		System.out.println(builder.buildAndExpand(urlParams).toUri());

		if (logger.isInfoEnabled())
		{
			logger.info("Rest end point Url : {}", builder.buildAndExpand(urlParams).toUri());
		}

		HttpEntity<?> httpEntity = new HttpEntity<>(headers);
		return new RestTemplate().exchange(builder.buildAndExpand(urlParams).toUri(),
					HttpMethod.PUT, httpEntity, new ParameterizedTypeReference<T>()
					{
					});

	}

	public static Boolean checkIfSuccess(HttpStatus status)
	{
		return (status == HttpStatus.OK || status == HttpStatus.ACCEPTED
					|| status == HttpStatus.NO_CONTENT || status == HttpStatus.CREATED);
	}

}
