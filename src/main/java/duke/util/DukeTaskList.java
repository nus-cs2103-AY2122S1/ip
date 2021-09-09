package duke.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import duke.task.Task;

public class DukeTaskList {
    private final ArrayList<Task> tasklist;

    public DukeTaskList() {
        this.tasklist = new ArrayList<>();
    }

    public DukeTaskList(ArrayList<Task> tasklist) {
        this.tasklist = tasklist;
    }

    /**
     * Adds a Task to the arraylist
     * @param toAdd The task to be added
     * @return The task that was added
     */
    public Optional<Task> addTask(Task toAdd) {
        this.tasklist.add(toAdd);
        return Optional.of(toAdd);
    }

    /**
     * Sets the task of the given index to be completed
     * @param toComplete The index of the task to be completed
     * @return The task that was completed.
     */
    public Optional<Task> setDone(int toComplete) {

        if (toComplete >= this.tasklist.size() || toComplete < 0) {
            return Optional.empty();
        } else {
            Task completed = tasklist.get(toComplete);
            completed.markAsDone();
            return Optional.of(completed);
        }
    }

    /**
     * Removes the task of at the given index
     * @param indexToRemove The index of the task to be removed
     * @return The task that was removed.
     */
    public Optional<Task> toRemove(int indexToRemove) {
        if (indexToRemove >= this.tasklist.size() || indexToRemove < 0) {
            return Optional.empty();
        } else {
            Task toRemove = tasklist.get(indexToRemove);
            tasklist.remove(indexToRemove);
            return Optional.of(toRemove);
        }
    }

    /**
     * Finds the tasks that match a given string.
     * @param query The string to be queried.
     * @return A List of Tasks that matches the query.l
     */
    public List<Task> toFind(String query) {
        return this.tasklist.stream()
                .filter(x -> x.getDescription()
                        .matches(query))
                .collect(Collectors.toList());
    }

    public int getSize() {
        return this.tasklist.size();
    }

    public ArrayList<Task> getList() {
        return this.tasklist;
    }

    /**
     * Prints the entire list with formatting
     * @return String of the list
     */
    public String printList() {

        StringBuilder output = new StringBuilder("Here are the tasks in your list:\n");
        for (Task i : tasklist) {
            output.append(String.format("%d. %s\n",
                    tasklist.indexOf(i) + 1,
                    i));
        }

        output.deleteCharAt(output.length() - 1);
        return output.toString();
    }
}
