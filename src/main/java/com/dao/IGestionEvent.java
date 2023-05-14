package com.dao;

import java.util.List;

public interface IGestionEvent {

	public void ajouterEvent(Event e);
	public void supprimerEvent(int id);
	public Event rechercherParId(int id);
	public List<Event> getAllEvents();
	public List<Event> getEventParNom(String nomEvent);
	public void modifEvent(Event e);
}
