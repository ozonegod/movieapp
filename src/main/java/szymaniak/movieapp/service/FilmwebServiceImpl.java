package szymaniak.movieapp.service;

import info.talacha.filmweb.api.FilmwebApi;
import info.talacha.filmweb.connection.FilmwebException;
import info.talacha.filmweb.models.Film;
import info.talacha.filmweb.models.Profession;
import info.talacha.filmweb.search.models.FilmSearchResult;
import org.springframework.stereotype.Service;
import szymaniak.movieapp.model.MovieDBSummary.MovieDBDetailed;
import szymaniak.movieapp.model.domain.Actor;
import szymaniak.movieapp.model.domain.Director;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class FilmwebServiceImpl implements FilmwebService {

    private FilmwebApi filmwebApi;

    public FilmwebServiceImpl(FilmwebApi filmwebApi){
        this.filmwebApi = filmwebApi;
    }

    @Override
    public List<FilmSearchResult> findMoviesByTitle(String title) {
        return filmwebApi.findFilm(title);
    }

    @Override
    public Optional<FilmSearchResult> findMovieByTitle(String movieTitle, List<FilmSearchResult> filmSearchResults) {

        return filmSearchResults.stream()
                .filter(filmSearchResult -> filmSearchResult.getPolishTitle().equals(movieTitle)).findFirst();
    }

    @Override
    public Optional<Film> findMovieById(Long id) {
        try {
            return Optional.ofNullable(filmwebApi.getFilmData(id));
        } catch (FilmwebException e) {
            e.printStackTrace(); //TODO
        }
        return Optional.empty();
    }

    @Override
    public Set<Actor> findActors(Long id){
        try {
            return filmwebApi.getPersons(id, Profession.ACTOR, 0, 15).stream().map(Actor::new).collect(Collectors.toSet());
        } catch (FilmwebException e) {
            e.printStackTrace();
        }
        return Collections.emptySet();
    }


    public Set<Director> findDirectors(Long id) {
        try {
            return filmwebApi.getPersons(id, Profession.DIRECTOR, 0, 15).stream().map(Director::new).collect(Collectors.toSet());
        } catch (FilmwebException e) {
            e.printStackTrace();
        }
        return Collections.emptySet();
    }
}
