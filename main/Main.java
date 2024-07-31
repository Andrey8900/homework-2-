package main;

import model.Person;
import model.Gender;
import service.FamilyTree;
import service.Researcher;
import utils.WriterAndReader;

import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws Exception {
        // Создание экземпляров класса Person с датами рождения
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Person ivan = new Person(1, "Ivan", sdf.parse("1980-01-01"), Gender.MALE);
        Person maria = new Person(2, "Maria", sdf.parse("1982-02-02"), Gender.FEMALE);
        Person deti1 = new Person(3, "Deti1", sdf.parse("2005-03-03"), Gender.MALE);
        Person deti2 = new Person(4, "Deti2", sdf.parse("2007-04-04"), Gender.FEMALE);

        // Создание экземпляра класса FamilyTree и добавление членов семьи
        FamilyTree<Person> familyTree = new FamilyTree<>();
        familyTree.addMember(ivan);
        familyTree.addMember(maria);
        familyTree.addMember(deti1);
        familyTree.addMember(deti2);

        // Установка родителей и детей
        familyTree.addChild(ivan, deti1);
        familyTree.addChild(ivan, deti2);
        familyTree.addChild(maria, deti1);
        familyTree.addChild(maria, deti2);

        // Создание экземпляра класса Researcher
        Researcher researcher = new Researcher();

        // Получение всех потомков
        List<Person> descendants = researcher.getDescendants(ivan);

        // Создание экземпляра WriterAndReader
        WriterAndReader writerAndReader = new WriterAndReader();

        // Запись данных в файл
        writerAndReader.writeToFile("familyTree.ser", familyTree);

        System.out.println("Contents of familyTree.ser:");
        FamilyTree<Person> loadedFamilyTree = writerAndReader.readFromFile("familyTree.ser");
        for (Person member : loadedFamilyTree) {
            System.out.println(member);
        }

        // Сортировка и вывод информации о членах семьи
        System.out.println("\nFamily members sorted by name:");
        loadedFamilyTree.sortByName(Comparator.comparing(Person::getName));
        for (Person member : loadedFamilyTree) {
            System.out.println(member);
        }

        System.out.println("\nFamily members sorted by birth date:");
        loadedFamilyTree.sortByBirthDate(Comparator.comparing(Person::getBirthDate));
        for (Person member : loadedFamilyTree) {
            System.out.println(member);
        }
    }
}