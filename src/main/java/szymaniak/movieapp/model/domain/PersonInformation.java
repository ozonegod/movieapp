package szymaniak.movieapp.model.domain;

import java.net.URL;

public interface PersonInformation {
    void setId(Long id);

    void setRole(String role);

    void setInfo(String info);

    void setName(String name);

    void setPhotoUrl(URL photoUrl);

    void setMovie(Movie movie);
}
