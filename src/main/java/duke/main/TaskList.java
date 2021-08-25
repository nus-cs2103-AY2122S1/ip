package duke.main;

import duke.task.Task;
import java.util.ArrayList;

/**
 * The TaskList class is for handling the storage and manipulation of the user's tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Class constructor which takes in an ArrayList of task, which will be contained in the TaskList object.
     *
     * @param tasks The initial ArrayList of task.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Method to add a new Task into the TaskList, adds to the back of the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Method to mark the task at the specified index as done.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markTaskAsDone(int index) {
        this.tasks.get(index).markAsDone();
    }

    /**
     * Method to delete the task at the specified index.
     *
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    /**
     * Method to retrieve a task at the specified index.
     *
     * @param index The index of the task to be retrieved.
     * @return Task The task that is retrieved based on the index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Method to retrieve the whole ArrayList of task stored in the TaskList object.
     *
     * @return ArrayList The ArrayList of tasks that are stored in the TaskList object.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Overridden toString method which creates a String representation of all the tasks in the TaskList.
     *
     * @return String The String representation of all the tasks in the TaskList.
     */
    @Override
    public String toString() {
        String str = "";
        int size = tasks.size();
        if (size == 0) {
            str = "\t Nothing has been added to the list";
        }

        for (int i = 0; i < size; i++) {
            str += "\t " + (i + 1) + ". " + this.tasks.get(i) + "\n";
        }

        return str;
    }

    /**
     * Method that returns the size of the ArrayList in TaskList.
     *
     * @return int The number of tasks in the TaskList.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Method which finds all the Task in the TaskList that match the keyword given.
     *
     * @param keyword The keyword entered by the user.
     * @return TaskList The resultant TaskList that contains all the Task that match the keyword.
     */
    public TaskList findMatchingTask(String keyword) {
        ArrayList<Task> resultList = new ArrayList<>();

        for (Task t : this.tasks) {
            if (t.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                resultList.add(t);
            }
        }

        return new TaskList(resultList);
    }
}
