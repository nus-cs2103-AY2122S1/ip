package duke.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import duke.DukeExceptions;
import duke.tasks.Task;

/**
 * Class to handle all about storage. This includes creating, reading, and writing to file.
 */
public class Storage {

    private static final Path DIRECTORY = Paths.get("data");
    private static final Path FILE_PATH = Paths.get("data", "duke.txt");

    private final TaskList tasks = new TaskList();

    /**
     * Tries to create the data directory if it does not exist.
     */
    private static void directoryCreator() {
        try {
            Files.createDirectories(DIRECTORY);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    /**
     * Tries to create the data file if it does not exist.
     */
    private static void fileCreator() {
        try {
            Files.createFile(FILE_PATH);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Opens the file and import the data into the current TaskList.
     */
    public void open() {
        directoryCreator();
        fileCreator();
        List<String> saveFile = null;
        try {
            saveFile = Files.readAllLines(FILE_PATH);
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }

        tasks.importFromList(saveFile);

    }

    /**
     * Writes the current tasklist data into the file.
     */

    public void save() {
        try {
            Files.write(FILE_PATH, tasks.exportToText().getBytes());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    /**
     * Returns string representation of the current task list.
     *
     * @return String of the current list.
     */
    public String getList() {
        return tasks.toString();
    }

    /**
     * Adds the task to the current list.
     *
     * @param task Task to be added to the list.
     */
    public void addToList(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task in the specified index.
     *
     * @param index Index of task to be deleted from the list.
     */
    public void deleteFromList(int index) {
        tasks.remove(index);
    }

    /**
     * Marks specified task as finished.
     *
     * @param index Index of task to be marked as finished from the list.
     * @throws DukeExceptions if the task is already marked as finished.
     */
    public void markAsFinished(int index) throws DukeExceptions {
        tasks.markAsFinished(index);
    }

    /**
     * Returns the task in the specified index.
     *
     * @param index Index of task to be marked pulled.
     * @return Task in the index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the size of the list.
     *
     * @return Size of the TaskList.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Find tasks which has the keyword in the list.
     *
     * @param keyword The keyword to look for.
     * @return String representation of a list of tasks that has the keyword in the description.
     */
    public String find(String keyword) {
        return tasks.find(keyword);
    }
}
