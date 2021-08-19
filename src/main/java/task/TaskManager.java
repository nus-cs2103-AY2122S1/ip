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
 * The is the TaskManager class that that
 * contains a list of task.
 *
 * @author  HU JIAJUN
 * @version %I%, %G%
 * @since   1.0
 */

public class TaskManager {
    private static final String DIR_NAME = "data";
    private static final String FILE_NAME = "duke.txt";
    private final List<Task> tasks;

    /**
     * This is constructor method of TaskManager.
     */
    public TaskManager() {
        tasks = new ArrayList<>();
    }

    /**
     * Add task to TaskManager.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Get size of tasks.
     *
     * @return tasks size
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Find task by number.
     *
     * @param number number of task
     * @return       task by number if exists, else empty
     */
    public Task findTaskByNumber(int number) {
        int index = number - 1;
        if (index < 0 || index >= tasks.size()) {
            return null;
        }
        return tasks.get(index);
    }

    /**
     * Complete task by number, start from 1.
     *
     * @param number number of task
     * @throws DukeTaskNumberOutOfBoundsException if task number is out of bounds
     */
    public void completeTask(int number) throws DukeTaskNumberOutOfBoundsException {
        int index = number - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new DukeTaskNumberOutOfBoundsException("☹ OOPS!!! Task number is out of bounds.");
        }
        tasks.set(index, tasks.get(index).markAsDone());
    }

    /**
     * Delete task by number, start from 1.
     *
     * @param number number of task
     * @throws DukeTaskNumberOutOfBoundsException if task number is out of bounds
     */
    public Task deleteTask(int number) throws DukeTaskNumberOutOfBoundsException {
        int index = number - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new DukeTaskNumberOutOfBoundsException("☹ OOPS!!! Task number is out of bounds.");
        }
        return tasks.remove(index);
    }

    /**
     * Clear all tasks.
     */
    public void clearTasks() {
        tasks.clear();
    }

    /**
     * Print tasks from TaskManager with format:
     *      1. Task1
     *      2. Task2
     *      ...
     */
    public String[] printTasks() {
        return IntStream.range(0, tasks.size())
            .mapToObj(i -> (i + 1) + ". " + tasks.get(i).toString())
            .collect(Collectors.toList()).toArray(String[]::new);
    }

    /**
     * Load tasks from file with path: src/data/duke.txt.
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
     * Save tasks to file with path: src/data/duke.txt.
     */
    public void saveTasksToFile() throws DukeIOException {
        List<String> formattedTasks = tasks.stream()
                .map(task -> String.join(" | ", task.formatTask()))
                .collect(Collectors.toList());
        boolean saved = FileUtils.saveFile(DIR_NAME, FILE_NAME, formattedTasks);
        if (!saved) {
            throw new DukeIOException("☹ OOPS!!! Save tasks to file error.");
        }
    }

    /**
     * Print tasks which contains keyword from TaskManager with format:
     *      1. Task1
     *      2. Task2
     *      ...
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
