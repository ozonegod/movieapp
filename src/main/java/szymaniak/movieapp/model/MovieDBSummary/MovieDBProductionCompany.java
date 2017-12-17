package szymaniak.movieapp.model.MovieDBSummary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDBProductionCompany {

    @JsonProperty("name")
    private String name;
    @JsonProperty("id")
    private int id;
}
