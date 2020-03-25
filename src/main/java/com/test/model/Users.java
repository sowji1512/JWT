package com.test.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class Users {
 @Id
 @GeneratedValue(strategy=GenerationType.IDENTITY)
 long id;
 
 @NotBlank
 @Size(min=1,max=50)
 String name;
 @NotBlank
 @Size(min=1,max=50)
 String userName;
 @NotBlank
 @Size(min=5,max=50)
 @Email
 String userEmail;
 @NotBlank
 @Size(min=8)
 String password;
 @NotNull
 int roles;
 

 public Users(){
	 
 }
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Users(@NotBlank @Size(min = 1, max = 50) String userName, @NotBlank @Size(min = 8) String password) {
	super();
	this.userName = userName;
	this.password = password;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getUserEmail() {
	return userEmail;
}
public void setUserEmail(String userEmail) {
	this.userEmail = userEmail;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public int getRoles() {
	return roles;
}
public void setRoles(int roles) {
	this.roles = roles;
}
}
