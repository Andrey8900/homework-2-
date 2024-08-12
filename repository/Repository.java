package repository;

import java.util.List;

public interface Repository<T> {
    void add(T item);
    T find(int id);
    List<T> findAll();
    void save(String filename);
    void load(String filename);
}