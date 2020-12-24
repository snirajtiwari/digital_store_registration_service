package org.digitalstore.registration.dao.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(value="user")
public class User extends CommonEntity {

	private String userName;
	private String firstName;
	private String lastName;
	private String password;
	private String roleName;
	private Integer invalidLoginCount;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getInvalidLoginCount() {
		return invalidLoginCount;
	}
	public void setInvalidLoginCount(Integer invalidLoginCount) {
		this.invalidLoginCount = invalidLoginCount;
	}
	
	
}
