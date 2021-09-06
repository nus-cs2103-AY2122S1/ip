package kayu.storage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import kayu.exception.StorageException;
import kayu.task.Deadline;
import kayu.task.Event;
import kayu.task.Task;
import kayu.task.Todo;

/**
 * Handles the reading and writing of {@link kayu.task.Task} into files.
 */
public class TaskStorage extends Storage<Task> {

    protected static final String ERROR_UNABLE_TO_PARSE_TASK = "'%s' is an invalid Task entry.";

    // Default task file directory.
    private static final String DEFAULT_FILE_PATH = "data/tasks.txt";

    private TaskStorage(String directoryPath, String filePath) {
        super(directoryPath, filePath);
    }

    /**
     * Generates a {@link kayu.storage.TaskStorage} instance based on the default path.
     *
     * @return A {@link kayu.storage.TaskStorage} instance.
     */
    public static TaskStorage generate() {
        return TaskStorage.generate(DEFAULT_FILE_PATH);
    }

    /**
     * Generates a {@link kayu.storage.TaskStorage} instance based on a specified path.
     *
     * @param filePath File path string.
     * @return A {@link kayu.storage.TaskStorage} instance.
     */
    public static TaskStorage generate(String filePath) {
        String directoryPath = Storage.extractDirectoryPath(filePath);
        return new TaskStorage(directoryPath, filePath);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Task decode(String encoded) throws StorageException {
        String[] taskAsArray = encoded.split(Task.SPLIT_TEMPLATE);

        try {
            String keyword = taskAsArray[0];
            boolean isDone = taskAsArray[1].equals(Task.DONE);
            String desc = taskAsArray[2];
            LocalDate date;
            LocalTime time;

            switch (keyword) {
            case Todo.KEYWORD:
                return new Todo(desc, isDone);

            case Event.KEYWORD:
                date = LocalDate.parse(taskAsArray[3]);
                time = LocalTime.parse(taskAsArray[4]);
                return new Event(desc, isDone, date, time);

            case Deadline.KEYWORD:
                date = LocalDate.parse(taskAsArray[3]);
                time = LocalTime.parse(taskAsArray[4]);
                return new Deadline(desc, isDone, date, time);

            default:
                throw new StorageException(String.format(ERROR_UNABLE_TO_PARSE_TASK, encoded));
            }
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException exception) {
            throw new StorageException(String.format(ERROR_UNABLE_TO_PARSE_TASK, encoded));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String encode(Task decoded) {
        return decoded.toEncodedString();
    }
}
