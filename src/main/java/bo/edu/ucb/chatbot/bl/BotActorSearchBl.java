package bo.edu.ucb.chatbot.bl;

import bo.edu.ucb.chatbot.dto.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class BotActorSearchBl {
    private ActorSearchBl actorSearchBl;

    @Autowired
    public BotActorSearchBl(ActorSearchBl actorSearchBl) {
        this.actorSearchBl = actorSearchBl;
    }

    public List<String> processMessage(String firstname, String lastname) {
        List<String> result = new ArrayList<>();

        List<Actor> actorList =  actorSearchBl.findByActor(firstname, lastname);

        System.out.println("Lista de Actores: "+actorList);
        if (!actorList.isEmpty()) {
            result.add("Encontré las siguientes películas con el actor: "+firstname+" "+lastname);
            for (Actor actor : actorList) {
                result.add(actor.toString());
            }
        } else {
            result.add("No encontré ninguna película con el actor: "+firstname+" "+lastname);
        }

        return result;
    }
}
