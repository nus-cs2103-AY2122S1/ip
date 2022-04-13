package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

public class TaskList {
    private final List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task removeTask(int index) {
        return this.tasks.remove(index);
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Filters tasks by their description.
     * @param searchStr The string to filter by.
     */
    public TaskList filterTasks(String searchStr) {
        String searchInput = searchStr.toLowerCase();
        ArrayList<Task> tasks = this.tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(searchInput))
                .collect(Collectors.toCollection(ArrayList::new));

        return new TaskList(tasks);
    }

    /**
     * Filters tasks to a specific date.
     * @param date The date for which available tasks will be displayed.
     */
    public TaskList filterDate(LocalDate date) {
        ArrayList<Task> tasks = this.tasks.stream()
                .filter(task -> {
                    LocalDateTime time = getTaskDateTime(task);
                    if (time == null) {
                        return false;
                    }

                    return time.toLocalDate().isEqual(date);
                })
                .sorted(Comparator.comparing(this::getTaskDateTime))
                .collect(Collectors.toCollection(ArrayList::new));

        return new TaskList(tasks);
    }

    private LocalDateTime getTaskDateTime(Task task) {
        if (task instanceof Deadline) {
            return ((Deadline) task).getTime();
        }

        if (task instanceof Event) {
            return ((Event) task).getTime();
        }

        return null;
    }

    public int size() {
        return this.tasks.size();
    }

    public List<Task> items() {
        return new ArrayList<>(this.tasks);
    }
}
