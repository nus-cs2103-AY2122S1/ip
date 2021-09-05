package duke.data;

import duke.information.Contact;
import duke.information.Task;

import java.util.ArrayList;

/**
 * Class that deals with updating the ArrayLists of information.
 */
public class InformationList {
    /** Arraylist that contains the user's saved tasks. */
    private ArrayList<Task> tasks;
    /** Arraylist that contains the user's saved contacts. */
    private ArrayList<Contact> contacts;

    /**
     * Constructs InformationList class.
     * Creates a InformationList with 0 saved tasks and 0 saved contacts.
     *
     */
    public InformationList() {
        this.tasks = new ArrayList<>();
        this.contacts = new ArrayList<>();
    }

    /**
     * Adds a new task into the ArrayList.
     *
     * @param newTask The task to be added.
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Adds a new contact into the ArrayList.
     *
     * @param newContact The task to be added.
     */
    public void addContact(Contact newContact) {
        contacts.add(newContact);
    }

    /**
     * Deletes a task from the ArrayList.
     *
     * @param oldTaskIndex The task to be deleted.
     */
    public void deleteTask(int oldTaskIndex) {
        tasks.remove(oldTaskIndex);
    }

    /**
     * Deletes a new contact into the ArrayList.
     *
     * @param oldContactIndex The contact to be deleted.
     */
    public void deleteContact(int oldContactIndex) {
        contacts.remove(oldContactIndex);
    }

    /**
     * Marks a task in the ArrayList as done.
     *
     * @param index Index of the task.
     */
    public void markTaskAsDone(int index) {
        tasks.get(index).markAsDone();
    }

    /**
     * Gets the total size of all ArrayLists in InformationList.
     *
     * @return The total length of all ArrayLists.
     */
    public int getTotalSize() {
        return tasks.size() + contacts.size();
    }

    /**
     * Gets the size of the ArrayList storing tasks.
     *
     * @return The length of the ArrayList storing tasks.
     */
    public int getTasksSize() {
        return tasks.size();
    }

    /**
     * Gets the size of the ArrayList storing contacts.
     *
     * @return The length of the ArrayList storing contacts.
     */
    public int getContactsSize() {
        return contacts.size();
    }

    /**
     * Gets the Task at the specified index.
     *
     * @param index Index of the Task in the Task ArrayList.
     * @return Task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Gets the Contact at the specified index.
     *
     * @param index Index of the Contact in the Contact ArrayList.
     * @return Contact at the specified index.
     */
    public Contact getContact(int index) {
        return contacts.get(index);
    }

    /**
     * Filters through the ArrayList<Task> and returns a new String with tasks containing the keyword.
     *
     * @param keyword Keyword to be searched by.
     * @return A new String of tasks that contain the keyword.
     */
    public String searchTaskByKeyword(String keyword) {
        String filteredTaskList = "";
        for (Task task : this.tasks) {
            if (task.toString().contains(keyword)) {
                filteredTaskList += (task + "\n");
            }
        }
        return filteredTaskList;
    }

    /**
     * Filters through the ArrayList<Contact> and returns a new String with contacts containing the keyword.
     *
     * @param keyword Keyword to be searched by.
     * @return A new String of contacts that contain the keyword.
     */
    public String searchContactByKeyword(String keyword) {
        String filteredContactList = "";
        for (Contact contact : this.contacts) {
            if (contact.toString().contains(keyword)) {
                filteredContactList += (contact + "\n");
            }
        }
        return filteredContactList;
    }
}
