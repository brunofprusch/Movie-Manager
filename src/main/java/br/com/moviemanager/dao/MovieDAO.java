package br.com.moviemanager.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.moviemanager.model.Movie;

public class MovieDAO {

private static MovieDAO instance;
	
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
	
	
	public List<Movie> listAll(EntityManager em){
		
		List<Movie> listMovies = new ArrayList<Movie>();
		
		try{
			
			TypedQuery<Movie> query = em.createQuery("select m from Movie m ", Movie.class);
			
			listMovies = query.getResultList();
			
		}catch(NoResultException nre){
			System.out.println("Nenhum filme cadastrado na tabela");
		}
		
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
		em.persist(movie);
		em.flush();
	}
}
