package com.donalwebapp.admin;

import javax.ejb.Remote;


@Remote
public interface AdminDAO {
	<T> void addRowToDatabase(T entity);
}
