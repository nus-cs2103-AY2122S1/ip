package duke;

import java.util.ArrayList;

import duke.task.Task;

public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructor for class TaskList
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for class TaskList
     *
     * @param tasks the current list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the current list of tasks
     *
     * @return current list of tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
