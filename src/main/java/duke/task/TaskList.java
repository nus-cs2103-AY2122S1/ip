package duke.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

import duke.io.Ui;

/**
 * Encapsulates a list of tasks and support manipulation of the tasks.
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * Constructor for TaskList class. Initialises taskList to empty array.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Constructor for TaskList class. Initialises taskList to the parameter.
     *
     * @param taskList the list of tasks encapsulated by TaskList class.
     */
    public TaskList(ArrayList<Task> taskList) {
        assert taskList != null : "Task list cannot be null";
        this.taskList = taskList;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the number of tasks in the list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns the task at index i.
     *
     * @param i the index of the task.
     * @return the task at index i.
     */
    public Task get(int i) {
        return taskList.get(i);
    }

    /**
     * Removes the task at index i.
     *
     * @param i the index of the task.
     */
    public void remove(int i) {
        taskList.remove(i);
    }

    /**
     * Adds task to the end of the list.
     *
     * @param task the task to be added into the list.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Returns the string representation of the list of task.
     *
     * @return String that represents the list of task.
     */
    public String toString() {
        return taskList
                .stream()
                .map(Task::toDataFormat)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    /**
     * Prints out the task list formatted and indented.
     *
     * @return String representation of the list of tasks.
     */
    public String print() {
        return print("");
    }

    /**
     * Prints out the task list formatted and indented with additional message.
     *
     * @param message to be added.
     * @return String representation of the list of tasks.
     */
    public String print(String message) {
        if (taskList.size() == 0) {
            return Ui.print("There are no items in your list now!");
        }
        StringBuilder tasksString = new StringBuilder();
        if (!message.isEmpty()) {
            tasksString.append(message).append(System.lineSeparator());
        }
        for (int i = 0; i < taskList.size(); i++) {
            String taskAsString = i == taskList.size() - 1
                    ? String.format("%d.%s", i + 1, taskList.get(i))
                    : String.format("%d.%s\n", i + 1, taskList.get(i));
            tasksString.append(taskAsString);
        }
        return Ui.print(tasksString.toString());
    }

    /**
     * Filters the tasks by a given keyword and checks if task contains keyword.
     *
     * @param keyword the keyword to filter the tasks by.
     * @return the filtered tasks.
     */
    public TaskList filter(String keyword) {
        assert keyword != null : "Cannot filter a keyword that is null";
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.toString().contains(keyword)) {
                tasks.add(task);
            }
        }
        return new TaskList(tasks);
    }

    /**
     * Sorts the task list by alphabetical order of the description.
     *
     * @return String representation of the sorted task list.
     */
    public String sort() {
        Collections.sort(taskList);
        return print();
    }
}
