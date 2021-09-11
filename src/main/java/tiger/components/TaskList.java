package tiger.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import tiger.constants.Priority;
import tiger.exceptions.actions.TigerIndexOutOfBoundsException;
import tiger.exceptions.storage.TigerStorageLoadException;


/**
 * A list to storage the {@code Tasks}.
 */

public class TaskList {

    private final ArrayList<Task> taskList;

    /**
     * Initialises an empty {@code TaskList}.
     */

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Initialises a current taskList from an existing one.
     *
     * @param taskList the current taskList.
     */

    private TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Given a String loaded from storage, interpret it and return its corresponding {@code TaskList}.
     *
     * @param s String loaded from storage.
     * @return the corresponding {@code TaskList} object.
     * @throws TigerStorageLoadException if the loaded string is corrupted, or if there is so {@code IOException}.
     */

    public static TaskList getTaskListFromStringRepresentation(String s) throws TigerStorageLoadException {
        String[] stringArray = s.split("\n");
        ArrayList<Task> newTaskList = new ArrayList<>();
        for (String line : stringArray) {
            if (line.length() != 0) {
                newTaskList.add(Task.getTaskFromStringRepresentation(line));
            }
        }
        return new TaskList(newTaskList);
    }

    /**
     * Given a String loaded from storage, do a partial load and return its corresponding {@code TaskList}.
     *
     * @param s String loaded from storage.
     * @return the corresponding {@code TaskList} object.
     */

    public static TaskList getPartialTaskListFromStringRepresentation(String s) {
        String[] stringArray = s.split("\n");
        ArrayList<Task> newTaskList = new ArrayList<>();
        for (String line : stringArray) {
            if (line.length() != 0) {
                try {
                    newTaskList.add(Task.getTaskFromStringRepresentation(line));
                } catch (TigerStorageLoadException e) {
                    continue;
                }
            }
        }
        return new TaskList(newTaskList);
    }

    /**
     * Adds a task.
     *
     * @param task task to be added.
     * @return a new {@code TaskList}.
     */

    public TaskList addTask(Task task) {
        this.taskList.add(task);
        return new TaskList(this.taskList);
    }

    /**
     * Delete a task.
     *
     * @param index index of task to be deleted (from 1 - length of {@code TaskList}).
     * @return a new {@code TaskList}.
     * @throws TigerIndexOutOfBoundsException if index is out of bounds.
     */

    public TaskList deleteTask(int index) throws TigerIndexOutOfBoundsException {
        try {
            this.taskList.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new TigerIndexOutOfBoundsException(index, this.taskList.size());
        }
        return new TaskList(this.taskList);
    }

    /**
     * Shows the String representation of a single {@code Task}.
     *
     * @param index of {@code Task} to show.
     * @return String representation of the {@code Task}.
     * @throws TigerIndexOutOfBoundsException if index is out of bounds.
     */

    public String showTask(int index) throws TigerIndexOutOfBoundsException {
        try {
            return this.taskList.get(index).toString();
        } catch (IndexOutOfBoundsException e) {
            throw new TigerIndexOutOfBoundsException(index, this.taskList.size());
        }
    }

    /**
     * Returns another {@code TaskList}, with task descriptions matching the search String
     *
     * @param searchString the string to search for.
     * @return a new {@code TaskList}.
     */

    public TaskList findRelevantTasks(String searchString) {
        List<Task> newTasks = this.taskList.stream()
                .filter(
                    t -> t.getTaskDescription()
                            .toLowerCase(Locale.ENGLISH)
                            .contains(searchString.toLowerCase(Locale.ENGLISH))
                )
                .collect(Collectors.toList());

        TaskList taskList = new TaskList(new ArrayList<>(newTasks));
        return taskList;
    }

    /**
     * Returns another {@code TaskList}, with task descriptions matching the search priority
     *
     * @param priority the of tasks to search for.
     * @return a new {@code TaskList}.
     */

    public TaskList findTasksByPriority(Priority priority) {
        List<Task> newTasks = this.taskList.stream()
                .filter(t -> t.getPriority().equals(priority))
                .filter(t -> !t.taskIsDone()) // get tasks that are not done only
                .collect(Collectors.toList());

        TaskList taskList = new TaskList(new ArrayList<>(newTasks));
        return taskList;
    }

    /**
     * Returns the size of the {@code TaskList}.
     *
     * @return the size of the {@code TaskList}.
     */

    public int size() {
        return this.taskList.size();
    }

    /**
     * Marks the {@code Task} at the specified index as done.
     *
     * @param index the specified index.
     * @return a new {@code TaskList}.
     * @throws TigerIndexOutOfBoundsException if index is out of bounds.
     */

    public TaskList markTaskDone(int index) throws TigerIndexOutOfBoundsException {
        // index should be between 0 and n-1
        try {
            this.taskList.set(index, this.taskList.get(index).markDone());
        } catch (IndexOutOfBoundsException e) {
            throw new TigerIndexOutOfBoundsException(index, this.taskList.size());
        } finally {
            return new TaskList(this.taskList);
        }
    }

    /**
     * Returns the String representation of the {@code TaskList}.
     *
     * @return the String representation of the {@code TaskList}.
     */

    @Override
    public String toString() {
        String returnString = "";
        if (this.taskList.size() == 0) {
            return "You currently have no tasks.";
        }
        for (int i = 1; i < this.taskList.size() + 1; i++) {
            String row;
            if (i < 10) {
                // pad with a space
                row = String.format("%d.  %s", i, this.taskList.get(i - 1).toString());
            } else {
                row = String.format("%d. %s", i, this.taskList.get(i - 1).toString());
            }
            if (i != this.taskList.size()) {
                row += "\n";
            }
            returnString += row;
        }
        return returnString;
    }

    /**
     * Returns a String to be written to storage. The {@code Storage} class knows how to parse
     * this string when it is loaded later.
     *
     * @return the String to be written to storage.
     */

    public String getStorageRepresentation() {
        String returnString = "";
        for (Task t : this.taskList) {
            returnString += t.getStorageRepresentation();
            returnString += "\n";
        }
        return returnString;
    }
}
