package edu.um.arq.umflix.catalogservice.impl;

import edu.um.arq.umflix.catalogservice.CatalogService;
import java.util.List;
import java.util.ResourceBundle;
import edu.um.arq.umflix.catalogservice.exception.DaoException;
import edu.um.arq.umflix.catalogservice.mockclasses.Movie;
import edu.um.arq.umflix.catalogservice.mockclasses.MovieDao;

/**
 * Created with IntelliJ IDEA.
 * User: icalleros
 * Date: 25/05/13
 * Time: 07:40 PM
 * Implementation of the CatalogService.
 */

public class CatalogServiceImpl implements CatalogService {

    // properties, configuration file
    private static final String PROPERTIES = "properties";

    // resource bundle for those properties
    private static  ResourceBundle rb = ResourceBundle.getBundle(PROPERTIES);

    // key for the name of the class that implements MovieDao
    private static final String MOVIE_DAO_IMPL_K = "MOVIE_DAO_IMPL";

    public List<Movie> search(String movie,String token) throws DaoException {
        MovieDao movieDao = (MovieDao)getDao(rb.getString(MOVIE_DAO_IMPL_K));
           /*
                   For impl:
                            Dependency: ModelStorage
                            Movie
                            MovieDao has List <Movie> getMovieList(String variable);
            */
        return null;
    }

    /*
        Creates a new instance of a class, given its name - dao.
        Throws DaoException if there's an error and prints the stack trace.
     */
    public static Object getDao(String dao) throws DaoException {
        try {
            return Class.forName(dao).newInstance();
        } catch (InstantiationException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new DaoException();
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new DaoException();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new DaoException();
        }
    }
}

