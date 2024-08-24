package service;

import model.Person;
import model.Gender;
import repository.Repository;

import java.util.Date;

public class FamilyTreeService {
    private Repository repository;
    private PersonService personService;

    public FamilyTreeService(Repository repository, PersonService personService) {
        this.repository = repository;
        this.personService = personService;
    }

    public void addPerson(int id, String name, Date birthDate, Gender gender) {
        Person person = personService.createPerson(id, name, birthDate, gender);
        repository.save(person);
    }

    // Другие методы работы с семейным деревом
}
