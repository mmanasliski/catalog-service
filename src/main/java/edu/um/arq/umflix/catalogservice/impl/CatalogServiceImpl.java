package edu.um.arq.umflix.catalogservice.impl;

import edu.um.arq.umflix.catalogservice.CatalogService;
import edu.umflix.authenticationhandler.AuthenticationHandler;
import edu.umflix.authenticationhandler.exceptions.InvalidTokenException;
import edu.umflix.model.Movie;
import edu.umflix.persistence.MovieDao;
import org.apache.log4j.Logger;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;


/**
 *
 * Implementation of the CatalogService.
 * Instances an AuthenticationHandler for token validation.
 * Instances a MovieDao for getting Movie's list, returns it.
 * Throws InvalidTokenException instead if the token was invalid.
 *
 */
@WebService(portName = "CatalogServicePort",
        serviceName = "CatalogServiceWebService",
        targetNamespace = "http://um.org/wsdl")
@Stateless(name = "CatalogService")
public class CatalogServiceImpl implements CatalogService {

    // Configuration file name
//    private static final String PROPERTIES = "conf.dao_factory";
//    private static  ResourceBundle rb = ResourceBundle.getBundle(PROPERTIES);

    // Key for the name of the classes that implement MovieDao and AuthenticationHandler
//    private static final String MOVIE_DAO_IMPL_K ="MOVIE_DAO_IMPL";
//    private static final String AUTH_HANDLER_IMPL_K ="AUTH_HANDLER_IMPL";

    @EJB(beanName = "MovieDao")
    MovieDao movieDao;
    @EJB(beanName = "AuthenticationHandler")
    AuthenticationHandler authHandler;


    protected static Logger logger = Logger.getLogger("CatalogServiceImpl.class");

    public List<Movie> search(String key,String token) throws InvalidTokenException {

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
}

