package szymaniak.movieapp.service;

import org.springframework.stereotype.Service;
import szymaniak.movieapp.model.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> showAllMovies();
    Movie addNewMovie(Movie movie);
}
