package com.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class GestionCategorieImpJpa implements IGestionCategorie {
	EntityManagerFactory emf;
	EntityManager em;
	EntityTransaction et;
	public   GestionCategorieImpJpa() {
		emf=Persistence.createEntityManagerFactory("punit1");
		em=emf.createEntityManager();
		et=em.getTransaction();
	}
	@Override
	public Categorie rechercheParId(int id) {
		return em.find(Categorie.class, id);
	}
	@Override
	public List<Categorie> getAllCategories() {
		Query q= em.createQuery("select c from Categorie c");
		  return q.getResultList();
}
}