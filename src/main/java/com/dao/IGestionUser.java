package com.dao;

import java.util.List;

public interface IGestionUser {
	
	String verification(User u);
	void registration(User u);
	public void supprimerUser(int id);
	public User rechercherParId(int id);
	public List<User> getAllUsers();
	public List<User> getUserParNom(String username);
	public void modifUser(User u);

}
