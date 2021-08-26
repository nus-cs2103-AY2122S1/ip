package bruh.tasklist;

import bruh.task.LocalDateTimeOrString;
import bruh.task.Task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TaskList implements Serializable {
    private final List<Task> taskList;
    private final boolean isFiltered;

    public TaskList() {
        this.taskList = new ArrayList<>();
        this.isFiltered = false;
    }

    private TaskList(List<Task> taskList) {
        this.taskList = taskList;
        this.isFiltered = true;
    }

    public String listTasks() {
        if (taskList.isEmpty()) {
            return getTaskCountDesc();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the ").append(isFiltered ? "matching " : "").append("tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            sb.append(String.format("\n%d.%s", i + 1, taskList.get(i).toString()));
        }
        return sb.toString();
    }

    public String getTaskCountDesc() {
        return taskList.isEmpty() ? "There are no " + (isFiltered ? "matching " : "") + "tasks."
                : String.format("Now you have %d%s task%s in the list.", taskList.size(), (isFiltered ? " matching" : ""),
                (taskList.size() == 1 ? "" : "s"));
    }

    public void add(Task taskToAdd) {
        taskList.add(taskToAdd);
    }

    public Task remove(int indexToDelete) {
        return taskList.remove(indexToDelete);
    }

    public Task markAsDone(int indexToMarkDone) {
        Task task = taskList.get(indexToMarkDone);
        task.markAsDone();
        return task;
    }

    private TaskList filterByPredicate(Predicate<Task> predicate) {
        List<Task> filtered = taskList.stream().filter(predicate).collect(Collectors.toList());
        return new TaskList(filtered);
    }

    public TaskList filterByDateTimeOrString(LocalDateTimeOrString dateTimeOrString) {
        return filterByPredicate(task -> task.isAtTime(dateTimeOrString));
    }

    public TaskList filterBySearch(String searchTerm) {
        return filterByPredicate(task -> task.descriptionContains(searchTerm));
    }
}
