package br.com.moviemanager.util;

import javax.persistence.EntityManager;

import br.com.moviemanager.dao.MovieDAO;
import br.com.moviemanager.model.Movie;

public class TableGanerator {

	public static void main(String[] args) {
		EntityManager em = MovieDAO.getInstance().createEntityManager();
		em.getTransaction().begin();
		
		Movie mv = new Movie();
		mv.setName("O Maravilhoso Agora");
		mv.setCountry("EUA");
		mv.setGenre("Drama/Romance");
		mv.setScore(10);
		mv.setYear(2015);
		
		MovieDAO.getInstance().save(em, mv);
		
		em.getTransaction().commit();
		em.close();
	}
}
