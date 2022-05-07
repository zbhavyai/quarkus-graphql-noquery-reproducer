package org.acme.microprofile.graphql;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class FilmService {

    private List<Film> films = new ArrayList<>();

    public FilmService() {

        Film aNewHope = new Film();
        aNewHope.setTitle("A New Hope");
        aNewHope.setReleaseDate(LocalDate.of(1977, Month.MAY, 25));
        aNewHope.setEpisode(4);
        aNewHope.setDirector("George Lucas");

        Film theEmpireStrikesBack = new Film();
        theEmpireStrikesBack.setTitle("The Empire Strikes Back");
        theEmpireStrikesBack.setReleaseDate(LocalDate.of(1980, Month.MAY, 21));
        theEmpireStrikesBack.setEpisode(5);
        theEmpireStrikesBack.setDirector("George Lucas");

        Film returnOfTheJedi = new Film();
        returnOfTheJedi.setTitle("Return Of The Jedi");
        returnOfTheJedi.setReleaseDate(LocalDate.of(1983, Month.MAY, 25));
        returnOfTheJedi.setEpisode(6);
        returnOfTheJedi.setDirector("George Lucas");

        films.add(aNewHope);
        films.add(theEmpireStrikesBack);
        films.add(returnOfTheJedi);
    }

    public Uni<List<Film>> getFilms() {
        return Uni.createFrom().item(films);
    }

    public Uni<Boolean> addFilm(Film f) {
        return Uni.createFrom().item(films.add(f));
    }

    public Uni<Boolean> deleteFilm(int id) {
        try {
            films.remove(id);
            return Uni.createFrom().item(true);
        }

        catch (IndexOutOfBoundsException | UnsupportedOperationException e) {
            return Uni.createFrom().item(false);
        }
    }
}
