package szymaniak.movieapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import szymaniak.movieapp.model.Movie;
import szymaniak.movieapp.service.MovieService;

@Controller
@RequestMapping(value = "/movie")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public String showListOfMovies(Model model){
        model.addAttribute("movies", movieService.showAllMovies());
        return "movie/show";
    }

    @GetMapping(value = "/addMovie")
    public String showFormToAddNewMovie(Model model){
        model.addAttribute("movie", new Movie());
        return "movie/addmovie";
    }

    @PostMapping(value = "/addMovie")
    public String addNewMovie(@ModelAttribute Movie movie){
        movieService.addNewMovie(movie);
        return "redirect:/movie";
    }
}
