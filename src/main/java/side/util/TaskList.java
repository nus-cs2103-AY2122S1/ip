package side.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import side.tasks.Deadline;
import side.tasks.Event;
import side.tasks.Task;

/**
 * Provides a wrapper around an ArrayList of Tasks to encapsulate the list of tasks given to
 * Side by its user. Supports operations on tasks in it.
 *
 * @author Lua Yi Da
 */

public class TaskList {

    private static final String FILEPATH = "./data";
    private ArrayList<Task> tasks;
    private int taskLabel;
    private Storage storage;

    /**
     * Initialises a new TaskList instance with storage and Task arraylist.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.taskLabel = 0;
        this.storage = new Storage();
    }

    /**
     * Helper method to retrieve saved tasks from previous session.
     *
     * @return String of retrieved tasks.
     */
    public String retrieve() {
        try {
            this.tasks = this.storage.retrieve(FILEPATH);
            this.taskLabel = this.tasks.size();
            return "Here's your history...\n" + this.listToString();
        } catch (FileNotFoundException e) {
            return "You have no history...";
        }
    }

    /**
     * Helper method to save tasks for future use. Prints message if IOException occurs.
     */
    public void save() {
        try {
            this.storage.save(this.tasks, FILEPATH);
        } catch (IOException e) {
            System.out.println("Ran into problem reading file...");
        }
    }

    /**
     * Calculates the number of tasks in the TaskList.
     *
     * @return Number of tasks in TaskList.
     */
    public int length() {
        return this.tasks.size();
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param description String representation of task information.
     */
    public void addTask(String description) {
        Task task = new Task(description);
        this.tasks.add(task);
        taskLabel++;
        this.save();
    }

    /**
     * Adds an event to the TaskList.
     *
     * @param description String representation of event information.
     * @param startDatetime Time at which the event starts.
     * @param endDatetime Time at which event ends.
     */
    public void addEvent(String description, String startDatetime, String endDatetime) {
        Event event = new Event(description, startDatetime, endDatetime);
        this.tasks.add(event);
        taskLabel++;
        this.save();
    }

    /**
     * Adds a deadline to the TaskList.
     *
     * @param description String representation of deadline information.
     * @param time Time at which the deadline elapses.
     */
    public void addDeadline(String description, String time) {
        Deadline event = new Deadline(description, time);
        this.tasks.add(event);
        taskLabel++;
        this.save();
    }

    /**
     * Marks a task as done.
     *
     * @param index Index of task to be marked as done.
     * @return String response of Side in response to marking task as done.
     */
    public String markTaskDone(int index) {
        String response = tasks.get(index).markAsDone();
        this.save();
        return response;
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param index Index of task to be removed.
     * @return String response of Side in response to deleting task.
     */
    public String delete(int index) {
        Task taskToDelete = this.tasks.get(index);
        this.tasks.remove(index);
        taskLabel--;
        String taskQuantifier = this.tasks.size() == 1 ? "task..." : "tasks...";
        String taskCount = "\nYou now have " + tasks.size() + " " + taskQuantifier;
        this.save();
        return "Fine, I'll delete: " + taskToDelete.toString() + taskCount;
    }

    /**
     * Converts list to a string for use.
     *
     * @return String representing list.
     */
    public String listToString() {
        StringBuilder tasksList = new StringBuilder();

        if (this.taskLabel == 0) {
            return "Nothing to see here...";
        }

        for (int i = 0; i < this.taskLabel; i++) {
            String fullTaskString = (i + 1) + ". " + this.tasks.get(i).toString() + "\n";
            tasksList.append(fullTaskString);
        }

        return tasksList.toString();
    }

    /**
     * Helper method to return existing tasks that contain {@code input} as substring.
     *
     * @param input String representing user input.
     * @return ArrayList of tasks matching given description.
     */
    public ArrayList<Task> findTasks(String input) {
        ArrayList<Task> matchedTaskList = new ArrayList<>();

        for (Task t : this.tasks) {
            // Checks if input is a substring of task description
            if (t.getDescription().contains(input)) {
                matchedTaskList.add(t);
            }
        }

        return matchedTaskList;
    }

    /**
     * Overrides toString to format tasklist into string.
     *
     * @return String representing list.
     */
    @Override
    public String toString() {
        if (this.taskLabel == 0) {
            return "No tasks yet, stop checking...";
        }

        return "Fine, here are your tasks: \n" + this.listToString();
    }
}
