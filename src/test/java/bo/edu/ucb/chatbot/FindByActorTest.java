package bo.edu.ucb.chatbot;

import bo.edu.ucb.chatbot.bl.ActorSearchBl;
import bo.edu.ucb.chatbot.dao.ActorDao;
import bo.edu.ucb.chatbot.dto.Actor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest
class FindByActorTests {

	@Autowired
	ActorSearchBl actorSearchBl;

	@Test
	void findExactlyOne() {
		// Buscamos la pelicula
		List<Actor> actors = actorSearchBl.findByActor("Ed", "Chase");
		// Probamos que el resultado sea el epserado
		assertNotNull(actors, "La busqueda retorno una lista nula");
		Actor actor = actors.get(0);
		assertEquals("ED", actor.getFirst_name(), "No se encontro un actor con ese nombre");
        assertEquals("CHASE", actor.getLast_name(), "No se encontro un actor con ese apellido");
        assertEquals("ALONE TRIP", actor.getTitle(), "El titulo de la pelicula no coincide");
		assertTrue( actor.getDescription().startsWith("A Fast-Paced Character Study of a Composer And a Dog who must Outgun a Boat in An Abandoned Fun House"), "La descripcion de la película no coincide");
		assertEquals("1h 22m", actor.getLengthLabel(), "La hora no coincide o esta en formato incorrecto");
	}

}
