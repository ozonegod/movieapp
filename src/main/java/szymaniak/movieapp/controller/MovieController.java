package szymaniak.movieapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import szymaniak.movieapp.model.MovieDBSummary.MovieDBDetailed;
import szymaniak.movieapp.service.MovieDBService;

@Controller
@RequestMapping(value = "/movie")
public class MovieController {

    private MovieDBService movieDBService;

    public MovieController(MovieDBService movieDBService) {
        this.movieDBService = movieDBService;
    }

    @GetMapping(value="/{id}")
    public String showDetailsOfMovie(@PathVariable String id){
        MovieDBDetailed movieDetails = movieDBService.findMovieDetails(id);

        return "movie/details.html";
    }
}
