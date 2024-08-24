package view;

import presenter.FamilyTreePresenter;
import model.Gender;
import view.MenuItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class AddPersonMenuItem implements MenuItem {
    private FamilyTreePresenter presenter;

    public AddPersonMenuItem(FamilyTreePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Birth Date (YYYY-MM-DD): ");
        String birthDateString = scanner.nextLine();
        Date birthDate = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            birthDate = dateFormat.parse(birthDateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            return;
        }

        System.out.print("Enter Gender (MALE/FEMALE): ");
        String genderString = scanner.nextLine();
        Gender gender;
        try {
            gender = Gender.valueOf(genderString.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid gender. Please enter MALE or FEMALE.");
            return;
        }

        presenter.addPerson(id, name, birthDate, gender);
    }

    @Override
    public String getLabel() {
        return "Add Person";
    }
}
