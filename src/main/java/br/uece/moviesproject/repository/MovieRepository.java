package br.uece.moviesproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.uece.moviesproject.domain.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

}