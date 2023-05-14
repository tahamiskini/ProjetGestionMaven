package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.dao.IGestionUser;

public class GestionUserImp implements IGestionUser {
	Connection connection = ConnexionBD.getConnection();
	
	@Override
	public String verification(User u) {
        String username = null;
        try {
         
            PreparedStatement ps = connection.prepareStatement("SELECT username FROM user WHERE login = ? AND password = ?");
            ps.setString(1, u.getLogin());
            ps.setString(2, u.getPassword());
            
          
            ResultSet rs = ps.executeQuery();
            
            
            if (rs.next()) {
                username = rs.getString("username");
            }
            
         
            rs.close();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return username;
    }
	
	@Override
	public void registration(User u) {
        try {
           
            PreparedStatement ps = connection.prepareStatement("INSERT INTO user (login, password, username) VALUES (?, ?, ?)");
            ps.setString(1, u.getLogin());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getUsername());
            
       
            ps.executeUpdate();
            
         
            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void supprimerUser(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User rechercherParId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUserParNom(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modifUser(User u) {
		// TODO Auto-generated method stub
		
	}
    



}
