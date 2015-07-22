package br.com.moviemanager.rest;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.moviemanager.dao.MovieDAO;
import br.com.moviemanager.model.Movie;

@Path("/filme")
public class FilmeService {

	
	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Movie> listAll() {
		
		List<Movie> list = new ArrayList<Movie>();
		
		Movie movieUm =  new Movie();
		movieUm.setId(1L);
		movieUm.setName("A Culpa é das Estrelas");
		movieUm.setGenre("Drama/Romance");
		movieUm.setScore(10);
		
		list.add(movieUm);
		
		Movie movieDois =  new Movie();
		movieDois.setId(2L);
		movieDois.setName("Cidades de Papel");
		movieDois.setGenre("Aventura/Comédia/Romance");
		movieDois.setScore(10);
		
		list.add(movieDois);
		
		Movie movieTres =  new Movie();
		movieTres.setId(3L);
		movieTres.setName("O Maravilhoso Agora");
		movieTres.setGenre("Drama/Comédia/Romance");
		movieTres.setScore(10);
		
		list.add(movieTres);
		
		Movie movieQuatro =  new Movie();
		movieQuatro.setId(4L);
		movieQuatro.setName("Meu Passado Me Condena 2");
		movieQuatro.setGenre("Comedia/Comedia-Romance");
		movieQuatro.setScore(10);
		
		list.add(movieQuatro);
		
		return list;
	}
	
	
	@GET
	@Path("/filmes")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Movie> listAllMovies(){
		
		EntityManager em = MovieDAO.getInstance().createEntityManager();
		
		List<Movie> listMovie = MovieDAO.getInstance().listAll(em);
		
		return listMovie;
	}
	
	
	@POST
	@Path("/createMovie")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Movie createMovie(Movie movie){
		
		EntityManager em = MovieDAO.getInstance().createEntityManager();
		em.getTransaction().begin();
		
		MovieDAO.getInstance().save(em, movie);
		
		em.getTransaction().commit();
		em.close();
		
		return movie;
	}
	
	
}
