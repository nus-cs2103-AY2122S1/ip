package aoi.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import aoi.data.TaskList;
import aoi.task.Deadline;
import aoi.task.Event;
import aoi.task.Task;
import aoi.task.Todo;

/**
 * Encapsulates a Storage object that handles loading and saving of Tasks.
 *
 * @author Owen Tan
 * @version aoi.Aoi Level-9
 */
public class Storage {
    private String path;
    private TaskList tasks;

    /**
     * Public constructor of Storage.
     *
     * @param path Path of text file stored.
     * @param tasks Associated TaskList.
     */
    public Storage(String path, TaskList tasks) {
        this.path = path;
        this.tasks = tasks;
    }

    /**
     * Loads the file into Storage and parses Tasks into TaskList.
     */
    public void load() {
        try {
            File fileDir = new File(path).getParentFile();
            File file = new File(path);

            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }

            if (!file.createNewFile()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;

                while ((line = reader.readLine()) != null) {
                    tasks.add(parseTask(line));
                }
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("Something went wrong with loading");
        }
    }

    /**
     * Saves Tasks from TaskList into text file.
     */
    public void save() {
        try {
            FileWriter writer = new FileWriter(path);
            ArrayList<Task> lst = tasks.getList();
            for (Task task: lst) {
                writer.write(task.printInSaveFormat());
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Something went wrong with saving");
        }
    }

    private Task parseTask(String task) {
        // Format to Parse: T | 0 | description | addInfo | notes (optional)
        final int maxLengthT = 4;
        final int maxLengthD = 5;
        final int maxLengthE = 5;
        final int taskPos = 0;
        final int isDonePos = 1;
        final int descriptionPos = 2;
        final int addInfoPos = 3;
        final int notesPos = 4;

        String[] tokens = task.split(" \\| ");
        Task taskCreated = null;
        boolean isDone = tokens[isDonePos].equals("1");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime timestamp = null;
        assert tokens[0].equals("T") || tokens[0].equals("D") || tokens[0].equals("E");
        String notes;
        switch (tokens[taskPos]) {
        case "T":
            notes = tokens.length == maxLengthT ? tokens[maxLengthT - 1] : "";
            taskCreated = new Todo(tokens[descriptionPos], isDone, notes);
            break;
        case "D":
            try {
                timestamp = LocalDateTime.parse(tokens[addInfoPos], format);
                notes = tokens.length == maxLengthD ? tokens[notesPos] : "";
                taskCreated = new Deadline(tokens[descriptionPos], isDone, timestamp, notes);
            } catch (DateTimeParseException e) {
                System.out.println("Error parsing task from saved file");
            }
            break;
        case "E":
            try {
                timestamp = LocalDateTime.parse(tokens[3], format);
                notes = tokens.length == maxLengthE ? tokens[notesPos] : "";
                taskCreated = new Event(tokens[descriptionPos], isDone, timestamp, notes);
            } catch (DateTimeParseException e) {
                System.out.println("Error parsing task from saved file");
            }
            break;
        default:
        }
        return taskCreated;
    }
}
