package view;

import presenter.FamilyTreePresenter;

import java.util.Scanner;

public class ConsoleView {
    private FamilyTreePresenter presenter;
    private Scanner scanner;

    public ConsoleView(FamilyTreePresenter presenter) {
        this.presenter = presenter;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Добавить члена семьи");
            System.out.println("2. Установить родителя для ребенка");
            System.out.println("3. Показать всех членов семьи");
            System.out.println("4. Сортировать по имени");
            System.out.println("5. Сортировать по дате рождения");
            System.out.println("6. Сохранить семейное дерево в файл");
            System.out.println("7. Загрузить семейное дерево из файла");
            System.out.println("8. Показать всех потомков");
            System.out.println("9. Выход");
            System.out.print("Введите номер действия: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Считывание символа новой строки

            switch (choice) {
                case 1:
                    presenter.addFamilyMember();
                    break;
                case 2:
                    presenter.setParentForChild();
                    break;
                case 3:
                    presenter.showFamilyMembers();
                    break;
                case 4:
                    presenter.sortByName();
                    break;
                case 5:
                    presenter.sortByBirthDate();
                    break;
                case 6:
                    presenter.saveFamilyTree();
                    break;
                case 7:
                    presenter.loadFamilyTree();
                    break;
                case 8:
                    presenter.showDescendants();
                    break;
                case 9:
                    System.out.println("Выход из программы.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
            }
        }
    }

    public Scanner getScanner() {
        return scanner;
    }
}