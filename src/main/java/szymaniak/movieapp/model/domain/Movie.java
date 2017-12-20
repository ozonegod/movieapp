package szymaniak.movieapp.model.domain;

import lombok.Data;

import javax.persistence.*;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imdbid;
    private String title;
    private String polishTitle;
    private String status;
    private String releaseDate;
    @ElementCollection
    private List<String> genres;
    private boolean forAdult;
    private String overwiew;
    private String polishOverwiew;
    private int duration;
    private URL filmwebWebsite;
    private int votes;
    private float rate;
    @OneToMany(mappedBy = "movie")
    private Set<Director> directors;
    @OneToMany(mappedBy = "movie")
    private Set<Actor> actors;

}
