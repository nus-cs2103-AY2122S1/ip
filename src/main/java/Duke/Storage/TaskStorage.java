package Duke.Storage;

import Duke.Task.*;

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

public class TaskStorage implements Storage<Task> {
    private final String path;

    public TaskStorage(String path) throws IOException {
        this.path = path;

        // Create directories and file if they don't already exist
        Files.createDirectories(Paths.get(path).getParent());
        createFileIfNotExist(path);
    }

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

    private static void createFileIfNotExist(String path) throws IOException {
        File file = new File(path);
        file.createNewFile();
    }

    /**
     * Parses the string argument as a Task.
     * The string is expected to be in the format "[T|D|E],[0|1],<description>,<date>",
     * where `[T|D|E]` represents the type of task and `[0|1]` represents the completion status.
     *
     * @param data the string to be parsed
     * @return the Task object
     * @throws FileFormatException if the string is not in the specified format
     */
    private static Task parseTask(String data) throws FileFormatException {
        String[] splitData = data.split(",", -1);
        if (splitData.length != 4) {
            throw new FileFormatException("Expected 4 values, got " + splitData.length);
        }

        int completionStatus;
        try {
            completionStatus = Integer.parseInt(splitData[1]);
        } catch (NumberFormatException e) {
            throw new FileFormatException(String.format("Unable to parse \"%s\" as an integer", splitData[1]));
        }
        if (!(completionStatus == 0 || completionStatus == 1)) {
            throw new FileFormatException("Expected 0 or 1 for completion status, got " + completionStatus);
        }

        String type = splitData[0], description = splitData[2], date = splitData[3];
        boolean isDone = completionStatus == 1;

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
            }
        } catch (InvalidTaskException e) {
            throw new FileFormatException("", e);
        } catch (DateTimeParseException e) {
            throw new FileFormatException(String.format("Unable to parse \"%s\" as a date", date));
        }

        throw new FileFormatException(String.format("Unrecognized task type \"%s\"", type));
    }

    private static String encodeTask(Task task) {
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
