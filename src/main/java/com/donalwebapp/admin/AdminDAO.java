package com.donalwebapp.admin;

import java.util.List;

import com.donalwebapp.entities.Users;


public interface AdminDAO {
	<T> void addRowToDatabase(T entity);
	List<Users> getAllUsers();
}
