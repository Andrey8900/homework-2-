package service;

import model.Person;

import java.util.ArrayList;
import java.util.List;

public class Researcher {
    public List<Person> getDescendants(Person person) {
        return collectDescendants(person);
    }

    private List<Person> collectDescendants(Person person) {
        List<Person> descendants = new ArrayList<>();
        if (person != null) {
            for (Person child : person.getChildren()) {
                descendants.add(child);
                descendants.addAll(collectDescendants(child));
            }
        }
        return descendants;
    }
}