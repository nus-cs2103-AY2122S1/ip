package duke.data;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * Represents storage that can store data into a file and read data from a file.
 */
public class Storage {
    /** Path of the current folder as a string */
    public static final String DIRECTORY_PATH = System.getProperty("user.dir");
    /** Path of file containing data saved */
    private static File data;
    /** A temporary list of tasks */
    private TaskList tasks;

    /**
     * Constructor of the `Storage` class.
     *
     * @param filePath Path to the file to be loaded.
     * @param tasks A temporary list of tasks.
     */
    public Storage(Path filePath, TaskList tasks) {
        Storage.data = filePath.toFile();
        this.tasks = tasks;
        this.readFile();
    }

    /**
     * Reads data in the file. If the directory or file doesn't exist, create a new file in the correct
     * location.
     */
    private void readFile() {
        try {
            Scanner fileScanner = new Scanner(Storage.data);
            while (fileScanner.hasNextLine()) {
                String task = fileScanner.nextLine();
                this.readData(task);
            }
            fileScanner.close();
        } catch (FileNotFoundException fileNotFoundException) { // If file doesn't exist, create it.
            try {
                Storage.data.createNewFile();
            } catch (IOException ioException) { // If directory doesn't exist, create it.
                File directory = Paths.get(Storage.DIRECTORY_PATH, "data").toFile();
                directory.mkdirs();
                this.readFile(); // Run this method again to create a file.
            }
        }
    }

    /**
     * Reads a line of data, creates a task and adds it to the task list.
     *
     * @param line A line of data.
     */
    private void readData(String line) {
        // Split data into words
        String[] splitted = line.split(" / ");

        // Determine type of the task and create corresponding task instance
        Task task;
        if (splitted[0].equals("T")) { // a todo task
            task = new ToDo(splitted[2]);
        } else if (splitted[0].equals("D")) { // a task with deadline
            task = new Deadline(splitted[2], splitted[3]);
        } else if (splitted[0].equals("E")) { // an event
            task = new Event(splitted[2], splitted[3]);
        } else {
            task = new Task(splitted[2]);
        }

        // Check whether task is done
        if (splitted[1].equals("1")) {
            task.setDone();
        }

        // Add to task list
        tasks.addTask(task);
    }

    /**
     * Adds a task to the file.
     *
     * @param task The task to be added.
     */
    public void addToFile(Task task) {
        try {
            FileWriter fileWriter = new FileWriter(Storage.data, true);
            fileWriter.append(task.toFileFormatString()); // write to file.
            fileWriter.close();
        } catch (IOException ioException) {
            this.readFile();
            Ui.showFileNotFoundError();
        }
    }

    /**
     * Removes a task from the file.
     *
     * @param index Index of the task in the task list.
     */
    public void removeFromFile(int index) {
        try {
            List<String> lines = Files.readAllLines(Storage.data.toPath());
            FileWriter fileWriter = new FileWriter(Storage.data);
            for (int i = 0; i < this.tasks.getNumOfTasks(); i++) { // remove this task from file.
                if (i != index) {
                    fileWriter.append(lines.get(i) + "\n");
                }
            }
            fileWriter.close();
        } catch (IOException ioException) {
            this.readFile();
            Ui.showFileNotFoundError();
        }
    }

    /**
     * Rewrites data from taskList to the file.
     */
    public void rewriteFile() {
        try {
            FileWriter fileWriter = new FileWriter(Storage.data);
            for (int i = 0; i < this.tasks.getNumOfTasks(); i++) {
                fileWriter.append(this.tasks.getFileFormattedTask(i));
            }
            fileWriter.close();
        } catch (IOException ioException) {
            this.readFile();
            Ui.showFileNotFoundError();
        }
    }
}
