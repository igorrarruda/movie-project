package br.uece.moviesproject.domain.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MovieNotFoundException extends RuntimeException {
    /**
    *
    */
    private static final long serialVersionUID = -8110112501282640473L;

    public MovieNotFoundException(Integer id) {
        super("Movie for ID " + id + " does not exist.");
    }
}