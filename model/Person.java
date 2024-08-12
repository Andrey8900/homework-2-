package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Person implements Identifiable {
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

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Date getBirthDate() {
        return birthDate;
    }

    @Override
    public Gender getGender() {
        return gender;
    }

    public Person getFather() {
        return father;
    }

    @Override
    public void setFather(Identifiable father) {
        this.father = (Person) father;
    }

    public Person getMother() {
        return mother;
    }

    @Override
    public void setMother(Identifiable mother) {
        this.mother = (Person) mother;
    }

    @Override
    public List<? extends Identifiable> getChildren() {
        return children;
    }

    @Override
    public void addChild(Identifiable child) {
        children.add((Person) child);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Person{id=").append(id)
                .append(", name='").append(name).append('\'')
                .append(", birthDate=").append(birthDate)
                .append(", gender=").append(gender);

        if (father != null) {
            sb.append(", father='").append(father.getName()).append('\'');
        }
        if (mother != null) {
            sb.append(", mother='").append(mother.getName()).append('\'');
        }

        if (!children.isEmpty()) {
            sb.append(", children=[");
            for (Person child : children) {
                sb.append(child.getName()).append(", ");
            }
            sb.setLength(sb.length() - 2); // удалить последнюю запятую и пробел
            sb.append(']');
        }

        sb.append('}');
        return sb.toString();
    }
}