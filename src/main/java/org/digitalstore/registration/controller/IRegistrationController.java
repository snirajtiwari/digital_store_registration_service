package org.digitalstore.registration.controller;

import java.util.List;

import javax.validation.Valid;

import org.digitalstore.registration.constant.RegistrationPath;
import org.digitalstore.registration.constant.SwaggerConstants;
import org.digitalstore.registration.dao.entity.User;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import io.swagger.annotations.Api;

@Api(tags = { SwaggerConstants.REGISTRATION_TAG })
public interface IRegistrationController {

	@RequestMapping(value = RegistrationPath.USERS, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	ResponseEntity<List<User>> getUserList(
			@RequestParam(value = RegistrationPath.USER_NAME, required = false) String userName);

	@RequestMapping(value = RegistrationPath.USER, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	ResponseEntity<User> createUser(@Valid @RequestBody User user) throws Exception;

	@RequestMapping(value = RegistrationPath.USER, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	ResponseEntity<User> updateUser(@Valid @RequestBody User user);
}
