package other;

import java.io.Serializable;
import model.Identifiable;

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

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    @Override
    public String toString() {
        return "Dog{id=" + id + ", name='" + name + '\'' + ", breed='" + breed + '\'' + '}';
    }
}