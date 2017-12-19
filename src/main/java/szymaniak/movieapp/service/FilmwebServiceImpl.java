package szymaniak.movieapp.service;

import info.talacha.filmweb.api.FilmwebApi;
import info.talacha.filmweb.connection.FilmwebException;
import info.talacha.filmweb.models.Film;
import info.talacha.filmweb.search.models.FilmSearchResult;
import org.springframework.stereotype.Service;
import szymaniak.movieapp.model.MovieDBSummary.MovieDBDetailed;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

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
    public FilmSearchResult findMovieByOriginalTitle(MovieDBDetailed movieDBDetailed, List<FilmSearchResult> filmSearchResults) {
        Supplier<FilmSearchResult> filmSearchResultSupplier = () -> filmSearchResults.stream().filter(filmSearchResult -> movieDBDetailed.getTitle().equals(filmSearchResult.getTitle())).findFirst().orElse(filmSearchResults.isEmpty()? null: filmSearchResults.get(0));
        return filmSearchResults.stream()
                .filter(filmSearchResult -> movieDBDetailed.getTitle().equals(filmSearchResult.getTitle()) && movieDBDetailed.getOriginalTitle().equals(filmSearchResult.getAlternativeTitle()))
                .findFirst().orElseGet(filmSearchResultSupplier);
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
}
