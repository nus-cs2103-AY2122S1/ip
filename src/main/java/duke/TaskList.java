package duke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import duke.tasks.Event;
import duke.tasks.Task;

/**
 * Wrapper class around List object, representing a list of tasks.
 */
public class TaskList {
    private final List<Task> tasks;

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

    /**
     * Retrieves an event which occurs on the input date.
     * @param date Input date.
     * @return An Optional container which holds the event occurring on the input date.
     * If no such event can be found, Empty is returned.
     */
    public Optional<Event> findEvent(LocalDate date) {
        return this.stream()
                .filter((task) -> TaskType.identifyTask(task) == TaskType.EVENT)
                .map((task) -> (Event) task)
                .filter((event) -> event.getTime().equals(date))
                .findAny();
    }
}
