package bo.edu.ucb.chatbot.dao;

import bo.edu.ucb.chatbot.dto.Person;

public class PersonMysql implements PersonStore {
    public PersonMysql() {
    }

    @Override
    public void persistPerson(Person person){
        // Guardadndo en Mysql
        System.out.println("Guardando en Mysql");
    }


    public void persistPerson(String name, String surename){
        Person person = new Person(name, surename);
        persistPerson(person);
    }

    @Override
    public void deletePerson(Person person) {
        System.out.println("Eliminand en Mysql");
    }

    @Override
    public Person findByName(String name) {
        System.out.println("Buscando en Mysql");
        return null;
    }
}
