package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Saves the task list to the local path.
     *
     * @param storage the Storage instance
     * @throws IOException thrown when there is an error saving
     */
    public void save(Storage storage) throws IOException {
        assert (tasks != null) : "Task list is not initialised!";

        storage.write(tasks);
    }

    /**
     * Removes the task from the list.
     *
     * @param index the index starting with 1.
     * @throws IndexOutOfBoundsException thrown when index out of bound.
     * @return string for status.
     */
    public String remove(int index) throws IndexOutOfBoundsException {
        assert (tasks != null) : "Task list is not initialised!";

        Task task = tasks.remove(index);
        return "Noted. I've removed this task:\n  "
                + task
                + "\nNow you have "
                + tasks.size()
                + " tasks in the list.\n";
    }

    /**
     * Remove the task from the list.
     *
     * @return string for status.
     */
    public String clear() {
        assert (tasks != null) : "Task list is not initialised!";

        tasks = new ArrayList<>();
        return "Cleared all tasks.";
    }

    /**
     * Adds task to the list.
     *
     * @param task the task to be added.
     * @return string for status.
     */
    public String add(Task task) {
        assert (tasks != null) : "Task list is not initialised!";

        tasks.add(task);

        return "Got it. I've added this task:\n  "
                + task
                + "\nNow you have "
                + tasks.size()
                + " tasks in the list.\n";
    }

    /**
     * Marks the task as done.
     *
     * @param index the index starting with 1.
     */
    public String setDone(int index) {
        assert (tasks != null) : "Task list is not initialised!";

        Task task = tasks.get(index);
        task.setDone();
        return "Nice! I've marked this task as done:\n" + task + "\n";
    }

    /**
     * Filters out the tasks by the search string and print the result.
     *
     * @param searchString the string contained by the description.
     */
    public String filter(String searchString) {
        assert (tasks != null) : "Task list is not initialised!";

        List<Task> searchResult = tasks
                .stream()
                .filter(str -> str.getDescription().contains(searchString))
                .collect(Collectors.toList());

        StringBuilder output;
        if (searchResult.isEmpty()) {
            output = new StringBuilder("No Result match your search keyword.\n");
        } else {
            output = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 1; i <= searchResult.size(); i++) {
                Task task = searchResult.get(i - 1);
                output.append(i).append(". ").append(task).append("\n");
            }
        }

        return output.toString();
    }

    @Override
    public String toString() {
        assert (tasks != null) : "Task list is not initialised!";

        StringBuilder output;

        if (tasks.isEmpty()) {
            output = new StringBuilder("Hey! You have not added any task...\n");
        } else {
            output = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 1; i <= tasks.size(); i++) {
                Task task = tasks.get(i - 1);
                output.append(i).append(". ").append(task).append("\n");
            }
        }

        return output.toString();
    }
}
