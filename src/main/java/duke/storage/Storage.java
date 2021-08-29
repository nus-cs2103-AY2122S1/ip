package duke.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import duke.parser.Parser;
import duke.parser.UnableToParseException;
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
    public static final String DEFAULT_FILE_PATH = "data/duke.txt";
    /**
     * Delimiter for the saved format in storage.
     */
    public static final String DELIMITER = " | ";
    private final Path path;

    /**
     * Constructor of the Storage class.
     *
     * @param filePath The specified file path of the txt file.
     */
    public Storage(String filePath) {
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
            return new TaskList();
        }

        List<String> lines = Files.readAllLines(this.path);
        ArrayList<Task> tasks = new ArrayList<>();
        for (String line : lines) {
            String[] split = line.split(Pattern.quote(Storage.DELIMITER));
            if (split.length == 0) {
                throw new UnableToParseException(this.path.toString());
            }

            String description;
            boolean isDone;
            UnableToParseException e = new UnableToParseException("\"" + line + "\" at " + this.path);

            switch (split[0]) {
            case "T":
                if (split.length != 3) {
                    throw e;
                }

                isDone = Parser.parseStringToIsDone(split[1]);
                description = split[2];
                Todo todo = new Todo(description);
                if (isDone) {
                    todo.markAsDone();
                }
                tasks.add(todo);
                break;
            case "D":
                if (split.length != 4) {
                    throw e;
                }

                isDone = Parser.parseStringToIsDone(split[1]);
                description = split[2];
                LocalDate by;
                try {
                    by = LocalDate.parse(split[3]);
                } catch (DateTimeParseException exception) {
                    throw e;
                }

                Deadline deadline = new Deadline(description, by);
                if (isDone) {
                    deadline.markAsDone();
                }
                tasks.add(deadline);
                break;
            case "E":
                if (split.length != 4) {
                    throw e;
                }

                isDone = Parser.parseStringToIsDone(split[1]);
                description = split[2];
                String at = split[3];
                Event event = new Event(description, at);
                if (isDone) {
                    event.markAsDone();
                }
                tasks.add(event);
                break;
            default:
                throw e;
            }
        }
        return new TaskList(tasks);
    }

    /**
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
