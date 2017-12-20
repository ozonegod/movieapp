package szymaniak.movieapp.service;

import com.sun.xml.internal.bind.v2.TODO;
import info.talacha.filmweb.models.Film;
import info.talacha.filmweb.models.Profession;
import info.talacha.filmweb.search.models.FilmSearchResult;
import org.springframework.stereotype.Service;
import szymaniak.movieapp.model.MovieDBSummary.MovieDBDetailed;
import szymaniak.movieapp.model.MovieDBSummary.MovieDBGenre;
import szymaniak.movieapp.model.domain.CrewMember;
import szymaniak.movieapp.model.domain.Movie;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieDBService movieDBService;
    private FilmwebService filmwebService;
    private MovieDBDetailed movieDB;
    private FilmSearchResult movieFilmwebSummary;
    private Movie movie;

    public MovieServiceImpl(MovieDBService movieDBService, FilmwebService filmwebService) {
        this.movieDBService = movieDBService;
        this.filmwebService = filmwebService;
    }

    @Override
    public Movie createMovie(String id) {
        movie = new Movie();
        movieDB = movieDBService.findMovieDetails(id);
        movieFilmwebSummary = filmwebService.findMovieByOriginalTitle(movieDB, filmwebService.findMoviesByTitle(movieDB.getTitle()));
        Optional<Film> movieFilmweb = filmwebService.findMovieById(movieFilmwebSummary.getId());
        try {
            populateBasicInformations(movieFilmweb);
        } catch (MalformedURLException e) {
            e.printStackTrace(); //TODO
        }
        addGenresToMovie();
        addCrewToMovie(movieFilmweb, movieFilmwebSummary.getId());
        return movie;
    }
    private void populateBasicInformations(Optional<Film> movieFilmweb) throws MalformedURLException {
        movie.setReleaseDate(movieDB.getReleaseDate());
        movie.setStatus(movieDB.getStatus());
        movie.setTitle(movieDB.getTitle());
        movie.setDuration(movieFilmweb.isPresent()? movieFilmweb.get().getDuration(): 0);
        movie.setFilmwebWebsite(movieFilmweb.isPresent()? movieFilmweb.get().getWebsiteURL(): new URL("http://filmweb.pl") );
        movie.setForAdult(movieDB.isAdult());
        movie.setImdbid(movieDB.getImdbId());
        movie.setOverwiew(movieDB.getOverview());
        movie.setPolishOverwiew(movieFilmweb.isPresent()? movieFilmweb.get().getPlot():"Brak danych"); //TODO
        movie.setPolishTitle(movieFilmweb.isPresent()? movieFilmweb.get().getPolishTitle(): "Brak danych"); //TODO
        movie.setRate(movieFilmweb.isPresent()? movieFilmweb.get().getRate(): 0);
        movie.setVotes(movieFilmweb.isPresent()? movieFilmweb.get().getVotes(): 0);
    }
    private void addGenresToMovie(){
        movie.setGenres(movieDB.getGenres().stream().map(MovieDBGenre::getName).collect(Collectors.toList()));
    }

    private void addCrewToMovie(Optional<Film> filmwebMovie, Long filmwebMovieId){
        movie.setListOfActors(filmwebService.findCrewByRole(filmwebMovieId, Profession.ACTOR));
        movie.setListOfDirectors(filmwebService.findCrewByRole(filmwebMovieId, Profession.DIRECTOR));
    }
}
