package com.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.dao.Club;
import com.dao.Event;
import com.dao.Faculte;
import com.dao.GestionClubImpJpa;
import com.dao.GestionEventImpJpa;
import com.dao.GestionFaculteImpJpa;
import com.dao.GestionUserImpJpa;
import com.dao.IGestionClub;
import com.dao.IGestionEvent;
import com.dao.IGestionFaculte;
import com.dao.IGestionUser;
import com.dao.User;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet(urlPatterns={"/listeFaculte","/addFaculte","/supprimerFaculte","/modifierFaculte","/rechercherFaculte","/listeClub","/addClub","/supprimerClub","/modifierClub","/rechercherClub","/listeClient","/addClient","/supprimerClient","/modifierClient","/rechercherClient","/listeProjet","/addProjet","/supprimerProjet","/modifierProjet","/rechercherProjet","/listeTache","/addTache","/affecterUserClub","/supprimerTache","/modifierTache","/rechercherTache","/login" , "/logout" , "/register","/listeUser","/supprimerUser","/modifierUser","/rechercherUser"})
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirstServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

    IGestionUser user;
    IGestionFaculte faculte;
    IGestionClub club;
    IGestionEvent event;
    
    public void init() throws ServletException{
    	user = new GestionUserImpJpa();
    	faculte = new GestionFaculteImpJpa();
    	club = new GestionClubImpJpa();
    	event = new GestionEventImpJpa();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path=request.getServletPath();
		HttpSession session = request.getSession(false);
		if (path.equals("/register")) {
	        request.getRequestDispatcher("register.jsp").forward(request, response);
	    }
		if(session == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

		else if(path.equals("/listeFaculte")) {
			List<Faculte> liste = faculte.getAllFacultes();
			int itemsPerPage = 5; 
			int currentPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1; // Get the current page number from the request parameter

			request.setAttribute("listeF", liste);
			int totalFacultes = liste.size();
		    int totalPages = (int) Math.ceil((double) totalFacultes / itemsPerPage);

		    int startIndex = (currentPage - 1) * itemsPerPage;
		    int endIndex = Math.min(startIndex + itemsPerPage, totalFacultes);

		    List<Faculte> facultesForPage = liste.subList(startIndex, endIndex);

		    request.setAttribute("listeF", facultesForPage);
		    request.setAttribute("totalPages", totalPages);
		    request.setAttribute("currentPage", currentPage);
			request.getRequestDispatcher("Facultes.jsp").forward(request, response);
		}
		else if (path.equals("/addFaculte")) {
			request.getRequestDispatcher("ajouterFaculte.jsp").forward(request, response);
		}
		else if (path.equals("/supprimerFaculte")) {
			int id = Integer.parseInt(request.getParameter("id"));
			faculte.supprimerFaculte(id);
			response.sendRedirect(request.getContextPath()+"/listeFaculte");
		}
		else if(path.equals("/modifierFaculte")) {
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("faculte", faculte.rechercherParId(id));
			request.getRequestDispatcher("modifierFaculte.jsp").forward(request, response);
		}

		
		else if(path.equals("/listeClub")) {
			List<Club> liste = club.getAllClubs();
			int itemsPerPage = 5; // Number of items to display per page
			int currentPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1; // Get the current page number from the request parameter

			request.setAttribute("listeC", liste);
			int totalClubs = liste.size();
		    int totalPages = (int) Math.ceil((double) totalClubs / itemsPerPage);

		    int startIndex = (currentPage - 1) * itemsPerPage;
		    int endIndex = Math.min(startIndex + itemsPerPage, totalClubs);

		    List<Club> clubsForPage = liste.subList(startIndex, endIndex);

		    request.setAttribute("listeC", clubsForPage);
		    request.setAttribute("totalPages", totalPages);
		    request.setAttribute("currentPage", currentPage);
			request.getRequestDispatcher("Clubs.jsp").forward(request, response);
		}
		else if (path.equals("/addClub")) {
			request.setAttribute("facultes", faculte.getAllFacultes());
			request.getRequestDispatcher("ajouterClub.jsp").forward(request, response);
		}
		else if (path.equals("/supprimerClub")) {
			int id = Integer.parseInt(request.getParameter("id"));
			club.supprimerClub(id);
			response.sendRedirect(request.getContextPath()+"/listeClub");
		}
		else if(path.equals("/modifierClub")) {
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("club", club.rechercherParId(id));
			request.setAttribute("facultes", faculte.getAllFacultes());
			request.getRequestDispatcher("modifierClub.jsp").forward(request, response);
		}
		
		else if (path.equals("/affecterUserClub")) {
			request.setAttribute("clubs", club.getAllClubs());
			request.setAttribute("users", user.getAllUsers());
			request.getRequestDispatcher("affecterUserClub.jsp").forward(request, response);
		}
		
		else if(path.equals("/listeEvent")) {
			List<Event> liste = event.getAllEvents();
			int itemsPerPage = 5; // Number of items to display per page
			int currentPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1; // Get the current page number from the request parameter

			request.setAttribute("listeE", liste);
			int totalEvents = liste.size();
		    int totalPages = (int) Math.ceil((double) totalEvents / itemsPerPage);

		    int startIndex = (currentPage - 1) * itemsPerPage;
		    int endIndex = Math.min(startIndex + itemsPerPage, totalEvents);

		    List<Event> eventsForPage = liste.subList(startIndex, endIndex);

		    request.setAttribute("listeE", eventsForPage);
		    request.setAttribute("totalPages", totalPages);
		    request.setAttribute("currentPage", currentPage);
			request.getRequestDispatcher("Events.jsp").forward(request, response);
		}
		else if (path.equals("/addEvent")) {
			request.setAttribute("clubs", club.getAllClubs());
			request.getRequestDispatcher("ajouterEvent.jsp").forward(request, response);
		}
		else if (path.equals("/supprimerEvent")) {
			int id = Integer.parseInt(request.getParameter("id"));
			event.supprimerEvent(id);
			response.sendRedirect(request.getContextPath()+"/listeEvent");
		}
		else if(path.equals("/modifierEvent")) {
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("event", event.rechercherParId(id));
			request.setAttribute("clubs", club.getAllClubs());
			request.getRequestDispatcher("modifierEvent.jsp").forward(request, response);
		}
		
		else if(path.equals("/login")) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		/*else if (path.equals("/register")) {
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}*/
		else if (path.equals("/logout")) {
			if(session != null) {
				session.invalidate();
				response.sendRedirect(request.getContextPath()+"/login");
				
			}
			
		}
		
		else if (path.equals("/")) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		else if(path.equals("/listeUser")) {
			List<User> liste = user.getAllUsers();
			int itemsPerPage = 4; // Number of items to display per page
			int currentPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1; // Get the current page number from the request parameter

			request.setAttribute("listeU", liste);
			int totalUsers = liste.size();
		    int totalPages = (int) Math.ceil((double) totalUsers / itemsPerPage);

		    int startIndex = (currentPage - 1) * itemsPerPage;
		    int endIndex = Math.min(startIndex + itemsPerPage, totalUsers);

		    List<User> usersForPage = liste.subList(startIndex, endIndex);

		    request.setAttribute("listeU", usersForPage);
		    request.setAttribute("totalPages", totalPages);
		    request.setAttribute("currentPage", currentPage);
			request.getRequestDispatcher("Users.jsp").forward(request, response);
		}

		else if (path.equals("/supprimerUser")) {
			int id = Integer.parseInt(request.getParameter("id"));
			user.supprimerUser(id);
			response.sendRedirect(request.getContextPath()+"/listeUser");
		}
		else if(path.equals("/modifierUser")) {
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("user", user.rechercherParId(id));
			request.getRequestDispatcher("modifierUser.jsp").forward(request, response);
		}
		
	

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path=request.getServletPath();
		


		 
		 if(path.equals("/addFaculte")) {
				String nomFac=request.getParameter("nomFac");
				faculte.ajouterFaculte(new Faculte (nomFac));
				response.sendRedirect(request.getContextPath()+"/listeFaculte");
			}
			 else if(path.equals("/rechercherFaculte")) {
					String txt = request.getParameter("rech");
					request.setAttribute("listeF", faculte.getFaculteParNom(txt));
					request.getRequestDispatcher("Facultes.jsp").forward(request, response);

				}
			 else if(path.equals("/modifierFaculte")) {
					int id = Integer.parseInt(request.getParameter("id"));
					String nomFac = request.getParameter("nomFac");

					
					faculte.modifFaculte(new Faculte (id,nomFac));
					response.sendRedirect(request.getContextPath()+"/listeFaculte");
				}

		 
			 else if(path.equals("/addClub")) {
					String nomClub=request.getParameter("nomClub");
					String descriptionClub=request.getParameter("descriptionClub");
					int idfaculte = Integer.parseInt(request.getParameter("idfaculte"));
					Faculte f = faculte.rechercherParId(idfaculte);
					club.ajouterClub(new Club (nomClub,descriptionClub,f));
					response.sendRedirect(request.getContextPath()+"/listeClub");
				}
				 else if(path.equals("/rechercherClub")) {
						String txt = request.getParameter("rech");
						request.setAttribute("listeC", club.getClubParNom(txt));
						request.getRequestDispatcher("Clubs.jsp").forward(request, response);

					}
				 else if(path.equals("/modifierClub")) {
						int id = Integer.parseInt(request.getParameter("id"));
						String nomClub = request.getParameter("nomClub");
						String descriptionClub = request.getParameter("descriptionClub");
						int idfaculte = Integer.parseInt(request.getParameter("idfaculte"));
						Faculte f = faculte.rechercherParId(idfaculte);				
						club.modifClub(new Club (id,nomClub,descriptionClub,f));
						response.sendRedirect(request.getContextPath()+"/listeClub");
					}
		 
				 else if(path.equals("/affecterUserClub")) {
						int idclub = Integer.parseInt(request.getParameter("idclub"));
						
						int iduser = Integer.parseInt(request.getParameter("iduser"));
						
						club.affecterUserClub(iduser, idclub);
						response.sendRedirect(request.getContextPath()+"/listeClub");
					}
		 
				 else if(path.equals("/addEvent")) {
						String nomEvent=request.getParameter("nomEvent");
						String descriptionEvent=request.getParameter("descriptionEvent");
						int idclub = Integer.parseInt(request.getParameter("idclub"));
						Club c = club.rechercherParId(idclub);
						event.ajouterEvent(new Event (nomEvent,descriptionEvent,c));
						response.sendRedirect(request.getContextPath()+"/listeEvent");
					}
					 else if(path.equals("/rechercherEvent")) {
							String txt = request.getParameter("rech");
							request.setAttribute("listeE", event.getEventParNom(txt));
							request.getRequestDispatcher("Events.jsp").forward(request, response);

						}
					 else if(path.equals("/modifierEvent")) {
							int id = Integer.parseInt(request.getParameter("id"));
							String nomEvent = request.getParameter("nomEvent");
							String descriptionEvent = request.getParameter("descriptionEvent");
							int idclub = Integer.parseInt(request.getParameter("idclub"));
							Club c = club.rechercherParId(idclub);				
							event.modifEvent(new Event (id,nomEvent,descriptionEvent,c));
							response.sendRedirect(request.getContextPath()+"/listeEvent");
						}
		 

		 
				 else if(path.equals("/login")) {
				String login=request.getParameter("login");
				String password=request.getParameter("password");
				String r= user.verification(new User(login,password));
				if(r != null) {
					HttpSession session = request.getSession();
					session.setAttribute("role", r);

					
					response.sendRedirect(request.getContextPath()+"/listeFaculte");			
				}
				else {
					request.setAttribute("message","Erreur login ou mdp");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			}
			else if(path.equals("/register")) {
				String username=request.getParameter("username");
				String login=request.getParameter("login");
				String password=request.getParameter("password");
				String role=request.getParameter("role");
				user.registration(new User(username,login,password,role));
				response.sendRedirect(request.getContextPath()+"/login");	

			}

		 
			 else if(path.equals("/rechercherUser")) {
					String txt = request.getParameter("rech");
					request.setAttribute("listeU", user.getUserParNom(txt));
					request.getRequestDispatcher("Users.jsp").forward(request, response);

				}
			 else if(path.equals("/modifierUser")) {
					int id = Integer.parseInt(request.getParameter("id"));
					String username = request.getParameter("username");
					String login = request.getParameter("login");
					String password = request.getParameter("password");
					String role = request.getParameter("role");

					
					user.modifUser(new User (id,username,login,password,role));
					response.sendRedirect(request.getContextPath()+"/listeUser");
				}

	}

}
