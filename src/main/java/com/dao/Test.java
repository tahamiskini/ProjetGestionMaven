package com.dao;

import java.sql.Connection;
import java.util.List;

public class Test {
	
	public static void main(String[]args) {
		//GestionProduitImp gestion = new GestionProduitImp();
		//Produit p1 = new Produit(2,"T-Shirt",50,30);
		//gestion.modifProduit(p1);
		//gestion.ajouterProduit(p1);
		//Produit p2 = new Produit("bb",60,70);
		//gestion.ajouterProduit(p2);
		//gestion.supprimerProduit(7);
		//List<Produit> lp = gestion.getAllProduits();
		//List<Produit> lp2 = gestion.getProduitParNom("shirt");
		//System.out.println(gestion.rechercherParId(4));
		//System.out.println(lp2);
		IGestionUser user = new GestionUserImpJpa();
		
	}

	
}
