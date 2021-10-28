package bo.edu.ucb.chatbot.dao;

import bo.edu.ucb.chatbot.dto.Person;

public class PersonFirebase implements PersonStore {

    public PersonFirebase() {
    }

    @Override
    public void persistPerson(Person person) {
        // Guardando en Firebas
        System.out.println("Guardando en Firebase");
    }

    @Override
    public void deletePerson(Person person) {
        System.out.println("Eliminando en Firebase");
    }

    @Override
    public Person findByName(String name) {
        System.out.println("Buscando en Firebas");
        return null;
    }
}
