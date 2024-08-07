package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface Identifiable extends Serializable {
    int getId();
    void addChild(Identifiable child);
    void setFather(Identifiable father);
    void setMother(Identifiable mother);
    Gender getGender();
    String getName();
    Date getBirthDate();
    List<? extends Identifiable> getChildren();
}