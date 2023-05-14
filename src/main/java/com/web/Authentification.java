package com.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
//import javax.servlet.http.HttpSession;

import com.dao.GestionProduitImp;
import com.dao.GestionUserImp;
import com.dao.IGestionProduit;
import com.dao.IGestionUser;
import com.dao.Produit;
import com.dao.User;

@WebServlet(urlPatterns = {"/login"})
public class Authentification extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Authentification () {
        super();
    }
        IGestionUser user;
        public void init() throws ServletException {
        	user= new GestionUserImp();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path  = request.getServletPath();
		if(path.equals("/login")) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    String path  = request.getServletPath();
		    if(path.equals("/login")) {
		    	String login = request.getParameter("login");
				String password = request.getParameter("password");
				String s = user.verification(new User(login, password));
				if(s !=null) {
					HttpSession session = request.getSession();
					session.setAttribute("username", s);
					response.sendRedirect(request.getContextPath()+"/listeProduits");
				}
				else {
					request.setAttribute("message","Erreur login ou mdp");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
		    }
			
	
		
			
		
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
