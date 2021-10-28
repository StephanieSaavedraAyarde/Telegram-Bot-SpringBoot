package bo.edu.ucb.chatbot;

import bo.edu.ucb.chatbot.bl.FilmSearchBl;
import bo.edu.ucb.chatbot.dao.FilmDao;
import bo.edu.ucb.chatbot.exception.SakilaException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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
@ExtendWith(MockitoExtension.class)
class FindByTitleUnitTests {

	@Mock
	FilmDao filmDao;

	@Test
	void testEmptyOrNullTitle() {

		assertNotNull(filmDao, "No funciono el mock del FilmDao");
		// Como el objetivo del test es probar el codigop de findByTitle del BL
		// Debo decirle al test que retornar cuando necesite invocar a: filmDao.findByTitle(title);
		// when(filmDao.findByTitle("")).thenReturn(new ArrayList<>());

		FilmSearchBl filmSearchBl = new FilmSearchBl(filmDao);

		SakilaException ex1 = assertThrows(SakilaException.class, () -> {
			filmSearchBl.findByTitle(null);
		});
		assertEquals(ex1.getCode(), 403); // 1000 significa blanco o nulo

		SakilaException ex2 = assertThrows(SakilaException.class, () -> {
			filmSearchBl.findByTitle("");
		});
		assertEquals(ex2.getCode(), 403); // 1000 significa blanco o nulo

		SakilaException ex3 = assertThrows(SakilaException.class, () -> {
			filmSearchBl.findByTitle("            ");
		});
		assertEquals(ex3.getCode(), 403); // 1000 significa blanco o nulo

	}

}
