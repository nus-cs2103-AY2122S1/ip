package duke.storage;

import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.ToDoTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper class to handle loading and saving the list of tasks into storage.
 *
 * @author Jay Aljelo Saez Ting
 */
public class StorageHandler {
    private static final String DEFAULT_PATH = "data/tasks.txt";
    private static final Charset DEFAULT_ENCODING = StandardCharsets.UTF_8;

    private static StorageHandler instance;

    private Path path;

    private StorageHandler() {
        path = Paths.get(DEFAULT_PATH);
    }

    /**
     * Gets the sole instance of the StorageHandler class.
     *
     * @return The sole StorageHandler instance.
     */
    public static StorageHandler getInstance() {
        if (instance == null) {
            instance = new StorageHandler();
        }
        return instance;
    }

    /**
     * Saves the list of tasks into storage.
     *
     * @param tasks The list of tasks.
     * @throws IOException If an I/O error occurs.
     */
    public void saveTasks(List<Task> tasks) throws IOException {
        String fileContents = createFileContents(tasks);
        Files.createDirectories(path.getParent());
        try (BufferedWriter writer = Files.newBufferedWriter(path, DEFAULT_ENCODING)) {
            writer.write(fileContents, 0, fileContents.length());
        }
    }

    /**
     * Loads the list of tasks from storage.
     *
     * @return The list of tasks.
     * @throws IOException If an I/O error occurs or if any task in the tasks file cannot be parsed.
     */
    public List<Task> loadTasks() throws IOException {
        if (Files.notExists(path)) {
            return new ArrayList<>();
        }
        try (BufferedReader reader = Files.newBufferedReader(path, DEFAULT_ENCODING)) {
            List<Task> tasks = new ArrayList<>();
            String line = reader.readLine();
            while (line != null) {
                Task task;
                try {
                    task = parseTask(line);
                } catch (IllegalArgumentException e) {
                    throw new IOException();
                }
                tasks.add(task);
                line = reader.readLine();
            }
            return tasks;
        }
    }

    private String createFileContents(List<Task> tasks) {
        StringBuilder fileContentsSb = new StringBuilder();
        for (Task task : tasks) {
            fileContentsSb.append(task.getRepresentation()).append("\n");
        }
        String fileContents = fileContentsSb.toString();
        return fileContents;
    }

    private Task parseTask(String taskRepresentation) throws IllegalArgumentException {
        String[] tokens = taskRepresentation.strip().split(" ");
        // throw error if there are less than 3 tokens
        if (tokens.length < 3) {
            throw new IllegalArgumentException();
        }
        String type = tokens[0];
        Task task;
        StringBuilder taskDescriptionSb = new StringBuilder();
        StringBuilder timeSb = new StringBuilder();
        int timeStartIndex = tokens.length;
        String taskDescription;
        String time;
        switch (type) {
        case "T":
            for (int i = 2; i < tokens.length; i++) {
                String token = tokens[i];
                taskDescriptionSb.append(token).append(" ");
            }
            taskDescription = taskDescriptionSb.toString().strip();
            if (taskDescription.length() == 0) {
                throw new IllegalArgumentException();
            }
            task = new ToDoTask(taskDescription);
            break;
        case "D":
            for (int i = 2; i < tokens.length; i++) {
                String token = tokens[i];
                if (token.equals("/by")) {
                    timeStartIndex = i + 1;
                    break;
                }
                taskDescriptionSb.append(token).append(" ");
            }
            for (int i = timeStartIndex; i < tokens.length; i++) {
                String token = tokens[i];
                timeSb.append(token).append(" ");
            }
            taskDescription = taskDescriptionSb.toString().strip();
            time = timeSb.toString().strip();
            if (taskDescription.length() == 0 || time.length() == 0) {
                throw new IllegalArgumentException();
            }
            task = new DeadlineTask(taskDescription, time);
            break;
        case "E":
            for (int i = 2; i < tokens.length; i++) {
                String token = tokens[i];
                if (token.equals("/at")) {
                    timeStartIndex = i + 1;
                    break;
                }
                taskDescriptionSb.append(token).append(" ");
            }
            for (int i = timeStartIndex; i < tokens.length; i++) {
                String token = tokens[i];
                timeSb.append(token).append(" ");
            }
            taskDescription = taskDescriptionSb.toString().strip();
            time = timeSb.toString().strip();
            if (taskDescription.length() == 0 || time.length() == 0) {
                throw new IllegalArgumentException();
            }
            task = new EventTask(taskDescription, time);
            break;
        default:
            throw new IllegalArgumentException();
        }
        try {
            int done = Integer.parseInt(tokens[1]);
            switch (done) {
            case 0:
                break;
            case 1:
                task.markDone();
                break;
            default:
                throw new IllegalArgumentException();
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
        return task;
    }
}
