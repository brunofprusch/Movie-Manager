package br.com.moviemanager.rest;

import br.com.moviemanager.dao.MovieDAO;
import br.com.moviemanager.model.Movie;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path(value="/movie")
public class MovieService {
    public Logger logger = LoggerFactory.getLogger((Class)MovieService.class);

    @GET
    @Path(value="/test")
    @Produces(value={"application/json"})
    public List<Movie> listAll() {
        this.logger.info("TEST: Start test service.");
        ArrayList<Movie> list = new ArrayList<Movie>();
        Movie movieUm = new Movie();
        movieUm.setId(Long.valueOf(1));
        movieUm.setName("A Culpa \u00e9 das Estrelas");
        movieUm.setGenre("Drama/Romance");
        movieUm.setScore(10);
        list.add(movieUm);
        Movie movieDois = new Movie();
        movieDois.setId(Long.valueOf(2));
        movieDois.setName("Cidades de Papel");
        movieDois.setGenre("Aventura/Com\u00e9dia/Romance");
        movieDois.setScore(10);
        list.add(movieDois);
        
        Movie movieTres = new Movie();
        movieTres.setId(Long.valueOf(3));
        movieTres.setName("O Maravilhoso Agora");
        movieTres.setGenre("Drama/Com\u00e9dia/Romance");
        movieTres.setScore(10);
        list.add(movieTres);
        
        Movie movieQuatro = new Movie();
        movieQuatro.setId(Long.valueOf(4));
        movieQuatro.setName("Meu Passado Me Condena 2");
        movieQuatro.setGenre("Comedia/Comedia-Romance");
        movieQuatro.setScore(10);
        list.add(movieQuatro);
        
        this.logger.info("TEST: FINISH test service.");
        return list;
    }

    @GET
    @Path(value="/notAssisted")
    @Produces(value={"application/json"})
    public List<Movie> listMoviesNotAssisted() {
        this.logger.info("NOT ASSISTED MOVIES: Start search list not assisted movies service.");
        EntityManager em = null;
        List listMovie = null;
        try {
            MovieDAO.getInstance();
            em = MovieDAO.createEntityManager();
            this.logger.info("NOT ASSISTED MOVIES: Searching movies not assisted.");
            listMovie = MovieDAO.getInstance().listNotAssisted(em);
        }
        catch (NoResultException nre) {
            this.logger.error("NOT ASSISTED MOVIES: No result. {}", (Throwable)nre);
        }
        catch (Exception e) {
            this.logger.error("NOT ASSISTED MOVIES: Exeception. {}", (Throwable)e);
        }
        finally {
            em.close();
        }
        return listMovie;
    }

    @GET
    @Path(value="/assisted")
    @Produces(value={"application/json"})
    public List<Movie> listMoviesAssisted() {
        this.logger.info("ASSISTED MOVIES: Start search list not assisted movies service.");
        EntityManager em = null;
        List listMovie = null;
        try {
            MovieDAO.getInstance();
            em = MovieDAO.createEntityManager();
            this.logger.info("ASSISTED MOVIES: Searching movies not assisted.");
            listMovie = MovieDAO.getInstance().listAssisted(em);
        }
        catch (NoResultException nre) {
            this.logger.error("ASSISTED MOVIES: No result. {}", (Throwable)nre);
        }
        catch (Exception e) {
            this.logger.error("ASSISTED MOVIES: Exeception. {}", (Throwable)e);
        }
        finally {
            em.close();
        }
        return listMovie;
    }

    @POST
    @Path(value="/create")
    @Consumes(value={"application/json"})
    @Produces(value={"application/json"})
    public Movie createMovie(Movie movie) {
        this.logger.info("CREATE MOVIE: Start create movie service.");
        EntityManager em = null;
        try {
            MovieDAO.getInstance();
            em = MovieDAO.createEntityManager();
            em.getTransaction().begin();
            MovieDAO.getInstance().save(em, movie);
            this.logger.info("CREATE MOVIE: Commit for create movie={}.", (Object)movie);
            em.getTransaction().commit();
        }
        catch (Exception e) {
            this.logger.error("CREATE MOVIE: Exeception. {}", (Throwable)e);
        }
        finally {
            em.close();
        }
        return movie;
    }

    @DELETE
    @Path(value="/flagAsAssisted")
    @Consumes(value={"application/json"})
    public String flagAsAssistedMovie(Movie movie) {
        String code;
        block6 : {
            this.logger.info("FLAG TO ASSISTED: Start delete movie service.");
            EntityManager em = null;
            code = "0";
            try {
                MovieDAO.getInstance();
                em = MovieDAO.createEntityManager();
                em.getTransaction().begin();
                if (movie.getId() != null) {
                    Movie movieLoaded = MovieDAO.getInstance().load(em, movie.getId());
                    movieLoaded.setAssisted(true);
                    movieLoaded.setScore(movie.getScore());
                    movieLoaded.setNote(movie.getNote());
                    em.getTransaction().commit();
                    break block6;
                }
                this.logger.error("FLAG TO ASSISTED: Movie not flag to assisted. Id is null. id={}", (Object)movie.getId());
                code = "1";
            }
            catch (Exception e) {
                this.logger.error("FLAG TO ASSISTED: Exeception. {}", (Throwable)e);
                code = "1";
            }
            finally {
                em.close();
            }
        }
        return code;
    }

    @GET
    @Path(value="/loadById")
    @Produces(value={"application/json"})
    public Movie loadById(@QueryParam(value="id") Long id) {
        Movie movie;
        block6 : {
            this.logger.info("LOAD BY ID: Start load movie by id.");
            EntityManager em = null;
            movie = null;
            try {
                MovieDAO.getInstance();
                em = MovieDAO.createEntityManager();
                em.getTransaction().begin();
                if (id != null) {
                    movie = MovieDAO.getInstance().load(em, id);
                    break block6;
                }
                this.logger.error("LOAD BY ID: Not load movie. Id is null. id={}", (Object)id);
            }
            catch (Exception e) {
                this.logger.error("LOAD BY ID: Exeception. {}", (Throwable)e);
            }
            finally {
                em.close();
            }
        }
        return movie;
    }
}