package bo.edu.ucb.chatbot.dao;

import bo.edu.ucb.chatbot.dto.Person;

public interface PersonStore {

    void persistPerson(Person person);

    void deletePerson(Person person);

    Person findByName(String name);


}
