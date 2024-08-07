package service;

import model.Identifiable;

import java.util.ArrayList;
import java.util.List;

public class Researcher<T extends Identifiable> {
    public List<T> getDescendants(T person) {
        return collectDescendants(person);
    }

    private List<T> collectDescendants(T person) {
        List<T> descendants = new ArrayList<>();
        if (person != null) {
            for (Identifiable child : person.getChildren()) {
                T childAsT = (T) child; // Преобразование типа
                descendants.add(childAsT);
                descendants.addAll(collectDescendants(childAsT));
            }
        }
        return descendants;
    }
}