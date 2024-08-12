package utils;

import service.FamilyTreeService;
import model.Identifiable;

import java.io.*;

public class WriterAndReader {

    public <T extends Identifiable & Serializable> void writeToFile(String filename, FamilyTreeService<T> familyTreeService) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(familyTreeService);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T extends Identifiable & Serializable> FamilyTreeService<T> readFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (FamilyTreeService<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}