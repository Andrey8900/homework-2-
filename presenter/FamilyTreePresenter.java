package presenter;

import model.Person;
import service.FamilyTreeService;

import java.util.List;

public class FamilyTreePresenter {
    private FamilyTreeService service;

    public FamilyTreePresenter(FamilyTreeService service) {
        this.service = service;
    }

    public void addFamilyMember(Person person) {
        service.addFamilyMember(person);
    }

    public boolean setParentForChild(int parentId, int childId) {
        return service.setParentForChild(parentId, childId);
    }

    public List<Person> getFamilyMembers() {
        return service.getFamilyMembers();
    }

    public void sortByName() {
        service.sortByName();
    }

    public void sortByBirthDate() {
        service.sortByBirthDate();
    }

    public boolean saveFamilyTree(String filename) {
        return service.saveFamilyTree(filename);
    }

    public boolean loadFamilyTree(String filename) {
        return service.loadFamilyTree(filename);
    }

    public List<Person> getDescendants(int id) {
        return service.getDescendants(id);
    }
}