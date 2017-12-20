package szymaniak.movieapp.service;

import info.talacha.filmweb.models.Film;
import info.talacha.filmweb.search.models.FilmSearchResult;
import org.springframework.stereotype.Service;
import szymaniak.movieapp.model.MovieDBSummary.MovieDBDetailed;
import szymaniak.movieapp.model.MovieDBSummary.MovieDBGenre;
import szymaniak.movieapp.model.domain.Movie;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieDBService movieDBService;
    private FilmwebService filmwebService;
    private MovieDBDetailed movieDB;
    private Movie movie;

    public MovieServiceImpl(MovieDBService movieDBService, FilmwebService filmwebService) {
        this.movieDBService = movieDBService;
        this.filmwebService = filmwebService;
    }

    @Override
    public Movie createMovie(String id) {
        movie = new Movie();
        movieDB = movieDBService.findMovieDetails(id);
        Optional<FilmSearchResult> movieFilmwebSummary = filmwebService.findMovieByTitle(movieDB.getTitle(), filmwebService.findMoviesByTitle(movieDB.getTitle()));
        movieFilmwebSummary.ifPresent(this::searchMovieOnFilmweb);
        addGenresToMovie();
        return movie;
    }

    private void searchMovieOnFilmweb(FilmSearchResult movieFilmwebSummary){
        Optional<Film> movieFilmweb = filmwebService.findMovieById(movieFilmwebSummary.getId());
        movieFilmweb.ifPresent(this::populateBasicInformations);
        addCrewToMovie(movieFilmwebSummary.getId());
    }

    private void populateBasicInformations(Film movieFilmweb) {
        movie.setReleaseDate(movieDB.getReleaseDate());
        movie.setStatus(movieDB.getStatus());
        movie.setTitle(movieDB.getTitle());
        movie.setDuration(movieFilmweb.getDuration());
        movie.setFilmwebWebsite(movieFilmweb.getWebsiteURL());
        movie.setForAdult(movieDB.isAdult());
        movie.setImdbid(movieDB.getImdbId());
        movie.setOverwiew(movieDB.getOverview());
        movie.setPolishOverwiew(movieFilmweb.getPlot());
        movie.setPolishTitle(movieFilmweb.getPolishTitle());
        movie.setRate(movieFilmweb.getRate());
        movie.setVotes(movieFilmweb.getVotes());
    }
    private void addGenresToMovie(){
        movie.setGenres(movieDB.getGenres().stream().map(MovieDBGenre::getName).collect(Collectors.toList()));
    }

    private void addCrewToMovie(Long filmwebMovieId){
        movie.setActors(filmwebService.findActors(filmwebMovieId));
        movie.setDirectors(filmwebService.findDirectors(filmwebMovieId));
    }
}
