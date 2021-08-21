package aisu;
import aisu.task.Deadline;
import aisu.task.Event;
import aisu.task.Task;
import aisu.task.Todo;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the Tasklist class.
 * It stores Tasks as a list.
 */
public class Tasklist {
    private final List<Task> list;
    public enum TaskTypes {
        T, D, E
    }

    public Tasklist() {
        this.list = new ArrayList<>();
    }

    public Tasklist(List<Task> currList) {
        this.list = currList;
    }

    public List<Task> getListData() {
        // May not be good OOP practice.
        return this.list;
    }

    public int getListSize() {
        return this.list.size();
    }

    private void checkError(int n) throws AisuException {
        if (n <= 0 || n > this.list.size()) {
            throw new AisuException("That is an invalid task number!");
        }
    }

    private Task getTaskAt(int n) throws AisuException {
        checkError(n);
        return this.list.get(n - 1);
    }

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
