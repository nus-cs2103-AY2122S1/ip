package main.java.duke.extensions;

import main.java.duke.tasks.Task;

/**
 * A contact class.
 */
public class Contact extends Task {
    private final int contactNumber;

    /**
     * Constructs a new contact object.
     *
     * @param name name of the contact person
     * @param contactNumber contact number of the person
     */
    public Contact(String name, int contactNumber) {
        super(name);
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return (this.name + ": " + this.contactNumber + "\n");
    }
}
