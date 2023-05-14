package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestionProduitImp implements IGestionProduit {
	
	Connection connection = ConnexionBD.getConnection();

	@Override
	public void ajoutProduit(Produit p) {
		try {
			PreparedStatement ps=connection.prepareStatement("insert into produit (nom,prix,quantite) values (?,?,?)");
			ps.setString(1, p.getNom());
			ps.setDouble(2, p.getPrix());
			ps.setInt(3, p.getQuantite());
			ps.executeUpdate(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
   
	@Override
	public void supprimeProduit(int id) {
		try {
			PreparedStatement ps=connection.prepareStatement("delete from produit where id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

	@Override
	public Produit rechercheParId(int id) {
		Produit p= null;
		PreparedStatement ps;
		try {
			 ps=connection.prepareStatement("SELECT * FROM produit WHERE id = ?");
			 ps.setInt(1, id);
		     ResultSet rs = ps.executeQuery();
		   if  (rs.next()) {
			   p = new Produit(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4));				
		   }
		    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return p;
	}

	@Override
	public List<Produit> getaAllProduits() {
		List<Produit> liste= new ArrayList<Produit>();
		try {
			PreparedStatement ps=connection.prepareStatement("select * from produit");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Produit p = new Produit(rs.getInt(1),rs.getNString(2),rs.getDouble(3),rs.getInt(4));
				liste.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return liste;
	}
	

	@Override
	public List<Produit> getProduitParNom(String nom) {
		List<Produit> liste= new ArrayList<Produit>();
		try {
			PreparedStatement ps=connection.prepareStatement("select * from produit where nom like ?");
			ps.setString(1,"%" +nom+ "%");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Produit p = new Produit(rs.getInt(1),rs.getNString(2),rs.getDouble(3),rs.getInt(4));
				liste.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return liste;
	}

	@Override
	public void modifProduit(Produit p) {
		PreparedStatement ps;
		try {
			ps=connection.prepareStatement("UPDATE produit SET nom=? , prix=?, quantite=? where id=?");
			ps.setString(1, p.getNom());
		    ps.setDouble(2, p.getPrix());
		    ps.setInt(3, p.getQuantite());
		    ps.setInt(4, p.getId());
		    ps.executeUpdate();
		  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
