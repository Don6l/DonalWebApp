package com.donalwebapp.admin;


public interface AdminDAO {
	<T> void addRowToDatabase(T entity);
}
