package bo.edu.ucb.chatbot;

import bo.edu.ucb.chatbot.dao.PersonStore;
import bo.edu.ucb.chatbot.dto.Person;

public class PersonBL {

    private PersonStore personStore;

    public PersonBL(PersonStore personStore) {
        this.personStore = personStore;
    }

    public void mainLogic() {
        Person person = personStore.findByName("Juan");
        personStore.deletePerson(person);
    }
}
