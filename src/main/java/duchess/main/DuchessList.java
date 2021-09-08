package duchess.main;

import java.util.ArrayList;

import duchess.task.Task;

/**
 * This class implements a DuchessList to be used in storing string from Duchess.
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */
public class DuchessList {
    /** The ArrayList which stores the messages by the user.*/
    private ArrayList<Task> taskList;

    /**
     * Constructs a DuchessList.
     */
    public DuchessList() {
        taskList = new ArrayList<>();
    }

    /**
     * Adds a string to the back of the DuchessList.
     * @param taskToAdd The string to be added.
     */
    public void add(Task taskToAdd) {
        taskList.add(taskToAdd);
    }

    /**
     * Prints the items in the DuchessList in order.
     * @return The string of printed items in a list.
     */
    public String printList() {
        String printed = "";
        int size = taskList.size();
        for (int i = 0; i < size; i++) {
            printed += String.format("%d. " + taskList.get(i) + (i == size - 1 ? "" : "\n"), i + 1);
        }
        return printed.isBlank() ? "You have nothing in your list." : printed;
    }

    /**
     * Checks if the item number is within range of the list's items.
     * @param listNumber The item number (not ArrayList index) to be checked.
     * @return Whether the number is within the list's range.
     */
    public boolean checkWithinRange(int listNumber) {
        return 0 < listNumber && listNumber <= taskList.size();
    }

    /**
     * Returns the item at the list number.
     * @param listNumber The item number whose item is to be returned.
     * @return The item at the list number.
     * @throws IllegalArgumentException Only accepts integers whose values are within range of the ArrayList.
     */
    public Task getTask(int listNumber) throws IllegalArgumentException {
        if (checkWithinRange(listNumber)) {
            return taskList.get(listNumber - 1); // -1 due to difference between item list number and array indexes
        } else {
            throw new IllegalArgumentException("Task index is not within range."); // Not within range
        }
    }

    /**
     * Gets the size of the DuchessList.
     * @return The number of elements in the DuchessList.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Deletes the task with the given number in the list.
     * @param listNumber The number in the list of the task to be deleted.
     * @return The deleted task.
     */
    public Task delete(int listNumber) {
        assert checkWithinRange(listNumber) : "Number should be within the list.";
        return taskList.remove(listNumber - 1); // -1 due to difference between item list number and indexes
    }

    /**
     * Checks if a task already exists in the list.
     * @param taskToCheck The task to be checked for duplicate entries in the list.
     * @return Whether the task already exists in the list.
     */
    public boolean checkForDuplicates(Task taskToCheck) {
        for (Task t : taskList) {
            if (t.equals(taskToCheck)) {
                return true;
            }
        }
        return false;
    }
}
