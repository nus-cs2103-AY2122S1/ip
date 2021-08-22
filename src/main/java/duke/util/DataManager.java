package duke.util;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.exception.DukeException;
import duke.exception.DukeIOException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class encapsulates the data manager used for persisting tasks.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class DataManager {
    // Storage file used for persisting task
    private final File data;
    // Relative path of file
    private final String filePath;

    public DataManager(String filePath) {
        data = new File(filePath);
        this.filePath = filePath;
    }

    /**
     * Reads data from storage file.
     * Returns an ArrayList<Task> containing all tasks persisted in storage.
     *
     * @return ArrayList of tasks persisted in storage.
     */
    public ArrayList<Task> readData() {
        try {
            Scanner sc = new Scanner(data);
            ArrayList<Task> loadedTasks = new ArrayList<>();
            while (sc.hasNext()) {
                loadedTasks.add(convertTxtToTasks(sc.nextLine()));
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
            fw.write(task.convertToTxt() + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            throw new DukeIOException();
        }
    }

    /**
     * Updates storage file.
     * Runs after an edit is made to the ToDoList.
     *
     * @param tasks Updated state of task list after edit is made.
     * @throws DukeIOException if there is error writing to the storage file.
     */
    public void updateData(ArrayList<Task> tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task t: tasks) {
                fw.write(t.convertToTxt() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeIOException();
        }
    }

    /**
     * Converts the txt format stored in storage back to a Task object.
     *
     * @param txt Text format of Task stored in storage file.
     * @return Task object of text stored in storage.
     */
    private Task convertTxtToTasks(String txt) {
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
            System.err.println("IOException: Unable to create directory/file. Your data will not be saved!");
        }
    }
}
