package memocat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import memocat.task.Deadline;
import memocat.task.Event;
import memocat.task.Task;
import memocat.task.TaskList;
import memocat.task.Todo;

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
     * Constructs a storage instance to handle task storage.
     *
     * @param filePath Path to the data save file directory.
     * @param fileName File name of the data save file.
     */
    public Storage(String filePath, String fileName) {
        this.filePath = filePath;
        this.fileName = fileName;
    }

    /**
     * Reads data from the data file memocat.txt.
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
     * Converts a data string read from memocat.txt into task.
     *
     * @return The task represented by the string.
     */
    private Task dataStringToTask(String data) {
        String[] taskInfo = data.split(" [|] ");
        assert taskInfo.length >= 3 : "Invalid data read";

        String taskType = taskInfo[0];
        Task task;
        switch (taskType) {
        case "T":
            // task is todo
            assert taskInfo.length == 3 : "Invalid todo format";
            task = new Todo(taskInfo[2]);

            break;
        case "D":
            // task is deadline
            assert taskInfo.length == 4 : "Invalid todo format";

            LocalDate by;
            try {
                by = parser.stringToLocalDate(taskInfo[3]);
            } catch (MemoCatException e) {
                by = LocalDate.now();
            }
            task = new Deadline(taskInfo[2], by);
            break;
        case "E":
            // task is event
            assert taskInfo.length == 4 : "Invalid todo format";

            LocalDate at;
            try {
                at = parser.stringToLocalDate(taskInfo[3]);
            } catch (MemoCatException e) {
                at = LocalDate.now();
            }
            task = new Event(taskInfo[2], at);
            break;
        default:
            // not of any task type
            throw new IllegalArgumentException("Task type not recognized: " + taskType);
        }

        if (Integer.parseInt(taskInfo[1]) == 1) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Writes a list of tasks to the memocat.txt data file.
     *
     * @param tasks The list of tasks to be written.
     */
    public void writeTasksToData(TaskList tasks) throws MemoCatException {
        Path dataFileFullPath = Path.of(this.filePath + this.fileName);
        try {
            ArrayList<String> tasksString = tasks.toDataString();

            Files.write(dataFileFullPath, tasksString);
        } catch (IOException ioException) {
            try {
                Path dataFileDirectory = Path.of(this.filePath);
                Files.createDirectories(dataFileDirectory);
                Files.createFile(dataFileFullPath);
            } catch (IOException ioExp) {
                throw new MemoCatException("Failed to create Directories/File: " + ioExp.getMessage());
            }
        }
    }
}
