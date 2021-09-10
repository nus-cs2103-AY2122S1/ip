package saber.tasklist;

import java.util.ArrayList;

import saber.task.Task;

/**
 * Represents the TaskList used to store tasks when Saber application is still running
 */
public class TaskList {
    protected ArrayList<Task> taskList;

    /**
     * Constructs a TaskList object which takes in an ArrayList of tasks
     *
     * @param taskList the taskList (an ArrayList) that will be used to store the tasks
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task to the TaskList
     *
     * @param task the task that will be added
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes a task given its index
     *
     * @param taskIndex the index of the task that will be deleted
     */
    public void delete(int taskIndex) {
        taskList.remove(taskIndex);
    }

    /**
     * Gets the size of the TaskList
     *
     * @return size of the tasklist
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Gets a task given its index
     *
     * @param index the index of the task that will be returned
     * @return the Task specified
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Sorts a TaskList according to its done level and return a new TaskList
     *
     * @return sorted tasklist
     */
    public TaskList sort() {
        ArrayList<Task> temp = new ArrayList<>();
        taskList.stream().forEach((task) -> temp.add(task));
        temp.sort((Task a, Task b) -> Boolean.valueOf(a.getIsDone()).compareTo(Boolean.valueOf(b.getIsDone())));
        return new TaskList(temp);
    }
}
