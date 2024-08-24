package service;

import model.Person;
import model.Gender;

import java.util.Date;

public class PersonService {
    public Person createPerson(int id, String name, Date birthDate, Gender gender) {
        return new Person(id, name, birthDate, gender);
    }
}