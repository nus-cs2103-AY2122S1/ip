package duke.storage;

import duke.ui.Ui;
import duke.data.task.*;
import duke.data.exception.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Class that deals with loading tasks from the file and updating tasks in the file.
 *
 * @author Wang Hong Yong
 */
public class Storage {
    private static final String FILE_NAME = "./data/allTasks.txt";
    private static final File file = new File(FILE_NAME);

    /**
     * Loads the tasks from the file.
     *
     * @return An arraylist of the tasks.
     * @throws DukeException if the tasks cannot be loaded.
     */
    public static ArrayList<Task> loadFile() throws DukeException {
        try {
            Scanner sc = new Scanner(file);
            ArrayList<Task> loadedTasks = new ArrayList<>();
            while (sc.hasNextLine()) {
                loadedTasks.add(formatToRead(sc.nextLine()));
            }
            return loadedTasks;
        } catch (FileNotFoundException e) {
            createFile();
            return new ArrayList<>();
        }
    }

    /**
     * Create the file according to the tasklist.
     */
    private static void createFile() {
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            Ui.printCreateDirectoryErr();
        }
    }

    /**
     * Writes the file according to the tasklist.
     *
     * @param task String representatio of all tasks.
     * @throws DukeException if unexpected error is encountered.
     */
    public static void writeFile(Task task) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(FILE_NAME, true);
            fileWriter.write(task.formatToWrite() + System.lineSeparator());
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException(Ui.getIoMsg());
        }
    }

    /**
     * Updates the file according to the tasklist.
     *
     * @param tasks String representatio of all tasks.
     * @throws DukeException if unexpected error is encountered.
     */
    public static void updateFile(ArrayList<Task> tasks) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(FILE_NAME);
            for (Task t: tasks) {
                fileWriter.write(t.formatToWrite() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException(Ui.getIoMsg());
        }
    }

    /**
     * Returns a task that corresponds with the string description of the task.
     *
     * @param s String representation of the task to be returned.
     * @return A task that corresponds with the string description of the task.
     * @throws DukeException if task cannot be identified.
     */
    private static Task formatToRead(String s) throws DukeException {
        String[] info = s.split(" \\| ");
        Task task;

        switch (info[0]) {
        case "D":
            task = new Deadline(info[2], info[3]);
            break;
        case "E":
            task = new Event(info[2], info[3]);
            break;
        case "T":
            task = new Todo(info[2]);
            break;
        default:
            throw new DukeException("Error unknown value: " + info[0]);
        }
        if (info[1].equals("1")) {
            task.markAsDone();
        }
        return task;
    }
}
