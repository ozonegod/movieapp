package szymaniak.movieapp.model.domain;

import lombok.Data;

import javax.persistence.*;
import java.net.URL;

@Entity
@Data
public class Director implements PersonInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String role;
    private String info;
    private String name;
    private URL photoUrl;
    @ManyToOne
    @JoinColumn(name="id")
    private Movie movie;
}
