package bobbybot.util;

import java.util.List;

import bobbybot.exceptions.TaskNoOutOfBoundsException;
import bobbybot.person.Person;

/**
 * Represents a contact list
 */
public class PersonList {
    private final List<Person> contacts;
    public PersonList(List<Person> contacts) {
        this.contacts = contacts;
    }

    /**
     * Getter for Person
     * @param i person to get, index starts from 0
     * @return task chosen
     */
    public Person getContact(int i) {
        return contacts.get(i);
    }

    /**
     * Adds a person to contacts list
     * @param person person to add
     */
    public void addPerson(Person person) {
        contacts.add(person);
    }

    /**
     * Delete a task and return response, starting from 1
     * @param i index to delete starting from 1
     */
    public void deleteContact(int i) throws TaskNoOutOfBoundsException {
        if (i < 1) {
            throw new TaskNoOutOfBoundsException("You are trying to delete a task number < 1");
        } else if (i > contacts.size()) {
            throw new TaskNoOutOfBoundsException("Task number you are trying to delete does not exist");
        }

        Person contactToDelete = contacts.get(i - 1);
        System.out.println("  " + contactToDelete);
        contacts.remove(contactToDelete);
    }

    /**
     * Returns size of person list
     * @return size
     */
    public int size() {
        return contacts.size();
    }


}
