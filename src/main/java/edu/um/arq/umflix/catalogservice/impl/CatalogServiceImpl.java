package edu.um.arq.umflix.catalogservice.impl;

import edu.um.arq.umflix.catalogservice.CatalogService;
import java.util.List;
import java.util.ResourceBundle;

import edu.um.arq.umflix.catalogservice.exception.DaoException;
import edu.umflix.authenticationhandler.exceptions.InvalidTokenException;
import edu.umflix.authenticationhandler.AuthenticationHandler;
import edu.umflix.model.Movie;
import edu.umflix.persistence.MovieDao;
import org.apache.log4j.Logger;

/**
 *
 * Implementation of the CatalogService.
 * Instances an AuthenticationHandler for token validation.
 * Instances a MovieDao for getting Movie's list, returns it.
 * Throws InvalidTokenException instead if the token was invalid.
 *
 */

public class CatalogServiceImpl implements CatalogService {

    // Configuration file name
    private static final String PROPERTIES = "conf.dao_factory";
    private static  ResourceBundle rb = ResourceBundle.getBundle(PROPERTIES);

    // Key for the name of the classes that implement MovieDao and AuthenticationHandler
    private static final String MOVIE_DAO_IMPL_K ="MOVIE_DAO_IMPL";
    private static final String AUTH_HANDLER_IMPL_K ="AUTH_HANDLER_IMPL";

    protected static Logger logger = Logger.getLogger("CatalogServiceImpl.class");

    public List<Movie> search(String key,String token) throws DaoException, InvalidTokenException {
        MovieDao movieDao = (MovieDao)getDao(rb.getString(MOVIE_DAO_IMPL_K));
        AuthenticationHandler authHandler = (AuthenticationHandler)getDao(rb.getString(AUTH_HANDLER_IMPL_K));

        //Authentication.
        if (authHandler.validateToken(token)){
            // Movie search
            if(key==null){
                //Null string movie for getting all movies
                return movieDao.getMovieList();
            } else {
                //Not null string is the key for searching the movie.
                return movieDao.getMovieListByKey(key);
            }
        } else {
            throw new InvalidTokenException();
        }

    }

    /*
    *
    *   @param dao Name of the class to get.
    *   @return Returns a new instance of the class.
    *   @throws DaoException Throws when there was an error accessing persistence classes.
    *
    */
    protected Object getDao(String dao){
        try {
            return Class.forName(dao).newInstance();
        } catch (Exception exc) {
            if(exc instanceof InstantiationException || exc instanceof IllegalAccessException ||
                    exc instanceof ClassNotFoundException || exc instanceof NoSuchFieldException ) {
                logger.error("Cannot getDao",exc);
                throw new DaoException();
            } else {
                throw new RuntimeException(exc);
            }
        }
    }
}

