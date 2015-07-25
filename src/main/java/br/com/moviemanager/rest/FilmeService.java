package br.com.moviemanager.rest;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.moviemanager.dao.MovieDAO;
import br.com.moviemanager.model.Movie;

@Path("/movie")
public class FilmeService {

	public Logger logger = LoggerFactory.getLogger(FilmeService.class);
	
	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Movie> listAll() {
		logger.info("TEST: Start test service.");
		
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
		
		logger.info("TEST: FINISH test service.");
		return list;
	}
	
	
	@GET
	@Path("/allMovies")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Movie> listAllMovies(){
		logger.info("ALL MOVIES: Start search all movies service.");
		
		EntityManager em = null;
		List<Movie> listMovie = null;
		
		try{
			em = MovieDAO.getInstance().createEntityManager();
			
			logger.info("ALL MOVIES: Searching all movies.");
			listMovie = MovieDAO.getInstance().listAll(em);
			
		}catch(NoResultException nre){
			logger.error("ALL MOVIES: No result. {}", nre);
		}catch(Exception e){
			logger.error("ALL MOVIES: Exeception. {}", e);
		}finally{
			em.close();
		}

		return listMovie;
	}
	
	
	@POST
	@Path("/createMovie")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Movie createMovie(Movie movie){
		logger.info("CREATE MOVIE: Start create movie service.");
		EntityManager em = null;
		
		try{
			em = MovieDAO.getInstance().createEntityManager();
			em.getTransaction().begin();
			
			MovieDAO.getInstance().save(em, movie);
			logger.info("CREATE MOVIE: Commit for create movie={}.", movie);
			em.getTransaction().commit();
			
		}catch(Exception e){
			logger.error("CREATE MOVIE: Exeception. {}", e);
		}finally{
			em.close();
		}
		
		return movie;
	}
	
	
}
