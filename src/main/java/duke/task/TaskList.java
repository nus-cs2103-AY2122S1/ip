package duke.task;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks = new ArrayList<>();

    // Adds a new task to the storage
    public void add(Task task) {
        tasks.add(task);
    }

    // returns number of Tasks stored in the storage
    public int size() {
        return tasks.size();
    }

    // Checks if the storage is empty
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    // Marks a task as done
    public void markDone(int ind) {
        if (ind < 0 || ind >= tasks.size()) {
            throw new IllegalArgumentException(
                    "☹ OOPS!!! Index entered is not valid. " +
                            "Please use 'list' and check for the appropriate index for task(s)."
            );
        }
        tasks.get(ind).markDone();
    }

    // Gets a task from the storage
    public Task get(int ind) {
        if (ind < 0 || ind >= tasks.size()) {
            throw new IllegalArgumentException(
                    "☹ OOPS!!! Index entered is not valid. " +
                            "Please use 'list' and check for the appropriate index for task(s)."
            );
        }
        return tasks.get(ind);
    }

    // Deletes a task from storage
    public void delete(int ind) {
        if (ind < 0 || ind >= tasks.size()) {
            throw new IllegalArgumentException(
                    "☹ OOPS!!! Index entered is not valid. " +
                            "Please use 'list' and check for the appropriate index for task(s)."
            );
        }
        tasks.remove(ind);
    }

    // Lists out all tasks from the storage in order of index
    @Override
    public String toString() {
        String result = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            result += ("\n" + (i + 1) + "." + tasks.get(i).toString());
        }
        return result;
    }

}
