package duke.task;

import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class TaskList {
    private ArrayList<Task> tasks;
    public static String[] OperationType = new String[]{"bye", "done", "delete", "list",
            "todo", "deadline", "event", "find"};

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
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

    public String find(String keyword) {
        StringBuilder result = new StringBuilder();
        final int[] count = {0};

        tasks.stream().
                filter(task -> task.toString().contains(keyword)).
                forEach(task -> result.append(++count[0]).append(".")
                        .append(task.toString()).append("\n"));

        if (count[0] == 0){
            return "";
        }
        return result.toString();
    }

    public int size () {
        return this.tasks.size();
    }
}
