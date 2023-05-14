package com.dao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class GestionProduitImpJpa implements IGestionProduit{
	
	EntityManagerFactory emf;
	EntityManager em;
	EntityTransaction et;
	
	
	public  GestionProduitImpJpa() {
		emf=Persistence.createEntityManagerFactory("punit1");
		em=emf.createEntityManager();
		et=em.getTransaction();
	}
	@Override
	public void ajoutProduit(Produit p) {
		et.begin();
		em.persist(p);
		et.commit();
		
	}
	@Override
	public void supprimeProduit(int id) {
		
	}
	@Override
	public Produit rechercheParId(int id) {
		return em.find(Produit.class, id);
	}
	@Override
	public List<Produit> getaAllProduits() {
	  Query q= em.createQuery("select p from Produit p");
	  return q.getResultList();
	}
	@Override
	public List<Produit> getProduitParNom(String nom) {
		// TOD
		return null;
	}
	@Override
	public void modifProduit(Produit p) {
		// TODO Auto-generated method stub
		
	}

}
