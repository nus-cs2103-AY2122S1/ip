package biscuit.storage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import biscuit.exceptions.BiscuitException;
import biscuit.task.Deadline;
import biscuit.task.Event;
import biscuit.task.Task;
import biscuit.task.ToDo;

/**
 * Storage class deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    private final String filePath;

    private final ArrayList<Task> tasks;

    /**
     * Constructs Storage class.
     *
     * @param filePath Path to file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        tasks = new ArrayList<>();
    }

    /**
     * Saves changes made to file in filePath.
     *
     * @throws BiscuitException Unable to save.
     */
    public void save() throws BiscuitException {
        try {
            try (PrintWriter printWriter = new PrintWriter(filePath)) {
                tasks.stream().map(this::convertToCsv).forEach(printWriter::println);
            }
        } catch (IOException e) {
            throw new BiscuitException("Woof! Something went wrong and I was unable to save your most recent change.");
        }
    }

    /**
     * Loads list of tasks from file in file path.
     *
     * @return List of files.
     * @throws BiscuitException Unable to load save file.
     */
    public List<Task> load() throws BiscuitException {
        try {
            File fileToSave = new File(filePath);
            if (!fileToSave.exists()) {
                fileToSave.getParentFile().mkdirs();
                fileToSave.createNewFile();
            }
            try (Scanner scanner = new Scanner(new File(filePath))) {
                while (scanner.hasNext()) {
                    tasks.add(convertToTask(scanner.nextLine()));
                }
            }
        } catch (IOException e) {
            throw new BiscuitException("Woof! Could not create save file to save data to.");
        }
        return tasks;
    }

    /**
     * Converts task to csv string.
     *
     * @param task Task to convert.
     * @return Csv string.
     */
    private String convertToCsv(Task task) {
        String csv = task.getType().toString() + "," + task.isDone() + ","
                + escapeSpecialCharacters(task.getDescription());
        switch (task.getType()) {
        case DEADLINE:
            Deadline deadline = (Deadline) task;
            csv += "," + escapeSpecialCharacters(deadline.getDate().toString());
            break;
        case EVENT:
            Event event = (Event) task;
            csv += "," + escapeSpecialCharacters(event.getDate().toString());
            break;
        default:
            break;
        }
        return csv;
    }

    /**
     * Converts csv string to task.
     *
     * @param csv Csv string to convert.
     * @return Task.
     * @throws BiscuitException Invalid csv string.
     */
    private Task convertToTask(String csv) throws BiscuitException {
        try {
            // splits csv string by commas, but ignores commas in quotes
            String[] values = csv.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            boolean isDone = Boolean.parseBoolean(values[1]);
            String description = unescapeSpecialCharacters(values[2]);
            switch (Task.TaskType.valueOf(values[0])) {
            case DEADLINE:
                return new Deadline(isDone, description, LocalDate.parse(values[3]));
            case EVENT:
                return new Event(isDone, description, LocalDateTime.parse(values[3]));
            case TODO:
                return new ToDo(isDone, description);
            default:
                throw new BiscuitException("Woof! Invalid save data, could not load saved tasks.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new BiscuitException("Woof! Invalid save data, could not load saved tasks.");
        }
    }

    /**
     * Escapes special characters of commas, quotes and new lines.
     *
     * @param data Data to escape.
     * @return String with special characters escaped.
     */
    private String escapeSpecialCharacters(String data) {
        // Solution below adapted from https://www.baeldung.com/java-csv
        String escapedData = data.replaceAll("\\R", " ");
        if (escapedData.contains(",") || escapedData.contains("\"") || escapedData.contains("'")) {
            escapedData = escapedData.replace("\"", "\"\"");
            escapedData = "\"" + escapedData + "\"";
        }
        return escapedData;
    }

    /**
     * Unescapes special characters of commas, quotes and new lines.
     *
     * @param data Data to unescape.
     * @return String with special characters unescaped.
     */
    private String unescapeSpecialCharacters(String data) {
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"\"", "\"");
            data = data.substring(1, data.length() - 1);
        }
        return data;
    }
}
