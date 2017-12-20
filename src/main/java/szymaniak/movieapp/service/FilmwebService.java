package szymaniak.movieapp.service;

import info.talacha.filmweb.models.Film;
import info.talacha.filmweb.models.Profession;
import info.talacha.filmweb.search.models.FilmSearchResult;
import szymaniak.movieapp.model.MovieDBSummary.MovieDBDetailed;
import szymaniak.movieapp.model.domain.Actor;
import szymaniak.movieapp.model.domain.PersonInformation;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FilmwebService {

    List<FilmSearchResult> findMoviesByTitle(String title);
    FilmSearchResult findMovieByOriginalTitle(MovieDBDetailed movieDBDetailed, List<FilmSearchResult> filmSearchResults);
    Optional<Film> findMovieById(Long id);
    Set<PersonInformation> findCrewByRole(Long id, Profession role, PersonInformation personInformation);
}
