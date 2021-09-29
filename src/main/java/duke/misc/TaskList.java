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
     * @throws IOException In case of invalid file directory.
     */
    public void initialise() throws IOException {
        storage = new Storage();
        tasks = storage.readData();
    }

    /**
     * Returns size of current TaskList.
     *
     * @return size of current TaskList
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Creates a String with all task information appended in rows.
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
     * @throws IOException In case of invalid file directory.
     */
    public String addTask(Task task) throws IOException {
        tasks.add(task);
        saveData();
        return task.toString();
    }

    /**
     * Deletes specified task from current TaskList.
     *
     * @param idx Index of task to be deleted.
     * @return The String output by toString method of task to be added.
     * @throws DukeException In case index of task is out of bounds.
     * @throws IOException In case of invalid file directory.
     */
    public String deleteTask(int idx) throws DukeException, IOException {
        if (idx <= 0 || idx > tasks.size()) {
            throw new InvalidIndexException();
        }
        String message = tasks.get(idx - 1).toString();
        tasks.remove(idx - 1);
        saveData();
        return message;
    }

    /**
     * Marks specified task from current TaskList as done.
     *
     * @param idx Index of task to be marked completed.
     * @return The String output by toString method of task to be added.
     * @throws DukeException In case if index of task is out of bounds.
     * @throws IOException In case of invalid file directory.
     */
    public String completeTask(int idx) throws DukeException, IOException {
        if (idx <= 0 || idx > tasks.size()) {
            throw new InvalidIndexException();
        }
        tasks.get(idx - 1).markAsDone();
        saveData();
        return tasks.get(idx - 1).toString();
    }

    /**
     * Saves all tasks into specified file directory.
     *
     * @throws IOException In case of invalid file directory.
     */
    public void saveData() throws IOException {
        storage.writeData(tasks);
    }

    /**
     * Checks if String b is a subsequence of String a.
     *
     * @param a The 'Container' String.
     * @param b The 'Containee' String.
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
                .filter(task -> isSubSequence(task.getDescription(), key))
                .map(Task::toString)
                .map(description -> "- " + description)
                .collect(Collectors.joining());
    }
}
