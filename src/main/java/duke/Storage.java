package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ListIterator;

import duke.exception.StorageException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * The Storage class encapsulates methods related to saving and loading
 * into a text file containing the list of tasks.
 */
public class Storage {

    private static final String DIRECTORY_NAME = "data";
    private static final String FILE_NAME = "taskList.txt";

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the given ArrayList of Tasks according to the directory and file names.
     * Creates a new directory and file if they do not exist.
     *
     * @param lst the given ArrayList of Tasks.
     * @throws StorageException when there is a problem with creating directories or files.
     */
    public void save(TaskList lst) throws StorageException {
        // directory and file names
        Path directory = Paths.get(DIRECTORY_NAME);
        Path taskFile = directory.resolve(FILE_NAME);

        // to create string that's to be saved in the file
        StringBuilder res = new StringBuilder();
        ListIterator<Task> iter = lst.makeIterator();

        try {
            // check if directory exists, if it does not then create it
            if (Files.notExists(directory)) {
                Files.createDirectories(directory);
            }

            // check if file exists, if it does not then create it
            if (Files.notExists(taskFile)) {
                Files.createFile(taskFile);
            }

            // create string of tasks
            while (iter.hasNext()) {
                res.append(iter.next().printToFile());
                if (iter.hasNext()) {
                    res.append("\n");
                }
            }

            // write string of tasks into file
            Files.writeString(taskFile, res.toString());

        } catch (IOException e) {
            throw new StorageException(e.getMessage());
        }
    }

    /**
     * Reads the taskList.txt file and returns an ArrayList of Tasks based on it.
     * Throws a DukeException if the file does not exist.
     *
     * @return An ArrayList containing the tasks in the text file.
     * @throws StorageException when the file does not exist.
     */
    public ArrayList<Task> load() throws StorageException {
        ArrayList<Task> result = new ArrayList<>();

        // directory and file names
        Path taskFile = Paths.get(filePath);

        // check if directory and file exist
        // if not return an empty ArrayList<Task>
        if (Files.notExists(taskFile)) {
            throw new StorageException("text file given does not exist!");
        }

        // parse each line
        try {
            Files.lines(taskFile).forEach(line -> {
                Task newTask = parseInput(line);
                if (newTask != null) {
                        result.add(newTask);
                    }
                }
            );
        } catch (IOException e) {
            throw new StorageException(e.getMessage());
        }

        return result;
    }

    /**
     * Parses a given string and returns a Task based on that string.
     *
     * @param str the given string to parse.
     * @return The Task based on the string. Returns null if the string is invalid.
     */
    private static Task parseInput(String str) {
        String[] strArr = str.split(" \\| ");
        Task newTask;

        // create new task according to input
        switch (strArr[0]) {
        case "T":
            newTask = new ToDo(strArr[2]);
            break;
        case "D":
            newTask = new Deadline(strArr[2], strArr[3]);
            break;
        case "E":
            newTask = new Event(strArr[2], strArr[3]);
            break;
        default:
            newTask = null;
            break;
        }

        return newTask;
    }
}
