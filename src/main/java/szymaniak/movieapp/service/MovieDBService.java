package szymaniak.movieapp.service;

import szymaniak.movieapp.model.MovieDBSummary.MovieDBDetailed;
import szymaniak.movieapp.model.MovieDBSummary.MovieDBSummaryCollection;

public interface MovieDBService {
    MovieDBSummaryCollection findMoviesByTitle(String title, int page);
    MovieDBDetailed findMovieDetails(String id);
}
