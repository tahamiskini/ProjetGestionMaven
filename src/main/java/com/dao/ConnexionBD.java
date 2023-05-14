package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBD {
	private static Connection connection;
	private ConnexionBD() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost/gestioncomm","root","");
			System.out.println("connexion établie...");
		} catch (ClassNotFoundException e) {
			System.out.println("Probléme  chargement pilote...");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Probléme établissement connexion...");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		if(connection==null)
			new ConnexionBD();
		return connection;
	}
	
	

}
