package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.AuguryException;
import duke.exceptions.FileIoException;
import duke.tasks.Task;
import duke.tasks.TaskFactory;
import duke.tasks.TaskList;

/**
 * The {@code Storage} class handles reading and writing data from files.
 */
public class Storage {

    private String path;

    /**
     * Initializes a {@code Storage} instance to have a {@code String path}.
     *
     * @param path {@code String} representing the filepath to work on.
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Initializes a {@code TaskList t} using data from the save file.
     * If save file does not exist, {@code Storage} will attempt to create the directory and file.
     *
     * @param tasks {@code TaskList} to write data to.
     * @throws IOException If file cannot be found or written to
     */
    public void initializeTaskList(TaskList tasks) throws IOException {
        String directory = "data";
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdir();
        }
        File f = new File(path);
        f.createNewFile();

        loadTaskListFromStorage(tasks);
    }

    /**
     * Saves the given {@code TaskList tasks} into the save file.
     *
     * @param tasks {@code TaskList} to read data from.
     * @throws FileIoException If file cannot be found or written to
     */
    public void saveTaskListToStorage(TaskList tasks) throws AuguryException {
        try {
            File f = new File(path);
            String s = convertTaskListToString(tasks);
            writeStringToStorage(s);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileIoException("File error occured");
        }
    }

    private String convertTaskListToString(TaskList xs) {
        ArrayList<Task> tasks = xs.getTasks();
        ArrayList<String> tasksString = new ArrayList<>();

        for (Task t : tasks) {
            tasksString.add(t.toString());
        }

        String res = String.join("\n", tasksString);
        return res;
    }

    private void loadTaskListFromStorage(TaskList t) throws IOException {
        // read tasks.txt
        Scanner s = new Scanner(new File(path));
        ArrayList<String> tasks = new ArrayList<>();
        while (s.hasNext()) {
            tasks.add(s.nextLine());
        }

        // create a task for each line
        TaskFactory tf = new TaskFactory();
        for (String task : tasks) {
            Task newTask = tf.createTask(task);
            t.addTask(newTask);
        }
    }

    private void writeStringToStorage(String s) throws IOException {
        FileWriter fw = new FileWriter(path);
        fw.write(s);
        fw.close();
    }

}
