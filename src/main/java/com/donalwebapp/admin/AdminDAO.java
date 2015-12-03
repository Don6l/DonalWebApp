package com.donalwebapp.admin;

import java.util.List;

import javax.ejb.Remote;

import com.donalwebapp.entities.Users;

@Remote
public interface AdminDAO {
	<T> void addRowToDatabase(T entity);
	List<Users> getAllUsers();
	void removeUser(String user);
	Users getUserById(String id);
}
