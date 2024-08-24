package view;

import presenter.FamilyTreePresenter;
import view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {
    private FamilyTreePresenter presenter;
    private List<MenuItem> menuItems;

    public ConsoleView(FamilyTreePresenter presenter) {
        this.presenter = presenter;
        this.menuItems = new ArrayList<>();
        initializeMenu();
    }

    private void initializeMenu() {
        menuItems.add(new view.AddPersonMenuItem(presenter));
        // Добавить другие пункты меню
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            if (choice < 1 || choice > menuItems.size()) {
                System.out.println("Invalid choice. Try again.");
                continue;
            }
            menuItems.get(choice - 1).execute();
        }
    }

    private void displayMenu() {
        System.out.println("Menu:");
        for (int i = 0; i < menuItems.size(); i++) {
            System.out.println((i + 1) + ". " + menuItems.get(i).getLabel());
        }
    }
}