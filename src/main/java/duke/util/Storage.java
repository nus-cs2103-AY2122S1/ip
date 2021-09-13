package duke.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeIoException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * This class encapsulates the storage used for loading and saving data.
 *
 * @author Teo Sin Yee
 */
public class Storage {
    private static final String FILE_NAME = "./data/tasks.txt";
    private static final File file = new File(FILE_NAME);

    /**
     * Reads data from file.
     * Returns an ArrayList containing all the data in the file.
     *
     * @return ArrayList containing all tasks.
     */
    public ArrayList<Task> loadTasks() {
        try {
            Scanner sc = new Scanner(file);
            ArrayList<Task> loadedTasks = new ArrayList<>();
            while (sc.hasNextLine()) {
                loadedTasks.add(formatToTaskList(sc.nextLine()));
            }
            return loadedTasks;
        } catch (FileNotFoundException e) {
            createNewFile();
            return new ArrayList<>();
        }
    }

    /**
     * Creates a new file to store data
     **/
    private void createNewFile() {
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            System.err.println("Error creating directory/file. Your data were not saved. :_(");
        }
    }

    /**
     * Writes data to storage file.
     *
     * @param task Task to be saved to the storage file.
     * @throws DukeIoException If there is error writing to the storage file.
     */
    public void writeToFile(Task task) throws DukeIoException {
        if (!file.exists()) {
            createNewFile();
        }
        try {
            FileWriter fw = new FileWriter(FILE_NAME, true);
            fw.write(task.formatToSave() + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            throw new DukeIoException("Error: Unable to write to data file. Your data were not saved. :_(");
        }
    }

    /**
     * Updates storage file after changes are made to the task list.
     *
     * @param tasks Edited task list.
     * @throws DukeIoException If there is an error updating storage file.
     */
    public static void updateData(ArrayList<Task> tasks) throws DukeIoException {
        assert file.exists() : "The tasks.txt file does not exist.";
        try {
            FileWriter fw = new FileWriter(FILE_NAME);
            for (Task t: tasks) {
                fw.write(t.formatToSave() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeIoException("Error: Unable to write to data file."
                    + "Your data were not saved. :_(");
        }
    }

    /**
     * Formats the data saved in the storage file back to a Task object.
     *
     * @param s Text format of the Task in the storage file.
     * @return Task object of data in the storage file.
     */
    private static Task formatToTaskList(String s) {
        String[] details = s.split(" \\| ");
        Task task;

        switch (details[0]) {
        case "T":
            task = new Todo(details[2]);
            break;
        case "D":
            task = new Deadline(details[2], details[3]);
            break;
        case "E":
            task = new Event(details[2], details[3]);
            break;
        default:
            throw new IllegalStateException("Error: Unexpected character: " + details[0]);
        }
        if (details[1].equals("1")) {
            task.markAsDone();
        }
        return task;
    }
}
