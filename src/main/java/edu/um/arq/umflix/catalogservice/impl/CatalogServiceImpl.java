package edu.um.arq.umflix.catalogservice.impl;

import edu.um.arq.umflix.catalogservice.CatalogService;
import java.util.List;
import java.util.ResourceBundle;
import edu.um.arq.umflix.catalogservice.exception.DaoException;
import edu.um.arq.umflix.catalogservice.mockclasses.AuthenticationHandler;
import edu.um.arq.umflix.catalogservice.mockclasses.Movie;
import edu.um.arq.umflix.catalogservice.mockclasses.MovieDao;
import edu.um.arq.umflix.catalogservice.mockclasses.InvalidTokenException;
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
    private static final String PROPERTIES = "dao_factory";
    private static  ResourceBundle rb = ResourceBundle.getBundle(PROPERTIES);

    // Key for the name of the classes that implement MovieDao and AuthenticationHandler
    private static final String MOVIE_DAO_IMPL_K = "MOVIE_DAO_IMPL";
    private static final String AUTH_HANDLER_IMPL_K = "AUTH_HANDLER_IMPL";

    private static Logger logger = Logger.getLogger("CatalogServiceImpl.class");

    public List<Movie> search(String movie,String token) throws DaoException, InvalidTokenException {
        MovieDao movieDao = (MovieDao)getDao(rb.getString(MOVIE_DAO_IMPL_K));
        AuthenticationHandler authHandler = (AuthenticationHandler)getDao(rb.getString(AUTH_HANDLER_IMPL_K));
           /*
                   For impl:
                            Dependency: ModelStorage
                            Movie
                            MovieDao has List <Movie> getMovieList(String variable);

                            AuthenticationHandler
                            has public boolean validateToken(String token,List<Role> roles) throws InvalidTokenException;
                                public String authenticate(User user);
                                public User getUserOfToken(String token) throws InvalidTokenException;
            */
        return null;
    }

    /*
    *
    *   @param dao Name of the class to get.
    *   @return Returns a new instance of the class.
    *   @throws DaoException Throws when there was an error accessing persistence classes.
    *
    */
    private static Object getDao(String dao) throws DaoException {
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

