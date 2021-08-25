package task;

import exception.DukeIOException;
import exception.DukeTaskNumberOutOfBoundsException;
import util.DateTimeUtils;
import util.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The is the TaskList class that that
 * contains a list of task.
 */
public class TaskList {
    private static final String DIR_NAME = "data";
    private static final String FILE_NAME = "duke.txt";
    private final List<Task> tasks;

    /**
     * Constructs a TaskList object.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds task to TaskList.
     *
     * @param task Task.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns size of tasks.
     *
     * @return Tasks size.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns task by number.
     *
     * @param number Number of task.
     * @return Task by number if exists, else empty.
     */
    public Task findTaskByNumber(int number) {
        int index = number - 1;
        if (index < 0 || index >= tasks.size()) {
            return null;
        }
        return tasks.get(index);
    }

    /**
     * Completes task by number, start from 1.
     *
     * @param number Number of task.
     * @throws DukeTaskNumberOutOfBoundsException If task number is out of bounds.
     */
    public void completeTask(int number) throws DukeTaskNumberOutOfBoundsException {
        int index = number - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new DukeTaskNumberOutOfBoundsException("☹ OOPS!!! Task number is out of bounds.");
        }
        tasks.set(index, tasks.get(index).markAsDone());
    }

    /**
     * Deletes task by number, start from 1.
     *
     * @param number Number of task.
     * @throws DukeTaskNumberOutOfBoundsException If task number is out of bounds.
     */
    public Task deleteTask(int number) throws DukeTaskNumberOutOfBoundsException {
        int index = number - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new DukeTaskNumberOutOfBoundsException("☹ OOPS!!! Task number is out of bounds.");
        }
        return tasks.remove(index);
    }

    /**
     * Clears all tasks.
     */
    public void clearTasks() {
        tasks.clear();
    }

    /**
     * Prints tasks from TaskList.
     */
    public String[] printTasks() {
        return IntStream.range(0, tasks.size())
            .mapToObj(i -> (i + 1) + ". " + tasks.get(i).toString())
            .collect(Collectors.toList()).toArray(String[]::new);
    }

    /**
     * Loads tasks from file with path: src/data/duke.txt.
     */
    public void loadTasksFromFile() throws DukeIOException {
        List<String> formattedTasks = FileUtils.loadFile(DIR_NAME, FILE_NAME);
        try {
            formattedTasks.forEach(formattedTask -> {
                String[] contents = formattedTask.split(" \\| ", 4);
                String taskID = contents[0];
                String isDone = contents[1];
                String name = contents[2];
                switch (taskID) {
                    case Todo.ID:
                        Todo todo = new Todo(name, isDone.equals("0"));
                        addTask(todo);
                        break;
                    case Deadline.ID:
                        String byTime = contents[3];
                        Deadline deadline = new Deadline(
                            name,
                            DateTimeUtils.parseDateTime(byTime),
                            isDone.equals("0")
                        );
                        addTask(deadline);
                        break;
                    case Event.ID:
                        String atTime = contents[3];
                        String[] dateTimes = atTime.split(" ", 2);
                        String[] times = dateTimes[1].split("-", 2);
                        String atDate = dateTimes[0];
                        String startTime = times[0];
                        String endTime = times[1];
                        EventDateTime eventDateTime = new EventDateTime(
                            DateTimeUtils.parseDate(atDate),
                            DateTimeUtils.parseTime(startTime),
                            DateTimeUtils.parseTime(endTime)
                        );
                        Event event = new Event(name, eventDateTime, isDone.equals("0"));
                        addTask(event);
                        break;
                    default:
                        break;
                }
            });
        } catch (Exception e) {
            throw new DukeIOException("☹ OOPS!!! Load tasks from file error.");
        }
    }

    /**
     * Saves tasks to file with path: src/data/duke.txt.
     */
    public void saveTasksToFile() throws DukeIOException {
        List<String> formattedTasks = tasks.stream()
                .map(task -> String.join(" | ", task.formatTask()))
                .collect(Collectors.toList());
        boolean isSaved = FileUtils.isFileSaved(DIR_NAME, FILE_NAME, formattedTasks);
        if (!isSaved) {
            throw new DukeIOException("☹ OOPS!!! Save tasks to file error.");
        }
    }

    /**
     * Prints tasks which contains keyword from TaskList.
     *
     * @param keyword Keyword of task name.
     */
    public String[] findTasks(String keyword) {
        List<Task> filteredTasks = tasks.stream()
            .filter(task -> task.getName().contains(keyword))
            .collect(Collectors.toList());
        return IntStream.range(0, filteredTasks.size())
            .mapToObj(i -> (i + 1) + ". " + filteredTasks.get(i).toString())
            .collect(Collectors.toList()).toArray(String[]::new);
    }
}
