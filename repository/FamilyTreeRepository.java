package repository;

import model.Person;
import service.WriterAndReader;

import java.util.ArrayList;
import java.util.List;

public class FamilyTreeRepository implements Repository<Person> {
    private List<Person> members;
    private WriterAndReader writerAndReader;

    public FamilyTreeRepository() {
        this.members = new ArrayList<>();
        this.writerAndReader = new WriterAndReader();
    }

    @Override
    public void add(Person person) {
        members.add(person);
    }

    @Override
    public Person find(int id) {
        for (Person member : members) {
            if (member.getId() == id) {
                return member;
            }
        }
        return null;
    }

    @Override
    public List<Person> findAll() {
        return members;
    }

    @Override
    public void save(String filename) {
        writerAndReader.writeToFile(filename, members);
    }

    @Override
    public void load(String filename) {
        List<Person> loadedMembers = writerAndReader.readFromFile(filename);
        if (loadedMembers != null) {
            members = loadedMembers;
        }
    }
}