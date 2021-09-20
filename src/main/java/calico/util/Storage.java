package calico.util;

// import java packages
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// import duke packages
import calico.CalicoException;
import calico.command.Deadline;
import calico.command.Event;
import calico.command.Todo;
import calico.task.Task;

/**
 * Stores the tasks in an external file.
 * Allows for saving and loading tasks.
 */
public class Storage {
    protected static File f;
    protected String filePath = "data/duke.txt";

    /**
     * Creates a Storage for Duke chatbot.
     *
     * @param filePath Filepath to save tasks and load tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        f = new File(this.filePath);
        f.mkdirs(); // handle the folder-does-not-exist-yet case
    }

    /**
     * Save tasks to storage.
     *
     * @param tasks Tasks to be saved.
     * @throws CalicoException If unable to write to file.
     */
    public void saveTasks(ArrayList<Task> tasks) throws CalicoException {
        try {
            // create a blank new file to write to
            if (f.exists()) {
                f.delete();
            }
            f.createNewFile();
            assert f.exists() : "File f should be created";
            FileWriter fw = new FileWriter(filePath);

            // write to file
            for (Task t : tasks) {
                fw.write(t.getCat() + "," + t.getStatusIcon() + "," + t.getDesc() + "," + t.getDueTime());
                fw.write(System.getProperty("line.separator"));
            }
            fw.close();
        } catch (IOException e) {
            throw new CalicoException(e.getMessage());
        }
    }

    /**
     * Load tasks from storage.
     *
     * @return ArrayList of tasks saved in file.
     * @throws CalicoException If unable to create a file for saving.
     */
    public ArrayList<Task> loadTasks() throws CalicoException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner s = new Scanner(f);

            // scan from file
            while (s.hasNext()) {
                Task t;
                String c = s.nextLine();
                String[] taskParts = c.split(",");
                assert taskParts.getClass().isArray() : "taskComponents should be an array";

                // Check type of Task
                switch (taskParts[0]) {
                case "T":
                    t = new Todo(taskParts[2]);
                    break;
                case "D":
                    t = new Deadline(taskParts[2], taskParts[3]);
                    break;
                case "E":
                    t = new Event(taskParts[2], taskParts[3]);
                    break;
                default:
                    throw new CalicoException("Invalid task type when loading: " + taskParts[0]);
                }

                // Check if task is completed
                if (taskParts[1].equals("X")) {
                    t.markDone();
                }
                tasks.add(t);
            }
        } catch (FileNotFoundException e) {
            try {
                // create new file if not found
                f.createNewFile();
                assert f.exists() : "File f should be created";
            } catch (IOException g) {
                // throws error if file cannot be created
                throw new CalicoException(g.getMessage());
            }
        }

        return tasks;
    }

    /**
     * Converts Storage to string format.
     *
     * @return Storage as a string.
     */
    @Override
    public String toString() {
        return "file path is " + filePath;
    }
}
