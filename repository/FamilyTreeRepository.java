package repository;

import model.Person;

import java.util.ArrayList;
import java.util.List;

public class FamilyTreeRepository implements Repository {
    private List<Person> people;

    public FamilyTreeRepository() {
        this.people = new ArrayList<>();
    }

    @Override
    public void save(Person person) {
        people.add(person);
    }

    // Другие методы для работы с хранилищем
}
