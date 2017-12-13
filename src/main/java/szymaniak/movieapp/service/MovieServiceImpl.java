package szymaniak.movieapp.service;

import org.springframework.stereotype.Service;
import szymaniak.movieapp.model.Movie;
import szymaniak.movieapp.repository.MovieRepository;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> showAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie addNewMovie(Movie movie) {
        movieRepository.save(movie);
        return movie;
    }

}
