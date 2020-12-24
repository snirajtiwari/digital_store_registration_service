package org.digitalstore.registration.bo.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.digitalstore.registration.bo.UserBo;
import org.digitalstore.registration.dao.UserDao;
import org.digitalstore.registration.dao.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class UserBoImpl implements UserBo {

	@Autowired
	private UserDao userDao;

	/**
	 * 
	 */
	private static final long serialVersionUID = 8614406582274469734L;

	@Override
	public User createUser(User user) {
		// Encrypt Password and save in DB
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		user.updateAuditInfo();
		return userDao.insert(user);
	}

	@Override
	public User updateUser(User user) {

		// Update Modifier Info
		user.updateModifiedInfo();

		return userDao.save(user);
	}

	@Override
	public List<User> getUserList(String userName) {
		if (StringUtils.isNotEmpty(userName)) {
			return userDao.findByUserName(userName);
		} else {
			return userDao.findAll();
		}
	}
}
