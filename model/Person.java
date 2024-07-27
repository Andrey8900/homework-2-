package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private Date birthDate;
    private Gender gender;
    private Person father;
    private Person mother;
    private List<Person> children;

    public Person(int id, String name, Date birthDate, Gender gender) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.children = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public Person getFather() {
        return father;
    }

    public void setFather(Person father) {
        this.father = father;
    }

    public Person getMother() {
        return mother;
    }

    public void setMother(Person mother) {
        this.mother = mother;
    }

    public List<Person> getChildren() {
        return children;
    }

    public void addChild(Person child) {
        children.add(child);
    }

    @Override
    public String toString() {
        return "Person{id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", gender=" + gender +
                ", father=" + (father != null ? father.getName() : "null") +
                ", mother=" + (mother != null ? mother.getName() : "null") +
                ", children=" + childrenNames() +
                '}';
    }

    private String childrenNames() {
        StringBuilder names = new StringBuilder();
        for (Person child : children) {
            if (names.length() > 0) {
                names.append(", ");
            }
            names.append(child.getName());
        }
        return names.toString();
    }
}