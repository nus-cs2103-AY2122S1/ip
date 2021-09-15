package duke.task;

import duke.Ui;

import java.util.ArrayList;

/**
 * Represents the list of tasks that are added by the user.
 */
public class TaskList {
    private ArrayList<Task> userList;

    /**
     * Represents a constructor for the TaskList class where the task list is initialized as an array list.
     */
    public TaskList() {
        userList = new ArrayList<>();
    }

    /**
     * Returns every task in the task list.
     * 
     * @return String representation of the various tasks (if any) in the task list
     */
    public String printList() {
        if (userList.isEmpty()) {
            String response = Ui.printInput("You don't have any tasks in the list!");
            return response;
        } 
        
        String response = "";
        int count = 1;
        for (int i = 0; i < userList.size(); i++) {
            Task t = userList.get(i);
            Ui.printInput(t.toString());
            response += count + ". " + t + "\n";
            count++;
        }
        return response;
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

    /**
     * Checks if the specified task is present in the task list.
     *
     * @param task Task object;
     * @return true or false
     */
    public boolean isTaskPresent(Task task) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).equals(task)) {
                return true;
            }        
        }
        return false;
    }
}
