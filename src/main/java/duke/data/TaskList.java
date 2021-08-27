package duke.data;

import java.util.ArrayList;

import duke.data.exception.DukeException;
import duke.data.tasks.Task;

public class TaskList {
    private final ArrayList<Task> lst;

    public TaskList() {
        this(new ArrayList<>());
    }

    public TaskList(ArrayList<Task> loadedTasks) {
        this.lst = loadedTasks;
    }


    public String addToList(Task t) {
        this.lst.add(t);
        return String.format("Got it. I've added this task:\n    %s\n" +
                "You now have %d tasks in the list.", t, this.lst.size());
    }

    public String deleteTask(int n) {
        if (n < 1 || n > this.lst.size()) {
            throw new DukeException("There is no task " + n);
        } else {
            Task removed = this.lst.remove(n - 1);
            return String.format("Noted. I've removed this task:\n    %s\n" +
                    "Now you have %d tasks in the list.", removed.toString(), this.lst.size());
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

    public String getSaveData() {
        StringBuilder output = new StringBuilder();
        for (Task task : this.lst) {
            output.append(task.getSaveData()).append("~");
        }
        return output.toString();
    }

    public String markAsDone(int n) {
        if (n < 1 || n > this.lst.size()) {
            throw new DukeException("There is no task " + n);
        } else {
            return "Nice! I've marked this task as done:\n" +
                    this.lst.get(n - 1).completeTask();
        }
    }
}
