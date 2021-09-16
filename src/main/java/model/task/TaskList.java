package model.task;

import java.util.ArrayList;

/**
 * Class for dealing with a list of tasks object or its subclasses alternatives.
 *
 * @author Kan Jitpakdi
 * @author GitHub: kanjitp
 * @version 0.03
 * @since 0.01
 */
public class TaskList {
    /** ArrayList containing the tasks */
    private final ArrayList<Task> tasks;

    /**
     * enum for type of tasks available.
     */
    public enum TaskType {
        TODO, DEADLINE, EVENT
    }

    /**
     * default constructor for TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Default constructor for TaskList.
     *
     * @param tasks the arraylist of the tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Add the task to the taskList.
     *
     * @param task the task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Remove the task at given index.
     *
     * @param index index of the task to be removed.
     */
    public void remove(int index) {
        tasks.remove(index);
    }

    /**
     * Return the task at specific index.
     *
     * @param index index of the task.
     * @return the task at that index.
     * @throws IndexOutOfBoundsException if the index is invalid.
     */
    public Task get(int index) throws IndexOutOfBoundsException {
        return tasks.get(index);
    }

    /**
     * Getter for the array list of tasks.
     *
     * @return ArrayList of Task objects or its alternatives.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Getter for length of the list of tasks.
     *
     * @return the length of the tasks.
     */
    public int length() {
        return tasks.size();
    }

    /**
     * Check whether the task list contains any task matching the task description
     *
     * @param taskDescription the task description to check if this task list contains any task of such description
     * @return whether this task list has any task matching the task description
     */
    public boolean contains(String taskDescription) {
        return this.tasks.stream().anyMatch(task -> task.getDescription().equals(taskDescription));
    }

    /**
     * Check whether the task list contains any task which is equal to the task given in the argument
     *
     * @param task the task to check if it is contained in this task list
     * @return whether this task list has any task matching the given task
     */
    public boolean contains(Task task) {
        return this.tasks.stream().anyMatch(taskInList -> taskInList.equals(task));
    }

    /**
     * Static helper method for manually adding the task to the specified taskList.
     *
     * @param taskList    the taskList to add the task to.
     * @param type        the type of the task to be added to the taskList.
     * @param isDone      whether the task should be done or not.
     * @param description the description of the task.
     * @param time        if the task is subclass of time task specify the time in yyyy-MM-dd
     *                    or put empty string or null for non time task.
     */
    public static void addTaskByType(TaskList taskList, TaskType type, boolean isDone,
                                     String description, String time) {
        switch (type) {
        case TODO:
            taskList.add(new Todo(description, isDone));
            break;
        case EVENT:
            taskList.add(new Event(description, isDone, time));
            break;
        case DEADLINE:
            taskList.add(new Deadline(description, isDone, time));
            break;
        default:
            throw new IllegalArgumentException("addTask Unsuccessful");
        }
    }

    /**
     * A list representation of the tasks numbered from 1 to the last task in the list.
     *
     * @return string representation of the list of tasks.
     */
    @Override
    public String toString() {
        StringBuilder tasksDialog = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            tasksDialog.append("    ").append(i + 1).append(".").append(tasks.get(i).toString()).append("\n");
        }
        return tasksDialog.toString();
    }
}
