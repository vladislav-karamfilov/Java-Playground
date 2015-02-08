package it.vkaramfilov.librarysystem;

public abstract class Person {
    private static final int NAME_MIN_LENGTH = 2;

    private String name;

    protected Person(String name) {
        this.setName(name);
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Person name cannot be null!");
        }

        String trimmedName = name.trim();
        if (trimmedName.length() < NAME_MIN_LENGTH) {
            throw new IllegalArgumentException("Person name cannot be less than " + NAME_MIN_LENGTH + " characters long!");
        }

        this.name = trimmedName;
    }
}
