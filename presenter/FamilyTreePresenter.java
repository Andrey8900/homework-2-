package presenter;

import model.Person;
import model.Gender;
import service.FamilyTree;
import service.WriterAndReader;
import service.Researcher;
import view.ConsoleView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class FamilyTreePresenter {
    private FamilyTree<Person> familyTree;
    private WriterAndReader writerAndReader;
    private ConsoleView view;
    private Researcher<Person> researcher;

    public FamilyTreePresenter() {
        this.familyTree = new FamilyTree<>();
        this.writerAndReader = new WriterAndReader();
        this.researcher = new Researcher<>();
        this.view = new ConsoleView(this);
    }

    public void addFamilyMember() {
        Scanner scanner = view.getScanner();
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
            familyTree.addMember(person);

            System.out.print("Укажите ID отца (если есть, иначе введите 0): ");
            int fatherId = scanner.nextInt();
            if (fatherId != 0) {
                Person father = familyTree.findMember(fatherId);
                if (father != null) {
                    person.setFather(father);
                    father.addChild(person);
                } else {
                    System.out.println("Отец с таким ID не найден.");
                }
            }

            System.out.print("Укажите ID матери (если есть, иначе введите 0): ");
            int motherId = scanner.nextInt();
            if (motherId != 0) {
                Person mother = familyTree.findMember(motherId);
                if (mother != null) {
                    person.setMother(mother);
                    mother.addChild(person);
                } else {
                    System.out.println("Мать с таким ID не найдена.");
                }
            }

            // Добавление братьев и сестер
            System.out.print("Укажите количество братьев/сестер: ");
            int siblingCount = scanner.nextInt();
            for (int i = 0; i < siblingCount; i++) {
                System.out.print("Укажите ID брата/сестры: ");
                int siblingId = scanner.nextInt();
                Person sibling = familyTree.findMember(siblingId);
                if (sibling != null) {
                    sibling.addChild(person);
                    person.addChild(sibling);
                } else {
                    System.out.println("Брат/сестра с таким ID не найден.");
                }
            }

        } catch (Exception e) {
            System.out.println("Ошибка при добавлении члена семьи: " + e.getMessage());
        }
    }

    public void setParentForChild() {
        Scanner scanner = view.getScanner();

        System.out.print("Введите ID родителя: ");
        int parentId = scanner.nextInt();
        System.out.print("Введите ID ребенка: ");
        int childId = scanner.nextInt();

        Person parent = familyTree.findMember(parentId);
        Person child = familyTree.findMember(childId);

        if (parent != null && child != null) {
            familyTree.addChild(parent, child);
            System.out.println("Родитель успешно установлен.");
        } else {
            System.out.println("Родитель или ребенок не найден.");
        }
    }

    public void showFamilyMembers() {
        System.out.println("Члены семьи:");
        for (Person member : familyTree) {
            System.out.println(member);
        }
    }

    public void sortByName() {
        familyTree.sortByName();
        System.out.println("Члены семьи отсортированы по имени:");
        showFamilyMembers();
    }

    public void sortByBirthDate() {
        familyTree.sortByBirthDate();
        System.out.println("Члены семьи отсортированы по дате рождения:");
        showFamilyMembers();
    }

    public void saveFamilyTree() {
        writerAndReader.writeToFile("familyTree.ser", familyTree);
        System.out.println("Семейное дерево сохранено в файл.");
    }

    public void loadFamilyTree() {
        FamilyTree<Person> loadedFamilyTree = writerAndReader.readFromFile("familyTree.ser");
        if (loadedFamilyTree != null) {
            familyTree = loadedFamilyTree;
            System.out.println("Семейное дерево загружено из файла.");
            showFamilyMembers(); // Вывод загруженных данных на экран
        } else {
            System.out.println("Не удалось загрузить семейное дерево из файла.");
        }
    }

    public void showDescendants() {
        Scanner scanner = view.getScanner();
        System.out.print("Введите ID человека для поиска потомков: ");
        int id = scanner.nextInt();
        Person person = familyTree.findMember(id);

        if (person != null) {
            List<Person> descendants = researcher.getDescendants(person);
            System.out.println("Потомки " + person.getName() + ":");
            for (Person descendant : descendants) {
                System.out.println(descendant);
            }
        } else {
            System.out.println("Человек не найден.");
        }
    }

    public void start() {
        view.start();
    }
}