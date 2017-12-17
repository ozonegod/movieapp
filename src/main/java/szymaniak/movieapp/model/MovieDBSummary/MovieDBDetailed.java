package szymaniak.movieapp.model.MovieDBSummary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDBDetailed {

    @JsonProperty("adult")
    private boolean adult;
    @JsonProperty("backdrop_path")
    private String backdropPath;
    @JsonProperty("belongs_to_collection")
    private Object belongsToCollection;
    @JsonProperty("budget")
    private int budget;
    @JsonProperty("genres")
    private List<MovieDBGenre> genres = null;
    @JsonProperty("homepage")
    private String homepage;
    @JsonProperty("id")
    private int id;
    @JsonProperty("imdb_id")
    private String imdbId;
    @JsonProperty("original_language")
    private String originalLanguage;
    @JsonProperty("original_title")
    private String originalTitle;
    @JsonProperty("overview")
    private String overview;
    @JsonProperty("popularity")
    private double popularity;
    @JsonProperty("poster_path")
    private String posterPath;
    @JsonProperty("production_companies")
    private List<MovieDBProductionCompany> productionCompanies = null;
    @JsonProperty("production_countries")
    private List<MovieDBProductionCountry> productionCountries = null;
    @JsonProperty("release_date")
    private String releaseDate;
    @JsonProperty("revenue")
    private int revenue;
    @JsonProperty("runtime")
    private Object runtime;
    @JsonProperty("spoken_languages")
    private List<MovieDBSpokenLanguage> movieDBSpokenLanguages = null;
    @JsonProperty("status")
    private String status;
    @JsonProperty("tagline")
    private String tagline;
    @JsonProperty("title")
    private String title;
    @JsonProperty("video")
    private boolean video;
    @JsonProperty("vote_average")
    private int voteAverage;
    @JsonProperty("vote_count")
    private int voteCount;
}
