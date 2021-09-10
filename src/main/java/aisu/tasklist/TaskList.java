package aisu.tasklist;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import aisu.exception.AisuException;
import aisu.task.Deadline;
import aisu.task.Event;
import aisu.task.Task;
import aisu.task.Todo;


/**
 * A TaskList class that stores Tasks as a list.
 *
 * @author Liaw Xin Yan
 */
public class TaskList {
    private final List<Task> list;
    public enum TaskTypes {
        T, D, E
    }

    /**
     * Constructor to initialise TaskList.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Constructor to initialise TaskList.
     * @param currList current task list data to be used.
     */
    public TaskList(List<Task> currList) {
        this.list = currList;
    }

    /**
     * Retrieves the list data.
     * @return The list object.
     */
    public List<Task> getListData() {
        return this.list;
    }

    /**
     * Retrieves the list size.
     * @return Size of list.
     */
    public int getListSize() {
        return this.list.size();
    }

    /**
     * Checks if tasklist is empty.
     * @return True if tasklist is empty.
     */
    public boolean isEmpty() {
        return this.getListSize() == 0;
    }

    /**
     * Checks if a number is within range of the tasklist size.
     *
     * @param n Integer to be checked.
     * @throws AisuException If out of range.
     */
    private void checkError(int n) throws AisuException {
        if (n <= 0 || n > this.list.size()) {
            throw new AisuException("That is an invalid task number!");
        }
    }

    /**
     * Retrieves a task specified in the list.
     * @param n The index of the task.
     * @return The task.
     * @throws AisuException if index is out of bounds.
     */
    private Task getTaskAt(int n) throws AisuException {
        checkError(n);
        return this.list.get(n - 1);
    }

    /**
     * Adds a given task to the tasklist.
     *
     * @param line Information of the task to be added.
     * @param type Type of task.
     * @return The task that is added.
     * @throws AisuException If task formatting is invalid.
     */
    public Task addTask(String line, TaskTypes type) throws AisuException { // adds new task to taskList
        Task newTask;
        switch(type) {
        case T:
            newTask = new Todo(line);
            break;
        case D:
            try {
                String[] temp = line.split(" /by ");
                newTask = new Deadline(temp[0], temp[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new AisuException("Your formatting is wrong! Write as: deadline (task) /by (yyyy-mm-dd)");
            }
            break;
        case E:
            try {
                String[] temp = line.split(" /at ");
                newTask = new Event(temp[0], temp[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new AisuException("Your formatting is wrong! Write as: event (task) /at (date range)");
            }
            break;
        default:
            throw new AisuException("Invalid input!");
        }
        this.list.add(newTask);
        return newTask;
    }

    /**
     * Deletes a specific task indicated by the index.
     * @param n Index of the task to be deleted.
     * @return The deleted task.
     * @throws AisuException if index is out of range.
     */
    public Task deleteTask(int n) throws AisuException {
        checkError(n);
        Task deletedTask = this.list.get(n - 1);
        this.list.remove(n - 1);
        return deletedTask;
    }

    /**
     * Marks task as completed.
     * @param n Index of the task to be marked as completed.
     * @return The completed task.
     * @throws AisuException if index is out of range.
     */
    public Task markDone(int n) throws AisuException {
        checkError(n);
        this.list.get(n - 1).markAsDone();
        return this.getTaskAt(n);
    }

    /**
     * Adds a specified tag to a specified task.
     * @param n Index of the task to add a tag for.
     * @param tag The tag name.
     * @return The tagged task.
     * @throws AisuException if index is out of range.
     */
    public Task tagTask(int n, String tag) throws AisuException {
        checkError(n);
        this.list.get(n - 1).addTag(tag);
        return this.getTaskAt(n);
    }

    /**
     * Finds tasks with a certain keyword in the tasklist.
     * @param keyword Keyword used for searching.
     * @return Tasklist with tasks containing that keyword.
     */
    public List<Task> findTasksWith(String keyword) {
        // @@author {chrisgzf}-reused
        return list.stream().filter(task -> task.toString().contains(keyword)).collect(Collectors.toList());
    }

    /**
     * Returns the string formatting of the task list.
     * @return The string version of the list.
     */
    @Override
    public String toString() {
        if (!this.list.isEmpty()) {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < this.list.size(); i++) {
                result.append(i + 1).append(". ").append(list.get(i)).append("\n");
            }
            return result.toString();
        }
        return "Oops, the list is empty! :O";
    }

}
