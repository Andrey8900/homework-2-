package main;

import presenter.FamilyTreePresenter;
import repository.FamilyTreeRepository;
import repository.Repository;
import service.FamilyTreeService;
import service.PersonService;
import view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        Repository repository = new FamilyTreeRepository();
        PersonService personService = new PersonService();
        FamilyTreeService service = new FamilyTreeService(repository, personService);
        FamilyTreePresenter presenter = new FamilyTreePresenter(service);
        ConsoleView view = new ConsoleView(presenter);
        view.start();
    }
}
