package szymaniak.movieapp.service;

import info.talacha.filmweb.models.Film;
import info.talacha.filmweb.search.models.FilmSearchResult;
import szymaniak.movieapp.model.MovieDBSummary.MovieDBDetailed;
import szymaniak.movieapp.model.domain.Actor;
import szymaniak.movieapp.model.domain.Director;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FilmwebService {

    List<FilmSearchResult> findMoviesByTitle(String title);
    Optional<FilmSearchResult> findMovieByTitle(String movieTitle, List<FilmSearchResult> filmSearchResults);
    Optional<Film> findMovieById(Long id);
    Set<Actor> findActors(Long id);
    Set<Director> findDirectors(Long id);
}
