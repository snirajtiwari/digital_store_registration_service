package org.digitalstore.registration.bo;

import java.io.Serializable;
import java.util.List;

import org.digitalstore.registration.dao.entity.User;

public interface UserBo extends Serializable
{

	User createUser(User user);

	User updateUser(User user);

	List<User> getUserList(String userName);

}
