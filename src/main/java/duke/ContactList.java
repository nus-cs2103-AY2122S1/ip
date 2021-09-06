package main.java.duke;
import java.util.ArrayList;

import main.java.duke.extensions.Contact;

/**
 * A list of tasks.
 */
public class ContactList {
    protected ArrayList<Contact> contactList;
    /**
     * Constructs a new task list with a given list of tasks
     *
     * @param taskList a list of tasks
     */
    public ContactList(ArrayList<Contact> taskList) {
        this.contactList = taskList;
    }

    /**
     * Constructs a new empty task list
     */
    public ContactList() {
        this.contactList = new ArrayList<>();
    }

    public ArrayList<Contact> getContactList() {
        return this.contactList;
    }

}