package service;

import model.Person;

import java.io.*;
import java.util.List;

public class WriterAndReader {

    public void writeToFile(String filename, List<Person> members) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(members);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Person> readFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<Person>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}