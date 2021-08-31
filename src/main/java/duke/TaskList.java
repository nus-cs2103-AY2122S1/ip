package duke;

import java.util.ArrayList;

import duke.tasks.Task;

/**
 * Class that handles an ArrayList of Tasks.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    private enum Format {
        LIST,
        SAVE
    }

    /**
     * TaskList constructor for empty tasks list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * TaskList constructor for a given tasks list.
     *
     * @param taskList list of tasks
     */
    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    /**
     * Returns a String to display for the list command.
     *
     * @return String for display
     */
    public String stringifyTasksForList() {
        return this.stringify(Format.LIST);
    }

    /**
     * Returns a String to save.
     *
     * @return String for saving
     */
    public String stringifyTasksForSave() {
        return this.stringify(Format.SAVE);
    }

    private String stringify(Format format) {
        if (this.tasks.size() == 0) {
            return "No tasks added yet!";
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task t = this.tasks.get(i);
            res.append(i + 1).append(". ").append(
                    format == Format.LIST ? t.toString() : t.toSaveString()
            ).append("\n");
        }
        return res.substring(0, res.length() - 1);
    }

    /**
     * Returns a String for Find command.
     *
     * @return String for Find
     */
    public String stringifyTasksForFind(String keyword) {
        if (this.tasks.size() == 0) {
            return "No tasks added yet!";
        }

        boolean found = false;
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task t = this.tasks.get(i);
            if (t.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                found = true;
                res.append(i + 1).append(". ").append(t.toString()).append("\n");
            }
        }

        if (!found) {
            return "No tasks match that keyword :(";
        } else {
            return res.substring(0, res.length() - 1);
        }
    }

    /**
     * Returns size of task list.
     *
     * @return size as an int
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Returns Task at given index.
     *
     * @param x int index
     * @return Task at index x
     */
    public Task get(int x) {
        return this.tasks.get(x);
    }

    /**
     * Removes Task at given index.
     *
     * @param x int index
     * @return Removed task
     */
    public Task remove(int x) {
        return this.tasks.remove(x);
    }

    /**
     * Adds Task to list.
     *
     * @param t Task to add
     * @return boolean indicating if the operation was a success
     */
    public boolean add(Task t) {
        return this.tasks.add(t);
    }
}
