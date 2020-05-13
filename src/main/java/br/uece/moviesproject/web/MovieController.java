package br.uece.moviesproject.web;

import br.uece.moviesproject.domain.entity.Movie;
import br.uece.moviesproject.domain.error.MovieNotFoundException;
import br.uece.moviesproject.repository.MovieRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Movie>> getAll() {
        List<Movie> movies = movieRepository.findAll();
        return ResponseEntity.ok(movies);
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Movie> getById(@PathVariable Integer id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (!movie.isPresent()) {
            throw new MovieNotFoundException(id);
        } else {
            return ResponseEntity.ok(movie.get());
        }
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Movie> save(@RequestBody Movie movie) {
        Movie saved = movieRepository.save(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Movie> update(@PathVariable Integer id, @RequestBody Movie movie) {
        Optional<Movie> movieFromDb = movieRepository.findById(id);
        if (!movieFromDb.isPresent()) {
            throw new MovieNotFoundException(id);
        } else {
            movie.setId(id);
            Movie updated = movieRepository.save(movie);
            return ResponseEntity.ok(updated);
        }
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Integer id) {
        Optional<Movie> movieFromDb = movieRepository.findById(id);
        if (!movieFromDb.isPresent()) {
            throw new MovieNotFoundException(id);
        } else {
            movieRepository.deleteById(id);
        }
    }
}