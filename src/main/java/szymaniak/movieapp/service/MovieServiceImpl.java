package szymaniak.movieapp.service;

import info.talacha.filmweb.models.Film;
import info.talacha.filmweb.search.models.FilmSearchResult;
import org.springframework.stereotype.Service;
import szymaniak.movieapp.model.MovieDBSummary.MovieDBDetailed;
import szymaniak.movieapp.model.domain.Movie;

import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    MovieDBService movieDBService;
    FilmwebService filmwebService;

    public MovieServiceImpl(MovieDBService movieDBService, FilmwebService filmwebService) {
        this.movieDBService = movieDBService;
        this.filmwebService = filmwebService;
    }

    @Override
    public Movie createMovie(String id) {
        MovieDBDetailed movieDB = movieDBService.findMovieDetails(id);
        FilmSearchResult movieFilmwebSummary = filmwebService.findMovieByOriginalTitle(movieDB, filmwebService.findMoviesByTitle(movieDB.getTitle()));
        Optional<Film> movieFilmweb = filmwebService.findMovieById(movieFilmwebSummary.getId());
        return null;
    }
}
