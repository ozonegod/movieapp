package szymaniak.movieapp.service;

import info.talacha.filmweb.models.Film;
import info.talacha.filmweb.search.models.FilmSearchResult;
import szymaniak.movieapp.model.MovieDBSummary.MovieDBDetailed;

import java.util.List;
import java.util.Optional;

public interface FilmwebService {
    List<FilmSearchResult> findMoviesByTitle(String title);
    FilmSearchResult findMovieByOriginalTitle(MovieDBDetailed movieDBDetailed, List<FilmSearchResult> filmSearchResults);
    Optional<Film> findMovieById(Long id);
}