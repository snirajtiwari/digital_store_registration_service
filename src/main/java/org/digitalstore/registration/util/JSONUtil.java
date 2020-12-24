package org.digitalstore.registration.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * The Class SequenceGenerator.
 */
public class JSONUtil
{

	private static ObjectMapper objectMapper = new ObjectMapper();

	static
	{
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.registerModule(new JavaTimeModule());
	}

	/**
	 * To json string.
	 * @param obj the obj
	 * @return the string
	 * @throws JsonProcessingException the json processing exception
	 */
	public static String toJsonString(Object obj) throws JsonProcessingException
	{
		return objectMapper.writeValueAsString(obj);
	}

	/**
	 * To object.
	 * @param <T> the generic type
	 * @param json the json
	 * @param clazz the clazz
	 * @return the t
	 * @throws Exception the exception
	 */
	public static <T> T toObject(String json, Class<T> clazz) throws Exception
	{
		return objectMapper.readValue(json, clazz);
	}
}
