package com.donalwebapp.daoimpl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.donalwebapp.admin.AdminDAO;
import com.donalwebapp.entities.Users;

@Stateless(name="online")
@LocalBean
public class AdminDAOImpl implements AdminDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	public AdminDAOImpl(){
		
	}
	
	public AdminDAOImpl(final EntityManager entityManager){
		this.entityManager = entityManager;
	}
	

	public <T> void addRowToDatabase(T entity) {
		if(entityManager.contains(entity)){
			entityManager.persist(entity);
		}
		else{
			entityManager.merge(entity);
		}
	}

	public List<Users> getAllUsers() {

		final Query query = entityManager.createQuery("SELECT u FROM Users u");
		System.out.println(query);
		return query.getResultList();
	
	}
	
	public Users getUserById(String id){
		return entityManager.find(Users.class,id);
	}
	
	public void removeUser(String user){
		entityManager.remove(getUserById(user));
		System.out.println(user);
	}


}
