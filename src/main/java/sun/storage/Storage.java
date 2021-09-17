package sun.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import sun.data.exception.SunException;
import sun.data.task.Deadline;
import sun.data.task.Event;
import sun.data.task.Task;
import sun.data.task.ToDo;

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
     * @throws SunException if the tasks cannot be loaded.
     */
    public ArrayList<Task> loadTasks() throws SunException {
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
     * @throws SunException if unexpected error is encountered.
     */
    public void updateFile(String tasks) throws SunException {
        assert file.exists() : "The file does not exist!";
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(tasks);
            fw.close();
        } catch (IOException e) {
            throw new SunException("Unexpected error.");
        }
    }

    /**
     * Returns a task that corresponds with the string description of the task.
     *
     * @param task String representation of the task to be returned.
     * @return A task that corresponds with the string description of the task.
     * @throws SunException if task cannot be identified.
     */
    public Task taskFormatter(String task) throws AssertionError {
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
            throw new AssertionError("Unknown task");
        }
        if (doneStatus.equals("X")) {
            t.markAsDone();
        }
        return t;
    }
}
