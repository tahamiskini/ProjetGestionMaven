package com.dao;
import java.util.List;

public interface IGestionCategorie {
	public Categorie rechercheParId(int id);
	public List<Categorie> getAllCategories();

}
