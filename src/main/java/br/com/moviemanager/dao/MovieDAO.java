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

public class MovieDAO {
    private static MovieDAO instance;
    public Logger logger = LoggerFactory.getLogger((Class)MovieDAO.class);

    public static MovieDAO getInstance() {
        if (instance == null) {
            instance = new MovieDAO();
        }
        return instance;
    }

    public static EntityManager createEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory((String)"moviemanager");
        EntityManager em = factory.createEntityManager();
        return em;
    }

    public List<Movie> listAll(EntityManager em) throws NoResultException {
        List<Movie> listMovies = new ArrayList();
        TypedQuery query = em.createQuery("select m from Movie m ", (Class)Movie.class);
        listMovies = query.getResultList();
        return listMovies;
    }

    public List<Movie> listNotAssisted(EntityManager em) {
        List<Movie> listMovies = new ArrayList();
        TypedQuery query = em.createQuery("select m from Movie m where m.assisted = false", (Class)Movie.class);
        listMovies = query.getResultList();
        return listMovies;
    }

    public List<Movie> listAssisted(EntityManager em) {
        List<Movie> listMovies = new ArrayList();
        TypedQuery query = em.createQuery("select m from Movie m where m.assisted = true", (Class)Movie.class);
        listMovies = query.getResultList();
        return listMovies;
    }

    public Movie load(EntityManager em, Long id) {
        Movie movie = new Movie();
        try {
            movie = (Movie)em.find((Class)Movie.class, (Object)id);
        }
        catch (Exception e) {
            System.out.println("Erro ao buscar o Filme de id = " + id);
        }
        return movie;
    }

    public void save(EntityManager em, Movie movie) {
        this.logger.info("SAVE: Persist movie={}.", (Object)movie);
        em.persist((Object)movie);
        em.flush();
        this.logger.info("SAVE: id={}.", (Object)movie.getId());
    }

    public void delete(EntityManager em, Movie movie) {
        em.remove((Object)movie);
        this.logger.info("DELETE: Movie deleted. movie={}.", (Object)movie);
    }
}