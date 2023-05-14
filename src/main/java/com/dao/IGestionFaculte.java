package com.dao;

import java.util.List;

public interface IGestionFaculte {

	public void ajouterFaculte(Faculte f);
	public void supprimerFaculte(int id);
	public Faculte rechercherParId(int id);
	public List<Faculte> getAllFacultes();
	public List<Faculte> getFaculteParNom(String nomFac);
	public void modifFaculte(Faculte f);
}
