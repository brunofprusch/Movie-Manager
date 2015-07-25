package br.com.moviemanager.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.moviemanager.model.Movie;
import br.com.moviemanager.rest.FilmeService;

public class MovieDAO {

private static MovieDAO instance;
	
public Logger logger = LoggerFactory.getLogger(MovieDAO.class);

	public static MovieDAO getInstance(){
		if(instance == null){
			instance = new MovieDAO();
		}
		return instance;
	}
	
	
	public static EntityManager createEntityManager(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("moviemanager");
		EntityManager em = factory.createEntityManager();
		return em;
	}
	
	
	public List<Movie> listAll(EntityManager em) throws NoResultException{
		
		List<Movie> listMovies = new ArrayList<Movie>();
		
		TypedQuery<Movie> query = em.createQuery("select m from Movie m ", Movie.class);
			
		listMovies = query.getResultList();
			
		return listMovies;
	}
	
	/*
	public MovieDTO load(EntityManager em, Long id){
		
		Movie movie = new Movie();
		
		try{
			
			movie = em.find(Movie.class, id);
		
		}catch(Exception e){
			System.out.println("Erro ao buscar o Filme de id = " + id);
		}
		
		return MovieDTO.filmeToFilmeDTO(movie);
	}
	*/
	
	public void save(EntityManager em, Movie movie){
		logger.info("SAVE: Persist movie={}.", movie);
		em.persist(movie);
		em.flush();
		logger.info("SAVE: id={}.", movie.getId());
	}
}
