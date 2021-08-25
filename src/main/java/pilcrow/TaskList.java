package pilcrow;

import java.util.ArrayList;

// Abstraction to handle the list of tasks altered when Pilcrow runs
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor for an empty TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructor for a TaskList with pre-existing Tasks.
     * @param taskList Pre-existing ArrayList of Tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Set the isDone status of a specified Task within the TaskList.
     * @param index Integer index of the specified Task.
     * @param isDone Boolean of whether the specified Task is done.
     */
    public void setTaskIsDone(int index, Boolean isDone) {
        index--;
        if (index < 0 || index >= this.taskList.size()) {
            throw new InvalidInputException("Must enter valid task number.");
        }
        this.taskList.get(index).setIsDone(isDone);
    }

    /**
     * Add a Task to the TaskList.
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Specifies a Task to be deleted from the TaskList.
     * @param index Integer index of specified Task.
     */
    public void deleteTask(int index) {
        index--;
        if (index < 0 || index >= this.taskList.size()) {
            throw new InvalidInputException("Must enter valid task number.");
        }
        this.taskList.remove(index);
    }

    /**
     * Retrieves specified Task from TaskList.
     * @param index Integer index of specified Task.
     * @return Specified Task.
     */
    public Task getTask(int index) {
        index--;
        if (index < 0 || index >= this.taskList.size()) {
            throw new InvalidInputException("Must enter valid task number.");
        }
        return this.taskList.get(index);
    }

    @Override
    public String toString() {
        String tasksAsString = "";
        for (int i = 0; i < taskList.size(); i++) {
            tasksAsString += (i + 1) + ". " + taskList.get(i) + "\n";
        }
        return tasksAsString;
    }

    /**
     * Returns number of Tasks currently in the TaskList.
     * @return Integer number of Tasks in the TaskList.
     */
    public int getNumberOfTasks() {
        return this.taskList.size();
    }
}