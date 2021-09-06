package main.java.duke.extensions;

import main.java.duke.tasks.Task;

public class Contact extends Task {
    private int contactNumber;

    public Contact(String name, int contactNumber) {
        super(name);
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return (this.name + ": " + this.contactNumber + "\n");
    }
}