package duke;

import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A collection of storage io methods.
 */
public class Storage {

    /**
     * The file path of the data file.
     */
    private final String filePath;

    /**
     * The file name of the data file.
     */
    private final String fileName;

    /**
     * Parser instance to handle parsing.
     */
    private final Parser parser = new Parser();

    /**
     * Construct a storage instance to handle task storage.
     *
     * @param filePath Path to the data save file directory.
     * @param fileName File name of the data save file.
     */
    public Storage(String filePath, String fileName) {
        this.filePath = filePath;
        this.fileName = fileName;
    }

    /**
     * Read data from the data file duke.txt.
     *
     * @return A TaskList of tasks read.
     */
    public TaskList readData() {
        File dataFile = new File(this.filePath + this.fileName);

        try {
            Scanner fileScanner = new Scanner(dataFile);
            ArrayList<Task> tasks = new ArrayList<>();
            while (fileScanner.hasNextLine()) {
                String data = fileScanner.nextLine();
                tasks.add(dataStringToTask(data));
            }
            fileScanner.close();
            return new TaskList(tasks);
        } catch (FileNotFoundException e) {
            // return empty array list of task
            return new TaskList(new ArrayList<>());
        }
    }

    /**
     * Convert a data string read from duke.txt into task.
     *
     * @return The task represented by the string.
     */
    private Task dataStringToTask(String data) {
        String[] taskInfo = data.split(" [|] ");

        String taskType = taskInfo[0];
        Task task;
        switch (taskType) {
        case "T":
            // task is todo
            task = new Todo(taskInfo[2]);

            break;
        case "D":
            // task is deadline
            LocalDate by;
            try {
                by = parser.stringToLocalDate(taskInfo[3]);
            } catch (DukeException e) {
                by = LocalDate.now();
            }
            task = new Deadline(taskInfo[2], by);
            break;
        case "E":
            // task is event
            LocalDate at;
            try {
                at = parser.stringToLocalDate(taskInfo[3]);
            } catch (DukeException e) {
                at = LocalDate.now();
            }
            task = new Event(taskInfo[2], at);
            break;
        default:
            // not of any task type
            throw new IllegalArgumentException("Task type not recognized: " + taskType);
        }
        if (Integer.parseInt(taskInfo[1]) == 1) task.markAsDone();
        return task;
    }

    /**
     * Write a list of tasks to the duke.txt data file.
     *
     * @param tasks The list of tasks to be written.
     */
    public void writeTasksToData(TaskList tasks) throws DukeException {
        try {
            Files.write(Paths.get(this.filePath + this.fileName), tasks.toDataString());

        } catch (IOException ioException) {
            try {
                Files.createDirectories(Paths.get(this.filePath));
                Path p = Files.createFile(Paths.get(this.filePath + this.fileName));
            } catch (IOException ioExp) {
                throw new DukeException("Failed to create Directories/File: " + ioExp.getMessage());
            }
        }
    }
}