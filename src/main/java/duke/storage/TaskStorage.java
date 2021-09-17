package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.task.InvalidTaskException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A storage of tasks on the hard disk.
 *
 * @author cai
 */
public class TaskStorage implements Storage<Task> {
    /** The path to the file storing the tasks */
    private final String path;

    /**
     * Constructs a new TaskStorage with the specified path.
     * Creates the directories and file if they don't already exist.
     *
     * @param path The path to the file storing the tasks.
     * @throws IOException If an IOException is thrown when creating the directories or file.
     */
    public TaskStorage(String path) throws IOException {
        this.path = path;
        Files.createDirectories(Paths.get(path).getParent());
        createFileIfNotExist(path);
    }

    /**
     * Returns the list of tasks stored on the file.
     *
     * @return The list of tasks stored.
     * @throws IOException If an IOException is thrown when creating or reading from the file.
     */
    public List<Task> load() throws IOException {
        List<Task> list = new ArrayList<>();
        File file = new File(this.path);
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            createFileIfNotExist(this.path);
            return list;
        }

        while (scanner.hasNext()) {
            String data = scanner.nextLine();
            list.add(parseTask(data));
        }

        return list;
    }

    /**
     * Saves the specified list of tasks to the file, overwriting the previous contents on the file.
     *
     * @param list The list of elements to save.
     * @throws IOException If an IOException is thrown when writing to the file.
     */
    public void save(List<Task> list) throws IOException {
        FileWriter writer = new FileWriter(this.path);
        StringBuilder builder = new StringBuilder();
        for (Task task : list) {
            builder.append(encodeTask(task));
            builder.append("\n");
        }
        writer.write(builder.toString());
        writer.close();
    }

    /**
     * Creates the specified file if it does not exist.
     *
     * @param path The file to create.
     * @throws IOException If an IOException is thrown when creating the file.
     */
    private static void createFileIfNotExist(String path) throws IOException {
        File file = new File(path);
        file.createNewFile();
    }

    /**
     * Parses the string argument as a Task.
     * The string is expected to be in the format "[T|D|E],[0|1],<description>,<date>",
     * where `[T|D|E]` represents the type of task and `[0|1]` represents the completion status.
     *
     * @param data The string to be parsed.
     * @return The Task object.
     * @throws FileFormatException If the string is not in the specified format.
     */
    private static Task parseTask(String data) throws FileFormatException {
        String[] splitData = data.split(",", -1);
        if (splitData.length != 4) {
            throw new FileFormatException("Expected 4 values, got " + splitData.length);
        }

        String type = splitData[0];
        boolean isDone = parseCompletionStatus(splitData[1]) == 1;
        String description = splitData[2];
        String date = splitData[3];

        try {
            switch (type) {
                case Todo.TASK_TYPE_ICON:
                    if (date.length() > 0) {
                        throw new FileFormatException("Unexpected date for task type todo");
                    }
                    return new Todo(description, isDone);
                case Deadline.TASK_TYPE_ICON:
                    return new Deadline(description, LocalDate.parse(date), isDone);
                case Event.TASK_TYPE_ICON:
                    return new Event(description, LocalDate.parse(date), isDone);
                default:
                    throw new FileFormatException(String.format("Unrecognized task type \"%s\"", type));
            }
        } catch (InvalidTaskException e) {
            throw new FileFormatException("", e);
        } catch (DateTimeParseException e) {
            throw new FileFormatException(String.format("Unable to parse \"%s\" as a date", date));
        }
    }

    /**
     * Parses the string argument as a completion status, which is an integer of value 0 or 1.
     *
     * @param status String to parse.
     * @return An integer of value 0 or 1, representing the completion status.
     * @throws FileFormatException If the string cannot be parsed as an integer of value 0 or 1.
     */
    private static int parseCompletionStatus(String status) throws FileFormatException {
        int completionStatus;

        try {
            completionStatus = Integer.parseInt(status);
        } catch (NumberFormatException e) {
            throw new FileFormatException(String.format("Unable to parse \"%s\" as an integer", status));
        }

        if (!(completionStatus == 0 || completionStatus == 1)) {
            throw new FileFormatException("Expected 0 or 1 for completion status, got " + completionStatus);
        }

        return completionStatus;
    }

    /**
     * Returns the specified task as a string.
     * The string is in the format "[T|D|E],[0|1],<description>,<date>",
     * where `[T|D|E]` represents the type of task and `[0|1]` represents the completion status.
     *
     * @param task The task to encode.
     * @return The string representing the task.
     */
    private static String encodeTask(Task task) {
        assert task != null;

        String type = task.getTaskTypeIcon(), completionStatus = task.isDone() ? "1" : "0",
                description = task.getDescription(), date = "";

        if (task instanceof Deadline) {
            date = ((Deadline) task).getEndDate().toString();
        }
        if (task instanceof Event) {
            date = ((Event) task).getAt().toString();
        }

        return String.join(",", type, completionStatus, description, date);
    }
}
