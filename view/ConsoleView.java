package view;

import model.Gender;
import model.Person;
import presenter.FamilyTreePresenter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
                    addFamilyMember();
                    break;
                case 2:
                    setParentForChild();
                    break;
                case 3:
                    showFamilyMembers();
                    break;
                case 4:
                    sortByName();
                    break;
                case 5:
                    sortByBirthDate();
                    break;
                case 6:
                    saveFamilyTree();
                    break;
                case 7:
                    loadFamilyTree();
                    break;
                case 8:
                    showDescendants();
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

    private void addFamilyMember() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            System.out.print("Введите ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Считывание символа новой строки

            System.out.print("Введите имя: ");
            String name = scanner.nextLine();

            System.out.print("Введите дату рождения (yyyy-MM-dd): ");
            String birthDateStr = scanner.nextLine();
            Date birthDate = sdf.parse(birthDateStr);

            System.out.print("Введите пол (MALE/FEMALE): ");
            Gender gender = Gender.valueOf(scanner.nextLine().toUpperCase());

            Person person = new Person(id, name, birthDate, gender);
            presenter.addFamilyMember(person);

            System.out.print("Укажите ID отца (если есть, иначе введите 0): ");
            int fatherId = scanner.nextInt();
            if (fatherId != 0) {
                if (!presenter.setParentForChild(fatherId, id)) {
                    System.out.println("Отец с таким ID не найден.");
                }
            }

            System.out.print("Укажите ID матери (если есть, иначе введите 0): ");
            int motherId = scanner.nextInt();
            if (motherId != 0) {
                if (!presenter.setParentForChild(motherId, id)) {
                    System.out.println("Мать с таким ID не найдена.");
                }
            }

            // Добавление братьев и сестер
            System.out.print("Укажите количество братьев/сестер: ");
            int siblingCount = scanner.nextInt();
            for (int i = 0; i < siblingCount; i++) {
                System.out.print("Укажите ID брата/сестры: ");
                int siblingId = scanner.nextInt();
                if (!presenter.setParentForChild(siblingId, id)) {
                    System.out.println("Брат/сестра с таким ID не найден.");
                }
            }

        } catch (Exception e) {
            System.out.println("Ошибка при добавлении члена семьи: " + e.getMessage());
        }
    }

    private void setParentForChild() {
        System.out.print("Введите ID родителя: ");
        int parentId = scanner.nextInt();
        System.out.print("Введите ID ребенка: ");
        int childId = scanner.nextInt();

        if (!presenter.setParentForChild(parentId, childId)) {
            System.out.println("Родитель или ребенок не найден.");
        } else {
            System.out.println("Родитель успешно установлен.");
        }
    }

    private void showFamilyMembers() {
        List<Person> familyMembers = presenter.getFamilyMembers();
        System.out.println("Члены семьи:");
        for (Person member : familyMembers) {
            System.out.println(member);
        }
    }

    private void sortByName() {
        presenter.sortByName();
        System.out.println("Члены семьи отсортированы по имени:");
        showFamilyMembers();
    }

    private void sortByBirthDate() {
        presenter.sortByBirthDate();
        System.out.println("Члены семьи отсортированы по дате рождения:");
        showFamilyMembers();
    }

    private void saveFamilyTree() {
        if (presenter.saveFamilyTree("familyTree.ser")) {
            System.out.println("Семейное дерево сохранено в файл.");
        } else {
            System.out.println("Ошибка при сохранении семейного дерева в файл.");
        }
    }

    private void loadFamilyTree() {
        if (presenter.loadFamilyTree("familyTree.ser")) {
            System.out.println("Семейное дерево загружено из файла.");
            showFamilyMembers();
        } else {
            System.out.println("Не удалось загрузить семейное дерево из файла.");
        }
    }

    private void showDescendants() {
        System.out.print("Введите ID человека для поиска потомков: ");
        int id = scanner.nextInt();
        List<Person> descendants = presenter.getDescendants(id);

        if (descendants != null) {
            System.out.println("Потомки " + id + ":");
            for (Person descendant : descendants) {
                System.out.println(descendant);
            }
        } else {
            System.out.println("Человек не найден.");
        }
    }

    public Scanner getScanner() {
        return scanner;
    }
}