package chad.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import chad.task.DeadlineTask;
import chad.task.EventTask;
import chad.task.Task;
import chad.task.ToDoTask;

/**
 * Helper class to handle loading and saving the list of tasks into storage.
 *
 * @author Jay Aljelo Saez Ting
 */
public class StorageHandler {

    private static final String DEFAULT_PATH = "data/tasks.txt";
    private static final Charset DEFAULT_ENCODING = StandardCharsets.UTF_8;
    private static final String DATE_TIME_FORMAT_PATTERN = "yyyyMMddHHmm";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_PATTERN);
    private static final String DEADLINE_TASK_TIME_RELATION_TOKEN = "/by";
    private static final String EVENT_TASK_TIME_RELATION_TOKEN = "/at";
    private static final String TOKEN_DELIMITER = " ";
    private static final int TASK_MAIN_ARGUMENTS_START_INDEX = 2;
    private static final String TODO_TASK_TYPE_TOKEN = "T";
    private static final String DEADLINE_TASK_TYPE_TOKEN = "D";
    private static final String EVENT_TASK_TYPE_TOKEN = "E";

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
                    task = parseTaskRepresentation(line);
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
            fileContentsSb.append(task.getFileRepresentation()).append("\n");
        }
        String fileContents = fileContentsSb.toString();
        return fileContents;
    }

    private Task parseTaskRepresentation(String taskRepresentation) throws IllegalArgumentException {
        String[] tokens = taskRepresentation.strip().split(TOKEN_DELIMITER);
        checkTokensArrayLength(tokens);
        Task task = parseTask(tokens);
        String doneToken = tokens[1];
        markTaskDoneIfLabelledDone(task, doneToken);
        return task;
    }

    private void checkTokensArrayLength(String[] tokens) {
        if (tokens.length < 3) {
            throw new IllegalArgumentException();
        }
    }

    private Task parseTask(String[] tokens) throws IllegalArgumentException {
        String type = tokens[0];
        switch (type) {
        case TODO_TASK_TYPE_TOKEN:
            return parseToDoTask(tokens);
        case DEADLINE_TASK_TYPE_TOKEN:
            return parseDeadlineTask(tokens);
        case EVENT_TASK_TYPE_TOKEN:
            return parseEventTask(tokens);
        default:
            throw new IllegalArgumentException();
        }
    }

    private void markTaskDoneIfLabelledDone(Task task, String doneToken) throws IllegalArgumentException {
        int done;
        try {
            done = Integer.parseInt(doneToken);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
        switch (done) {
        case 0:
            break;
        case 1:
            task.markDone();
            break;
        default:
            throw new IllegalArgumentException();
        }
    }

    private ToDoTask parseToDoTask(String[] tokens) {
        String taskDescription = getTokenSequence(tokens,
                TASK_MAIN_ARGUMENTS_START_INDEX, tokens.length);
        checkTaskDescriptionLength(taskDescription);
        return new ToDoTask(taskDescription);
    }

    private DeadlineTask parseDeadlineTask(String[] tokens) {
        int timeRelationIndex = findTokenIndex(DEADLINE_TASK_TIME_RELATION_TOKEN, tokens,
                TASK_MAIN_ARGUMENTS_START_INDEX, tokens.length);
        int timeStartIndex = timeRelationIndex + 1;
        String taskDescription = getTokenSequence(tokens, TASK_MAIN_ARGUMENTS_START_INDEX, timeRelationIndex);
        String timeStr = getTokenSequence(tokens, timeStartIndex, tokens.length);
        checkTaskDescriptionLength(taskDescription);
        checkTimeStringLength(timeStr);
        LocalDateTime time = parseTime(timeStr);
        return new DeadlineTask(taskDescription, time);
    }

    private EventTask parseEventTask(String[] tokens) {
        int timeRelationIndex = findTokenIndex(EVENT_TASK_TIME_RELATION_TOKEN, tokens,
                TASK_MAIN_ARGUMENTS_START_INDEX, tokens.length);
        int timeStartIndex = timeRelationIndex + 1;
        String taskDescription = getTokenSequence(tokens, TASK_MAIN_ARGUMENTS_START_INDEX, timeRelationIndex);
        String timeStr = getTokenSequence(tokens, timeStartIndex, tokens.length);
        checkTaskDescriptionLength(taskDescription);
        checkTimeStringLength(timeStr);
        LocalDateTime time = parseTime(timeStr);
        return new EventTask(taskDescription, time);
    }

    private String getTokenSequence(String[] tokens, int inclusiveStart, int exclusiveEnd) {
        StringBuilder tokenSequenceSb = new StringBuilder();
        for (int i = inclusiveStart; i < exclusiveEnd; i++) {
            String token = tokens[i];
            tokenSequenceSb.append(token).append(TOKEN_DELIMITER);
        }
        return tokenSequenceSb.toString().strip();
    }

    private void checkTaskDescriptionLength(String taskDescription) throws IllegalArgumentException {
        if (taskDescription.length() == 0) {
            throw new IllegalArgumentException();
        }
    }

    private int findTokenIndex(String tokenQuery, String[] tokens, int inclusiveStart, int exclusiveEnd) {
        for (int i = inclusiveStart; i < exclusiveEnd; i++) {
            String token = tokens[i];
            if (token.equals(tokenQuery)) {
                return i;
            }
        }
        return tokens.length;
    }

    private void checkTimeStringLength(String timeString) throws IllegalArgumentException {
        if (timeString.length() == 0) {
            throw new IllegalArgumentException();
        }
    }

    private LocalDateTime parseTime(String timeStr) throws IllegalArgumentException {
        try {
            return LocalDateTime.parse(timeStr, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException();
        }
    }
}
