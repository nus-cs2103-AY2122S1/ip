package duke.task;

import duke.Ui;

import java.util.ArrayList;

/**
 * Represents the list of tasks that are added by the user.
 */
public class TaskList {
    private ArrayList<Task> userList;

    /**
     * Constructor for the TaskList class where the task list is initialized.
     */
    public TaskList() {
        userList = new ArrayList<>();
    }

    /**
     * Prints every task in the task list.
     */
    public void printList() {
        if (userList.isEmpty()) {
            Ui.printMessage("You don't have any tasks in the list!");
        } else {
            int count = 1;
            for (int i = 0; i < userList.size(); i++) {
                Task t = userList.get(i);
                System.out.println(count + ". " + t.toString());
                count++;
            }
        }
    }

    /**
     * Returns the size of the task list.
     *
     * @return Number of items in the task list.
     */
    public int getSize() {
        return userList.size();
    }

    /**
     * Returns the task at the specified index (val) from the task list.
     *
     * @param val Index value;
     * @return Task object.
     */
    public Task getTask(int val) {
        return userList.get(val);
    }

    /**
     * Adds the specified task to the task list.
     *
     * @param task Task object;
     */
    public void addTask(Task task) {
        userList.add(task);
    }

    /**
     * Removes the task at the specified index (val) from the task list.
     *
     * @param val Index value;
     */
    public void removeTask(int val) {
        userList.remove(val);
    }
}
