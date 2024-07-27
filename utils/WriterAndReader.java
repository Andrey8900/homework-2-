package utils;

import service.FamilyTree;
import java.io.*;

public class WriterAndReader {

    public void writeToFile(String filename, FamilyTree familyTree) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(familyTree);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FamilyTree readFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (FamilyTree) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}