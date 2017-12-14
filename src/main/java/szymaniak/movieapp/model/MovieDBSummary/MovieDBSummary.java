package szymaniak.movieapp.model.MovieDBSummary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDBSummary {

    @JsonProperty("vote_count")
    public int voteCount;
    @JsonProperty("id")
    public int id;
    @JsonProperty("video")
    public boolean video;
    @JsonProperty("vote_average")
    public double voteAverage;
    @JsonProperty("title")
    public String title;
    @JsonProperty("popularity")
    public double popularity;
    @JsonProperty("poster_path")
    public String posterPath;
    @JsonProperty("original_language")
    public String originalLanguage;
    @JsonProperty("original_title")
    public String originalTitle;
    @JsonProperty("genre_ids")
    public List<Integer> genreIds = null;
    @JsonProperty("backdrop_path")
    public String backdropPath;
    @JsonProperty("adult")
    public boolean adult;
    @JsonProperty("overview")
    public String overview;
    @JsonProperty("release_date")
    public String releaseDate;
}
