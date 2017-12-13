package szymaniak.movieapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import szymaniak.movieapp.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long>{
}
