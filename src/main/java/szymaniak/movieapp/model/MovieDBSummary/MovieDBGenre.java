package szymaniak.movieapp.model.MovieDBSummary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDBGenre {
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
}
