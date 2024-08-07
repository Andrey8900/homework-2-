package service;

import model.Identifiable;

import java.io.*;

public class WriterAndReader {

    public <T extends Identifiable & Serializable> void writeToFile(String filename, FamilyTree<T> familyTree) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(familyTree);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T extends Identifiable & Serializable> FamilyTree<T> readFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (FamilyTree<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}