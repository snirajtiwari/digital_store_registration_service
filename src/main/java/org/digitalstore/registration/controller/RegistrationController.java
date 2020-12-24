package org.digitalstore.registration.controller;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.digitalstore.registration.bo.UserBo;
import org.digitalstore.registration.dao.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController implements IRegistrationController {
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	private UserBo userBo;

	@Override
	public ResponseEntity<List<User>> getUserList(String userName) {
		logger.info("Get Userlist : userName : {}", userName);
		return new ResponseEntity<List<User>>(userBo.getUserList(userName), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<User> createUser(User user) throws Exception {
		logger.info("Add User : User Obj : {}", user);

		return new ResponseEntity<User>(userBo.createUser(user), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<User> updateUser(User user) {
		logger.info("Update User : User Obj : {}", user);
		return new ResponseEntity<User>(userBo.updateUser(user), HttpStatus.OK);
	}

}
