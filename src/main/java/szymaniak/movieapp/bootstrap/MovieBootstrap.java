package szymaniak.movieapp.bootstrap;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import szymaniak.movieapp.model.Movie;
import szymaniak.movieapp.repository.MovieRepository;

@Component
public class MovieBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private MovieRepository movieRepository;

    public MovieBootstrap(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    private void addMovies(){
        Movie movie = new Movie();
        movie.setTitle("Example Movie");
        movie.setScore(10.5);
        movie.setAlreadySeen(true);
        movieRepository.save(movie);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //addMovies();
    }
}
