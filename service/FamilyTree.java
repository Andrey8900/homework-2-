package service;

import model.Identifiable;
import model.Gender;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Comparator;

public class FamilyTree<T extends Identifiable> implements Iterable<T>, Serializable {
    private static final long serialVersionUID = 1L;

    private List<T> members;

    public FamilyTree() {
        this.members = new ArrayList<>();
    }

    public void addMember(T member) {
        members.add(member);
    }

    public T findMember(int id) {
        for (T member : members) {
            if (member.getId() == id) {
                return member;
            }
        }
        return null;
    }

    public void addChild(T parent, T child) {
        parent.addChild(child);
        if (parent.getGender() == Gender.MALE) {
            child.setFather(parent);
        } else if (parent.getGender() == Gender.FEMALE) {
            child.setMother(parent);
        }
    }

    public void sortByName() {
        members.sort(Comparator.comparing(Identifiable::getName));
    }

    public void sortByBirthDate() {
        members.sort(Comparator.comparing(Identifiable::getBirthDate));
    }

    @Override
    public Iterator<T> iterator() {
        return members.iterator();
    }
}