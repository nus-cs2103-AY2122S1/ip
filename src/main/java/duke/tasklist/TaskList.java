package duke.tasklist;

import duke.task.LocalDateTimeOrString;
import duke.task.Task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList implements Serializable {
    private final List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public String listTasks() {
        if (tasks.isEmpty()) {
            return getTaskCountDesc();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("\n%d.%s", i + 1, tasks.get(i).toString()));
        }
        return sb.toString();
    }

    public String getTaskCountDesc() {
        return tasks.isEmpty() ? "There are no tasks."
                : String.format("Now you have %d task%s in the list.", tasks.size(),
                (tasks.size() == 1 ? "" : "s"));
    }

    public void add(Task taskToAdd) {
        tasks.add(taskToAdd);
    }

    public Task remove(int indexToDelete) {
        return tasks.remove(indexToDelete);
    }

    public Task markAsDone(int indexToMarkDone) {
        Task task = tasks.get(indexToMarkDone);
        task.markAsDone();
        return task;
    }

    public TaskList filterByDateTimeOrString(LocalDateTimeOrString dateTimeOrString) {
        List<Task> filtered = tasks.stream().filter(x -> x.isAtTime(dateTimeOrString)).collect(Collectors.toList());
        return new TaskList(filtered);
    }
}
