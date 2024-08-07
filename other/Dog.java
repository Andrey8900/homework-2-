package other;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import model.Identifiable;
import model.Gender;

public class Dog implements Identifiable, Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String breed;

    public Dog(int id, String name, String breed) {
        this.id = id;
        this.name = name;
        this.breed = breed;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    @Override
    public List<? extends Identifiable> getChildren() {
        return Collections.emptyList(); // Dogs do not have children in this context
    }

    @Override
    public void addChild(Identifiable child) {
        // No implementation needed for Dog
    }

    @Override
    public void setFather(Identifiable father) {
        // No implementation needed for Dog
    }

    @Override
    public void setMother(Identifiable mother) {
        // No implementation needed for Dog
    }

    @Override
    public Gender getGender() {
        // No implementation needed for Dog
        return null;
    }

    @Override
    public Date getBirthDate() {
        // No implementation needed for Dog
        return null;
    }

    @Override
    public String toString() {
        return "Dog{id=" + id + ", name='" + name + '\'' + ", breed='" + breed + '\'' + '}';
    }
}