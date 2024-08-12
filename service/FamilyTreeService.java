package service;

import model.Gender;
import model.Person;
import repository.FamilyTreeRepository;

import java.util.List;

public class FamilyTreeService {
    private FamilyTreeRepository repository;
    private Researcher<Person> researcher;

    public FamilyTreeService(FamilyTreeRepository repository) {
        this.repository = repository;
        this.researcher = new Researcher<>();
    }

    public void addFamilyMember(Person person) {
        repository.add(person);
    }

    public boolean setParentForChild(int parentId, int childId) {
        Person parent = repository.find(parentId);
        Person child = repository.find(childId);

        if (parent != null && child != null) {
            parent.addChild(child);
            if (parent.getGender() == Gender.MALE) {
                child.setFather(parent);
            } else if (parent.getGender() == Gender.FEMALE) {
                child.setMother(parent);
            }
            return true;
        }
        return false;
    }

    public void sortByName() {
        List<Person> members = repository.findAll();
        members.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
    }

    public void sortByBirthDate() {
        List<Person> members = repository.findAll();
        members.sort((p1, p2) -> p1.getBirthDate().compareTo(p2.getBirthDate()));
    }

    public List<Person> getFamilyMembers() {
        return repository.findAll();
    }

    public boolean saveFamilyTree(String filename) {
        try {
            repository.save(filename);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean loadFamilyTree(String filename) {
        try {
            repository.load(filename);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Person> getDescendants(int personId) {
        Person person = repository.find(personId);
        if (person != null) {
            return researcher.getDescendants(person);
        }
        return null;
    }
}