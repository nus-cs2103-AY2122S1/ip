package duke.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import duke.parser.UnableToParseException;
import duke.parser.storage.StorageParser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * This class manages the persistence layer of the Duke app.
 */
public class Storage {
    /**
     * Default file path where the storage is saved.
     */
    public static final String DEFAULT_FILE_PATH = "./data/duke.txt";
    /**
     * Delimiter for the saved format in storage.
     */
    public static final String DELIMITER = " | ";
    private final Path path;

    /**
     * Constructor of the Storage class.
     */
    public Storage() {
        this(DEFAULT_FILE_PATH);
    }

    /**
     * Constructor of the Storage class.
     *
     * @param filePath The specified file path of the txt file.
     */
    public Storage(String filePath) {
        assert !filePath.isEmpty() : "file path should not be empty";
        this.path = Paths.get(filePath);
    }

    /**
     * Loads the txt file into a TaskList.
     *
     * @return The resultant task list.
     * @throws UnableToParseException Thrown if unable to parse the txt file.
     * @throws IOException Thrown if there was an unexpected IO error.
     */
    public TaskList load() throws UnableToParseException, IOException {
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent()); // create /data/ directory if it does not exist
            Files.createFile(path);
            assert Files.exists(path) : "file should be created at the specified directory";
            return new TaskList();
        }
        List<String> lines = Files.readAllLines(this.path);
        ArrayList<Task> tasks = new ArrayList<>();
        for (String line : lines) {
            String[] split = line.split(Pattern.quote(Storage.DELIMITER));
            switch (split[0]) {
            case "T":
                Todo todo = StorageParser.parseTodo(split);
                tasks.add(todo);
                break;
            case "D":
                Deadline deadline = StorageParser.parseDeadline(split);
                tasks.add(deadline);
                break;
            case "E":
                Event event = StorageParser.parseEvent(split);
                tasks.add(event);
                break;
            default:
                throw new UnableToParseException("\"" + line + "\" at " + this.path);
            }
        }
        return new TaskList(tasks);
    }

    /**
     * Updates the corresponding task's data.
     * Solution adapted from https://stackoverflow.com/a/37624091 with modifications.
     *
     * @param index the line number to overwrite
     * @param task the new task
     * @throws IOException unexpected IO error.
     */
    public void update(int index, Task task) throws IOException {
        List<String> fileContent = new ArrayList<>(Files.readAllLines(this.path));
        fileContent.set(index, task.toSavableFormat());
        Files.write(this.path, fileContent);
    }

    /**
     * Appends a new task to storage.
     *
     * @param task The task to be appended.
     * @throws IOException Thrown if there was an unexpected IO error.
     */
    public void append(Task task) throws IOException {
        Files.write(this.path, (task.toSavableFormat() + "\n").getBytes(), StandardOpenOption.APPEND);
    }

    /**
     * Deletes the corresponding task in storage.
     * Solution adapted from https://stackoverflow.com/a/37624091 with modifications.
     *
     * @param taskId Id of the task to delete.
     * @throws IOException Thrown if there was an unexpected IO error.
     */
    public void delete(int taskId) throws IOException {
        List<String> fileContent = new ArrayList<>(Files.readAllLines(this.path));
        fileContent.remove(taskId - 1);
        Files.write(this.path, fileContent);
    }
}
