package szymaniak.movieapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String showView(@ModelAttribute SearchMovieDTO searchMovieDTO, Model model){
        model.addAttribute("moviedbcollection", movieDBService.findMovieByTitle(searchMovieDTO.getTitle()));
        return "movie/result.html";
    }
}
