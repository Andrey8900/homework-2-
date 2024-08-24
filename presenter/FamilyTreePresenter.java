package presenter;

import service.FamilyTreeService;
import model.Gender;

import java.util.Date;

public class FamilyTreePresenter {
    private FamilyTreeService service;

    public FamilyTreePresenter(FamilyTreeService service) {
        this.service = service;
    }

    public void addPerson(int id, String name, Date birthDate, Gender gender) {
        service.addPerson(id, name, birthDate, gender);
    }


}