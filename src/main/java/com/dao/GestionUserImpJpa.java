package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class GestionUserImpJpa implements IGestionUser {
	
	EntityManagerFactory emf;
	EntityManager em;
	EntityTransaction et;
	
	public GestionUserImpJpa() {
		emf=Persistence.createEntityManagerFactory("punit1");
		em=emf.createEntityManager();
		et=em.getTransaction();
	}

	@Override
	public String verification(User u) {
	
		Query q = em.createQuery("select u from User u where u.login = :l and u.password = :p");
		q.setParameter("l", u.getLogin());
		q.setParameter("p", u.getPassword());
		
		User usr = (User) q.getSingleResult();
		
		return usr.getRole();
	}
	@Override
	public void registration(User u) {

		et.begin();
		em.persist(u);
		et.commit();
		
	}

	@Override
	public void supprimerUser(int id) {

		User u=em.find(User.class, id);
		if(u != null){
		et.begin();
		em.remove(u);
		et.commit();
		}
		
	}

	@Override
	public User rechercherParId(int id) {

		return em.find(User.class, id);
	}

	@Override
	public List<User> getAllUsers() {

		Query q=em.createQuery("select u from User u");

		return q.getResultList();
	}

	@Override
	public List<User> getUserParNom(String username) {

		Query q=em.createQuery("select u from User u where u.username like :a");
		q.setParameter("a", "%"+username+"%");
		return q.getResultList();
	}

	@Override
	public void modifUser(User u) {

		et.begin();
	    em.merge(u);
	    et.commit();
		
	}

}
