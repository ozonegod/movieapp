package szymaniak.movieapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import szymaniak.movieapp.model.MovieDBSummary.MovieDBDetailed;
import szymaniak.movieapp.model.MovieDBSummary.MovieDBSummaryCollection;

@Service
public class MovieDBServiceImpl implements MovieDBService {

    private RestTemplate restTemplate;
    private final String api_url;
    private final String api_key;

    public MovieDBServiceImpl(RestTemplate restTemplate, @Value("${movie.api.url}") String api_url,
                              @Value("${movie.api.key}") String api_key) {
        this.restTemplate = restTemplate;
        this.api_url = api_url;
        this.api_key = api_key;
    }

    @Override
    public MovieDBDetailed findMovieDetails(String id) {
        UriComponents uriBuilder = UriComponentsBuilder
                .fromUriString(String.format("%smovie/{id}", api_url))
                .queryParam("api_key", api_key)
                .build().expand(id).encode();

        return restTemplate.getForObject(uriBuilder.toUriString(), MovieDBDetailed.class);
    }

    @Override
    public MovieDBSummaryCollection findMoviesByTitle(String title, int page) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(String.format("%ssearch/movie", api_url))
                .queryParam("query", title)
                .queryParam("api_key", api_key)
                .queryParam("page", page);

        ResponseEntity<MovieDBSummaryCollection> movies = restTemplate.getForEntity(uriBuilder.toUriString(), MovieDBSummaryCollection.class);
        return movies.getBody();
    }
}
