package utils;

import java.io.*;

public interface SerializableUtils<T> {
    void writeToFile(String filename, T object);
    T readFromFile(String filename);
}