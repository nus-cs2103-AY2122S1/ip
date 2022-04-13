package duke;

import java.util.ArrayList;

/**
 * Encapsulates a list of tasks, and supports operations on the list such as add, delete and mark-as-done.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            output += String.valueOf(i + 1) + ". " + tasks.get(i) + "\n";
        }
        return output;
    }

    /**
     * Converts the list of tasks into a string suitable for saving to a text file.
     *
     * @return A string suitable for saving to a text file.
     */
    public String toStringForFile() {
        String output = "";
        for (Task t : tasks) {
            output += t.toStringForFile() + System.lineSeparator();
        }
        return output;
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to mark as done.
     * @return The task that is marked as done.
     */
    public Task markAsDone(int index) {
        Task t = tasks.get(index - 1);
        t.markAsDone();
        return t;
    }

    /**
     * Returns the current size of the list of tasks.
     *
     * @return The current size of the TaskList.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Adds a task into the TaskList.
     *
     * @param t The task to add into the TaskList.
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param index The index of the task to delete.
     * @return The task that was deleted.
     */
    public Task delete(int index) {
        Task t = tasks.get(index - 1);
        tasks.remove(t);
        return t;
    }

    /**
     * Searches for the tasks that matches a given query.
     *
     * @param query The keyword to search for.
     * @return An ArrayList of tasks that contains the given query.
     */
    public ArrayList<Task> find(String query) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task: tasks) {
            if (task.toString().contains(query)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Gets a task from the list.
     *
     * @param index The task to be retrieved from the list.
     * @return The task with the index in the list.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Tags a task in the list.
     *
     * @param index The index of the task to tag.
     * @param description The description of the tag.
     * @return The tagged task.
     */
    public Task tag(int index, String description) {
        Task t = tasks.get(index - 1);
        t.setTag(description);
        return t;
    }
}
