package org.digitalstore.registration.dao;

import java.util.List;

import org.digitalstore.registration.dao.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends MongoRepository<User, String> {
	
	List<User> findByUserName(String userName);

	void deleteByUserName(String departmentName, String userName);
}
