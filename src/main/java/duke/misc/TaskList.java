package duke.misc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.exception.DukeException;
import duke.exception.InvalidIndexException;
import duke.task.Task;

/**
 * TaskList class which encapsulates all tasks, as well as handle information and
 * operations relating to the tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;

    /**
     * Constructor for TaskList class.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Initialises the TaskList using the stored data.
     *
     * @throws IOException Throws IOException if directory is Invalid.
     */
    public void initialise() throws IOException {
        storage = new Storage();
        tasks = storage.readData();
    }

    /**
     * Creates a string with all task information appended in rows.
     *
     * @return String of all task information.
     */
    public String displayList() {
        return IntStream.range(0, tasks.size())
                .mapToObj(idx -> String.format("%d. %s", idx + 1, tasks.get(idx).toString()))
                .collect(Collectors.joining());
    }

    /**
     * Adds a task to current TaskList.
     *
     * @param task The Task object to be added.
     * @return The String output by toString method of task.
     */
    public String addTask(Task task) {
        tasks.add(task);
        return task.toString();
    }

    /**
     * Deletes specified task from current TaskList.
     *
     * @param idx Index of task to be deleted.
     * @return The String output by toString method of task to be added.
     * @throws DukeException Throws DukeException if index of task is out of bounds.
     */
    public String deleteTask(int idx) throws DukeException {
        if (idx <= 0 || idx > tasks.size()) {
            throw new InvalidIndexException();
        }
        String message = tasks.get(idx - 1).toString();
        tasks.remove(idx - 1);
        return message;
    }

    /**
     * Marks specified task from current TaskList as done.
     *
     * @param idx Index of task to be completed.
     * @return The String output by toString method of task to be added.
     * @throws DukeException Throws DukeException if index of task is out of bounds.
     */
    public String completeTask(int idx) throws DukeException {
        if (idx <= 0 || idx > tasks.size()) {
            throw new InvalidIndexException();
        }
        tasks.get(idx - 1).markAsDone();
        return tasks.get(idx - 1).toString();
    }

    /**
     * Saves all tasks into specified directory.
     *
     * @throws IOException Throws IOException if directory is invalid.
     */
    public void saveData() throws IOException {
        storage.writeData(tasks);
    }

    /**
     * Checks if String b is a subsequence of String a.
     *
     * @param a 'Container' String.
     * @param b 'Containee' String.
     * @return The boolean value of the subsequence query.
     */
    public boolean isSubSequence(String a, String b) {
        assert !a.isEmpty();

        // Convert all characters to lower case and remove all whitespace characters.
        a = a.toLowerCase().replaceAll("\\s+", "");
        b = b.toLowerCase().replaceAll("\\s+", "");

        int i = 0;
        int j = 0;
        while (i < a.length() && j < b.length()) {
            if (a.charAt(i) == b.charAt(j)) {
                j++;
            }
            i++;
        }
        return j == b.length();
    }

    /**
     * Finds all tasks with specified keyword.
     *
     * @param key Abbreviation or specific keyword.
     * @return String of all tasks found appended in rows.
     */
    public String findTasks(String key) {
        return tasks.stream()
                .map(Task::getDescription)
                .filter(description -> isSubSequence(description, key))
                .collect(Collectors.joining("\n"));
    }
}
