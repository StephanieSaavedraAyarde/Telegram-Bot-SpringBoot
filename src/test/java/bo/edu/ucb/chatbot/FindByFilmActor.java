package bo.edu.ucb.chatbot;

import bo.edu.ucb.chatbot.bl.ActorFilmSearchBl;
import bo.edu.ucb.chatbot.dao.ActorFilmDao;
import bo.edu.ucb.chatbot.dto.ActorFilm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest
class FindByFilmActor {

	@Autowired
	ActorFilmSearchBl actorFilmSearchBl;

	@Test
	void findExactlyOne() {
		// Buscamos la pelicula
		List<ActorFilm> actors = actorFilmSearchBl.findByTitleActor("Ed", "Chase", "ALone Trip");
		// Probamos que el resultado sea el epserado
		assertNotNull(actors, "La busqueda retorno una lista nula");
		ActorFilm actorFilm = actors.get(0);
		assertEquals("ED", actorFilm.getFirst_name(), "No se encontro un actor con ese nombre");
        assertEquals("CHASE", actorFilm.getLast_name(), "No se encontro un actor con ese apellido");
        assertEquals("ALONE TRIP", actorFilm.getTitle(), "El titulo de la pelicula no coincide");
		assertTrue( actorFilm.getDescription().startsWith("A Fast-Paced Character Study of a Composer And a Dog who must Outgun a Boat in An Abandoned Fun House"), "La descripcion de la película no coincide");
		assertEquals("1h 22m", actorFilm.getLengthLabel(), "La hora no coincide o esta en formato incorrecto");
	}
}