package duke;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Contains the task list.
 * Has operations to add/delete tasks in the list.
 */
public class TaskList {
    private final List<Task> tasks;
    private final Parser parser = new Parser();

    /**
     * Constructor for a task list.
     *
     * @param tasks A list of tasks.
     */
    public TaskList(List<Task> tasks) {
        assert (tasks != null);
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks in this list.
     *
     * @return The number of tasks in this list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task at the specified position in this list.
     *
     * @param idx Index of the task to return.
     * @return Returns the task at the specified position in this list.
     */
    public Task get(int idx) {
        return this.tasks.get(idx);
    }

    /**
     * Appends the specified task to the end of this list
     *
     * @param task Task to be appended to this list
     */
    public void addTask(Task task) {
        assert (task != null);
        this.tasks.add(task);
    }

    /**
     * Removes the first occurrence of the specified task from this list, if it is present.
     *
     * @param toBeDeleted Task to be removed from this list, if present.
     */
    public void deleteTask(Task toBeDeleted) {
        assert (toBeDeleted != null);
        tasks.remove(toBeDeleted);
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param taskIdx The index of the task to be marked as done.
     */
    public void markTaskAsDone(int taskIdx) {
        Task task = this.tasks.get(taskIdx);
        task.markAsDone();
    }

    /**
     * Creates a ToDo task.
     *
     * @param task The task description of the ToDo.
     * @return A ToDO object.
     */
    public ToDo createTodo(String task) {
        assert (task != null);
        return new ToDo(task, false);
    }

    /**
     * Creates a deadline.
     *
     * @param task The task description of the deadline.
     * @return A DeadLine object.
     * @throws UnrecognizableCommandException If the command is invalid.
     * @throws InvalidDateTimeException       If the date is invalid.
     */
    public DeadLine createDeadLine(String task) throws UnrecognizableCommandException, InvalidDateTimeException {
        assert (task != null);
        if (!task.contains(" /by ")) {
            throw new UnrecognizableCommandException();
        }
        try {
            String[] lst = task.split(" /by ");
            String description = lst[0];
            String ddl = lst[1];
            LocalDate date = parser.stringToLocalDate(ddl);
            return new DeadLine(description, date, false);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new UnrecognizableCommandException();
        }

    }

    /**
     * Creates a event
     *
     * @param task The task description of the event.
     * @return An event object.
     * @throws UnrecognizableCommandException If the command is invalid.
     * @throws InvalidDateTimeException       If the date is invalid.
     */
    public Event createEvent(String task) throws UnrecognizableCommandException, InvalidDateTimeException {
        assert (task != null);
        if (!task.contains(" /at ")) {
            throw new UnrecognizableCommandException();
        }
        try {
            String[] lst = task.split(" /at ");
            String description = lst[0];
            String period = lst[1];
            LocalDate date = parser.stringToLocalDate(period);
            return new Event(description, date, false);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new UnrecognizableCommandException();
        }
    }

    /**
     * Finds a list of matching tasks given some keywords.
     *
     * @param keywords Keywords to be found among all tasks.
     * @return A list of matched tasks.
     */
    public List<Task> findMatchedTasks(String... keywords) {
        Stream<Task> matchedTasksStream = Stream.empty();
        for (String keyword : keywords) {
            if (keyword.isEmpty()) {
                continue;
            }
            matchedTasksStream = this.tasks.stream()
                    .filter((task) -> task.getDescription().contains(keyword));
        }
        return matchedTasksStream.collect(Collectors.toList());
    }
}
