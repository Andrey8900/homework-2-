package service;

import model.Identifiable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class FamilyTree<T extends Identifiable & Serializable> implements Iterable<T>, Serializable {
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
        // Предполагается, что T - это Person, и у него есть метод addChild
        if (parent instanceof model.Person) {
            model.Person personParent = (model.Person) parent;
            personParent.addChild((model.Person) child);
            // Установка родительской связи для ребенка
            if (personParent.getGender() == model.Gender.MALE) {
                ((model.Person) child).setFather(personParent);
            } else if (personParent.getGender() == model.Gender.FEMALE) {
                ((model.Person) child).setMother(personParent);
            }
        }
    }

    public void sortByName(Comparator<? super T> comparator) {
        Collections.sort(members, comparator);
    }

    public void sortByBirthDate(Comparator<? super T> comparator) {
        Collections.sort(members, comparator);
    }

    @Override
    public Iterator<T> iterator() {
        return members.iterator();
    }
}