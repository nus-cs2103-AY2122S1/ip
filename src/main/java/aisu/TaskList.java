package aisu;

import aisu.task.Deadline;
import aisu.task.Event;
import aisu.task.Task;
import aisu.task.Todo;

import java.util.ArrayList;
import java.util.List;

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

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(List<Task> currList) {
        this.list = currList;
    }

    public List<Task> getListData() {
        return this.list;
    }

    public int getListSize() {
        return this.list.size();
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
        Task newTask = new Todo("dummy");
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
        }
        this.list.add(newTask);
        return newTask;
    }

    public Task deleteTask(int n) throws AisuException {
        checkError(n);
        Task deletedTask = this.list.get(n - 1);
        this.list.remove(n - 1);
        return deletedTask;
    }

    public Task markDone(int n) throws AisuException {
        checkError(n);
        this.list.get(n - 1).markAsDone();
        return this.getTaskAt(n);
    }

    public String findTasksWith(String keyword) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.list.size(); i++) {
            if (list.get(i).toString().contains(keyword)) {
                result.append(i + 1).append(". ").append(list.get(i)).append("\n");
            }
        }
        return result.toString();
    }

    @Override
    public String toString() {
        if (this.list.isEmpty()) {
            return "Oops, the list is empty! :O";
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.list.size(); i++) {
            result.append(i + 1).append(". ").append(list.get(i)).append("\n");
        }
        return result.toString();
    }

}
