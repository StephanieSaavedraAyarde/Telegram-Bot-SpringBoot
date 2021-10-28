package bo.edu.ucb.chatbot.bl;

import bo.edu.ucb.chatbot.dto.ActorFilm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class BotActorFilmSearchBl {
    private ActorFilmSearchBl actorFilmSearchBl;

    @Autowired
    public BotActorFilmSearchBl(ActorFilmSearchBl actorFilmSearchBl) {
        this.actorFilmSearchBl = actorFilmSearchBl;
    }

    public List<String> processMessage(String firstname, String lastname, String title) {
        List<String> result = new ArrayList<>();

        List<ActorFilm> actorList =  actorFilmSearchBl.findByTitleActor(firstname, lastname, title);

        if (!actorList.isEmpty()) {
            result.add("Encontré las siguientes películas donde actuo el actor: "+
                        firstname+" "+lastname+" y contiene "+title+" en el titulo");
            for (ActorFilm actor : actorList) {
                result.add(actor.toString());
            }
        } else {
            result.add("No encontré ninguna película donde actue el actor: "+firstname+" "+lastname+
                        " y contenga "+title+" en el titulo");
        }

        return result;
    }
}