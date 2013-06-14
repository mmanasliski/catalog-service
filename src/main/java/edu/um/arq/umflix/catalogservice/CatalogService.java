package edu.um.arq.umflix.catalogservice;

import java.util.List;
import edu.umflix.authenticationhandler.exceptions.InvalidTokenException;
import edu.umflix.model.Movie;

/* *
 *
 * This interface provides the information about movies in UMFlix's Catalog.
 * Provides to: Apps, UMFlix WebApp and VendorManager.
 *
 */

public interface CatalogService {
    /*
    *
    * @param name Searches for movies matching name.
    * @param token Validates the session with token.
    * @return Returns a list with the movies found.
    * @throws DaoException Throws when there was an error accessing persistence classes.
    * @throws InvalidTokenException Throws when the token was invalid.
    *
    */
    List<Movie> search(String key,String token) throws InvalidTokenException;
}