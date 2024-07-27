package service;

import model.Person;
import model.Gender;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class FamilyTree implements Iterable<Person>, Serializable {
    private static final long serialVersionUID = 1L;

    private List<Person> members;

    public FamilyTree() {
        this.members = new ArrayList<>();
    }

    public void addMember(Person person) {
        members.add(person);
    }

    public Person findMember(int id) {
        for (Person member : members) {
            if (member.getId() == id) {
                return member;
            }
        }
        return null;
    }

    public void addChild(Person parent, Person child) {
        parent.addChild(child);
        if (parent.getGender() == Gender.MALE) {
            child.setFather(parent);
        } else if (parent.getGender() == Gender.FEMALE) {
            child.setMother(parent);
        }
    }

    public void sortByName() {
        Collections.sort(members, Comparator.comparing(Person::getName));
    }

    public void sortByBirthDate() {
        Collections.sort(members, Comparator.comparing(Person::getBirthDate));
    }

    @Override
    public Iterator<Person> iterator() {
        return members.iterator();
    }
}