package szymaniak.movieapp.service;

import szymaniak.movieapp.model.MovieDBSummary.MovieDB;
import szymaniak.movieapp.model.MovieDBSummary.MovieDBSummaryCollection;

public interface MovieDBService {
    MovieDBSummaryCollection findMovieByTitle(String title);
    MovieDB createCompleteMovie(String title);
}
