package duke.storage;

import java.io.File;
import java.io.FileWriter;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.data.exception.DukeException;
import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.ToDo;


/**
 * Class that deals with loading tasks from the file and saving tasks in the file.
 *
 * @author Won Ye Ji
 */
public class Storage {
    private final String filePath;
    private final File file;

    /**
     * Constructor for Storage class.
     *
     * @param filePath The filepath of the stored file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        file = new File(filePath);
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            System.err.println("IOException: Unable to create file.");
        }
    }

    /**
     * Loads the tasks from the file.
     *
     * @return An arraylist of the tasks.
     * @throws DukeException if the tasks cannot be loaded.
     */
    public ArrayList<Task> loadTasks() throws DukeException {
        try {
            Scanner sc = new Scanner(file);
            ArrayList<Task> list = new ArrayList<>();
            while (sc.hasNext()) {
                Task task = taskFormatter(sc.nextLine());
                list.add(task);
            }
            return list;
        } catch (IOException e) {
            System.err.println("Error loading file.");
            return new ArrayList<Task>();
        }
    }

    /**
     * Updates the file according to the tasklist.
     *
     * @param tasks String representatio of all tasks.
     * @throws DukeException if unexpected error is encountered.
     */
    public void updateFile(String tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(tasks);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Unexpected error.");
        }
    }

    /**
     * Returns a task that corresponds with the string description of the task.
     *
     * @param task String representation of the task to be returned.
     * @return A task that corresponds with the string description of the task.
     * @throws DukeException if task cannot be identified.
     */
    public Task taskFormatter(String task) throws DukeException {
        String type = task.substring(1, 2);
        String doneStatus = task.substring(4, 5);
        Task t;
        switch (type) {
        case "D":
            String d = task.substring(7, task.length() - 1);
            String[] descD = d.split("by: ");
            String firstD = descD[0].substring(0, descD[0].length() - 2);
            t = new Deadline(firstD, descD[1]);
            break;
        case "E":
            String e = task.substring(7, task.length() - 1);
            String[] descE = e.split("at: ");
            String firstE = descE[0].substring(0, descE[0].length() - 2);
            t = new Event(firstE, descE[1]);
            break;
        case "T":
            String descT = task.substring(7);
            t = new ToDo(descT);
            break;
        default:
            throw new DukeException("Unknown task");
        }
        if (doneStatus.equals("X")) {
            t.markAsDone();
        }
        return t;
    }
}
