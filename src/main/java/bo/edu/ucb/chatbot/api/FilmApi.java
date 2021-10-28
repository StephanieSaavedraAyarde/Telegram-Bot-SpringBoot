package bo.edu.ucb.chatbot.api;

import bo.edu.ucb.chatbot.bl.FilmSearchBl;
import bo.edu.ucb.chatbot.dto.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Bajo la perspectiva de High Cohesion. El API rest debería validar que lo que el cliente envía, entenediendo por
 * cliente a las aplicaciones Web y Móvil, son datos correctos..
 *
 * Por ejemplo para la busqueda por titulo el API rest no debería pasarle un titulo nulo.
 */
@RestController()
public class FilmApi {

    FilmSearchBl filmSearchBl;

    @Autowired
    public FilmApi(FilmSearchBl filmSearchBl) {
        this.filmSearchBl = filmSearchBl;
    }

    @GetMapping(value = "/film/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Film> findBytTitle(@PathVariable(name = "title") String title) {
        System.out.println("Invocando al metodo GET!!!!!!!!!!!");
        return filmSearchBl.findByTitle(title);
    }
}
