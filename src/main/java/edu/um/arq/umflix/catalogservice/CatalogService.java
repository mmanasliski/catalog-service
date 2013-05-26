package edu.um.arq.umflix.catalogservice;

import java.util.List;

import edu.um.arq.umflix.catalogservice.exception.DaoException;
import edu.um.arq.umflix.catalogservice.mockclasses.Movie;

/**
 * Created with IntelliJ IDEA.
 * User: icalleros
 * Date: 25/05/13
 * Time: 05:15 PM
 *
 * This interface provides the information about movies in UMFlix's Catalog.
 */

public interface CatalogService {
    /*
       Validates the token and returns a list of the movies with titled "name".
    */
    List<Movie> search(String name,String token) throws DaoException;
}