package bo.edu.ucb.chatbot;

import bo.edu.ucb.chatbot.bl.BotFilmSearchBl;
import bo.edu.ucb.chatbot.bl.FilmSearchBl;
import bo.edu.ucb.chatbot.dto.Film;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/*
 * Se procederá a probar el comportamiento del Bot, ahora mismo el Bot
 * debe recibir una palabra y debe retornar las peliculas que ha encontrado. Como representación del metodo
 * toString un mensaje por pelicula.
 * Si la consulta tiene respuesta debe retornar:
 *
 * Encontre las siguientes peliculas:
 * <Una linea por pelicula en formato Film.toString>
 *
 * En caso de que no encuentre las peliculas debe retornar:
 * No encontre ninguna pelicula para: <CONSULTA>
 */
@ExtendWith(MockitoExtension.class)
public class BotFilmSearchBlUnitTest {

    @Mock
    FilmSearchBl filmSearchBl;

    @Test
    public void filmNotFound() {
        String message = "AAA";
        // Cuando se invoque al metodo findByTitle con el parametro message (AAA) se retornará siempre vacio.
        when(filmSearchBl.findByTitle(message)).thenReturn(new ArrayList<>());
        BotFilmSearchBl botFilmSearchBl = new BotFilmSearchBl(filmSearchBl);
        List<String> botResponse = botFilmSearchBl.processMessage(message);

        assertEquals(botResponse.size(), 1, "Debería retornar unicamente un mensaje");
        assertTrue(botResponse.get(0).startsWith("No encontré ninguna película para:"), "El mensaje para pelicuals inexistentes es incorrecto");
    }

    @Test
    public void exactlyOneFound() {
        Film filmOne = new Film();
        filmOne.setFilmId(1);
        filmOne.setTitle("Lo que el viento se llevo");
        filmOne.setRating("AAA");
        Film filmTwo = new Film();
        filmTwo.setFilmId(2);
        filmTwo.setTitle("Pinochio");
        filmTwo.setRating("AAA");
        Film filmThree = new Film();
        filmThree.setFilmId(3);
        filmThree.setTitle("El libro de la selva");
        filmThree.setRating("AAA");
        List<Film> mockResulList = new ArrayList<>();
        mockResulList.add(filmOne);
        mockResulList.add(filmTwo);
        mockResulList.add(filmThree);


        String message = "AAA";
        // Cuando se invoque al metodo findByTitle con el parametro message (AAA) se retornará 3 peliculas.
        when(filmSearchBl.findByTitle(message)).thenReturn(mockResulList);
        BotFilmSearchBl botFilmSearchBl = new BotFilmSearchBl(filmSearchBl);
        List<String> botResponse = botFilmSearchBl.processMessage(message);

        assertEquals(botResponse.size(), 4, "Debería retornar unicamente un mensaje");
        assertTrue(botResponse.get(0).equals("Encontré las siguientes películas:"), "El mensaje para pelicuals encontradsa es incorrecto");
        assertTrue(botResponse.get(1).contains("viento se llevo"), "La primera película es incorrecta: " + botResponse.get(1) + "||");
        assertTrue(botResponse.get(2).contains("Pinochio"), "La segunda película es incorrecta"+ botResponse.get(2) + "||");
        assertTrue(botResponse.get(3).contains("libro de la selva"), "La tercer película es incorrecta"+ botResponse.get(3) + "||");

    }

}
