package duke.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.DukeException;
import duke.tasks.Deadline;
import duke.tasks.DoAfter;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;


/**
 * Class that deals with the saving and loading of persistent task data
 * that persists throughout multiple runs of Duke.
 */
public class PersistentStorage {

    /**
     * String that represents a filepath to the text
     * file for persistent storage.
     */
    private String filepath;

    /**
     * Constructor for a PersistentStorage.
     *
     * @param filepath The filepath to the text file used for
     *                 persistent storage
     */
    public PersistentStorage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads tasks from the text file at the provided filepath.
     *
     * @return A Tasklist containing all tasks in the provided text file
     * @throws DukeException if no such file found at the provided filepath
     */
    public Tasklist loadTasks() throws DukeException {
        File file = new File(this.filepath);
        if (!file.exists()) {
            // Create the necessary files and load an empty Tasklist
            file.getParentFile().mkdirs();
            return new Tasklist();
        } else {
            // Read data from file
            try {
                Scanner fileReader = new Scanner(file);
                Tasklist storedTasks = new Tasklist();

                String fileData = "";
                while (fileReader.hasNext()) {
                    fileData += fileReader.nextLine() + "\n";
                }

                // Check if fileData is empty
                if (fileData.equals("")) {
                    fileReader.close();
                    return new Tasklist();
                }

                // Parse fileData and return a Tasklist
                String[] allTasks = fileData.split("\n");

                for (String task : allTasks) {
                    validateFileString(task);

                    // Split task string into tokens
                    String[] tokens = task.split(" \\| ");

                    String taskType = tokens[0];
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                    boolean isDone = tokens[1].equals("1");;
                    String description = tokens[2];;
                    String rawDateTimeInfo;

                    switch(taskType) {
                    case "D":
                        rawDateTimeInfo = tokens[3];
                        LocalDateTime deadlineDateTime = LocalDateTime.parse(rawDateTimeInfo, formatter);
                        Deadline deadline = new Deadline(description, deadlineDateTime, isDone);
                        storedTasks.addTask(deadline);
                        break;
                    case "E":
                        rawDateTimeInfo = tokens[3];
                        LocalDateTime eventDateTime = LocalDateTime.parse(rawDateTimeInfo, formatter);
                        Event event = new Event(description, eventDateTime, isDone);
                        storedTasks.addTask(event);
                        break;
                    case "T":
                        ToDo toDo = new ToDo(description, isDone);
                        storedTasks.addTask(toDo);
                        break;
                    case "DA":
                        rawDateTimeInfo = tokens[3];
                        LocalDateTime doAfterDateTime = LocalDateTime.parse(rawDateTimeInfo, formatter);
                        DoAfter doAfter = new DoAfter(description, doAfterDateTime, isDone);
                        storedTasks.addTask(doAfter);
                        break;
                    default:
                        throw new DukeException("There was an error in reading your file! :(");
                    }
                }
                fileReader.close();
                return storedTasks;
            } catch (FileNotFoundException e) {
                throw new DukeException("Error loading file!");
            }
        }
    }

    /**
     * Saves tasks to the text file at the provided filepath.
     *
     * @throws DukeException if no such file found at the provided filepath
     */
    public void saveTasks(Tasklist tasklist) throws DukeException {
        File file = new File(this.filepath);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            // Convert tasks into string representation
            String taskData = "";
            ArrayList<Task> allTasks = tasklist.getAllTasks();
            for (int i = 0; i < tasklist.getTotalTasks(); i++) {
                Task task = allTasks.get(i);
                taskData += (i == tasklist.getTotalTasks() - 1
                    ? task.getFileRepr()
                    : task.getFileRepr() + "\n");
            }

            // Write data to file
            FileWriter writer = new FileWriter(file);
            writer.write(taskData);
            writer.close();
        } catch (IOException e) {
            throw new DukeException("An error occurred while trying to save data to your file :(");
        }
    }

    /**
     * Validate file string in persistent storage file.
     *
     * @param fileRepr The file String to be validated.
     * @throws DukeException If a parse error occurs.
     */
    public void validateFileString(String fileRepr) throws DukeException {
        String[] tokenized = fileRepr.split(" \\| ");
        int tokenLength = tokenized.length;

        // Check correct length
        if (tokenLength < 3 || tokenLength > 4) {
            throw new DukeException("There was an error in parsing your file!");
        }

        // Check task type
        String taskType = tokenized[0];
        boolean isTodo = taskType.equals("T");
        boolean isDeadline = taskType.equals("D");
        boolean isEvent = taskType.equals("E");
        boolean isDoAfter = taskType.equals("DA");
        boolean isValidTask = (isTodo || isDeadline || isEvent || isDoAfter);
        if (!isValidTask) {
            throw new DukeException("There was an error in parsing your file!");
        }
    }
}
