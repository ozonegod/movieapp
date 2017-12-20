package szymaniak.movieapp.model.domain;

import info.talacha.filmweb.models.Person;
import lombok.Data;

import javax.persistence.*;
import java.net.URL;

@Entity
@Data
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String role;
    private String info;
    private String name;
    private URL photoUrl;
    @ManyToOne
    @JoinColumn(name="id", insertable = false, updatable = false)
    private Movie movie;

    public Director(Person person) {
        this.role = person.getRole();
        this.info = person.getInfo();
        this.name = person.getName();
        this.photoUrl = person.getPhotoUrl();
    }
}
