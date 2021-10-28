package bo.edu.ucb.chatbot.bl;

import bo.edu.ucb.chatbot.dao.ActorFilmDao;
import bo.edu.ucb.chatbot.dto.ActorFilm;
import bo.edu.ucb.chatbot.exception.SakilaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ActorFilmSearchBl {

    private final ActorFilmDao actorFilmDao;

    @Autowired
    public ActorFilmSearchBl(ActorFilmDao actorFilmDao) {
        this.actorFilmDao = actorFilmDao;
    }

    public List<ActorFilm> findByTitleActor(String firstname, String lastname, String title) {
        if (firstname == null || firstname.trim().equals("")
            || lastname == null || lastname.trim().equals("")
            || title == null || title.trim().equals("")) {
            throw new SakilaException(403, "Bad request: The title parameter is mandatory and can't be null or empty");
        }
        return actorFilmDao.findByTitleActor(firstname, lastname, title);
    }
}
