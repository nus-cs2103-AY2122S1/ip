package task;

import exception.DukeIOException;
import exception.DukeTaskNumberOutOfBoundsException;
import utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The is the TaskManager class that that
 * contains a list of task.
 *
 * @author  HU JIAJUN
 * @version %I%, %G%
 * @since   1.0
 */

public class TaskManager {
    private static final String INDENTATION = "     ";
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
            throw new DukeTaskNumberOutOfBoundsException(INDENTATION + "☹ OOPS!!! Task number is out of bounds.");
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
            throw new DukeTaskNumberOutOfBoundsException(INDENTATION + "☹ OOPS!!! Task number is out of bounds.");
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
    public void printTasks() {
        for(int i = 0; i < tasks.size(); i++) {
            String task = INDENTATION + (i + 1) + ". " + tasks.get(i).toString();
            System.out.println(task);
        }
    }

    /**
     * Load tasks from file with path: src/data/duke.txt.
     *
     */
    public void loadTasksFromFile() throws DukeIOException {
        List<String> formattedTasks = FileUtils.loadFile(DIR_NAME, FILE_NAME);
        try {
            for (String formattedTask : formattedTasks) {
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
                        Deadline deadline = new Deadline(name, byTime, isDone.equals("0"));
                        addTask(deadline);
                        break;
                    case Event.ID:
                        String atTime = contents[3];
                        Event event = new Event(name, atTime, isDone.equals("0"));
                        addTask(event);
                        break;
                }
            }
        } catch (Exception e) {
            throw new DukeIOException(INDENTATION + "☹ OOPS!!! Load tasks from file error.");
        }
    }

    /**
     * Save tasks to file with path: src/data/duke.txt.
     *
     * @return whether tasks are saved to file successfully
     */
    public boolean saveTasksToFile() throws DukeIOException {
        List<String> formattedTasks = tasks.stream()
                .map(task -> String.join(" | ", task.formatTask()))
                .collect(Collectors.toList());
        boolean saved = FileUtils.saveFile(DIR_NAME, FILE_NAME, formattedTasks);
        if (!saved) {
            throw new DukeIOException(INDENTATION + "☹ OOPS!!! Save tasks to file error.");
        }
        return true;
    }
}
