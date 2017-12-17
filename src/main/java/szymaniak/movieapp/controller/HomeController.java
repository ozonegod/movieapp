package szymaniak.movieapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import szymaniak.movieapp.dto.SearchMovieDTO;
import szymaniak.movieapp.service.MovieDBService;

@Controller
@RequestMapping(value = "/")
public class HomeController {

    private MovieDBService movieDBService;

    public HomeController(MovieDBService movieDBService) {
        this.movieDBService = movieDBService;
    }

    @GetMapping
    public String showIndexPage(Model model){
        model.addAttribute("searchMovie", new SearchMovieDTO());
        return "index.html";
    }

    @PostMapping(value = "/processForm")
    public String showMovies(@ModelAttribute SearchMovieDTO searchMovieDTO, Model model){
        model.addAttribute("moviedbcollection", movieDBService.findMovieByTitle(searchMovieDTO.getTitle(), 1));
        model.addAttribute("title", searchMovieDTO.getTitle());
        return "movie/result.html";
    }

    @GetMapping(value = "/results")
    public String showMoviesByPage(Model model, @RequestParam int page, @RequestParam String title){
        model.addAttribute("moviedbcollection", movieDBService.findMovieByTitle(title, page));
        model.addAttribute("title", title);
        return "movie/result.html";
    }
}
