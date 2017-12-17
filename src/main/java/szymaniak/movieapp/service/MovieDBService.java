package szymaniak.movieapp.service;

import szymaniak.movieapp.model.MovieDBSummary.MovieDB;
import szymaniak.movieapp.model.MovieDBSummary.MovieDBDetailed;
import szymaniak.movieapp.model.MovieDBSummary.MovieDBSummaryCollection;

public interface MovieDBService {
    MovieDBSummaryCollection findMovieByTitle(String title, int page);
    MovieDB createCompleteMovie(String title, int page);
    MovieDBDetailed findMovieDetails(String id);
}
