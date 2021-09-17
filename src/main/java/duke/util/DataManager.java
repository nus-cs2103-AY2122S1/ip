package duke.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.exception.DukeIoException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * This class encapsulates the data manager used for persisting tasks.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class DataManager {
    private static final String IOEXCEPTION_UNABLE_TO_CREATE_DIRECTORY =
            "IOException: Unable to create directory/file. Your data will not be saved!";
    // Storage file used for persisting task
    private final File data;
    // Relative path of file
    private final String filePath;

    /**
     * Constructs the DataManager that use the specified filepath as persistent storage for Duke.
     *
     * @param filePath The path to the file used for persistent storage.
     */
    public DataManager(String filePath) {
        this.data = new File(filePath);
        this.filePath = filePath;
    }

    /**
     * Reads data from storage file.
     * Returns an ArrayList of tasks containing all tasks persisted in storage.
     *
     * @return ArrayList of tasks persisted in storage.
     */
    public ArrayList<Task> readData() {
        try {
            Scanner sc = new Scanner(data);
            ArrayList<Task> loadedTasks = new ArrayList<>();
            while (sc.hasNext()) {
                loadedTasks.add(convertTextToTasks(sc.nextLine()));
            }
            return loadedTasks;
        } catch (FileNotFoundException e) {
            createNewFile();
            return new ArrayList<>();
        }
    }

    /**
     * Writes data to storage file.
     * Takes in a task to be stored in persisted storage.
     *
     * @param task Task to be persisted in storage.
     */
    public void writeToFile(Task task) throws DukeException {
        if (!data.exists()) {
            createNewFile();
        }

        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(task.convertToText() + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            throw new DukeIoException();
        }
    }

    /**
     * Updates storage file.
     * Runs after an edit is made to the ToDoList.
     *
     * @param tasks Updated state of task list after edit is made.
     * @throws DukeIoException if there is error writing to the storage file.
     */
    public void updateData(ArrayList<Task> tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task t : tasks) {
                fw.write(t.convertToText() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeIoException();
        }
    }

    /**
     * Converts the txt format stored in storage back to a Task object.
     *
     * @param txt Text format of Task stored in storage file.
     * @return Task object of text stored in storage.
     */
    private Task convertTextToTasks(String txt) {
        String[] extracted = txt.split(" \\| ");
        String taskType = extracted[0];
        Task task;

        switch (taskType) {
        case "T":
            task = new ToDo(extracted[2]);
            break;
        case "D":
            task = new Deadline(extracted[2], extracted[3]);
            break;
        case "E":
            task = new Event(extracted[2], extracted[3]);
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + taskType);
        }
        if (extracted[1].equals("1")) {
            task.markAsDone();
        }
        return task;
    }

    private void createNewFile() {
        try {
            data.getParentFile().mkdirs();
            data.createNewFile();
        } catch (IOException e) {
            System.err.println(IOEXCEPTION_UNABLE_TO_CREATE_DIRECTORY);
        }
    }
}
