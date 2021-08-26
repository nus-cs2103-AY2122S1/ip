package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Wrapper class around List object, representing a list of tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructor for TaskList object.
     */
    public TaskList() {
        this(new ArrayList<Task>());
    }

    /**
     * Constructor for TaskList object.
     * @param tasks List of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Stream<Task> stream() {
        return this.tasks.stream();
    }

    public Task get(int i) {
        return this.tasks.get(i);
    }

    public Task remove(int i) {
        return this.tasks.remove(i);
    }

    public int size() {
        return this.tasks.size();
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Retrieves a list of tasks whose description contain the search query.
     * @param query Search query.
     * @return List of found tasks based on input search query.
     */
    public TaskList find(String query) {
        return new TaskList(
            this.stream()
                    .filter((task) -> task.getDescription().contains(query))
                    .collect(Collectors.toList())
        );
    }
}
