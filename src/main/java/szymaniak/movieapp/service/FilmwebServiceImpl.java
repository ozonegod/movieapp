package szymaniak.movieapp.service;

import info.talacha.filmweb.api.FilmwebApi;
import info.talacha.filmweb.connection.FilmwebException;
import info.talacha.filmweb.models.Film;
import info.talacha.filmweb.models.Person;
import info.talacha.filmweb.models.Profession;
import info.talacha.filmweb.search.models.FilmSearchResult;
import org.springframework.stereotype.Service;
import szymaniak.movieapp.model.MovieDBSummary.MovieDBDetailed;
import szymaniak.movieapp.model.domain.Actor;
import szymaniak.movieapp.model.domain.PersonInformation;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
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

    @Override
    public Set<PersonInformation> findCrewByRole(Long id, Profession role, PersonInformation crewMember) {
        Function<Person, PersonInformation> parseToCrewMember = person -> {
            crewMember.setId(person.getId());
            crewMember.setInfo(person.getInfo());
            crewMember.setName(person.getName());
            crewMember.setPhotoUrl(person.getPhotoUrl());
            crewMember.setRole(person.getRole());
            return crewMember;
        };
        try {
            return filmwebApi.getPersons(id, role, 0, 10).stream().map(parseToCrewMember).collect(Collectors.toSet());
        } catch (FilmwebException e) {
            e.printStackTrace(); //TODO
        }
        return Collections.emptySet();
    }

}
