package main;

import presenter.FamilyTreePresenter;
import repository.FamilyTreeRepository;
import service.FamilyTreeService;
import view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        FamilyTreeRepository repository = new FamilyTreeRepository();
        FamilyTreeService service = new FamilyTreeService(repository);
        FamilyTreePresenter presenter = new FamilyTreePresenter(service);
        ConsoleView view = new ConsoleView(presenter);
        view.start();
    }
}