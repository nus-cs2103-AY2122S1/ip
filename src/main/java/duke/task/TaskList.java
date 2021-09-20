package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Check if the given operation is valid.
     *
     * @param operation Operation input by user
     * @return A boolean - true if the user input operation is valid.
     */
    public static boolean isValidOperation(String operation) {
        String[] operationType = new String[]{"bye", "done", "delete", "list",
            "todo", "deadline", "event", "find", "snooze"};

        for (String str : operationType) {
            if (str.equals(operation)) {
                return true;
            }
        }
        return false;
    }


    public void markDone (int index) {
        this.tasks.get(index).markDone();
    }

    public void delete (int index) {
        this.tasks.remove(index);
    }

    public void add (Task task) {
        this.tasks.add(task);
    }

    public Task get (int index) {
        return this.tasks.get(index);
    }

    /**
     * Return a list of task containing given keyword.
     *
     * @param keyword Keyword input by user
     * @return List of task containing the given keyword.
     */
    public String find(String keyword) {
        StringBuilder result = new StringBuilder();
        final int[] count = {0};

        tasks.stream()
                .filter(task -> task
                        .toString()
                        .contains(keyword))
                .forEach(task -> result
                        .append(++count[0])
                        .append(".")
                        .append(task
                                .toString())
                        .append("\n"));

        if (count[0] == 0) {
            return "";
        }
        return result.toString();
    }

    public int size () {
        return this.tasks.size();
    }

    public void snooze(int index, LocalDate time) throws DukeException {
        Task task = this.get(index);
        if (task instanceof Event) {
            Event event = (Event) task;
            tasks.set(index, event.changeTime(time));
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            tasks.set(index, deadline.changeTime(time));
        } else {
            throw new DukeException("Sorry Todo type class cannot be snoozed");
        }
    }
}
