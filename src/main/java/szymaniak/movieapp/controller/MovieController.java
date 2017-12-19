package szymaniak.movieapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import szymaniak.movieapp.service.MovieService;

@Controller
@RequestMapping(value = "/movie")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(value="/{id}")
    public String showDetailsOfMovie(@PathVariable String id){
        movieService.createMovie(id);
        return "movie/details.html";
    }
}
