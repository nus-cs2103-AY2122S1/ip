package duke.data;

import java.util.ArrayList;

import duke.data.exception.DukeException;
import duke.data.tasks.Task;

/**
 * Represents the list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> lst;

    public TaskList() {
        this(new ArrayList<>());
    }

    public TaskList(ArrayList<Task> loadedTasks) {
        this.lst = loadedTasks;
    }

    /**
     * Adds a Task to the list.
     * @param t The task to be added
     * @return Notification of the task that was added
     */
    public String addToList(Task t) {
        this.lst.add(t);
        return String.format("Got it. I've added this task:\n    %s\n"
                + "You now have %d tasks in the list.", t, this.lst.size());
    }

    /**
     * Deletes the specified task.
     * @param n The position of the task to be deleted
     * @return Notification of the task that was deleted
     */
    public String deleteTask(int n) {
        if (n < 1 || n > this.lst.size()) {
            throw new DukeException("There is no task " + n);
        } else {
            Task removed = this.lst.remove(n - 1);
            return String.format("Noted. I've removed this task:\n    %s\n"
                    + "Now you have %d tasks in the list.", removed.toString(), this.lst.size());
        }
    }

    public boolean isEmpty() {
        return this.lst.isEmpty();
    }

    public int size() {
        return this.lst.size();
    }

    public Task get(int n) {
        return this.lst.get(n);
    }

    /**
     * Formats the data to be saved to file.
     * @return
     */
    public String getSaveData() {
        StringBuilder output = new StringBuilder();
        for (Task task : this.lst) {
            output.append(task.getSaveData()).append("~");
        }
        return output.toString();
    }

    /**
     * Marks the specified task as done.
     * @param n The index of the task that is to be marked as done
     * @return Notification of the task that was marked as done
     */
    public String markAsDone(int n) {
        if (n < 1 || n > this.lst.size()) {
            throw new DukeException("There is no task " + n);
        } else {
            return "Nice! I've marked this task as done:\n"
                    + this.lst.get(n - 1).completeTask();
        }
    }
}
