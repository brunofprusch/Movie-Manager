package br.com.moviemanager.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
	
	/*
	public List<MovieDTO> listAll(EntityManager em){
		
		List<Movie> listFilmes = new ArrayList<Movie>();
		
		try{
			
			TypedQuery<Movie> query = em.createQuery("select m from Movie m ", Movie.class);
			
			listFilmes = query.getResultList();
			
		}catch(NoResultException nre){
			System.out.println("Nenhum filme cadastrado na tabela");
		}
		
		return MovieDTO.listFilmeToListFilmeDTO(listFilmes);
	}
	
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
