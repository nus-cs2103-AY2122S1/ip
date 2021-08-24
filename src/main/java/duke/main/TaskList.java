package duke.main;

import java.util.ArrayList;
import java.util.Iterator;

import duke.task.Task;

/**
 * Represents an iterable list that can hold Tasks and encapsulates methods for interacting with the list.
 */
public class TaskList implements Iterable<Task> {

    /** ArrayList used to hold Tasks */
    private ArrayList<Task> taskList;

    /**
     * Constructs a TaskList that can hold Tasks.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Returns the iterator of the TaskList so that it is iterable.
     *
     * @return Iterator of the ArrayList representing the TaskList.
     */
    @Override
    public Iterator<Task> iterator() {
        return this.taskList.iterator();
    }

    /**
     * Adds the Task into the TaskList.
     *
     * @param newTask Task to be added into the TaskList.
     */
    public void addTask(Task newTask) {
        this.taskList.add(newTask);
    }

    /**
     * Removes a Task with the given index from the TaskList and returns it.
     *
     * @param taskIndex Index of the Task to be removed from the TaskList.
     * @return Task that was removed from the TaskList.
     */
    public Task removeTask(int taskIndex) {
        return this.taskList.remove(taskIndex);
    }

    /**
     * Marks a Task from the TaskList with the given index as done and returns it.
     *
     * @param taskIndex Index of the Task in the TaskList to be marked as done.
     * @return Task from the TaskList that was marked as done.
     */
    public Task markDoneInTaskList(int taskIndex) {
        Task task = this.taskList.get(taskIndex);
        task.markAsDone();
        return task;
    }

    /**
     * Prints all the Tasks in the TaskList line by line.
     */
    public void printList() {
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println(i + 1 + ". " + this.taskList.get(i));
        }
    }

    /**
     * Prints the number of tasks currently in the task list.
     */
    public void printNumberOfTasks() {
        System.out.println("\nYou now have " + this.taskList.size()
                + (this.taskList.size() == 1 ? " task " : " tasks ") + "in your list!");
    }

    public void printAllContains(String match) {
        boolean taskFound = false;
        for (int i = 0; i < this.taskList.size(); i++) {
            String description = taskList.get(i).getDescription();
            if (description.contains(match)) {
                taskFound = true;
                System.out.println("Task " + (i + 1) + ". " + this.taskList.get(i));
            }
        }

        if (!taskFound) {
            System.out.println("    -- NO MATCHING TASKS FOUND --");
        }
    }
}
