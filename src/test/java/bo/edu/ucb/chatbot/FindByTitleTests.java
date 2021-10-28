package bo.edu.ucb.chatbot;

import bo.edu.ucb.chatbot.bl.FilmSearchBl;
import bo.edu.ucb.chatbot.dao.FilmDao;
import bo.edu.ucb.chatbot.dto.Film;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Testcase 1: Busqueda por titulo de forma exacta
 * Descripción:
 *   - Se debe buscar una pelicula por titulo que solo retorne una ocurrencía.
 * Precondiciones:
 *   - Se conoce de antemano que pelicuals en BBDD tienen solo una ocurrencia por titulo.
 * Valores de entrada:
 *   - maude
 *   - bang kwai
 * Resultado esperado:
 *   - Solo un elemento para cada valor de entrada
 *   - maude:
 *         [
 *           {
 *             "film_id": 566,
 *             "title": "MAUDE MOD",
 *             "description": "A Beautiful Documentary of a Forensic Psychologist And a Cat who must Reach a Astronaut in Nigeria",
 *             "release_year": 2006,
 *             "language": "English",
 *             "originalLanguage": null,
 *             "rental_duration": 6,
 *             "rental_rate": 0.99,
 *             "length": 72,
 *             "replacement_cost": 20.99,
 *             "rating": "R",
 *             "special_features": "Trailers,Behind the Scenes",
 *             "last_update": "2006-02-15 05:03:42"
 *           }
 *         ]
 *
 *
 */
@SpringBootTest
class FindByTitleTests {

	@Autowired
	FilmSearchBl filmSearchBl;

	@Test
	void findExactlyOne() {
		// Buscamos la pelicula
		List<Film> films = filmSearchBl.findByTitle("maude");
		// Probamos que el resultado sea el epserado
		assertNotNull(films, "La busqueda retorno una lista nula");
		Film film = films.get(0);
		assertEquals("MAUDE MOD", film.getTitle(), "El titulo de la película no coincide");
		assertTrue( film.getDescription().startsWith("A Beautiful Documentary of a Forensic"), "La descripcion de la película no coincide");
		assertEquals("1h 12m", film.getLengthLabel(), "La hora no coincide o esta en formato incorrecto");
	}

}
