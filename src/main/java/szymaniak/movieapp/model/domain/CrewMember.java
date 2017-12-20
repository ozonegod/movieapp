package szymaniak.movieapp.model.domain;

import lombok.Data;

import javax.persistence.*;
import java.net.URL;
import java.util.Set;

@Entity
@Data
public class CrewMember {

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
}
